import classNames from "classnames/bind";

import styles from "./DefaultLayout.module.scss";
import { Header, Footer } from "../../components";
import { propTypes } from "react-bootstrap/esm/Image";

const cx = classNames.bind(styles);

function DefaultLayout({ props, children }) {
  return (
    <>
      <Header props/>
      <div className={cx("content")}>{children}</div>
      <Footer />
    </>
  );
}

export default DefaultLayout;
