import { Avatar, Box, Button, Grid, Stack } from '@mui/material'

import { useEffect, useRef, useState } from 'react';
import { editUserInfo, getUserInfo } from '../../../Service/userService';

import classNames from "classnames/bind";
import styles from "./UserProfile.module.scss";
import SuccessSnackbar from '../../../components/Snackbars/Success';
const cx = classNames.bind(styles)

function UserProfile() {

    const userID = window.sessionStorage.getItem('userID');
    var username = useRef('');

    useEffect(() => {
        getUserInfo(userID).then(data => {
            username.current = data.userName
            setFullName(data.fullName)
            setGmail(data.gmail)
            setPhone(data.phone)
            setAddress(data.address)
            setAvatar(data.avatar)
        }).catch(error => console.log(error));
    }, [userID])


    const [fullName, setFullName] = useState('');
    const [gmail, setGmail] = useState('');
    const [phone, setPhone] = useState('');
    const [address, setAddress] = useState('');
    const [avatar, setAvatar] = useState('');
    const [errorMsg, setErrorMsg] = useState('');

    const validInfo = () => {
        /* eslint-disable no-useless-escape */
        const phoneformat = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
        if (!phone.match(phoneformat)) {
            return "You have entered an invalid phone number";
        }

        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (!gmail.match(mailformat)) {
            return "You have entered an invalid email address!";
        }
    };

    const handleSubmit = () => {
        const check = validInfo();
        if (check) {
            setErrorMsg(check);
            return;
        }

        const userInfo = {
            userName: username.current,
            fullName: fullName,
            gmail: gmail,
            phone: phone,
            address: address,
            avatar: avatar
        }

        editUserInfo(userID, userInfo).catch(error => console.log(error))
    }

    return (<>
        <h2 className={cx('title')}>Hồ Sơ</h2>
        <Grid container spacing={0} sx={{ width: "100%", height: "75%" }}>
            <Grid className={cx('center', 'image')} item xs={4}>
                <Avatar src={avatar} className={cx('img')} />
            </Grid>
            <Grid className={cx('center')} item xs={8} >
                <Stack className={cx('info')}>
                    <div className={cx('text-field')}><input className={cx("input")} type="text" placeholder='tên' value={fullName} onChange={e => setFullName(e.target.value)} /></div>
                    <div className={cx('text-field')}><input className={cx("input")} type="text" placeholder='gmail' value={gmail} onChange={e => setGmail(e.target.value)} /></div>
                    <div className={cx('text-field')}><input className={cx("input")} type="text" placeholder='số điện thoại' value={phone} onChange={e => setPhone(e.target.value)} /></div>
                    <div className={cx('text-field')}><textarea className={cx("input")} type="text" rows={2} placeholder='địa chỉ' value={address} onChange={e => setAddress(e.target.value)} /></div>
                </Stack>
            </Grid>

        </Grid>
        <Box className='center' height={'24px'}>{!!errorMsg && <p className={cx('error')}>{errorMsg}</p>}</Box>
        <div className='center'><Button variant='contained' color='success' onClick={handleSubmit}>Lưu</Button></div>
        <SuccessSnackbar/>
    </>);
}

export default UserProfile;