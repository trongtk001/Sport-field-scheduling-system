import { Cart } from "../components";
import { AuthencationLayout, UserProfileLayout } from "../layout";
import { Home, SearchPage, LoginPage, SignUpPage, UserProfile, History, Support } from "../Page";
import FieldInfoPage from "../Page/FieldInfo";


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
    component: LoginPage,
    layout: AuthencationLayout
  },
  {
    path: "/signup",
    component: SignUpPage,
    layout: AuthencationLayout
  },
  {
    path: "/fieldinfo",
    component: FieldInfoPage,
  },
  {
    path: "/cart",
    component: Cart,
    layout: null
  },
  {
    path: "/UserProfileLayout",
    component: UserProfileLayout,
    layout: null
  },

];

const privateRoutes = [
  {
    path: "/user",
    component: UserProfile,
    layout: UserProfileLayout,
    roles: ['ADMIN', "USER"]
  },
  {
    path: "/user/history",
    component: History,
    layout: UserProfileLayout,
    roles: ['ADMIN', "USER"]
  },
  {
    path: "/user/support",
    component: Support,
    layout: UserProfileLayout,
    roles: ['ADMIN', "USER"]
  }
];

export { publicRoutes, privateRoutes };
