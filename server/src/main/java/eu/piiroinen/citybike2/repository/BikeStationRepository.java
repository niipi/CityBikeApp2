package eu.piiroinen.citybike2.repository;

import eu.piiroinen.citybike2.model.BikeStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeStationRepository {

    List<BikeStation> findAll();
    List<BikeStation> findByBikeStationId(Long bikeStationId);
}
