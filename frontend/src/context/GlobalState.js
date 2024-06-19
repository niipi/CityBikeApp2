import React, {useReducer, useEffect, useState, useMemo} from "react";
import AppReducer from "./AppReducer";
import StationContext from "./StationContext";
import axios from 'axios';

const GlobalState = (props) => {
    let initialState = {
        bikestations: [],
        selectedBikeStation: null,
        returningJourneys: null,
        departingJourneys: null,
    };

    const [state, dispatch] = useReducer(AppReducer, initialState);

    const getAllStations = async () => {
        try {
            let res = await axios.get("http://localhost:8080/bikestation/all");
            let data = res.data;
            console.log("Response data for all stations: ", data);
            dispatch({ type: "GET_ALL_STATIONS", payload: data.bikestations });
        } catch (error) {
            console.error("Error retrieving all stations: ", error);
        }
    };
    useEffect(() => {
        getAllStations();
    }, []);

    const getSelectedStation = async (id) => {
        try {
            let res = await axios.get(`http://localhost:8080/bikestation/${id}`);
            let data = res.data;
            console.log("Response data for selected station: ", data.selectedBikeStation);
            dispatch({ type: "GET_BIKESTATION", payload: data.selectedBikeStation });
            return data.selectedBikeStation;
        } catch (error) {
            console.error("Error selecting station: ", error);
        }
    };

    const getReturningJourneys = async (id) => {
        try {
            let res = await axios.get(`http://localhost:8080/journey/returning/count/${id}`);
            let data = res.data;
            console.log("Count for returning journeys was ", data);
            dispatch({ type: "GET_RETURNING_JOURNEYS", payload: data })
            return data;
        } catch (error) {
            console.error("Error counting return journeys: ", error)
        }
    }

    const getDepartingJourneys = async (id) => {
        try {
            let res = await axios.get(`http://localhost:8080/journey/departing/count/${id}`);
            let data = res.data;
            console.log("Count for departing journeys was ", data);
            dispatch({ type: "GET_DEPARTING_JOURNEYS", payload: data })
            return data;
        } catch (error) {
            console.error("Error counting departing journeys: ", error)
        }
    }

    return (
        <StationContext.Provider value={{
            bikestations: state.bikestations,
            getAllStations,
            getSelectedStation,
            selectedBikeStation: state.selectedBikeStation,
            getReturningJourneys,
            returningJourneys: state.returningJourneys,
            getDepartingJourneys,
            departingJourneys: state.departingJourneys,
        }}>
            {props.children}
        </StationContext.Provider>
    );
};

export default GlobalState;