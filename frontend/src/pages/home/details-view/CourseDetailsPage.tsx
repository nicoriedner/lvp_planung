import {useNavigate, useParams} from "react-router-dom";
import back from "../../../assets/back.png";
import {useDate} from "../../../components/context/useDate.ts";
import {useCourse} from "../../../components/context/useCourse.ts";
import type {Course, CourseData} from "../../../interfaces/pages/PageInterfaces.ts";
import {useEffect, useMemo, useState} from "react";
import "./CourseDetailsPage.css";
import "../week-view/WeekViewPage.css";

function CourseDetailsPage() {
    const {courseId} = useParams();
    const {year, currentWeek} = useDate();
    const {courses, setCourses} = useCourse();
    const navigate = useNavigate();
    const [isOpen, setIsOpen] = useState<boolean>(false);

    // Get basic course from context
    const weekKey = `${year}-W${currentWeek}`;
    const weekCourses = useMemo(() => {
        return courses[weekKey] || [];
    }, [courses, weekKey]);

    const [course, setCourse] = useState<Course | undefined>(undefined);
    const [courseData, setCourseData] = useState<CourseData | null>(null);
    const [notFound, setNotFound] = useState(false);

    // Fetch course if not in context (direct URL visit)
    useEffect(() => {
        // Check if course exists in context first
        const foundInContext = weekCourses.find(c => c.id === Number(courseId));

        if (foundInContext) {
            setCourse(foundInContext);
            return;
        }

        // If week courses are already loaded but course not found
        if (weekCourses.length > 0) {
            setNotFound(true);
            return;
        }

        // Fetch week courses if not loaded yet
        // TODO: Replace with actual API call
        // const weekData = await fetch(`/api/weeks/${year}/${currentWeek}/courses`).then(r => r.json());

        // Mock: Fetch week courses
        const weekData: Course[] = [
            {
                id: 1,
                title: "AGT(7)",
                startDate: "2025-10-20",
                endDate: "2025-10-22"
            },
            {
                id: 2,
                title: "Tech1",
                startDate: "2025-10-21",
                endDate: "2025-10-23"
            },
        ];

        // Store in context
        setCourses(prev => ({
            ...prev,
            [weekKey]: weekData
        }));

        // Find the course
        const foundCourse = weekData.find(c => c.id === Number(courseId));
        if (foundCourse) {
            setCourse(foundCourse);
        } else {
            setNotFound(true);
        }
    }, [courseId, weekCourses, weekKey, year, currentWeek, setCourses]);

    // Fetch course details once we have the basic course
    useEffect(() => {
        if (!course) return;

        // TODO: Replace with actual API call
        // const details = await fetch(`/api/courses/${courseId}/details`).then(r => r.json());

        // Mock data for now - only the additional details
        const details = {
            trainer: {id: 1, firstname: "Max Mustermann"},
            classroom: {id: 1, name: "Raum 101"},
            class: {id: 1, name: "Klasse A"},
            resources: [
                {id: 1, name: "Laptop"},
                {id: 2, name: "Beamer"}
            ]
        };

        // Combine course from context with fetched details
        setCourseData({
            ...course,
            ...details
        });
    }, [course, courseId]);

    const handleBack = () => {
        // sometimes when wrong id - error and week-not-loaded so it becomes 0
        navigate(`/home/year/week/${year}/${currentWeek}`)
    }

    if (notFound) {
        return (
            <div className="course-details-container">
                <section className="course-actions">
                    <h2>Schulung mit ID {courseId} nicht gefunden</h2>
                    <button onClick={handleBack} className="back-btn">
                        Zurück zur Übersicht
                        <img src={back} alt="back"/>
                    </button>
                </section>
                <section>
                    <p>Ungültige Schulungs-ID</p>
                    <p>Die von Ihnen gesuchte Schulung existiert so nicht!</p>
                    <p>Möglicherweiße wurde sie gelöscht</p>
                </section>
            </div>
        );
    }

    if (!courseData) return <div>Laden...</div>;

    return (
        <div className="course-details-container">
            <section className="course-header">
                <h1>{courseData.title}</h1>
            </section>
            <section className="course-actions">
                <button onClick={() => setIsOpen(true)}>
                    Schulung bearbeiten
                </button>
                <button onClick={handleBack} className="back-btn">
                    Zurück zur Übersicht
                    <img src={back} alt="back"/>
                </button>
            </section>
            <section className="course-details">
                <div className="course-details-left">
                    <p><strong>Trainer:</strong> {courseData.trainer.firstname}</p>
                    <p><strong>Raum:</strong> {courseData.classroom.name}</p>
                    <div>
                        <h3>Ressourcen:</h3>
                        <ul>
                            {courseData.resources.map(resource => (
                                <li key={resource.id}>{resource.name}</li>
                            ))}
                        </ul>
                    </div>
                </div>
                <div className="course-details-right">
                    <p className="course-date">
                        <strong>Zeitraum:</strong> {new Date(courseData.startDate).toLocaleDateString('de-DE')} - {new Date(courseData.endDate).toLocaleDateString('de-DE')}
                    </p>
                    <p><strong>Klasse:</strong> {courseData.class.name}</p>
                </div>
            </section>
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
        </div>
    );
}

export default CourseDetailsPage;