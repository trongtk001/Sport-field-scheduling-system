import { Navigate, useLocation } from "react-router-dom";
import { useAuth } from "./AuthProvider";

function RequireAuth({children, roles}) {

    const auth = useAuth();
    const location = useLocation();

    if(!auth.user) {
        return <Navigate to="/login" state={{from : location}} replace/>
    } else if (!roles.some(role => auth.user.roles.includes(role))) {
        return <Navigate to="/"/>
    }

    return children;
}

export default RequireAuth;