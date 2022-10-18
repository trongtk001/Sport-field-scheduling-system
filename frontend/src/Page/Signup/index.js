import { useEffect, useRef, useState } from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { registerService } from "../../Service/authenticationService";

import classNames from "classnames/bind";
import styles from "./SignUpForm.module.scss";
const cx = classNames.bind(styles)


function SignUpPage() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [fullname, setFullname] = useState('');
    const [phone, setPhone] = useState('');
    const [gmail, setGmail] = useState('');

    const navigate = useNavigate();
    const errorMsg = useRef();

    useEffect(() => {
        errorMsg.current.innerText = "";
    }, [username, password, fullname, phone, gmail])

    const handleSubmit = () => {

        if (username.trim() === "") {
            errorMsg.current.innerText = "Your username cannot be blank.";
            return;
        }

        if (fullname.trim() === "") {
            errorMsg.current.innerText = "Your fullname cannot be blank.";
            return;
        }

        /* eslint-disable no-useless-escape */
        const phoneformat = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
        if (!phone.match(phoneformat) && phone !== "") {
            errorMsg.current.innerText = "You have entered an invalid phone number";
            return;
        }

        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (!gmail.match(mailformat) && gmail !== "") {
            errorMsg.current.innerText = "You have entered an invalid email address!";
            return;
        }

        if (password.trim() === "") {
            errorMsg.current.innerText = "Your password cannot be blank.";
            return;
        } else if (password.length < 6 || password.length > 26) {
            errorMsg.current.innerText = "Passwrod must have from 6 - 26 characters"
            return;
        } else if (confirmPassword !== password) {
            errorMsg.current.innerText = "Password mismatch"
            return;
        }



        const registerInfo = {
            userName: username,
            password: password,
            fullName: fullname,
            phone: phone,
            gmail: gmail,
            status: 1,
            roles: ["USER"]
        }
        registerService(registerInfo).then(data => {
            navigate("/login")
        }).catch((error) => {
            if (error.response) {
                if (error.response.status === 409) {
                    errorMsg.current.innerText = "User with the same identity already exists";
                } else {
                    console.log(error.response)
                }
            } else if (error.request) {
                // The request was made but no response was received
                console.log(error.request);
            } else {
                // Something happened in setting up the request that triggered an Error
                console.log('Error', error.message);
            }
        })
    }


    return (
        <div className={cx('wrapper', "center")}>
            <Form onKeyDown={e => {e.key === "Enter" && handleSubmit()}}>
                <h1>Sign up</h1>
                <Form.Group  >
                    <Form.Label htmlFor="username-input">Username(*)</Form.Label>
                    <Form.Control id="username-input" value={username} onChange={e => setUsername(e.target.value)} type="text" placeholder="Enter username" required />
                </Form.Group>
                <Form.Group  >
                    <Form.Label htmlFor="fullname-input">Fullname(*)</Form.Label>
                    <Form.Control id="fullname-input" value={fullname} onChange={e => setFullname(e.target.value)} type="text" placeholder="Enter fullname" required />
                </Form.Group>
                <Row>
                    <Form.Group as={Col} md='6'>
                        <Form.Label htmlFor="phone-input">Phone</Form.Label>
                        <Form.Control id="phone-input" value={phone} onChange={e => setPhone(e.target.value)} type="text" placeholder="Enter phone" required />
                    </Form.Group>
                    <Form.Group as={Col} md='6' >
                        <Form.Label htmlFor="gmail-input">Gmail</Form.Label>
                        <Form.Control id="gmail-input" value={gmail} onChange={e => setGmail(e.target.value)} type="gmail" placeholder="Enter gmail" required />
                    </Form.Group>
                </Row>
                <Form.Group  >
                    <Form.Label htmlFor="password-input">Password(*)</Form.Label>
                    <Form.Control id="password-input" value={password} onChange={e => setPassword(e.target.value)} type="text" placeholder="Enter password" />
                </Form.Group>
                <Form.Group  >
                    <Form.Label htmlFor="comfirm-password-input">Confirm Password(*)</Form.Label>
                    <Form.Control id="comfirm-password-input" value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)} type="text" placeholder="Confirm password" />
                </Form.Group>
                <Form.Label className={cx("error-msg")} ref={errorMsg}></Form.Label>
                <Button onClick={handleSubmit} variant="primary" type="button">
                    Submit
                </Button>
                <Link to="/login">Sign in</Link>
            </Form>
        </div>
    );
}

export default SignUpPage;