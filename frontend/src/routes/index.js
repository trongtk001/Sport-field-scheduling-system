import Home from "../Page/Home/home.js";
import SearchPage from "../Page/Search/index.js";
import LoginForm from "../components/login/index.js";

const publicRoutes = [
  {
    path: "/",
    component: Home,
  },
  {
    path: "/search",
    component: SearchPage,
  },
  {
    path: "/login",
    component: LoginForm,
    layout: null
  }
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
