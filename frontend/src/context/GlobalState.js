import React, { useReducer, useEffect} from "react";
import AppReducer from "./AppReducer";
import StationContext from "./StationContext";
import axios from 'axios';

const GlobalState = (props) => {
    let initialState = {
        bikestations: [],
    };

    const [state, dispatch] = useReducer(AppReducer, initialState);

    const getAllStations = async () => {
        try {
            let res = await axios.get("http://localhost:8080/bikestation/all");
            let data = res.data;
            console.log("Response data: ", data);
            dispatch({ type: "GET_ALL_STATIONS", payload: data.bikestations });
        } catch (error) {
            console.error(error);
        }
    };
    useEffect(() => {
        getAllStations();
    }, []);

    return (
        <StationContext.Provider value={{
            bikestations: state.bikestations,
            getAllStations
        }}>
            {props.children}
        </StationContext.Provider>
    );
};

export default GlobalState;