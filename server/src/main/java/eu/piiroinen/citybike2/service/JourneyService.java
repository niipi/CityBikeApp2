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

    private JourneyRepository journeyRepository;
    private BikeStationRepository bikeStationRepository;

    @Autowired
    public JourneyService(JourneyRepository journeyRepository, BikeStationRepository bikeStationRepository) {
        this.journeyRepository = journeyRepository;
        this.bikeStationRepository = bikeStationRepository;
    }

    @Override
    public List<Journey> getJourneysByDepartureStationId(Long departureStationId) {
        if (bikeStationRepository.findById(departureStationId).isPresent()) {
            return this.journeyRepository.findByDepartureStationId(departureStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public List<Journey> getJourneysByReturnStationId(Long returnStationId) {
        if (bikeStationRepository.findById(returnStationId).isPresent()) {
            return this.journeyRepository.findByReturnStationId(returnStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public List<Journey> getJourneysByDepartureStationIdAndReturnStationId(Long departureStationId, Long returnStationId) {
        if (bikeStationRepository.findById(departureStationId).isPresent() && bikeStationRepository.findById(returnStationId).isPresent()) {
            return this.journeyRepository.findByDepartureStationIdAndReturnStationId(departureStationId, returnStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public Long getCountJourneysByDepartureStationId(Long departureStationId) {
        if (bikeStationRepository.findById(departureStationId).isPresent()) {
            return this.journeyRepository.countJourneysByDepartureStationId(departureStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public Long getCountJourneysByReturnStationId(Long returnStationId) {
        if (bikeStationRepository.findById(returnStationId).isPresent()) {
            return this.journeyRepository.countJourneysByReturnStationId(returnStationId);
        } else throw new NoSuchElementException();
    }

    @Override
    public Long getCountJourneysByDepartureStationIdAndDepartureTime(Long departureStationId, Date departureTime) {
        if (bikeStationRepository.findById(departureStationId).isPresent()) {
            return this.journeyRepository.countJourneysByDepartureStationIdAndDepartureTime(departureStationId, departureTime);
        } else throw new NoSuchElementException();
    }

    @Override
    public Long getCountJourneysByReturnStationIdAndReturnTime(Long returnStationId, Date returnTime) {
        if (bikeStationRepository.findById(returnStationId).isPresent()) {
            return this.journeyRepository.countJourneysByReturnStationIdAndReturnTime(returnStationId, returnTime);
        } else throw new NoSuchElementException();
    }
}
