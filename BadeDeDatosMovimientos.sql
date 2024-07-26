CREATE DATABASE `movimientosDb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

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