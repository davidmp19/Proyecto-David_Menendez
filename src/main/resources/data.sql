INSERT INTO Coche (id, marca, modelo) VALUES ('12345A', 'Ford', 'Fiesta');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345B', 'Porsche', '911 Carrera');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345C', 'Ferrari', 'Testarossa');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345D', 'Volkswagen', 'Polo');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345E', 'Mercedes', 'E55 AMG');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345F', 'Mercedes', 'CLK');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345G', 'Ferrari', 'Italia 458');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345H', 'Lamborghini', 'Urus');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345I', 'audi', 'a4');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345J', 'bmw', 'serie 3');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345K', 'honda', 'civic');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345L', 'toyota', 'corolla');

-- Inserts para Repuesto
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('A123456789A', 'Neumáticos', 10, 'Juego de neumáticos para automóvil', 10, '12345A');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('B123456789B', 'Frenos', 15, 'Sistema de frenado completo', 20, '12345B');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('C123456789C', 'Motor', 200, 'Motor de combustión interna de alto rendimiento', 1, '12345C');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('D123456789D', 'Batería', 30, 'Batería de alta capacidad', 2, '12345D');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('E123456789E', 'Faros', 12, 'Faros delanteros LED', 2, '12345E');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('F123456789F', 'Llantas', 10, 'Juego de llantas de aleación', 4, '12345F');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('G123456789G', 'Suspensión', 18, 'Suspensión deportiva ajustable', 2, '12345G');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('H123456789H', 'Escapes', 22, 'Sistema de escape deportivo', 2, '12345H');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('K123456789K', 'Motor', 150, 'Motor de combustión interna', 1, '12345I');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('L123456789L', 'Batería', 30, 'Batería de 12V', 1, '12345J');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('M123456789M', 'Faros delanteros', 10, 'Juego de faros delanteros LED', 2, '12345K');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('N123456789N', 'Filtro de aire', 5, 'Filtro de aire de alto rendimiento', 3, '12345L');
-- Inserts para Proveedor
INSERT INTO Proveedor (dni, nombre, direccion) VALUES ('11111111A', 'Proveedor de Neumáticos', 'Oviedo');
INSERT INTO Proveedor (dni, nombre, direccion) VALUES ('11111111B', 'Proveedor de Frenos', 'Oviedo');
INSERT INTO Proveedor (dni, nombre, direccion) VALUES ('11111111C', 'Proveedor de Motores', 'Oviedo');
INSERT INTO Proveedor (dni, nombre, direccion) VALUES ('11111111D', 'Proveedor de Baterías', 'Oviedo');
INSERT INTO Proveedor (dni, nombre, direccion) VALUES ('11111111E', 'Recambios Gijón', 'Calle Mayor, Gijón');


-- Inserts para Suministra
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111A', 'A123456789A');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111B', 'B123456789B');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111C', 'C123456789C');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111D', 'D123456789D');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111A', 'E123456789E');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111A', 'F123456789F');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111A', 'G123456789G');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111A', 'H123456789H');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111E', 'K123456789K');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111E', 'L123456789L');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111E', 'M123456789M');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111E', 'N123456789N');

INSERT INTO Usuario (username, password) VALUES ('david', '$2a$12$MAGpqZXWe42TeZTqpW8K9.vSYAwan1VZeTZ8F7VTBg5JStWJ7Y5Ea');
INSERT INTO Usuario (username, password) VALUES ('alejandro', '$2a$12$HKDzfhaHdqNmlWW0OAK0x.BzfrLFJ1mejJDe53sPQJe9pDAyAKmNa');
INSERT INTO Usuario (username, password) VALUES ('usuario1', '$2a$12$F3xcrUIsHRJVS3jGix.L8emvf4bSlcDgrK19zFLI7620gczlYAR/2');
