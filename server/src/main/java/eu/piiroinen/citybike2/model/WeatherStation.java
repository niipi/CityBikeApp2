package eu.piiroinen.citybike2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="weather_station")
public class WeatherStation {

    @Id
    @Column(name="weather_station_id")
    public Long id;

    @Column(name="name", nullable = false)
    public String name;

    @Column(name="x", nullable = false)
    public Float x;

    @Column(name="y", nullable = false)
    public Float y;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

}
