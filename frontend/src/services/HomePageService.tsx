import type {Week} from "../interfaces/pages/HomePage.ts";

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

export const getCalendarWeeksForMonth = (year: number, month: number): Week[] => {
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