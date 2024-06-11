package eu.piiroinen.citybike2.controller;

import eu.piiroinen.citybike2.model.BikeStation;
import eu.piiroinen.citybike2.service.BikeStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bikestation")
public class BikeStationController {

    private static final Logger LOG = LoggerFactory.getLogger(BikeStationService.class);

    @Autowired
    private BikeStationService bikeStationService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, List<BikeStation>>> getAllBikeStations() {
        Map<String, List<BikeStation>> response = new HashMap<>();
        try {
            response.put("bikestations", this.bikeStationService.getAllBikeStations());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            LOG.error("An exception occurred retrieving all bike stations: ", e);
            return ResponseEntity.internalServerError()
                    .body(new HashMap<>());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, BikeStation>> getBikeStationById(
            @PathVariable(name="id") Long bikeStationId) {
        Map<String, BikeStation> response = new HashMap<>();
        try {
            response.put("selectedBikeStation", this.bikeStationService.getBikeStationById(bikeStationId));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            LOG.error("An exception occurred retrieving bike station with id {}: ", bikeStationId, e);
            return ResponseEntity.internalServerError()
                    .body(new HashMap<>());
        }
    }
}
