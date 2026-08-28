CREATE TABLE IF NOT EXIST roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombreRol VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXIST usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) NOT NULL UNIQUE,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, --Guardara Hash Cifrado
    telefono VARCHAR(15),
    fechaNacimiento DATE NOT NULL,
    mayorEdad BOOLEAN NOT NULL DEFAULT FALSE,
    rolId BIGINT NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

--Inyeccion de roles para el negocio
INSERT IGNORE INTO roles (id, nombreRol) VALUES (1, 'ROLE_GERENTE'),
                                            (2, 'ROLE_EMPLEADO'),
                                            (3, 'ROLE_CLIENTE');