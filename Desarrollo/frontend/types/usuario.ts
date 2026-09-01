export interface Usuario {
    id?: number;
    rut: string;
    nombreUsuario: string;
    apellidoPaterno: string;
    apellidoMaterno: string;
    correo: string;
    password: string;
    telefono?: string;
    fechaNacimiento: string;
    mayorEdad?: boolean;
    rolId?: number;
    activo?: boolean;
    fechaCreacion?: string;
}

export interface LoginRequest {
    correo: string;
    password: string;
}