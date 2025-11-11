import React from "react";
import type {Course, Week} from "../pages/PageInterfaces.ts";

export interface DateContextProps {
    year: number;
    setYear: React.Dispatch<React.SetStateAction<number>>;
    month: number;
    setMonth: React.Dispatch<React.SetStateAction<number>>;
    weeks: Week[];
    currentWeek: number;
    setCurrentWeek: React.Dispatch<React.SetStateAction<number>>;
}

export interface CourseContextProps {
    courses: Record<string, Course[]>;
    setCourses: React.Dispatch<React.SetStateAction<Record<string, Course[]>>>
}