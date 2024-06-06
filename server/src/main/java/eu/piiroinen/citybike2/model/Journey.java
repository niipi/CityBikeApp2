package eu.piiroinen.citybike2.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="journeys")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="journey_id")
    public Long journeyId;

    @Column(name="departure_time")
    public Date departureTime;

    @Column(name="return_time")
    public Date returnTime;

    @Column(name="covered_distance")
    public Long distance;

    @Column(name="duration")
    public Long duration;

    @ManyToOne
    @JoinColumn(name="departure_station_id")
    private BikeStation departureStation;

    @ManyToOne
    @JoinColumn(name="return_station_id")
    private BikeStation returnStation;

    public Long getJourneyId() {
        return journeyId;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public Long getDistance() {
        return distance;
    }

    public Long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Journey: " +
                "journeyId=" + journeyId +
                ", departureTime=" + departureTime +
                ", returnTime=" + returnTime +
                ", distance=" + distance +
                ", duration=" + duration +
                ", departureStation=" + departureStation.bikeStationName +
                ", returnStation=" + returnStation.bikeStationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return Objects.equals(getJourneyId(), journey.getJourneyId()) && Objects.equals(getDepartureTime(), journey.getDepartureTime()) && Objects.equals(getReturnTime(), journey.getReturnTime()) && Objects.equals(getDistance(), journey.getDistance()) && Objects.equals(getDuration(), journey.getDuration()) && Objects.equals(departureStation, journey.departureStation) && Objects.equals(returnStation, journey.returnStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJourneyId(), getDepartureTime(), getReturnTime(), getDistance(), getDuration(), departureStation, returnStation);
    }
}
