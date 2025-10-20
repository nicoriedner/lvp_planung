import arrowLeft from "../../../assets/arrow-left.png";
import arrowRight from "../../../assets/arrow-right.png";
import back from "../../../assets/back.png";
import "./WeekViewPage.css";
import {useParams} from "react-router-dom";
import {useState} from "react";

function WeekViewPage() {
    const { year, calendarWeek } = useParams();
    const [currentCalendarWeek, setCurrentCalenderWeek] = useState<number>();

    const courses = [
        {
            title: "AGT(7)"
        },
        {
            title: "Tech1"
        },
        {
            title: "Tech2"
        },
        {
            title: "Tech3"
        }
    ];

    return (
        <>
            {/* information & navigation*/}
            <section className="week-navigation">
                <p className="week-display">Wochenplan: <span>KW {calendarWeek}, {year}</span></p>
                <div>
                    <p className="week">Woche</p>
                    <div className="nav-buttons">
                        <button>
                            <img src={arrowLeft} alt="zurück"/>
                        </button>
                        <button>
                            <img src={arrowRight} alt="vor"/>
                        </button>
                    </div>
                </div>
                <button className="back-btn">
                    Zurück zur Übersicht
                    <img src={back} alt="zurück"/>
                </button>
            </section>
            <section>

            </section>
        </>
    );
}

export default WeekViewPage;