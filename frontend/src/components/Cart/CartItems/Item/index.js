import { useState } from "react";

import { Grow } from "@mui/material";
import CloseIcon from '@mui/icons-material/Close';

import { useDispatch } from "react-redux";
import { cartSlice } from "../../../../redux/redux-reducer/cart";

import classNames from "classnames/bind";
import styles from "./Item.module.scss";

const cx = classNames.bind(styles)


function Item({ data }) {

    const dispatch = useDispatch();
    const [checked, setChecked] = useState(true);

    const [hours, setHours] = useState(data.time);

    const handleHoursControl = (e, id) => {
        var current;
        switch (e.target.value) {
            case "increase":
                if (hours < 10) {
                    current = hours + 1;
                    dispatch(cartSlice.actions.chageTime({ id: id, time: current }))
                    setHours(current)
                }
                break;
            case "decrease":
                if (hours > 1) {
                    current = hours - 1;
                    dispatch(cartSlice.actions.chageTime({ id: id, time: current }))
                    setHours(current)
                }
                break;
            default:
                console.log("error");
        }
    }

    const handleDelete = (id) => {
        setChecked(prev => !prev)
        setTimeout(() => {dispatch(cartSlice.actions.removeItem(id))}, 200);
    }

    return (
        <Grow in={checked}>
            <div className={cx("item")}>
                <img src={data.field.thumbnailUrl} alt="" draggable="false" />
                <div className={cx("info")}>
                    <h4>{data.field.fieldName}</h4>
                    <p>
                        <span>{data.field.price}/h</span>
                        <span className={cx("time")}>
                            <button value={"decrease"} onClick={e => handleHoursControl(e, data.field.id)}>-</button>
                            <span className={cx("hour-input")}>{hours}</span>
                            <button value={"increase"} onClick={e => handleHoursControl(e, data.field.id)}>+</button>
                        </span>
                    </p>
                </div>
                <CloseIcon className={cx("close-icon")} onClick={e => {handleDelete(data.field.id)}}/>
            </div>
        </Grow>
    );
}

export default Item;