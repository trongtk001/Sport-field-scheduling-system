import classNames from "classnames/bind";
import { useRef, useState } from "react";
import { Button, Form } from "react-bootstrap";
import callApi from "../../util/api";

import styles from "./LoginForm.module.scss";
const cx = classNames.bind(styles)

function LoginForm() {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')


    const handleSubmit = () => {
        const loginCredentials = {
            username: username,
            password: password
        }
        console.log(JSON.stringify(loginCredentials))
        callApi("/login", "POST", null, JSON.stringify(loginCredentials))
    }

    return (
        <div className={cx('wrapper')}>
            <Form>
                <Form.Group  >
                    <Form.Label htmlFor="username-input">Username</Form.Label>
                    <Form.Control id="username-input" value={username} onChange={e => setUsername(e.target.value)} type="text" placeholder="Enter username"/>
                    <p className={cx('username-msg')}></p>
                </Form.Group>
                <Form.Group  >
                    <Form.Label htmlFor="password-input">Password</Form.Label>
                    <Form.Control id="password-input" value={password} onChange={e => setPassword(e.target.value)} type="password" placeholder="Enter password" />
                </Form.Group>
                <Button onClick={handleSubmit} variant="primary" type="button">
                    Submit
                </Button>
            </Form>
        </div>
    );
}

export default LoginForm;