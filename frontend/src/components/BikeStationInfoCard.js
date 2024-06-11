import Card from 'react-bootstrap/Card';
import {useContext} from "react";
import StationContext from "../context/StationContext";

function BikeStationInfoCard() {

    const {selectedBikeStation} = useContext(StationContext);

    console.log("Rendering BikeStationInfoCard with station: ", selectedBikeStation.name);
    return(<Card className="infoCard">
        <h3>{selectedBikeStation.bikeStationName}</h3>
        <p>{selectedBikeStation.getBikeStationAddress}<br/>
            Coordinates: {selectedBikeStation.y}, {selectedBikeStation.x}</p>
        <p>More to come...</p>
    </Card>);
}

export default BikeStationInfoCard;