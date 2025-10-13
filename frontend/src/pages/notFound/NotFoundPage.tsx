import {Link} from "react-router-dom";
import "./NotFoundPage.css";

function NotFoundPage() {
    return (
        <div className="not-found">
            <h1>404</h1>
            <h2>Seite nicht gefunden</h2>
            <p>
                Die Seite, die Sie suchen, existiert nicht.
            </p>
            <Link to="/" className="back-home" >
                Zurück zur Startseite
            </Link>
        </div>
    );
}

export default NotFoundPage;