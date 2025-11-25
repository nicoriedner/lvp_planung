import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import {BrowserRouter} from "react-router-dom";
import {DateProvider} from "./components/context/DateContext.tsx";
import {CourseProvider} from "./components/context/CourseContext.tsx";
import {AuthProvider} from "./components/context/AuthContext.tsx";

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <BrowserRouter>
            <AuthProvider>
                <DateProvider>
                    <CourseProvider>
                        <App/>
                    </CourseProvider>
                </DateProvider>
            </AuthProvider>
        </BrowserRouter>
    </StrictMode>
)
