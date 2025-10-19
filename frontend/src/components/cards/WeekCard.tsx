import type {DateDetails} from "../../interfaces/cards/Cards.ts";
import open from "../../assets/open.png";
import {Link} from "react-router-dom";
import "./WeekCard.css";

function WeekCard({year, calenderWeek, startDate, endDate}: DateDetails) {
    return (
        <Link to={`/home/year/week/${year}/${calenderWeek}`} className="week-card">
            <div className="content">
                <h1>Kalenderwoche: {calenderWeek}</h1>
                <p>{startDate} - {endDate}</p>
            </div>
            <div className="open">
                <img src={open} alt="open"/>
            </div>
        </Link>
    );
}

export default WeekCard;