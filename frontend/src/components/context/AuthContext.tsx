import {type ReactNode, useEffect, useState} from "react";
import {AuthContext} from "./useAuth";
import type {User} from "../../interfaces/context/ContextInterfaces.ts";
import type {LoginResult} from "../../interfaces/context/ContextInterfaces.ts";
import {authService} from "../../routes/AuthRoutes.ts";

export const AuthProvider = ({children}: { children: ReactNode }) => {
    const [user, setUser] = useState<User | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    // beim rendern in main.tsx
    useEffect(() => {
        authService.setAuthErrorCallback(() => {
            setUser(null);
        });

        void verifyAuth();
    }, []);

    // TODO: cleanup code allot

    const verifyAuth = async (): Promise<void> => {
        try {
            const userData = await authService.verifyAuth();
            setUser(userData);
        } catch (error) {
            // Token expired or invalid - clear user state
            setUser(null);
            console.error('Auth verification failed:', error);
        } finally {
            setLoading(false);
        }
    };

    const login = async (
        email: string,
        password: string
    ): Promise<LoginResult> => {
        const result = await authService.login(email, password);

        if (result.success && result.user) {
            setUser(result.user);
        }

        // for testing
        setUser({email: "", id: "", name: ""})

        return result;
    };

    const logout = async (): Promise<void> => {
        try {
            await authService.logout();
        } finally {
            setUser(null);
            authService.resetLogoutFlag();
        }
    };

    return <AuthContext.Provider value={{ user, setUser, login, logout, loading, isAuthenticated: !!user }}>
        {children}
    </AuthContext.Provider>;
};