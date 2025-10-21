import { useNavigate, useParams } from "react-router-dom";
import arrowLeft from "../../../assets/arrow-left.png";
import arrowRight from "../../../assets/arrow-right.png";
import back from "../../../assets/back.png";
import "./WeekViewPage.css";
import type {Course, ProcessedCourse} from "../../../interfaces/pages/PageInterfaces.ts";
import {formatDMY} from "../../../services/HomePageService.tsx";

function WeekViewPage() {
    const { year, calendarWeek } = useParams();
    const navigate = useNavigate();

    const currentYear = Number(year);
    const currentCalendarWeek = Number(calendarWeek);

    {/* Course Mock-Data */}
    const courses: Course[] = [
        {
            id: "1",
            title: "AGT(7)",
            startDate: "2025-10-20", // Monday
            endDate: "2025-10-22"    // Wednesday
        },
        {
            id: "2",
            title: "Tech1",
            startDate: "2025-10-21", // Tuesday
            endDate: "2025-10-23"    // Thursday
        },
        {
            id: "3",
            title: "Tech2",
            startDate: "2025-10-23", // Friday
            endDate: "2025-10-25"    // Sunday
        },
        {
            id: "4",
            title: "Tech3",
            startDate: "2025-10-19", // Monday
            endDate: "2025-10-20"    // Monday (same day)
        }
    ];

    {/* Used for Week-Navigation to check if a year contains 52 or 53 calendar weeks */}
    const getWeeksInYear = (year: number): number => {
        const jan1 = new Date(year, 0, 1);
        const dec31 = new Date(year, 11, 31);
        const jan1Day = jan1.getDay();
        const dec31Day = dec31.getDay();

        // check if one of those two days falls on a thursday
        if (jan1Day === 4 || dec31Day === 4) {
            return 53;
        }
        return 52;
    };

    const handlePreviousWeek = () => {
        if (currentCalendarWeek > 1) {
            navigate(`/home/year/week/${currentYear}/${currentCalendarWeek - 1}`);
        } else {
            const previousYear = currentYear - 1;
            const weeksInPreviousYear = getWeeksInYear(previousYear);
            navigate(`/home/year/week/${previousYear}/${weeksInPreviousYear}`);
        }
    };

    const handleNextWeek = () => {
        const weeksInCurrentYear = getWeeksInYear(currentYear);

        if (currentCalendarWeek < weeksInCurrentYear) {
            navigate(`/home/year/week/${currentYear}/${currentCalendarWeek + 1}`);
        } else {
            navigate(`/home/year/week/${currentYear + 1}/1`);
        }
    };

    const handleBack = () => {
        navigate("/home");
    };

    {/* Gets all Days (Monday - Sunday) of a Week with their respective Date */}
    const getDaysForWeek = (year: number, week: number) => {
        const dayNames = ["Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"];
        const jan4 = new Date(Date.UTC(year, 0, 4));
        const jan4Weekday = jan4.getUTCDay() === 0 ? 7 : jan4.getUTCDay();
        const mondayOfWeek1 = new Date(jan4);
        mondayOfWeek1.setUTCDate(jan4.getUTCDate() - (jan4Weekday - 1));
        const targetMonday = new Date(mondayOfWeek1);
        targetMonday.setUTCDate(mondayOfWeek1.getUTCDate() + (week - 1) * 7);

        return Array.from({ length: 7 }, (_, i) => {
            const day = new Date(targetMonday);
            day.setUTCDate(targetMonday.getUTCDate() + i);
            return {
                name: dayNames[i],
                date: day.toISOString().split("T")[0],
            };
        });
    };

    {/* Variable containing the dates for the table header */}
    const days = getDaysForWeek(currentYear, currentCalendarWeek);

    const weekStart = days[0].date;
    const weekEnd = days[6].date;

    {/* Add information to a course - startDate, endDate and span as well information whether it spans outside the week-scope or not */}
    const processedCourses: ProcessedCourse[] = courses
        .filter(course => {
            // Check if course overlaps with the current week
            // Course must end on or after week start AND start on or before weekend
            return course.endDate >= weekStart && course.startDate <= weekEnd;
        })
        .map(course => {
            const courseStart = course.startDate;
            const courseEnd = course.endDate;

            // Clip course to week boundaries
            const clippedStart = (courseStart < weekStart) ? weekStart : courseStart;
            const clippedEnd = (courseEnd > weekEnd) ? weekEnd : courseEnd;

            // Find start and end day indices
            const startIndex = days.findIndex(d => d.date === clippedStart);
            const endIndex = days.findIndex(d => d.date === clippedEnd);

            return {
                ...course,
                startIndex: (startIndex >= 0) ? startIndex : 0,
                endIndex: (endIndex >= 0) ? endIndex : 6,
                span: (endIndex >= 0 ? endIndex : 6) - (startIndex >= 0 ? startIndex : 0) + 1,
                isClippedStart: courseStart < weekStart,
                isClippedEnd: courseEnd > weekEnd
            };
        });

    // Assign courses to rows (avoid overlaps)
    const rows: ProcessedCourse[][] = [];

    {/* 2-dimensional array containing courses per row */}
    processedCourses.forEach(course => {
        let placed = false;

        // Try to place in existing rows
        for (const row of rows) {
            const hasOverlap = row.some(existingCourse => {
                return !(course.endIndex < existingCourse.startIndex ||
                    course.startIndex > existingCourse.endIndex);
            });

            if (!hasOverlap) {
                row.push(course);
                placed = true;
                break;
            }
        }

        // Create new row if no suitable row found
        if (!placed) {
            rows.push([course]);
        }
    });

    {/* Add cells and fill them either blank or with course-data */}
    const buildRowCells = (rowCourses: ProcessedCourse[]) => {
        // Build table cells for each row
        const cells = [];
        let currentDayIndex = 0;

        // Sort courses by start index
        const sortedCourses = [...rowCourses].sort((a, b) => a.startIndex - b.startIndex);

        sortedCourses.forEach(course => {
            // Add empty cells before course
            while (currentDayIndex < course.startIndex) {
                cells.push(<td key={`empty-${currentDayIndex}`}></td>);
                currentDayIndex++;
            }

            // Add course cell
            cells.push(
                <td
                    key={`course-${course.id}`}
                    colSpan={course.span}
                    className={`course-cell ${course.isClippedStart ? 'clipped-start' : ''} ${course.isClippedEnd ? 'clipped-end' : ''}`}
                    onClick={() => navigate(`/home/course/${course.id}`)}
                    style={{ cursor: 'pointer' }}
                >
                    {course.title}
                </td>
            );
            currentDayIndex += course.span;
        });

        // Fill remaining empty cells
        while (currentDayIndex < 7) {
            cells.push(<td key={`empty-${currentDayIndex}`}></td>);
            currentDayIndex++;
        }

        return cells;
    };

    return (
        <>
            <section className="week-navigation">
                <p className="week-display">Wochenplan: <span>KW {(Number(calendarWeek) < 10) ? ('0' + calendarWeek) : calendarWeek}, {year}</span></p>
                <div>
                    <p className="week">Woche</p>
                    <div className="nav-buttons">
                        <button onClick={handlePreviousWeek}>
                            <img src={arrowLeft} alt="zurück" />
                        </button>
                        <button onClick={handleNextWeek}>
                            <img src={arrowRight} alt="vor" />
                        </button>
                    </div>
                </div>
                <button onClick={handleBack} className="back-btn">
                    Zurück zur Übersicht
                    <img src={back} alt="zurück" />
                </button>
            </section>
            <section>
                <table className="week-table">
                    <thead>
                    <tr>
                        {days.map((day) => (
                            <th key={day.name}>{day.name}<br/>{formatDMY(new Date(day.date))}</th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    {rows.map((rowCourses, index) => (
                        <tr key={index}>
                            {buildRowCells(rowCourses)}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </section>
        </>
    );
}

export default WeekViewPage;