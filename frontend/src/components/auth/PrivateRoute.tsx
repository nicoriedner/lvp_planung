import {Navigate, Outlet} from "react-router-dom";

interface PrivateRouteProps {
    isAuthenticated: boolean;
}

function PrivateRoute({isAuthenticated}: PrivateRouteProps) {
    if (!isAuthenticated) {
        return <Navigate to="/login" replace />
    }

    return <Outlet />
}

export default PrivateRoute;