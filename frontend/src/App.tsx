import './App.css'
import {Navigate, Route, Routes} from "react-router-dom";
import AppLayout from "./layouts/AppLayout.tsx";
import NotFoundPage from "./pages/notFound/NotFoundPage.tsx";
import LoginPage from "./pages/auth/LoginPage.tsx";
import PrivateRoute from "./components/auth/PrivateRoute.tsx";
import HomePage from "./pages/home/month-view/HomePage.tsx";
import CourseTablePage from "./pages/courses/CourseTablePage.tsx";
import TrainerTablePage from "./pages/trainers/TrainerTablePage.tsx";
import ClassroomTablePage from "./pages/classrooms/ClassroomTablePage.tsx";
import ResourceTablePage from "./pages/resources/ResourceTablePage.tsx";
import ProfilePage from "./pages/user/ProfilePage.tsx";
import SettingsPage from "./pages/user/SettingsPage.tsx";
import RegisterPage from "./pages/auth/RegisterPage.tsx";
import HelpPage from "./pages/user/HelpPage.tsx";
import ImprintPage from "./pages/user/ImprintPage.tsx";
import WeekTablePage from "./pages/home/week-view/WeekTablePage.tsx";
import CourseDetailsPage from "./pages/home/details-view/CourseDetailsPage.tsx";

function App() {
    const isAuthenticated = true;

    return (
        <>
            <Routes>
                {/* Auth-Route */}
                <Route path="/login" element={<LoginPage/>}/>

                <Route element={<PrivateRoute isAuthenticated={isAuthenticated}/>}>
                    {/* Layout-Route */}
                    <Route element={<AppLayout/>}>
                        {/* Main-Routes */}
                        <Route index element={<Navigate to="/home" replace/>}/>
                        <Route path="home" element={<HomePage />} />
                        <Route path="home/week/:year/:calendarWeek" element={<WeekTablePage />} />
                        <Route path="home/year/course/:courseId" element={<CourseDetailsPage />} />

                        <Route path="courses" element={<CourseTablePage/>}/>
                        <Route path="trainers" element={<TrainerTablePage/>}/>
                        <Route path="classrooms" element={<ClassroomTablePage/>}/>
                        <Route path="resources" element={<ResourceTablePage/>}/>

                        {/* User-related-Routes */}
                        <Route path="account">
                            <Route index element={<Navigate to="profile" replace/>}/>
                            <Route path="profile" element={<ProfilePage/>}/>
                            <Route path="settings" element={<SettingsPage/>}/>
                            <Route path="help" element={<HelpPage/>}/>
                            <Route path="register" element={<RegisterPage/>}/>
                            <Route path="imprint" element={<ImprintPage/>} />
                        </Route>
                    </Route>
                </Route>

                {/* Splash-Route */}
                <Route path="*" element={<NotFoundPage/>}/>
            </Routes>
        </>
    )
}

export default App
