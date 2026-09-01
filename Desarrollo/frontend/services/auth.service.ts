import { ENV } from '@/config/env';
import { Usuario, LoginRequest } from '@/types/usuario';

export const AuthService = {
    async registrar(usuario: Usuario): Promise<Usuario> {
        const res = await fetch(`${ENV.URL_API_GATEWAY}/auth/registro`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify(usuario),
        });

        if (!res.ok) {
            const errorData = await res.json().catch(() => ({}));
            throw new Error(errorData.message || 'Error en el registro de usuario');
        }

        return res.json();
    },

    async login(credentials: LoginRequest): Promise<Usuario> {
        const res = await fetch(`${ENV.URL_API_GATEWAY}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify(credentials),
        });

        if (!res.ok) {
            throw new Error('Las credenciales son inválidas');
        }

        return res.json();
    },
};