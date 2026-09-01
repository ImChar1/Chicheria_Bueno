export interface Producto {
    productoId: number;
    sku: string;
    productoNom: string;
    descripcion: string;
    imagenProductoUrl: string;
    precioBase: number;
    gradoAlchol: number;
    aplicaIla: boolean;
    stock: number;
    activo: boolean;
    fechaCreacion: string;
}