package com.microservice.movimientos.persistence;

import com.microservice.movimientos.dto.ReporteDTO;
import com.microservice.movimientos.entities.Cuenta;
import com.microservice.movimientos.entities.Movimiento;
import feign.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {
    @Query(
            "SELECT " +
            "DATE(m.fecha) AS fecha, " +
            "c.usuarioId AS usuarioId, " +
            "c.numeroCuenta AS numeroCuenta, " +
            "c.tipoCuenta AS tipoCuenta, " +
            "m.saldo AS saldoInicial, " +
            "c.estado AS estado, " +
            "m.valor AS valor, " +
            "CASE " +
            "   WHEN m.valor < 0 THEN m.saldo + m.valor " +
            "   ELSE m.saldo + m.valor " +
            "END AS saldoDisponible " +
            "FROM Movimiento m JOIN m.cuenta c " +
            "WHERE DATE(m.fecha) = :fecha " +
            "OR c.usuarioId = :usuarioId"
    )
    List<Object[]> findReportesByFechaAndUsuario(
            @Param("fecha") LocalDate fecha,
            @Param("usuarioId") Long usuarioId
    );

    @Query(
            "SELECT " +
            "DATE(m.fecha) AS fecha, " +
            "c.usuarioId AS usuarioId, " +
            "c.numeroCuenta AS numeroCuenta, " +
            "c.tipoCuenta AS tipoCuenta, " +
            "m.saldo AS saldoInicial, " +
            "c.estado AS estado, " +
            "m.valor AS valor, " +
            "CASE " +
            "   WHEN m.valor < 0 THEN m.saldo + m.valor " +
            "   ELSE m.saldo + m.valor " +
            "END AS saldoDisponible " +
            "FROM Movimiento m JOIN m.cuenta c"
    )
    List<Object[]> findReportes();

    @Query(
            "SELECT " +
                    "DATE(m.fecha) AS fecha, " +
                    "c.usuarioId AS usuarioId, " +
                    "c.numeroCuenta AS numeroCuenta, " +
                    "c.tipoCuenta AS tipoCuenta, " +
                    "m.saldo AS saldoInicial, " +
                    "c.estado AS estado, " +
                    "m.valor AS valor, " +
                    "CASE " +
                    "   WHEN m.valor < 0 THEN m.saldo + m.valor " +
                    "   ELSE m.saldo + m.valor " +
                    "END AS saldoDisponible " +
                    "FROM Movimiento m JOIN m.cuenta c " +
                    "WHERE c.usuarioId = :usuarioId"
    )
    List<Object[]> findReportesByUsuario(@Param("usuarioId") Long usuarioId);

    @Query(
            "SELECT " +
                    "DATE(m.fecha) AS fecha, " +
                    "c.usuarioId AS usuarioId, " +
                    "c.numeroCuenta AS numeroCuenta, " +
                    "c.tipoCuenta AS tipoCuenta, " +
                    "m.saldo AS saldoInicial, " +
                    "c.estado AS estado, " +
                    "m.valor AS valor, " +
                    "CASE " +
                    "   WHEN m.valor < 0 THEN m.saldo + m.valor " +
                    "   ELSE m.saldo + m.valor " +
                    "END AS saldoDisponible " +
                    "FROM Movimiento m JOIN m.cuenta c " +
                    "WHERE DATE(m.fecha) = :fecha"
    )
    List<Object[]> findReportesByFecha(@Param("fecha") LocalDate fecha);
}
