import React, { useState } from "react";
import "./LoginPage.css";
import passwordShow from "../../assets/password_show.png";
import passwordHide from "../../assets/password_hide.png";
import lfv_logo from "../../assets/lfv_steiermark_logo.png";
import {useLocation, useNavigate} from "react-router-dom";
import {useAuth} from "../../components/context/useAuth.ts";
import type {LoginResult} from "../../interfaces/context/ContextInterfaces.ts";
import warning from "../../assets/warning.png";

function LoginPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();
    const location = useLocation();
    const { login } = useAuth();

    const from = location.state?.from?.pathname || '/home';

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        console.log("Login attempt:", { email, password });

        const loadingTimeout = setTimeout(() => {
            setLoading(true);
        }, 200);

        const result: LoginResult = await login(email, password);

        clearTimeout(loadingTimeout);

        // for testing - change to success
        if (result.error) {
            navigate(from, { replace: true });
            setError('');
        } else {
            console.error(result.error || "error while trying to log in");
            setError("Login fehlgeschlagen");
        }

        setLoading(false);
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <div className="logo-container">
                    <img src={lfv_logo} alt="Landesfeuerwehrverband Steiermark" className="logo" />
                </div>

                {error && (
                    <div className="error-banner">
                        <div className="error-icon"><img src={warning} alt="warning"/></div>
                        <div className="error-content">
                            <div className="error-title">{error}</div>
                            <div className="error-hint">Bitte überprüfe deine Anmeldedaten und versuche es erneut</div>
                        </div>
                    </div>
                )}

                <form onSubmit={handleLogin} className="login-form">
                    <div className="input-group">
                        <label htmlFor="email">Email Addresse</label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="vorname.nachname@lfv.steiermark.at"
                            required
                        />
                    </div>

                    <div className="input-group">
                        <label htmlFor="password">Passwort</label>
                        <div className="password-input-wrapper">
                            <input
                                type={showPassword ? "text" : "password"}
                                id="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder={showPassword ? "dein passwort" : "••••••••••••"}
                                required
                            />
                            <button
                                type="button"
                                className="toggle-password"
                                onClick={() => setShowPassword(!showPassword)}
                                aria-label={showPassword ? "Passwort verstecken" : "Passwort anzeigen"}
                            >
                                <img
                                    src={showPassword ? passwordShow : passwordHide}
                                    alt={showPassword ? "Verstecken" : "Anzeigen"}
                                />
                            </button>
                        </div>
                    </div>

                    <button type="submit" className="login-button" disabled={loading}>
                        {loading ? 'Lädt...' : 'Login'}
                    </button>
                </form>
            </div>
        </div>
    );
}

export default LoginPage;