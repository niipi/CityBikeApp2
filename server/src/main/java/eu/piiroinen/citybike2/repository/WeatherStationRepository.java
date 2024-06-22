package eu.piiroinen.citybike2.repository;

import eu.piiroinen.citybike2.model.WeatherStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherStationRepository extends CrudRepository<WeatherStation, Long> {
    @Override
    List<WeatherStation> findAll();
}
