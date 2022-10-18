import { createContext, useContext, useEffect, useState } from "react";
import { getUserInfo } from "../Service/userService";

const AuthContext = createContext(null);

function AuthProvider({children}) {

    const userID = window.sessionStorage.getItem('userID')
    const [user, setUser] = useState({});

    useEffect(() => {
        userID && getUserInfo(userID).then(data => {
            setUser({
                userID: data.id,
                userName: data.userName,
                roles: data.roles
            })
        })
    }, [userID])

    const login = (user) => setUser(user);

    const logout = () => setUser(null);

    return ( 
    <AuthContext.Provider value={{user, login, logout}}>
        {children}
    </AuthContext.Provider> );
}

export default AuthProvider;

export const useAuth = () => useContext(AuthContext);