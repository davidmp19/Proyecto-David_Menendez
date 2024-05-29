INSERT INTO Coche (id, marca, modelo) VALUES ('12345A', 'ford', 'fiesta');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345B', 'porsche', '911 carrera');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345C', 'ferrari', 'testarrosa');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345D', 'vw', 'polo');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345E', 'mercedes', 'e55amg');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345F', 'mercedes', 'clk');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345G', 'ferrari', 'italia 458');
INSERT INTO Coche (id, marca, modelo) VALUES ('12345H', 'lamborghini', 'urus');

INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('A123456789A', 'Neumáticos', 10, 'Ruedas', 10, '12345A');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('B123456789B', 'repuesto2', 15, '27/03/2016', 20, '12345B');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('C123456789C', 'repuesto3', 15, '28/03/2016', 30, '12345C');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('D123456789D', 'repuesto4', 20, '29/03/2016', 40, '12345D');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('E123456789E', 'repuesto5', 25, '30/03/2016', 50, '12345E');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('F123456789F', 'repuesto6', 10, '27/03/2016', 10, '12345F');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('G123456789G', 'repuesto7', 15, '27/03/2016', 20, '12345G');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('H123456789H', 'repuesto8', 15, '28/03/2016', 30, '12345H');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('I123456789I', 'repuesto9', 20, '29/03/2016', 40, '12345A');
INSERT INTO Repuesto (id, nombre, peso, descripcion, unidades, fk_coche) VALUES ('J123456789J', 'repuesto10', 25, '30/03/2016', 50, '12345B');

INSERT INTO Proveedor (dni, nombre, direccion) VALUES ('11111111A', 'proveedor1', 'Oviedo');
INSERT INTO Proveedor (dni, nombre, direccion) VALUES ('11111111B', 'proveedor2', 'Oviedo');
INSERT INTO Proveedor (dni, nombre, direccion) VALUES ('11111111C', 'proveedor3', 'Oviedo');
INSERT INTO Proveedor (dni, nombre, direccion) VALUES ('11111111D', 'proveedor4', 'Oviedo');

INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111A', 'A123456789A');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111A', 'B123456789B');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111B', 'C123456789C');
INSERT INTO Suministra (dniProveedor, idRepuesto) VALUES ('11111111B', 'D123456789D');

INSERT INTO Usuario (username, password) VALUES ('david', '$2a$12$MAGpqZXWe42TeZTqpW8K9.vSYAwan1VZeTZ8F7VTBg5JStWJ7Y5Ea');
INSERT INTO Usuario (username, password) VALUES ('alejandro', '$2a$12$HKDzfhaHdqNmlWW0OAK0x.BzfrLFJ1mejJDe53sPQJe9pDAyAKmNa');
INSERT INTO Usuario (username, password) VALUES ('usuario1', '$2a$12$F3xcrUIsHRJVS3jGix.L8emvf4bSlcDgrK19zFLI7620gczlYAR/2');

