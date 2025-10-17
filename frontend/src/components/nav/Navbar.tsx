import {NavLink, useLocation} from "react-router-dom";
import {useState, useEffect, useRef} from "react";
import lfv_logo from "../../assets/lfv_steiermark_logo.png";
import profile from "../../assets/profile.png"
import "./Navbar.css";

function Navbar() {
    const [isOpen, setIsOpen] = useState<boolean>(false);
    const dropdownRef = useRef<HTMLDivElement>(null);
    const buttonRef = useRef<HTMLButtonElement>(null);
    const location = useLocation();

    useEffect(() => {
        setIsOpen(false);
    }, [location]);

    useEffect(() => {
        function handleClickOutside(event: MouseEvent) {
            if (
                dropdownRef.current &&
                buttonRef.current &&
                !dropdownRef.current.contains(event.target as Node) &&
                !buttonRef.current.contains(event.target as Node)
            ) {
                setIsOpen(false);
            }
        }

        if (isOpen) {
            document.addEventListener("mousedown", handleClickOutside);
        }

        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, [isOpen]);

    return (
        <nav className="navbar">
            <div>
                {/* Logo */}
                <img src={lfv_logo} className="navbar-logo" alt="LFV Steiermark"/>
                {/* Navigation Links */}
                <div className="navLinks">
                    <NavLink to="/home">Home</NavLink>
                    <NavLink to="/courses">Schulungen</NavLink>
                    <NavLink to="/trainers">Fachpersonal</NavLink>
                    <NavLink to="/classrooms">Lehrsäle</NavLink>
                    <NavLink to="/resources">Ressourcen</NavLink>
                </div>
            </div>
            {/* Profile Picture */}
            <button ref={buttonRef} onClick={() => setIsOpen(!isOpen)} className={isOpen ? 'active' : ''}>
                <img src={profile} className="profile-icon" alt="Profil"/>
            </button>
            {/* Dropdown Menu */}
            {
                isOpen &&
                <div ref={dropdownRef}>
                    <NavLink to="/account/profile">Mein Profil</NavLink>
                    <NavLink to="/account/settings">Einstellungen</NavLink>
                    <NavLink to="/account/help">Hilfe</NavLink>
                    <NavLink to="/account/register">Registrierung</NavLink>
                    <NavLink to="/account/imprint">Impressum</NavLink>
                    <button>Abmelden</button>
                </div>
            }
        </nav>
    );
}

export default Navbar;