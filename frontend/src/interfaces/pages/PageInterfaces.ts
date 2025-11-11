export interface Week {
    id: string;
    week: number;
    startDate: string; // YYYY-MM-DD
    endDate: string;   // YYYY-MM-DD
}

export interface Course {
    id: number;
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

export interface CourseData extends Course{
    trainer: Trainer;
    classroom: Classroom;
    class: Class;
    resources: Resource[];
}

export interface Trainer {
    id: number;
    firstname: string;
}

export interface Classroom {
    id: number;
    name: string;
}

export interface Class {
    id: number;
    name: string;
}

export interface Resource {
    id: number;
    name: string;
}