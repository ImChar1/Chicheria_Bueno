'use client' ;

import React, { useState } from 'react';
import { Usuario } from '@/types/usuario';

interface RegisterFormProps {
    onSubmit: (usuario: Usuario) => Promise<void>;
    isLoading: boolean;
    error: string | null;
}

export const RegisterForm: React.FC<RegisterFormProps> = ({ onSubmit, isLoading, error}) => {
    const [formData, setFormData] = useState<Usuario>({
        rut: '',
        nombreUsuario: '',
        apellidoPaterno: '',
        apellidoMaterno: '',
        correo: '',
        password: '',
        telefono: '',
        fechaNacimiento: '',
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value}));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSubmit(formData);
    };

    return (
        <form onSubmit={handleSubmit} className='bg-white p-8 rounded-xl shadow-lg border border-amber-100 max-w-lg w-full'>
            <h2 className='text-2xl font-bold text-amber-900 mb-2 text-center'>Crear Cuenta</h2>
            <p className='text-sm text-stone-600 mb-6 text-center'>Únete a Chichería Bueno para acceder al catálogo artesanal</p>

            {/*Alerta Legal +18 */}
            <div className='bg-amber-50 border-l-4 border-amber-600 p-3 mb-6 rounded text-xs text-amber-900'>
                <strong>Aviso Legal:</strong> Debes tener al menos 18 años para comprar bebidas alcoholicas. Tu edad se verificará automáticamente según tu fecha de nacimiento.
            </div>

            {error && (
                <div className='bg-red-50 text-red-600 p-3 rounded mb-4 text-sm border border-red-200'>
                    {error}
                </div>
            )}

            <div className='grid grid-cols-1 md:grid-cols-2 gap-4'>
                <div>
                    <label className='block text-xs font-semibold text-stone-700 mb-1'>RUT</label>
                    <input type="text" name="rut" placeholder='12345678-9' required value={formData.rut} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
                </div>

                <div>
                    <label className='block text-xs font-semibold text-stone-700 mb-1'>Nombre</label>
                    <input type="text" name="nombreUsuario" required value={formData.nombreUsuario} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
                </div>

                <div>
                    <label className='block text-xs font-semibold text-stone-700 mb-1'>Apellido Paterno</label>
                    <input type="text" name="apellidoPaterno" required value={formData.apellidoPaterno} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
                </div>

                <div>
                    <label className='block text-xs font-semibold text-stone-700 mb-1'>Apellido Materno</label>
                    <input type="text" name="apellidoMaterno" required value={formData.apellidoMaterno} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
                </div>
            

                <div>
                    <label className='block text-xs font-semibold text-stone-700 mb-1'>Correo Electrónico</label>
                    <input type="email" name="correo" required value={formData.correo} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
                </div>

                <div>
                    <label className='block text-xs font-semibold text-stone-700 mb-1'>Contraseña</label>
                    <input type="password" name="password" required value={formData.password} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
                </div>

                <div>
                    <label className='block text-xs font-semibold text-stone-700 mb-1'>Teléfono</label>
                    <input type="tel" name="telefono" placeholder='+56 9 12345678' value={formData.telefono} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
                </div>

                <div>
                    <label className='block text-xs font-semibold text-stone-700 mb-1'>Fecha Nacimiento</label>
                    <input type="date" name="fechaNacimiento" required value={formData.fechaNacimiento} onChange={handleChange} className='w-full px-3 py-2 border rounded-lg text-sm focus:ring-2 focus:ring-amber-500 outline-none'/>
                </div>
            </div>

            <button type="submit" disabled={isLoading} className='w-full mt-6 bg-amber-800 hover:bg-amber-900 text-white font-semibold py-2.5 rounded-lg transition-colors text-sm disabled:opacity-50'>
                {isLoading ? 'Registrando...' : 'Registrar Cuenta'}
            </button>
        </form>
    );
};