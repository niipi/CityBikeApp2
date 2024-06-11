import React, {useReducer, useEffect, useState, useMemo} from "react";
import AppReducer from "./AppReducer";
import StationContext from "./StationContext";
import axios from 'axios';

const GlobalState = (props) => {
    let initialState = {
        bikestations: [],
        selectedBikeStation: null,
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
            console.error("Error selecting station:", error);
        }
    };

    return (
        <StationContext.Provider value={{
            bikestations: state.bikestations,
            getAllStations,
            getSelectedStation,
            selectedBikeStation: state.selectedBikeStation,
        }}>
            {props.children}
        </StationContext.Provider>
    );
};

export default GlobalState;