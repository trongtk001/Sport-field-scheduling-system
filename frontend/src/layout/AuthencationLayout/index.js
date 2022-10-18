import { Link } from "react-router-dom";

import CloseIcon from '@mui/icons-material/Close';

import classNames from "classnames/bind";
import styles from "./AuthencationLayout.module.scss";
const cx = classNames.bind(styles);

function AuthencationLayout({ children }) {
    return (
        <div className={cx("wrapper")}>
            <Link to="/" className={cx("close-icon")}>
                <CloseIcon />
            </Link>
            <div className={cx("content")}>{children}</div>
        </div>
    );
}

export default AuthencationLayout;