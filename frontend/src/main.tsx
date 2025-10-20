import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import {BrowserRouter} from "react-router-dom";
import {DateProvider} from "./components/context/DateContext.tsx";

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <BrowserRouter>
            <DateProvider>
                <App/>
            </DateProvider>
        </BrowserRouter>
    </StrictMode>
)
