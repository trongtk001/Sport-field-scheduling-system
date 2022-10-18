import callApi from "../util/api";


function loginService(loginCredentials) {
    return callApi("auth/login", "POST", null, loginCredentials, true).then(res => res.data);
}

function logoutService() {
    return callApi("auth/logout", "POST", null, null, true);
}

function registerService(registerInfo) {
    return callApi("auth/register", "POST", null, registerInfo).then(res => res.data);
}



export { loginService, registerService, logoutService }