import Navbar from "../components/nav/Navbar.tsx";
import {Outlet} from "react-router-dom";

function AppLayout() {
    return (
        <>
            <Navbar />
            <main>
                <Outlet />
            </main>
        </>
    );
}

export default AppLayout;