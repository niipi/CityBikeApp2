package eu.piiroinen.citybike2.service;

import eu.piiroinen.citybike2.model.BikeStation;
import eu.piiroinen.citybike2.repository.BikeStationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class BikeStationService implements BikeStationServiceInterface{

    private static final Logger LOG = LoggerFactory.getLogger(BikeStationService.class);
    private BikeStationRepository bikeStationRepository;

    @Autowired
    public BikeStationService(BikeStationRepository bikeStationRepository) {
        this.bikeStationRepository = bikeStationRepository;
    }


    @Override
    public List<BikeStation> getAllBikeStations() {
        return this.bikeStationRepository.findAll();
    }

    @Override
    public BikeStation getBikeStationById(Long bikeStationId) throws NoSuchElementException {
        if (this.bikeStationRepository.findById(bikeStationId).isPresent()) {
            return this.bikeStationRepository.findById(bikeStationId).get();
        }
        else throw new NoSuchElementException();
    }
}
