

INSERT INTO Coche (marca, modelo) VALUES ('ford', 'fiesta');
INSERT INTO Coche (marca, modelo) VALUES ('porsche', '911 carrera');
INSERT INTO Coche (marca, modelo) VALUES ('ferrari', 'testarrosa');
INSERT INTO Coche (marca, modelo) VALUES ('vw', 'polo');
INSERT INTO Coche (marca, modelo) VALUES ('mercedes', 'e55amg');
INSERT INTO Coche (marca, modelo) VALUES ('mercedes', 'clk');
INSERT INTO Coche (marca, modelo) VALUES ('ferrari', 'italia 458');
INSERT INTO Coche (marca, modelo) VALUES ('lamborghini', 'urus');

INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto1', 10, '27/03/2016',10,'1');
INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto2', 15, '27/03/2016',20,'2');
INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto3', 15, '28/03/2016',30,'3');
INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto4', 20, '29/03/2016',40,'4');
INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto5', 25, '30/03/2016',50,'5');
INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto6', 10, '27/03/2016',10,'6');
INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto7', 15, '27/03/2016',20,'7');
INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto8', 15, '28/03/2016',30,'8');
INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto9', 20, '29/03/2016',40,'1');
INSERT INTO Repuesto (nombre, peso, fecha,unidades,FK_COCHE) VALUES ('repuesto10', 25, '30/03/2016',50,'2');



insert into Proveedor(dni,nombre,direccion) values ('11111111A','proveedor1','Oviedo');
insert into Proveedor(dni,nombre,direccion) values ('11111111B','proveedor2','Oviedo');
insert into Proveedor(dni,nombre,direccion) values ('11111111C','proveedor3','Oviedo');
insert into Proveedor(dni,nombre,direccion) values ('11111111D','proveedor4','Oviedo');
 
insert into suministra(dniProveedor,idRepuesto)values('11111111A',1);
insert into suministra(dniProveedor,idRepuesto)values('11111111A',2);
insert into suministra(dniProveedor,idRepuesto)values('11111111B',3);
insert into suministra(dniProveedor,idRepuesto)values('11111111B',4);

insert into Usuario(usuario, password) values ('david', '$2a$12$MAGpqZXWe42TeZTqpW8K9.vSYAwan1VZeTZ8F7VTBg5JStWJ7Y5Ea');
insert into Usuario(usuario, password) values ('alejandro', '$2a$12$HKDzfhaHdqNmlWW0OAK0x.BzfrLFJ1mejJDe53sPQJe9pDAyAKmNa');

