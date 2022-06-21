import { createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import fieldListReducer from "../redux-reducer/fieldList";

const compose = composeWithDevTools()


const store = createStore(fieldListReducer, compose);

export default store;