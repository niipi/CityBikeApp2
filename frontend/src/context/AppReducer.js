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
        default:
            return state;
    }
};