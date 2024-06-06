package eu.piiroinen.citybike2.service;

import eu.piiroinen.citybike2.model.Journey;
import eu.piiroinen.citybike2.repository.BikeStationRepository;
import eu.piiroinen.citybike2.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class JourneyService implements JourneyServiceInterface{

    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private BikeStationRepository bikeStationRepository;

    @Override
    public List<Journey> getJourneysByDepartureStationId(Long departureStationId) {
        if (bikeStationRepository.findByBikeStationId(departureStationId).isPresent()) {
            return this.journeyRepository.findByDepartureStationId(departureStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public List<Journey> getJourneysByReturnStationId(Long returnStationId) {
        if (bikeStationRepository.findByBikeStationId(returnStationId).isPresent()) {
            return this.journeyRepository.findByReturnStationId(returnStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public List<Journey> getJourneysByDepartureStationIdAndReturnStationId(Long departureStationId, Long returnStationId) {
        if (bikeStationRepository.findByBikeStationId(departureStationId).isPresent() && bikeStationRepository.findByBikeStationId(returnStationId).isPresent()) {
            return this.journeyRepository.findByDepartureStationIdAndReturnStationId(departureStationId, returnStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public int getCountJourneysByDepartureStationId(Long departureStationId) {
        if (bikeStationRepository.findByBikeStationId(departureStationId).isPresent()) {
            return this.journeyRepository.countJourneysByDepartureStationId(departureStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public int getCountJourneysByReturnStationId(Long returnStationId) {
        if (bikeStationRepository.findByBikeStationId(returnStationId).isPresent()) {
            return this.journeyRepository.countJourneysByReturnStationId(returnStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public int getCountJourneysByDepartureStationIdAndDepartureTime(Long departureStationId, Date departureTime) {
        if (bikeStationRepository.findByBikeStationId(departureStationId).isPresent()) {
            return this.journeyRepository.countJourneysByDepartureStationIdAndDepartureTime(departureStationId, departureTime);
        } else throw new NoSuchElementException();
    }

    @Override
    public int getCountJourneysByReturnStationIdAndReturnTime(Long returnStationId, Date returnTime) {
        if (bikeStationRepository.findByBikeStationId(returnStationId).isPresent()) {
            return this.journeyRepository.countJourneysByReturnStationIdAndReturnTime(returnStationId, returnTime);
        } else throw new NoSuchElementException();
    }
}
