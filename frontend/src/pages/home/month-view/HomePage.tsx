import {useState} from "react";
import "./HomePage.css";
import arrowLeft from "../../../assets/arrow-left.png";
import arrowRight from "../../../assets/arrow-right.png";
import WeekCard from "../../../components/cards/WeekCard.tsx";
import type {Week} from "../../../interfaces/pages/HomePage.ts";

function HomePage() {
    const now = new Date();
    const [year, setYear] = useState(now.getFullYear());
    const [month, setMonth] = useState(now.getMonth());

    const monthNames = [
        "Januar", "Februar", "März", "April", "Mai", "Juni",
        "Juli", "August", "September", "Oktober", "November", "Dezember"
    ];

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

    const formatYMD = (d: Date): string => {
        const y = d.getFullYear();
        const m = d.getMonth() + 1;
        const dd = d.getDate();
        return `${y}-${m < 10 ? "0" + m : m}-${dd < 10 ? "0" + dd : dd}`;
    }

    const getMonday = (d: Date): Date => {
        const day = d.getDay();
        const diff = (day === 0 ? -6 : 1 - day); // Montag ist Start
        return new Date(d.getFullYear(), d.getMonth(), d.getDate() + diff);
    }

    const getWeekNumber = (date: Date): number => {
        // ISO 8601: Woche gehört zum Jahr des Donnerstags
        const thursday = new Date(date);
        thursday.setDate(date.getDate() + 3 - (date.getDay() || 7) + 1);

        const jan1 = new Date(thursday.getFullYear(), 0, 1);
        const anchorMonday = getMonday(jan1);

        const daysDiff = Math.floor((thursday.getTime() - anchorMonday.getTime()) / (86400000));
        return Math.floor(daysDiff / 7) + 1;
    }

    const getCalendarWeeksForMonth = (year: number, month: number): Week[] => {
        const weeks: Week[] = [];
        const seen = new Set<number>();

        // Erster und letzter Tag des Monats
        const firstDay = new Date(year, month, 1);
        const lastDay = new Date(year, month + 1, 0);

        // Start: Montag der ersten Woche
        let currentMonday = getMonday(firstDay);
        const lastMonday = getMonday(lastDay);

        while (currentMonday <= lastMonday) {
            const sunday = new Date(currentMonday);
            sunday.setDate(sunday.getDate() + 6);

            const weekNumber = getWeekNumber(currentMonday);

            // Duplikate vermeiden
            if (!seen.has(weekNumber)) {
                seen.add(weekNumber);
                weeks.push({
                    id: `${year}-w${weekNumber}`,
                    week: weekNumber,
                    startDate: formatYMD(currentMonday),
                    endDate: formatYMD(sunday)
                });
            }

            // Nächster Montag
            currentMonday = new Date(currentMonday);
            currentMonday.setDate(currentMonday.getDate() + 7);
        }

        return weeks;
    }

    const weeks = getCalendarWeeksForMonth(year, month);

    return (
        <>
            <section className="header-section">
                <div className="date-info">
                    <p>Jahr: <span className="label">{year}</span></p>
                    <p>Monat: <span className="label">{monthNames[month]}</span></p>
                </div>
                <div className="add-course">
                    <button className="add-course-btn">
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
        </>
    );
}

export default HomePage;