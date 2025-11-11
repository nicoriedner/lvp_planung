import {createContext, useContext} from 'react';
import {type CourseContextProps} from '../../interfaces/context/ContextInterfaces.ts';

export const CourseContext = createContext<CourseContextProps | undefined>(undefined);

export function useCourse() {
    const context = useContext(CourseContext);
    if (!context) {
        throw new Error("useDate must be used within a DateProvider");
    }
    return context;
}
