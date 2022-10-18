import { useEffect, useRef, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { loginService } from "../../Service/authenticationService";


import classNames from "classnames/bind";
import styles from "./LoginForm.module.scss";
import { useAuth } from "../../auth/AuthProvider";
const cx = classNames.bind(styles)

function LoginPage() {
    const navigate = useNavigate();
    const auth = useAuth();

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const errorMsg = useRef();

    useEffect(() => {
        errorMsg.current.innerText = "";
    }, [username, password])


    const handleSubmit = () => {

        if (username.trim() === "") {
            errorMsg.current.innerText = "Your username cannot be blank."
            return;
        }

        if (password.trim() === "") {
            errorMsg.current.innerText = "Your password cannot be blank."
            return;
        }

        const loginCredentials = {
            username: username,
            password: password
        }
        loginService(loginCredentials).then(data => {
            window.sessionStorage.setItem("userID", data.id)
            window.sessionStorage.setItem("username", data.userName)
            auth.login({userID: data.id, username: data.userName, roles: data.roles})
            navigate("/")
        }).catch((error) => {
            if (error.response) {
                if (error.response.status === 403) {
                    errorMsg.current.innerText = "The login details you entered are incorrect. Try again..."
                }
            } else if (error.request) {
                console.log(error.request);
            } else {
                console.log('Error', error.message);
            }
        })
    }

    return (
        <div className={cx('wrapper', "center")}>
            <Form onKeyDown={e => {e.key === "Enter" && handleSubmit()}}>
                <h1>Log in</h1>

                <Form.Group >
                    <Form.Label htmlFor="username-input">Username</Form.Label>
                    <Form.Control id="username-input" value={username} onChange={e => setUsername(e.target.value)} type="text" placeholder="Enter username" required />
                    <p className={cx('username-msg')}></p>
                </Form.Group>
                <Form.Group  >
                    <Form.Label htmlFor="password-input">Password</Form.Label>
                    <Form.Control id="password-input" value={password} onChange={e => setPassword(e.target.value)} type="text" placeholder="Enter password" />
                </Form.Group>
                <Form.Label className={cx("error-msg")} ref={errorMsg}></Form.Label>
                <Button onClick={handleSubmit} variant="primary" type="button">
                    Submit
                </Button>
                <Link to="/signup">Sign up</Link>
            </Form>
        </div>
    );
}

export default LoginPage;