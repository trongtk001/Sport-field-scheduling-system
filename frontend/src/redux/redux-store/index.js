import { configureStore } from "@reduxjs/toolkit"
import { cartSlice } from "../redux-reducer/cart";
import { filterSlice } from "../redux-reducer/filter";



const store = configureStore({
    reducer: {
        filter: filterSlice.reducer,
        cart: cartSlice.reducer
    }
});

export default store;