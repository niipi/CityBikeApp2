package eu.piiroinen.citybike2.service;

import eu.piiroinen.citybike2.model.Journey;

import java.util.Date;
import java.util.List;

public interface JourneyServiceInterface {
    List<Journey> getJourneysByDepartureStationId(Long departureStationId);
    List<Journey> getJourneysByReturnStationId(Long returnStationId);
    List<Journey> getJourneysByDepartureStationIdAndReturnStationId(Long departureStationId, Long returnStationId);
    Long getCountJourneysByDepartureStationId(Long departureStationId);
    Long getCountJourneysByReturnStationId(Long returnStationId);
    Long getCountJourneysByDepartureStationIdAndDepartureTime(Long departureStationId, Date departureTime);
    Long getCountJourneysByReturnStationIdAndReturnTime(Long returnStationId, Date returnTime);
}
