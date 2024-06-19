import {MapContainer, Marker, TileLayer, Popup} from "react-leaflet";
import {useContext, useState, useEffect, useRef} from "react";
import StationContext from "../context/StationContext";
import BikeStationInfoCard from "./BikeStationInfoCard";
import {Icon} from "leaflet/src/layer";
import locationPinSvg from "../assets/locationpin.svg"
import {Button} from "react-bootstrap";

function MapView() {
    const { bikestations, getSelectedStation, selectedBikeStation, getReturningJourneys, getDepartingJourneys } = useContext(StationContext);
    const locationPin = new Icon({iconUrl: locationPinSvg})
    const [visibleInfoCard, setVisibleInfoCard] = useState(false);
    const allInfoIsLoaded = useRef(false);
    async function handleStationClick(id) {
        let selected = await getSelectedStation(id);
        await getReturningJourneys(selected.id);
        await getDepartingJourneys(selected.id);
        allInfoIsLoaded.current = true;
    }

    useEffect(() => {
        if (selectedBikeStation && allInfoIsLoaded.current) {
        setVisibleInfoCard(true);
        allInfoIsLoaded.current = false;
    }
        else setVisibleInfoCard(false);
    }, [selectedBikeStation]);

    return (
        <div className="mapContainerDiv" onClick={() => setVisibleInfoCard(false)}>
            <MapContainer center={[60.1718473, 24.945022]} zoom={14} scrollWheelZoom={true}>
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {bikestations.map((station) => (
                        <Marker key={station.id} position={[station.y, station.x]} icon={locationPin}>
                            <Popup>
                                <h4>{station.bikeStationName}</h4>
                                <Button onClick={() => handleStationClick(station.id)}>View info</Button>
                            </Popup>
                        </Marker>
                    )
                )}
            </MapContainer>
            {visibleInfoCard ? (<BikeStationInfoCard/>) : null }
        </div>
    );
}

export default MapView;