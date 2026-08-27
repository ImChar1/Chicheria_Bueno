CREATE TABLE IF NOT EXIST productos (
    productoId BIGINT AUTO_INCREMENT PRIMARY KEY,
    sku VARCHAR(50) NOT NULL UNIQUE,
    productoNom VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100),
    imagenProductoUrl VARCHAR(255),

    --Variables para los precios e impuestos
    precioBase DECIMAL(12, 2) NOT NULL,
    gradoAlchol DECIMAL(4, 1) NOT NULL,
    aplicaIla BOOLEAN NOT NULL DEFAULT TRUE,

    --Variables de inventario y su estado
    stock INT NOT NULL DEFAULT 0,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);