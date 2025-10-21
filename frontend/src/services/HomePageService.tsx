import type {Week} from "../interfaces/pages/PageInterfaces.ts";

export const formatDMY = (d: Date): string => {
    const y = d.getFullYear();
    const m = d.getMonth() + 1;
    const dd = d.getDate();
    return `${dd < 10 ? "0" + dd : dd}.${m < 10 ? "0" + m : m}.${y}`;
}

const getMonday = (d: Date): Date => {
    const day = d.getDay();
    const diff = (day === 0 ? -6 : 1 - day); // Montag ist Start
    return new Date(d.getFullYear(), d.getMonth(), d.getDate() + diff);
}

const getISOWeekAndYear = (date: Date): { week: number; year: number } => {
    // ISO 8601: Week belongs to the year that contains the Thursday
    const target = new Date(date);

    // Find Thursday of this week
    const dayOfWeek = (target.getDay() + 6) % 7; // Monday = 0, Sunday = 6
    target.setDate(target.getDate() - dayOfWeek + 3); // Thursday

    const isoYear = target.getFullYear();

    // January 4th is always in week 1
    const jan4 = new Date(isoYear, 0, 4);
    const jan4Monday = getMonday(jan4);

    // Calculate week number
    const diff = target.getTime() - jan4Monday.getTime();
    const weekNumber = 1 + Math.floor(diff / (7 * 24 * 60 * 60 * 1000));

    return { week: weekNumber, year: isoYear };
}

export const getCalendarWeeksForMonth = (year: number, month: number): Week[] => {
    const weeks: Week[] = [];
    const seen = new Set<string>();

    // Erster und letzter Tag des Monats
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);

    // Start: Montag der ersten Woche
    let currentMonday = getMonday(firstDay);
    const lastMonday = getMonday(lastDay);

    while (currentMonday <= lastMonday) {
        const sunday = new Date(currentMonday);
        sunday.setDate(sunday.getDate() + 6);

        // Check if the week overlaps with the target month
        const weekOverlapsMonth =
            (currentMonday.getMonth() === month && currentMonday.getFullYear() === year) ||
            (sunday.getMonth() === month && sunday.getFullYear() === year) ||
            (currentMonday < firstDay && sunday > lastDay);

        if (weekOverlapsMonth) {
            const { week: weekNumber, year: isoYear } = getISOWeekAndYear(currentMonday);
            const weekKey = `${isoYear}-w${weekNumber}`;

            if (!seen.has(weekKey)) {
                seen.add(weekKey);
                weeks.push({
                    id: weekKey,
                    week: weekNumber,
                    startDate: formatDMY(currentMonday),
                    endDate: formatDMY(sunday)
                });
            }
        }

        // Nächster Montag
        currentMonday = new Date(currentMonday);
        currentMonday.setDate(currentMonday.getDate() + 7);
    }

    return weeks;
}