import { createSlice } from "@reduxjs/toolkit"

const initialState = {
    items: []
}


export const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {
        addItem: (state, action) => {
            if (!state.items.find((item => item.field.id === action.payload.field.id))) {
                state.items.push(action.payload)
            }
        },
        removeItem: (state, action) => {
            const current = state.items.filter((item) => !(item.field.id === action.payload))
            state.items = current;
        },
        clearItem: (state, action) => {
            state.items = [];
        }
        ,
        chageTime: (state, action) => {
            const index = state.items.findIndex(item => item.field.id === action.payload.id)
            state.items[index].time = action.payload.time
        },
    }
})