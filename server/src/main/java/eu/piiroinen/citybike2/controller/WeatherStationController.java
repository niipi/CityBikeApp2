package eu.piiroinen.citybike2.controller;

import eu.piiroinen.citybike2.model.WeatherStation;
import eu.piiroinen.citybike2.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/weatherstation")
public class WeatherStationController {

    @Autowired
    private WeatherStationService weatherStationService;

    @GetMapping("/all")
    public ResponseEntity<List<WeatherStation>> getAllWeatherStations() {
        List<WeatherStation> stations = this.weatherStationService.getAllWeatherStations();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(stations);
    }
}
