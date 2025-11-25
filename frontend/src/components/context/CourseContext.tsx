import { type ReactNode, useState } from 'react';
import type {Course} from "../../interfaces/pages/PageInterfaces.ts";
import {CourseContext} from "./useCourse.ts";

export function CourseProvider({ children }: { children: ReactNode }) {
    const [courses, setCourses] = useState<Record<string, Course[]>>({});

    return (
        <CourseContext.Provider value={{ courses, setCourses }}>
            {children}
        </CourseContext.Provider>
    );
}
