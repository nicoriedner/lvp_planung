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

export interface AuthContextProps {
    user: UserProps | null;
    isAuthenticated: boolean;
    loading: boolean;

    login: (email: string, password: string) => Promise<LoginResultProps>;
    logout: () => Promise<void>;
}

export interface UserProps {
    id: string;
    email: string;
    name: string;
}

export interface LoginResultProps {
    success: boolean;
    error?: string;
}