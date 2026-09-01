'use client';

import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { LoginForm } from '@/components/auth/LoginForm';
import { AuthService } from '@/services/auth.service';
import { LoginRequest } from '@/types/usuario';

export default function LoginPage() {
    const router = useRouter();
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleLogin = async (credentials: LoginRequest) => {
        setIsLoading(true);
        setError(null);

        try {
            const usuarioAutenticado = await AuthService.login(credentials);

            // Guardar sesión del usuario temporalmente en localStorage (V1.0)
            localStorage.setItem('usuario', JSON.stringify(usuarioAutenticado));

            // Redirigir al catálogo de productos
            router.push('/catalogo');
        } catch (err: any) {
            setError(err.message || 'Error al iniciar sesión. Verifiquemos sus credenciales.');
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <main className='min-h-screen bg-amber-50/50 flex items-center justify-center p-4'>
            <LoginForm onSubmit={handleLogin} isLoading={isLoading} error={error} />
        </main>
    );
}