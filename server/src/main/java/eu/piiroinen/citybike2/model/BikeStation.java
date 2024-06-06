package eu.piiroinen.citybike2.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="stations")
public class BikeStation {

    @Id
    @Column(name="station_id")
    public Long bikeStationId;

    @Column(name="name", nullable = false)
    public String bikeStationName;

    @Column(name="address", nullable = false)
    public String getBikeStationAddress;

    @Column(name="city")
    public String bikeStationCity;

    @Column(name="capacity")
    public Integer capacity;

    @Column(name="x", nullable = false)
    public Long x;

    @Column(name="y", nullable = false)
    public Long y;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departureStation", cascade = CascadeType.ALL)
    List<Journey> departingJourneys;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "returnStation", cascade = CascadeType.ALL)
    List<Journey> returningJourneys;

    public Long getBikeStationId() {
        return bikeStationId;
    }

    public String getBikeStationName() {
        return bikeStationName;
    }

    public String getGetBikeStationAddress() {
        return getBikeStationAddress;
    }

    public String getBikeStationCity() {
        return bikeStationCity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "BikeStation: " +
                "bikeStationId=" + bikeStationId +
                ", bikeStationName='" + bikeStationName + '\'' +
                ", capacity=" + capacity +
                ", x=" + x +
                ", y=" + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BikeStation that = (BikeStation) o;
        return Objects.equals(bikeStationId, that.bikeStationId) && Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bikeStationId, x, y);
    }
}
