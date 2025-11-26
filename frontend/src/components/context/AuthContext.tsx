import {type ReactNode, useEffect, useState} from "react";
import {AuthContext} from "./useAuth";
import type {UserProps} from "../../interfaces/context/ContextInterfaces.ts";
import type {LoginResultProps} from "../../interfaces/context/ContextInterfaces.ts";

export const AuthProvider = ({children}: { children: ReactNode }) => {
    const [user, setUser] = useState<UserProps | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    // beim rendern in main.tsx
    useEffect(() => {
        void checkAuth();
    }, []);

    // TODO: cleanup code allot

    const checkAuth = async (): Promise<void> => {
        try {
            const response = await fetch('/', {
                method: 'GET',
                credentials: 'include',
            });

            if (response.ok) {
                const data = await response.json();
                setUser(data.user);
            } else {
                setUser(null);
            }
        } catch (error) {
            console.error('Auth check failed:', error);
            setUser(null);
        } finally {
            setLoading(false);
        }
    };

    const login = async (
        email: string,
        password: string
    ): Promise<LoginResultProps> => {
        try {
            // use axios
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                credentials: 'include',
                body: JSON.stringify({email, password}),
            });

            if (!response.ok) {
                const data = await response.json();
                return {
                    success: false,
                    error: data.error || 'Login fehlgeschlagen'
                };
            }

            const data = await response.json();
            setUser(data.user);
            return {success: true};
        } catch (error) {
            return {
                success: false,
                error: error instanceof Error ? error.message : 'Unknown error'
            };
        }
    };

    const logout = async (): Promise<void> => {
        try {
            await fetch('/api/auth/logout', {
                method: 'POST',
                credentials: 'include',
            });
        } catch (error) {
            console.error('Logout failed:', error);
        } finally {
            setUser(null);
        }
    };

    return <AuthContext.Provider value={{ user, login, logout, loading, isAuthenticated: !!user }}>
        {children}
    </AuthContext.Provider>;
};