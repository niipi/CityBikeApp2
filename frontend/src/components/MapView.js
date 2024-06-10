import { MapContainer, TileLayer } from "react-leaflet";

function MapView() {

return (
    <MapContainer center={[60.205, 24.89]} zoom={12} scrollWheelZoom={false}>
        <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
    </MapContainer>
);}

export default MapView;