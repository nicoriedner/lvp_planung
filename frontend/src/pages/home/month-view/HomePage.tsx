import {useState, useEffect} from "react";
import "./HomePage.css";
import arrowLeft from "../../../assets/arrow-left.png";
import arrowRight from "../../../assets/arrow-right.png";
import WeekCard from "../../../components/cards/WeekCard.tsx";
import {useDate} from "../../../components/context/useDate.ts";

type AddItemType = 'classroom' | 'class' | 'resource' | null;

function HomePage() {
    const { year, setYear, month, setMonth, weeks } = useDate();
    const [isOpen, setIsOpen] = useState<boolean>(false);
    const [addItemPopup, setAddItemPopup] = useState<AddItemType>(null);
    const [newItemName, setNewItemName] = useState<string>("");

    // Dropdown data arrays
    const [classrooms, setClassrooms] = useState([
        { id: 1, name: "Raum 101" },
        { id: 2, name: "Raum 102" },
        { id: 3, name: "Raum 103" },
        { id: 4, name: "Raum 104" },
        { id: 5, name: "Raum 105" },
    ]);

    const [classes, setClasses] = useState([
        { id: 1, name: "5A" },
        { id: 2, name: "5B" },
        { id: 3, name: "6A" },
        { id: 4, name: "6B" },
        { id: 5, name: "7A" },
    ]);

    const [resources, setResources] = useState([
        { id: 1, name: "Projektor" },
        { id: 2, name: "Laptop" },
        { id: 3, name: "Tablets" },
        { id: 4, name: "Whiteboard" },
        { id: 5, name: "Beamer" },
    ]);

    const monthNames = [
        "Januar", "Februar", "März", "April", "Mai", "Juni",
        "Juli", "August", "September", "Oktober", "November", "Dezember"
    ];

    // Form state
    const [formData, setFormData] = useState({
        title: "",
        classroom: "",
        class: "",
        dateFrom: "",
        dateTo: "",
        resources: [] as number[] // Multiple resources
    });

    // Prevent background scroll when popup is open
    useEffect(() => {
        if (isOpen || addItemPopup) {
            document.body.style.overflow = 'hidden';
        } else {
            document.body.style.overflow = 'unset';
        }

        return () => {
            document.body.style.overflow = 'unset';
        };
    }, [isOpen, addItemPopup]);

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

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleResourceToggle = (resourceId: number) => {
        setFormData(prev => ({
            ...prev,
            resources: prev.resources.includes(resourceId)
                ? prev.resources.filter(id => id !== resourceId)
                : [...prev.resources, resourceId]
        }));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        // TODO: Handle form submission
        console.log("Form submitted:", formData);

        // Reset form
        setFormData({
            title: "",
            classroom: "",
            class: "",
            dateFrom: "",
            dateTo: "",
            resources: []
        });

        setIsOpen(false);
    };

    const handleAddItem = () => {
        if (!newItemName.trim() || !addItemPopup) return;

        // get id from the backend
        const newId = Date.now(); // Simple ID generation

        // backend call here to set item and get id from response entity

        switch (addItemPopup) {
            case 'classroom':
                setClassrooms(prev => [...prev, { id: newId, name: newItemName }]);
                setFormData(prev => ({ ...prev, classroom: String(newId) }));
                break;
            case 'class':
                setClasses(prev => [...prev, { id: newId, name: newItemName }]);
                setFormData(prev => ({ ...prev, class: String(newId) }));
                break;
            case 'resource':
                setResources(prev => [...prev, { id: newId, name: newItemName }]);
                setFormData(prev => ({ ...prev, resources: [...prev.resources, newId] }));
                break;
        }

        setNewItemName("");
        setAddItemPopup(null);
    };

    const getAddItemTitle = () => {
        switch (addItemPopup) {
            case 'classroom': return 'Klassenraum hinzufügen';
            case 'class': return 'Klasse hinzufügen';
            case 'resource': return 'Ressource hinzufügen';
            default: return '';
        }
    };

    const getAddItemPlaceholder = () => {
        switch (addItemPopup) {
            case 'classroom': return 'Klassenraum Name';
            case 'class': return 'Klasse Name';
            case 'resource': return 'Ressource Name';
            default: return '';
        }
    };

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

                        {/* Header */}
                        <div className="popup-header">
                            <h2 className="popup-title">Schulung hinzufügen</h2>
                        </div>

                        {/* Form */}
                        <form onSubmit={handleSubmit} className="popup-form">
                            {/* Title */}
                            <div className="form-group">
                                <label className="form-label">Titel</label>
                                <input
                                    type="text"
                                    name="title"
                                    value={formData.title}
                                    onChange={handleInputChange}
                                    placeholder="Eintrag Titel eingeben"
                                    className="form-input"
                                    required
                                />
                            </div>

                            {/* Classroom and Class */}
                            <div className="form-row">
                                <div className="form-group">
                                    <label className="form-label">Klassenraum</label>
                                    <select
                                        name="classroom"
                                        value={formData.classroom}
                                        onChange={(e) => {
                                            if (e.target.value === '__add_new__') {
                                                setAddItemPopup('classroom');
                                            } else {
                                                handleInputChange(e);
                                            }
                                        }}
                                        className="form-select"
                                        required
                                    >
                                        <option value="">Wählen...</option>
                                        {classrooms.map(classroom => (
                                            <option key={classroom.id} value={classroom.id}>
                                                {classroom.name}
                                            </option>
                                        ))}
                                        <option value="__add_new__" className="add-option-item">
                                            + Klassenraum hinzufügen
                                        </option>
                                    </select>
                                </div>
                                <div className="form-group">
                                    <label className="form-label">Klasse</label>
                                    <select
                                        name="class"
                                        value={formData.class}
                                        onChange={(e) => {
                                            if (e.target.value === '__add_new__') {
                                                setAddItemPopup('class');
                                            } else {
                                                handleInputChange(e);
                                            }
                                        }}
                                        className="form-select"
                                        required
                                    >
                                        <option value="">Wählen...</option>
                                        {classes.map(cls => (
                                            <option key={cls.id} value={cls.id}>
                                                {cls.name}
                                            </option>
                                        ))}
                                        <option value="__add_new__" className="add-option-item">
                                            + Klasse hinzufügen
                                        </option>
                                    </select>
                                </div>
                            </div>

                            {/* Date Pickers */}
                            <div className="form-row">
                                <div className="form-group">
                                    <label className="form-label">Von</label>
                                    <input
                                        type="date"
                                        name="dateFrom"
                                        value={formData.dateFrom}
                                        onChange={handleInputChange}
                                        className="form-input"
                                        required
                                    />
                                </div>
                                <div className="form-group">
                                    <label className="form-label">Bis</label>
                                    <input
                                        type="date"
                                        name="dateTo"
                                        value={formData.dateTo}
                                        onChange={handleInputChange}
                                        className="form-input"
                                        required
                                    />
                                </div>
                            </div>

                            {/* Resources - Multi Select */}
                            <div className="form-group">
                                <label className="form-label">
                                    Ressourcen ({formData.resources.length} ausgewählt)
                                </label>
                                <div className="resources-container">
                                    {resources.map(resource => (
                                        <label key={resource.id} className="resource-checkbox">
                                            <input
                                                type="checkbox"
                                                checked={formData.resources.includes(resource.id)}
                                                onChange={() => handleResourceToggle(resource.id)}
                                            />
                                            <span className="resource-name">{resource.name}</span>
                                        </label>
                                    ))}
                                    <button
                                        type="button"
                                        className="add-resource-btn"
                                        onClick={() => setAddItemPopup('resource')}
                                    >
                                        <span className="plus-icon">+</span>
                                        Ressource hinzufügen
                                    </button>
                                </div>
                            </div>

                            {/* Footer Buttons */}
                            <div className="popup-footer">
                                <button
                                    type="button"
                                    onClick={() => setIsOpen(false)}
                                    className="btn-cancel"
                                >
                                    Abbrechen
                                </button>
                                <button
                                    type="submit"
                                    className="btn-submit"
                                >
                                    Erstellen
                                </button>
                            </div>
                        </form>
                    </div>
                </>
            )}

            {/* Add Item Mini Popup */}
            {addItemPopup && (
                <>
                    <div className="mini-popup-overlay" onClick={() => {
                        setAddItemPopup(null);
                        setNewItemName("");
                    }}></div>
                    <div className="mini-popup">
                        <h3 className="mini-popup-title">{getAddItemTitle()}</h3>
                        <input
                            type="text"
                            value={newItemName}
                            onChange={(e) => setNewItemName(e.target.value)}
                            placeholder={getAddItemPlaceholder()}
                            className="mini-popup-input"
                            autoFocus
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') {
                                    e.preventDefault();
                                    handleAddItem();
                                } else if (e.key === 'Escape') {
                                    setAddItemPopup(null);
                                    setNewItemName("");
                                }
                            }}
                        />
                        <div className="mini-popup-buttons">
                            <button
                                type="button"
                                onClick={() => {
                                    setAddItemPopup(null);
                                    setNewItemName("");
                                }}
                                className="mini-btn-cancel"
                            >
                                Abbrechen
                            </button>
                            <button
                                type="button"
                                onClick={handleAddItem}
                                className="mini-btn-submit"
                                disabled={!newItemName.trim()}
                            >
                                Hinzufügen
                            </button>
                        </div>
                    </div>
                </>
            )}
        </>
    );
}

export default HomePage;