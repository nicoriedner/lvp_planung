import {createContext, useContext} from "react";
import type {AuthContextProps} from "../../interfaces/context/ContextInterfaces.ts";

export const AuthContext = createContext<AuthContextProps | undefined>(undefined);

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth muss innerhalb von AuthProvider verwendet werden');
    }
    return context;
};