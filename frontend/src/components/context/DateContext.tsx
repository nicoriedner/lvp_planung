import { type ReactNode, useState } from 'react';
import type { Week } from "../../interfaces/pages/PageInterfaces.ts";
import { getCalendarWeeksForMonth } from "../../services/HomePageService";
import {DateContext} from "./useDate.ts";

export function DateProvider({ children }: { children: ReactNode }) {
    const now = new Date();
    const [year, setYear] = useState(now.getFullYear());
    const [month, setMonth] = useState(now.getMonth());
    const weeks: Week[] = getCalendarWeeksForMonth(year, month);
    const [currentWeek, setCurrentWeek] = useState(0);

    return (
        <DateContext.Provider value={{ year, setYear, month, setMonth, weeks, currentWeek, setCurrentWeek }}>
            {children}
        </DateContext.Provider>
    );
}
