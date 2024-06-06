package eu.piiroinen.citybike2.repository;

import eu.piiroinen.citybike2.model.BikeStation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeStationRepository {

    List<BikeStation> findAll();
    Optional<BikeStation> findByBikeStationId(Long bikeStationId);
}
