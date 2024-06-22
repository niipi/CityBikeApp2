package eu.piiroinen.citybike2.service;

import eu.piiroinen.citybike2.model.WeatherStation;
import eu.piiroinen.citybike2.repository.WeatherStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherStationService implements WeatherStationServiceInterface{

    private WeatherStationRepository weatherStationRepository;

    @Autowired
    public WeatherStationService(WeatherStationRepository weatherStationRepository) {
        this.weatherStationRepository = weatherStationRepository;
    }

    @Override
    public List<WeatherStation> getAllWeatherStations() {
        return this.weatherStationRepository.findAll();
    }
}
