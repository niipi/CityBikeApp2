package eu.piiroinen.citybike2.service;

import eu.piiroinen.citybike2.model.BikeStation;

import java.util.List;
import java.util.Optional;

public interface BikeStationServiceInterface {

    List<BikeStation> getAllBikeStations();

    BikeStation getBikeStationById(Long bikeStationId);

}
