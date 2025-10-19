import {useState, useEffect} from "react";
import "./HomePage.css";
import arrowLeft from "../../../assets/arrow-left.png";
import arrowRight from "../../../assets/arrow-right.png";
import WeekCard from "../../../components/cards/WeekCard.tsx";
import type {Week} from "../../../interfaces/pages/HomePage.ts";
import {getCalendarWeeksForMonth} from "../../../services/HomePageService.tsx";

function HomePage() {
    const now = new Date();
    const [year, setYear] = useState<number>(now.getFullYear());
    const [month, setMonth] = useState<number>(now.getMonth());
    const [isOpen, setIsOpen] = useState<boolean>(false);

    const monthNames = [
        "Januar", "Februar", "März", "April", "Mai", "Juni",
        "Juli", "August", "September", "Oktober", "November", "Dezember"
    ];

    // Prevent background scroll when popup is open
    useEffect(() => {
        if (isOpen) {
            document.body.style.overflow = 'hidden';
        } else {
            document.body.style.overflow = 'unset';
        }

        return () => {
            document.body.style.overflow = 'unset';
        };
    }, [isOpen]);

    const handlePreviousMonth = () => {
        if (month === 0) {
            setMonth(11);
            setYear(prev => prev - 1);
        } else {
            setMonth(prev => prev - 1);
        }
    };

    const handleNextMonth = () => {
        if (month === 11) {
            setMonth(0);
            setYear(prev => prev + 1);
        } else {
            setMonth(prev => prev + 1);
        }
    };

    const weeks: Week[] = getCalendarWeeksForMonth(year, month);

    return (
        <>
            {/* month and year navigation */}
            <section className="header-section">
                <div className="date-info">
                    <p>Jahr: <span className="label">{year}</span></p>
                    <p>Monat: <span className="label">{monthNames[month]}</span></p>
                </div>
                <div className="add-course">
                    <button className="add-course-btn" onClick={() => setIsOpen(true)}>
                        Schulung hinzufügen
                    </button>
                </div>
                <div className="month-navigation">
                    <p>Monat</p>
                    <div className="nav-buttons">
                        <button className="nav-btn" onClick={handlePreviousMonth}>
                            <img src={arrowLeft} alt="zurück"/>
                        </button>
                        <button className="nav-btn" onClick={handleNextMonth}>
                            <img src={arrowRight} alt="vor"/>
                        </button>
                    </div>
                </div>
            </section>

            {/* display all calendar weeks within a given month & year */}
            <section className="week-cards">
                {weeks.map((week) => (
                    <WeekCard
                        key={week.id}
                        year={year}
                        calenderWeek={week.week}
                        startDate={week.startDate}
                        endDate={week.endDate}
                    />
                ))}
            </section>

            {/* Add Course Entry Popup */}
            {isOpen && (
                <>
                    <div className="add-course-overlay" onClick={() => setIsOpen(false)}></div>
                    <div className="add-course-popup">
                        <button className="popup-close" onClick={() => setIsOpen(false)}>
                            ×
                        </button>
                        {/* Content will go here */}
                    </div>
                </>
            )}
        </>
    );
}

export default HomePage;