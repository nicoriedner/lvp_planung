export interface Week {
    id: string;
    week: number;
    startDate: string; // YYYY-MM-DD
    endDate: string;   // YYYY-MM-DD
}

export interface Course {
    id: string;
    title: string;
    startDate: string;
    endDate: string;
}

export interface ProcessedCourse extends Course {
    startIndex: number;
    endIndex: number;
    span: number;
    isClippedStart: boolean;
    isClippedEnd: boolean;
}