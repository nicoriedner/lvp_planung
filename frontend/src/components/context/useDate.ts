import {createContext, useContext} from 'react';
import { type DateContextProps} from './DateContext';

export const DateContext = createContext<DateContextProps | undefined>(undefined);

export function useDate() {
    const context = useContext(DateContext);
    if (!context) {
        throw new Error("useDate must be used within a DateProvider");
    }
    return context;
}
