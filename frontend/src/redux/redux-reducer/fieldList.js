const initState = {
  filter: {
    type: 'all',
    place: 'all'
  },
  searchValue: '',
};

function fieldListReducer(state = initState, action) {
  switch (action.type) {
    
    case "addFilter":
      return {
        ...state,
        filter: action.payload
      }

    case "addSearchValue":
      return {
        ...state,
        searchValue: action.payload
      }


    default:
      return state
  }
}

export default fieldListReducer;
