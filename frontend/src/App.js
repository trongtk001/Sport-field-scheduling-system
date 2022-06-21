import { Routes, Route } from "react-router-dom";
import { Fragment } from "react";
import { Provider } from "react-redux";
import "bootstrap/dist/css/bootstrap.min.css";

import "./App.scss";
import { publicRoutes } from "./routes";
import { DefaultLayout } from "./layout";
import store from "./redux/redux-store/fieldList";

function App() {
  return (
    <Provider store={store}>
      <Routes>
        {publicRoutes.map((route, index) => {
          let Layout = DefaultLayout;

          if (route.layout) {
            Layout = route.layout;
          } else if (route.layout === null) {
            Layout = Fragment;
          }
          const Page = route.component;
          return (
            <Route key={index} path={route.path} element={
              <Layout>
                <Page />
              </Layout>
            }
            />
          );
        })}
      </Routes>
    </Provider>
  );
}

export default App;
