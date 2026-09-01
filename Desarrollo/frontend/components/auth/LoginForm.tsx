'use client';

import React, { useState } from 'react';
import { LoginRequest } from '@/types/usuario';

interface LoginFormProps {
    onSubmit: (credentials: LoginRequest) => Promise<void>;
    isLoading: boolean;
    error: string | null;
}

export const LoginForm: React.FC<LoginFormProps> = ({ onSubmit, isLoading, error }) => {
    const [credentials, setCredentials] = useState<LoginRequest>({
        correo: '',
        password: '',
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setCredentials((prev) => ({ ...prev, [name]: value}));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSubmit(credentials);
    };

    return (
        <form onSubmit={handleSubmit} className='bg-white p-8 rounded-xl shadow-lg border border-amber-100 max-w-md w-full'>
            <h2 className='text-2xl font-bold text-amber-900 mb-2 text-center'>Iniciar Sesión</h2>
            <p className='text-sm text-stone-600 mb-6 text-center'>Accede a tu cuenta de Chichería Bueno</p>

            {error && (
                <div className='bg-red-50 text-red-600 p-3 rounded mb-4 text-sm border border-red-200'>
                    {error}
                </div>
            )}

            <div className='mb-4'>
                <label className='block text-xs font-semibold text-stone-700 mb-1'>Correo Electronico</label>
                <input type='email' name='correo' required value={credentials.correo} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
            </div>

            <div className='mb-6'>
                <label className='block text-xs font-semibold text-stone-700 mb-1'>Contraseña</label>
                <input type='password' name='password' required value={credentials.password} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
            </div>

            <button type='submit' disabled={isLoading} className='w-full bg-amber-800 hover:bg-amber-900 text-white font-semibold py-2.5 rounded-lg transition-colors text-sm disabled:opacity-50'>
                {isLoading ? 'Ingresando...' : 'Iniciar Sesión'}
            </button>
        </form>
    );
};