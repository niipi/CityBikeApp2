package eu.piiroinen.citybike2.repository;

import eu.piiroinen.citybike2.model.Journey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, Long> {

    List<Journey> findByDepartureStationId(Long departureStationId);
    List<Journey> findByReturnStationId(Long returnStationId);
    List<Journey> findByDepartureStationIdAndReturnStationId(Long departureStationId, Long returnStationId);
    int countJourneysByDepartureStationId(Long departureStationId);
    int countJourneysByReturnStationId(Long returnStationId);
    int countJourneysByDepartureStationIdAndDepartureTime(Long departureStationId, Date departureTime);
    int countJourneysByReturnStationIdAndReturnTime(Long returnStationId, Date returnTime);
}
