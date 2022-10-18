// const initState = {
//   filter: {
//     searchValue: '',
//     type: 'all',
//     place: 'all'
//   },
// };

// function fieldListReducer(state = initState, action) {
//   switch (action.type) {

//     case "addFilter":
//       return {
//         ...state,
//         filter: action.payload
//       }

//     case "addSearchValue":
//       return {
//         ...state,
//         searchValue: action.payload
//       }


//     default:
//       return state
//   }
// }

// export default fieldListReducer;

import { createSlice } from "@reduxjs/toolkit"

export const filterSlice = createSlice({
  name: "filter",
  initialState: {
    searchValue: '',
    type: 'all',
    place: 'all'
  },
  reducers: {
    addSearchValue: (state, action) => {
      state.searchValue = action.payload;
    }, 
    addFilter: (state, action) => {
      state.type = action.payload.type;
      state.place = action.payload.place;
    }
  }
})