CREATE TABLE temp(
                     ID SERIAL PRIMARY KEY,
                     station_id BIGINT,
                     Nimi VARCHAR(255) NOT NULL,
                     Namn VARCHAR(255) NOT NULL,
                     Name VARCHAR(255) NOT NULL,
                     Osoite VARCHAR(255) NOT NULL,
                     Adress VARCHAR(255) NOT NULL,
                     Kaupunki VARCHAR(50),
                     Stad VARCHAR(50),
                     Operaattor VARCHAR(50),
                     Kapasiteet INT,
                     x NUMERIC,
                     y NUMERIC
);

COPY temp(ID,station_id,Nimi,Namn,Name,Osoite,Adress,Kaupunki,Stad,Operaattor,Kapasiteet,x,y)
    FROM '/docker-entrypoint-initdb.d/csv/stations.csv'
    DELIMITER ','
    CSV HEADER;

CREATE TABLE stations(
                         station_id BIGINT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         address VARCHAR(255) NOT NULL,
                         city VARCHAR(50),
                         capacity INT,
                         x NUMERIC NOT NULL,
                         y NUMERIC NOT NULL
);

INSERT INTO stations(station_id, name, address, city, capacity, x, y)
SELECT station_id, Nimi, Osoite, Kaupunki, Kapasiteet, x, y FROM temp;

DROP TABLE temp;

CREATE TABLE temp_journey(
                             departure_time TIMESTAMP,
                             return_time TIMESTAMP,
                             departure_station_id BIGINT,
                             departure_station_name VARCHAR(255) NOT NULL,
                             return_station_id BIGINT,
                             return_station_name VARCHAR(255) NOT NULL,
                             covered_distance NUMERIC,
                             duration NUMERIC
);

CREATE FUNCTION populate_temp_journey_table() RETURNS void AS $$
DECLARE filename text;
BEGIN
FOR filename IN SELECT pg_ls_dir('/docker-entrypoint-initdb.d/csv/od-trips-2023/') LOOP
                    IF (filename ~ '.csv$') THEN
        EXECUTE format('COPY temp_journey(Departure_time,Return_time,Departure_station_id,Departure_station_name,Return_station_id,Return_station_name,Covered_distance,Duration)
            FROM %L
            WITH (FORMAT CSV, HEADER, DELIMITER '','')', '/docker-entrypoint-initdb.d/csv/od-trips-2023/' || filename);
END IF;
END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT populate_temp_journey_table();

CREATE TABLE journeys(
                         journey_id BIGINT GENERATED ALWAYS AS IDENTITY,
                         departure_time TIMESTAMP,
                         return_time TIMESTAMP,
                         departure_station_id BIGINT,
                         return_station_id BIGINT,
                         covered_distance NUMERIC,
                         duration NUMERIC,
                         PRIMARY KEY(journey_id),
                         CONSTRAINT fk_depart
                             FOREIGN KEY(departure_station_id)
                                 REFERENCES stations(station_id),
                         CONSTRAINT fk_return
                             FOREIGN KEY(return_station_id)
                                 REFERENCES stations(station_id)
);

INSERT INTO journeys(departure_time, return_time, departure_station_id, return_station_id, covered_distance, duration)
SELECT departure_time, return_time, departure_station_id, return_station_id, covered_distance, duration FROM temp_journey
WHERE covered_distance > 10 AND duration > 0 AND departure_station_id IN (SELECT station_id FROM stations) AND return_station_id IN (SELECT station_id FROM stations);

DROP TABLE temp_journey;

CREATE TABLE temp_weather(
                             Havaintoasema TEXT,
                             Vuosi TEXT,
                             Kuukausi TEXT,
                             Päivä TEXT,
                             Aika TEXT,
                             Lämpötilan_keskiarvo TEXT,
                             Ylin_lämpötila TEXT,
                             Alin_lämpötila TEXT,
                             Tunnin_sademäärä TEXT,
                             Keskituulen_nopeus TEXT,
                             Tuulen_puuska TEXT
);

CREATE FUNCTION populate_temp_weather_table() RETURNS void AS $$
DECLARE filename text;
BEGIN
FOR filename IN SELECT pg_ls_dir('/docker-entrypoint-initdb.d/csv/weather/') LOOP
                    IF (filename ~ '.csv$') THEN
        EXECUTE format('COPY temp_weather(Havaintoasema,Vuosi,Kuukausi,Päivä,Aika,Lämpötilan_keskiarvo,Ylin_lämpötila,Alin_lämpötila,Tunnin_sademäärä,Keskituulen_nopeus,Tuulen_puuska)
            FROM %L
            WITH (FORMAT CSV, HEADER, DELIMITER '','')', '/docker-entrypoint-initdb.d/csv/weather/' || filename);
END IF;
END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT populate_temp_weather_table();

CREATE TABLE weather_station(
                                weather_station_id BIGINT GENERATED ALWAYS AS IDENTITY,
                                name VARCHAR(100),
                                x NUMERIC,
                                y NUMERIC,
                                PRIMARY KEY(weather_station_id)
);

INSERT INTO weather_station(name, x, y) VALUES ('Espoo Nuuksio', 24.57, 60.29);
INSERT INTO weather_station(name, x, y) VALUES ('Espoo Tapiola', 24.79, 60.18);
INSERT INTO weather_station(name, x, y) VALUES ('Helsinki Malmi lentokenttä', 25.05, 60.25);
INSERT INTO weather_station(name, x, y) VALUES ('Helsinki Kaisaniemi', 24.94, 60.18);

CREATE TABLE weather(
                        weather_station_id BIGINT,
                        timestamp TIMESTAMP,
                        mean_temp NUMERIC,
                        max_temp NUMERIC,
                        min_temp NUMERIC,
                        rain_per_hour NUMERIC,
                        wind_speed NUMERIC,
                        wind_gust NUMERIC,
                        CONSTRAINT fk_weatherstation
                            FOREIGN KEY(weather_station_id)
                                REFERENCES weather_station(weather_station_id)
);

CREATE FUNCTION populate_weather_table_from_temp() RETURNS void AS $$
BEGIN
INSERT INTO weather(weather_station_id, timestamp, mean_temp, max_temp, min_temp, rain_per_hour, wind_speed, wind_gust)
SELECT
    station.weather_station_id,
    TO_TIMESTAMP(CONCAT(Vuosi, '-', Kuukausi, '-', Päivä, ' ', Aika), 'YYYY-MM-DD HH24:MI'),
    CASE
        WHEN Lämpötilan_keskiarvo ~ '^[0-9]+(\.[0-9]+)?$' THEN Lämpötilan_keskiarvo::float
            ELSE NULL
END,
        CASE
           WHEN Ylin_lämpötila ~ '^[0-9]+(\.[0-9]+)?$' THEN Ylin_lämpötila::float
           ELSE NULL
END,
        CASE
           WHEN Alin_lämpötila ~ '^[0-9]+(\.[0-9]+)?$' THEN Alin_lämpötila::float
           ELSE NULL
END,
        CASE
            WHEN Tunnin_sademäärä ~ '^[0-9]+(\.[0-9]+)?$' THEN Tunnin_sademäärä::float
            ELSE 0::float
END,
        CASE
           WHEN Keskituulen_nopeus ~'^[0-9]+(\.[0-9]+)?$' THEN Keskituulen_nopeus::float
           ELSE 0::float
END,
        CASE
           WHEN Tuulen_puuska ~ '^[0-9]+(\.[0-9]+)?$' THEN Tuulen_puuska::float
           ELSE 0::float
END
FROM temp_weather JOIN weather_station AS station ON temp_weather.Havaintoasema=station.name;
END;
$$ LANGUAGE plpgsql;

SELECT populate_weather_table_from_temp();

DROP TABLE temp_weather;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE stations TO springboot;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE journeys TO springboot;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE weather TO springboot;
GRANT SELECT ON TABLE stations TO rscript;
GRANT SELECT ON TABLE journeys TO rscript;
GRANT SELECT ON TABLE weather TO rscript;