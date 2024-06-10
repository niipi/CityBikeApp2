package eu.piiroinen.citybike2.repository;

import eu.piiroinen.citybike2.model.BikeStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeStationRepository extends CrudRepository<BikeStation, Long> {

    @Override
    List<BikeStation> findAll();
    @Override
    Optional<BikeStation> findById(Long bikeStationId);
}
