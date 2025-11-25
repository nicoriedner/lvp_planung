import {Navigate, Outlet, useLocation} from "react-router-dom";
import {useAuth} from "../context/useAuth.ts";

function PrivateRoute() {
    const {isAuthenticated, loading} = useAuth();
    const location = useLocation();

    if (loading) {
        return <div>Lädt...</div>; // Loading spinner möglich
    }

    if (!isAuthenticated) {
        return <Navigate to="/login" state={{ from: location }} replace />
    }

    return <Outlet />
}

export default PrivateRoute;