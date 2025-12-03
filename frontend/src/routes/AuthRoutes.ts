// src/services/authService.ts
import axios from 'axios';
import type { User, LoginResult } from '../interfaces/context/ContextInterfaces';

// Configure axios instance
const api = axios.create({
    baseURL: 'http://localhost:5000/api', // Adjust port as needed
    withCredentials: true,
});

let onAuthError: (() => void) | null = null;
let isLoggingOut = false;

api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (axios.isAxiosError(error) && error.response?.status === 401) {
            // token expired or invalid
            if (onAuthError && !isLoggingOut) {
                isLoggingOut = true;
                onAuthError();
            }
        }
        return Promise.reject(error);
    }
);

export const authService = {
    setAuthErrorCallback: (callback: () => void) => {
        onAuthError = callback;
    },

    resetLogoutFlag: () => {
        isLoggingOut = false;
    },

    verifyAuth: async (): Promise<User | null> => {
        try {
            const response = await api.get<{ user: User }>('/auth/verify-auth');
            return response.data.user;
        } catch (error) {
            console.error('Auth verification failed:', error);
            return null;
        }
    },

    login: async (email: string, password: string): Promise<LoginResult> => {
        try {
            const response = await api.post<{ user: User }>('/auth/login', {
                email,
                password,
            });

            return {
                success: true,
                user: response.data.user,
            };
        } catch (error) {
            if (axios.isAxiosError(error) && error.response) {
                return {
                    success: false,
                    error: error.response.data.error || 'Login fehlgeschlagen',
                };
            }

            return {
                success: false,
                error: error instanceof Error ? error.message : 'Unknown error',
            };
        }
    },

    logout: async (): Promise<void> => {
        try {
            await api.post('/auth/logout');
        } catch (error) {
            console.error('Logout failed:', error);
            throw error;
        }
    },
};