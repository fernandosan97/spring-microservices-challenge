CREATE DATABASE `clientDb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- clientDb.personas definition

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


-- clientDb.clientes definition

CREATE TABLE `clientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `persona_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6ek8bprfp08rlc1s7xt60itfq` (`persona_id`),
  CONSTRAINT `FKqqdwv2x70kik01nxcgkxvh8ue` FOREIGN KEY (`persona_id`) REFERENCES `personas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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