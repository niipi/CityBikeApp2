package eu.piiroinen.citybike2.service;

import eu.piiroinen.citybike2.model.Journey;

import java.util.Date;
import java.util.List;

public interface JourneyServiceInterface {
    List<Journey> getJourneysByDepartureStationId(Long departureStationId);
    List<Journey> getJourneysByReturnStationId(Long returnStationId);
    List<Journey> getJourneysByDepartureStationIdAndReturnStationId(Long departureStationId, Long returnStationId);
    int getCountJourneysByDepartureStationId(Long departureStationId);
    int getCountJourneysByReturnStationId(Long returnStationId);
    int getCountJourneysByDepartureStationIdAndDepartureTime(Long departureStationId, Date departureTime);
    int getCountJourneysByReturnStationIdAndReturnTime(Long returnStationId, Date returnTime);
}
