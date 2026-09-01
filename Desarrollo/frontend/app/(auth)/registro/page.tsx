'use client';

import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { RegisterForm } from '@/components/auth/RegisterForm';
import { AuthService } from '@/services/auth.service';
import { Usuario } from '@/types/usuario';

export default function RegistroPage() {
    const router = useRouter();
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleRegister = async (usuario: Usuario) => {
        setIsLoading(true);
        setError(null);

        try {
            await AuthService.registrar(usuario);
            // Redirigir al login luego de registrarse con exito
            router.push('/login?registrado=exito');
        } catch (err: any) {
            setError(err.message || 'Ocurrió un error inesperado al registrar el usuario.');
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <main className='min-h-screen bg-amber-50/50 flex items-center justify-center p-4'>
            <RegisterForm onSubmit={handleRegister} isLoading={isLoading} error={error} />
        </main>
    );
}