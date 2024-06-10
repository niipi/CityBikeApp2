export default (state, action) => {
    const { payload, type } = action;
    switch (type) {
        case "GET_ALL_STATIONS":
            return {
                ...state,
                bikestations: payload,
            };
        default:
            return state;
    }
};