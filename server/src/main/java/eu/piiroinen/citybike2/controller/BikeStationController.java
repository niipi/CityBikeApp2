package eu.piiroinen.citybike2.controller;

import eu.piiroinen.citybike2.model.BikeStation;
import eu.piiroinen.citybike2.service.BikeStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bikestation")
public class BikeStationController {

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
            return ResponseEntity.internalServerError()
                    .body(new HashMap<>());
        }
    }
}
