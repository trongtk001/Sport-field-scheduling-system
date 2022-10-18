import { Routes, Route } from "react-router-dom";
import { Fragment } from "react";

import { Provider } from "react-redux";
import AuthProvider from "./auth/AuthProvider";

import { privateRoutes, publicRoutes } from "./routes";

import { DefaultLayout } from "./layout";
import store from "./redux/redux-store";


import "./App.scss";
import RequireAuth from "./auth/RequireAuth";


function App() {
  return (
    <Provider store={store}>
      <AuthProvider>
        <Routes>
          {publicRoutes.map((route, index) => {
            let Layout = DefaultLayout;

            if (route.layout) {
              Layout = route.layout;
            } else if (route.layout === null) {
              Layout = Fragment;
            }
            const Element = <Layout><route.component/></Layout>
            return (
              <Route key={index} path={route.path} element={Element} />
            );
          })}

          {privateRoutes.map((route, index) => {
            let Layout = DefaultLayout;

            if (route.layout) {
              Layout = route.layout;
            } else if (route.layout === null) {
              Layout = Fragment;
            }
            const Element = <RequireAuth roles={route.roles}><Layout><route.component /></Layout></RequireAuth>
            return (
              <Route key={index} path={route.path} element={Element} />
            );
          })}
        </Routes>
      </AuthProvider>
    </Provider>
  );
}

export default App;
