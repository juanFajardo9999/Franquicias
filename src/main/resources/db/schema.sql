-- Creación de la tabla Franchise
CREATE TABLE IF NOT EXISTS franchise (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Creación de la tabla Branch
CREATE TABLE IF NOT EXISTS branch (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    franchise_id BIGINT,
    CONSTRAINT fk_franchise
        FOREIGN KEY (franchise_id)
        REFERENCES franchise(id)
        ON DELETE CASCADE
);

-- Creación de la tabla Product
CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    branch_id BIGINT,
    CONSTRAINT fk_branch
        FOREIGN KEY (branch_id)
        REFERENCES branch(id)
        ON DELETE CASCADE
);

-- Insertar datos iniciales en la tabla Franchise
INSERT INTO franchise (name) VALUES
('Franquicia A'),
('Franquicia B'),
('Franquicia C');

-- Insertar datos iniciales en la tabla Branch
INSERT INTO branch (name, franchise_id) VALUES
('Sucursal 1', 1),
('Sucursal 2', 1),
('Sucursal 3', 2),
('Sucursal 4', 3);

-- Insertar datos iniciales en la tabla Product
INSERT INTO product (name, stock, branch_id) VALUES
('Producto 1', 50, 1),
('Producto 2', 100, 1),
('Producto 3', 75, 2),
('Producto 4', 20, 3),
('Producto 5', 200, 4);
