CREATE DATABASE IF NOT EXISTS `clientDb`;
CREATE DATABASE IF NOT EXISTS `movimientosDb`;

USE clientDb;

CREATE TABLE `personas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` int NOT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `identificacion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `clientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `persona_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6ek8bprfp08rlc1s7xt60itfq` (`persona_id`),
  CONSTRAINT `FKqqdwv2x70kik01nxcgkxvh8ue` FOREIGN KEY (`persona_id`) REFERENCES `personas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

USE movimientosDb;

CREATE TABLE `cuentas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado` bit(1) DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo_inicial` double NOT NULL,
  `tipo_cuenta` enum('AHORRO','CORRIENTE') DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK7h7mqvcau3mcl0mbrkdrt7fnh` (`numero_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `movimientos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `tipo_movimiento` enum('DEPOSITO','RETIRO') DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `cuenta_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4moe88hxuohcysas5h70mdc09` (`cuenta_id`),
  CONSTRAINT `FK4moe88hxuohcysas5h70mdc09` FOREIGN KEY (`cuenta_id`) REFERENCES `cuentas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO clientDb.personas
(id, direccion, edad, genero, identificacion, nombre, telefono)
VALUES(3, 'Otavalo sn y principal', 30, 'Masculino', '123456789', 'Jose Lezama', '098254785');
INSERT INTO clientDb.personas
(id, direccion, edad, genero, identificacion, nombre, telefono)
VALUES(4, 'Amazonas y NNUU', 30, 'Femenino', '123456789', 'Marianela Montalvo', '097548965');
INSERT INTO clientDb.personas
(id, direccion, edad, genero, identificacion, nombre, telefono)
VALUES(5, '13 junio y Equinoccial ', 30, 'Masculino', '123456789', 'Juan Osorio', '098874587');


INSERT INTO clientDb.clientes
(id, contrasena, estado, persona_id)
VALUES(3, '123', 1, 3);
INSERT INTO clientDb.clientes
(id, contrasena, estado, persona_id)
VALUES(4, '5678', 1, 4);
INSERT INTO clientDb.clientes
(id, contrasena, estado, persona_id)
VALUES(5, '1245', 1, 5);


INSERT INTO movimientosDb.cuentas
(id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, usuario_id)
VALUES(6, 1, '478758', 1425.0, 'AHORRO', 3);
INSERT INTO movimientosDb.cuentas
(id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, usuario_id)
VALUES(7, 1, '225487', 700.0, 'CORRIENTE', 4);
INSERT INTO movimientosDb.cuentas
(id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, usuario_id)
VALUES(8, 1, '495878', 150.0, 'AHORRO', 5);
INSERT INTO movimientosDb.cuentas
(id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, usuario_id)
VALUES(9, 1, '496825', 0.0, 'AHORRO', 4);
INSERT INTO movimientosDb.cuentas
(id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, usuario_id)
VALUES(10, 1, '585545', 1000.0, 'CORRIENTE', 3);

INSERT INTO movimientosDb.movimientos
(id, fecha, saldo, tipo_movimiento, valor, cuenta_id)
VALUES(6, '2024-07-25 21:54:57.361245', 2000.0, 'RETIRO', -575.0, 6);
INSERT INTO movimientosDb.movimientos
(id, fecha, saldo, tipo_movimiento, valor, cuenta_id)
VALUES(7, '2024-07-25 21:55:16.410885', 100.0, 'DEPOSITO', 600.0, 7);
INSERT INTO movimientosDb.movimientos
(id, fecha, saldo, tipo_movimiento, valor, cuenta_id)
VALUES(8, '2024-07-25 21:55:53.269100', 0.0, 'DEPOSITO', 150.0, 8);
INSERT INTO movimientosDb.movimientos
(id, fecha, saldo, tipo_movimiento, valor, cuenta_id)
VALUES(9, '2024-07-25 21:56:13.619559', 540.0, 'RETIRO', -540.0, 9);