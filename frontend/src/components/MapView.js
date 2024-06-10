import {MapContainer, Marker, TileLayer, Popup} from "react-leaflet";
import {useContext} from "react";
import StationContext from "../context/StationContext";
import {Icon} from "leaflet/src/layer";
import locationPinSvg from "../assets/locationpin.svg"

function MapView() {
    const { bikestations } = useContext(StationContext);

    const locationPin = new Icon({iconUrl: locationPinSvg})

    return (
        <MapContainer center={[60.1718473, 24.945022]} zoom={14} scrollWheelZoom={true}>
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {bikestations.map((station) => (
                <Marker key={station.id} position={[station.y, station.x]} icon={locationPin}>
                    <Popup>
                        <h4>{station.bikeStationName}</h4>
                    </Popup>
                </Marker>
                )
            )}
        </MapContainer>
    );}

export default MapView;