package eu.piiroinen.citybike2.controller;

import eu.piiroinen.citybike2.repository.BikeStationRepository;
import eu.piiroinen.citybike2.service.JourneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/journey")
public class JourneyController {

    private static final Logger LOG = LoggerFactory.getLogger(JourneyController.class);
    @Autowired
    private JourneyService journeyService;
    @Autowired
    private BikeStationRepository bikeStationRepository;

    @GetMapping(value = "/returning/count/{id}")
    public ResponseEntity<Long> getCountReturningJourneysForStationId(
            @PathVariable(name="id") Long bikeStationId) {
        if (this.bikeStationRepository.existsById(bikeStationId)) {
            Long countReturning = this.journeyService.getCountJourneysByReturnStationId(bikeStationId);
            LOG.info("Returning count for station id {} is {}", bikeStationId, countReturning);
            return ResponseEntity.ok()
                    .body(countReturning);
        } else throw new NoSuchElementException();
    }


    @GetMapping(value = "/departing/count/{id}")
    public ResponseEntity<Long> getCountDepartingJourneysForStationId(
            @PathVariable(name="id") Long bikeStationId) {
        if (this.bikeStationRepository.existsById(bikeStationId)) {
            Long countDeparting = this.journeyService.getCountJourneysByDepartureStationId(bikeStationId);
            LOG.info("Departing count for station id {} is {}", bikeStationId, countDeparting);
            return ResponseEntity.ok()
                    .body(countDeparting);
        } else throw new NoSuchElementException();
    }
}
