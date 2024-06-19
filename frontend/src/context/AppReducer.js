export default (state, action) => {
    const { payload, type } = action;
    switch (type) {
        case "GET_ALL_STATIONS":
            return {
                ...state,
                bikestations: payload,
            };
        case "GET_BIKESTATION":
            return {
                ...state,
                selectedBikeStation: payload,
            };
        case "GET_RETURNING_JOURNEYS":
            return {
                ...state,
                returningJourneys: payload,
            }
        case "GET_DEPARTING_JOURNEYS":
            return {
                ...state,
                departingJourneys: payload,
            }
        default:
            return state;
    }
};