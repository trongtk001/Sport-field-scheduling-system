import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

import { Alert, Button, Chip, FormControl, Grid, InputLabel, MenuItem, OutlinedInput, Select, Stack, TextField } from "@mui/material"
import { LocalizationProvider, DesktopDatePicker } from '@mui/x-date-pickers';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import CloseIcon from '@mui/icons-material/Close';
import { Box } from "@mui/system";

import { useDispatch, useSelector } from "react-redux";
import { itemSelector } from "../../redux/redux-selector/cart";

import CartItems from "./CartItems";
import { createSchedule, createScheduleDetail } from "../../Service/scheduleService";


import classNames from "classnames/bind";
import styles from "./Cart.module.scss";
import { cartSlice } from "../../redux/redux-reducer/cart";
const cx = classNames.bind(styles)

function Cart({ action }) {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const itemData = useSelector(itemSelector);

    //input info
    const [name, setName] = useState("");
    const [note, setNote] = useState("");
    const [date, setDate] = useState(new Date());
    const [service, setService] = useState([]);

    const username = window.sessionStorage.getItem("username");

    const [errorMsg, setErrorMsg] = useState("");

    const handleSubmit = async () => {

        if (!username) {
            return;
        }


        if (name.trim() === "") {
            setErrorMsg("Tên không được để trống.")
            return;
        }


        const schedule = {
            name: name,
            note: note,
            bookingDate: + date,
            status: 1,
            userName: username
        }

        var scheduleID;

        await createSchedule(schedule).then(data => {
            scheduleID = data.id;
        }).catch((error) => {
            if (error.response) {
                if (error.response.status === 403) {
                    console.log("error");
                } else {
                    console.log(error.message)
                }
            } else if (error.request) {
                console.log(error.request);
            } else {
                console.log('Error', error.message);
            }
        })

        await submitScheduleDetail(scheduleID);

        dispatch(cartSlice.actions.clearItem());
        navigate("/")

        action();
    }

    const submitScheduleDetail = (scheduleID) => {
        itemData.forEach(item => {
            const scheduleDetail = {
                checkinDate: null,
                timeRemaining: item.time + ":00:00",
                fieldId: item.field.id,
                serviceIDs: null,
                scheduleID: scheduleID
            }

            createScheduleDetail(scheduleDetail).catch((error) => {
                console.log(error)
            })
        });
    }

    useEffect(() => {
        setErrorMsg("")
    }, [name])


    const handleServiceChange = (e) => {
        setService(
            e.target.value
        );
    }

    //


    const [total, setTotal] = useState(0);

    useEffect(() => {
        setTotal(itemData.reduce((partialSum, item) => partialSum + (item.field.price * item.time), 0))
    }, [itemData])

    return (
        <div className={cx("container", "center")} onClick={e => { if (e.currentTarget === e.target) { action() } }} >
            <div className={cx("cart")}>
                <CloseIcon className={cx("close-icon")} onClick={action} />
                <Grid container spacing={2}>
                    <div className={cx("line")}></div>
                    <Grid item xs={6} >
                        <div className={cx("items")}>
                            <CartItems data={itemData} />
                            <div className={cx("total")}>
                                <span>Tổng</span>
                                <span>{total}VND</span>
                            </div>
                        </div>
                    </Grid>
                    <Grid item xs={6} className={cx("form")}>
                        <Stack spacing={3}>
                            <TextField error={!!errorMsg} helperText={errorMsg} label="Tên" color="success" value={name} onChange={e => setName(e.target.value)} />
                            <TextField label="Ghi chú" color="success" value={note} onChange={e => setNote(e.target.value)} />
                            <LocalizationProvider dateAdapter={AdapterDateFns}>
                                <DesktopDatePicker
                                    label="Ngày"
                                    inputFormat="MM/dd/yyyy"
                                    value={date}
                                    onChange={setDate}
                                    renderInput={(params) => <TextField {...params} color="success" />}
                                />
                            </LocalizationProvider>
                            <FormControl>
                                <InputLabel color="success">Dịch vụ</InputLabel>
                                <Select multiple color="success" value={service} onChange={handleServiceChange} input={<OutlinedInput label="Dịch vụ" />}
                                    renderValue={(selected) => (
                                        <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                                            {selected.map((value) => (
                                                <Chip key={value} label={value} color="success" />
                                            ))}
                                        </Box>
                                    )}>
                                    <MenuItem value="Thuê Giày">
                                        Thuê Giày
                                    </MenuItem>
                                    <MenuItem value="Thuê Áo">
                                        Thuê Áo
                                    </MenuItem>
                                    <MenuItem value="Nước">
                                        Nước
                                    </MenuItem>
                                </Select>
                            </FormControl>
                            {!username && <Alert severity="error">Bạn cần đăng nhập để đặt lịch</Alert>}
                            <Button variant="contained" color="success" onClick={handleSubmit}>Đặt lịch</Button>
                            <Link to="/" onClick={action}>Thuê thêm</Link>
                        </Stack>
                    </Grid>
                </Grid>
            </div>
        </div >
    );
}

export default Cart;