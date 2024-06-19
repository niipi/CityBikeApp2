import Card from 'react-bootstrap/Card';
import {useContext} from "react";
import StationContext from "../context/StationContext";

function BikeStationInfoCard() {

    const {selectedBikeStation, returningJourneys, departingJourneys} = useContext(StationContext);

    return(<Card className="infoCard">
        <h3>{selectedBikeStation.bikeStationName}</h3>
        <p>{selectedBikeStation.getBikeStationAddress}<br/>
            Coordinates: {selectedBikeStation.y}, {selectedBikeStation.x}<br/>
            Returning journeys: {returningJourneys}<br/>
            Departing journeys: {departingJourneys}</p>
    </Card>);
}

export default BikeStationInfoCard;