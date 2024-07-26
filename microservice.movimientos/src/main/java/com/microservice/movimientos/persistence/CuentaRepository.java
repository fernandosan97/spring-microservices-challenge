package com.microservice.movimientos.persistence;

import com.microservice.movimientos.entities.Cuenta;
import feign.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface CuentaRepository extends CrudRepository<Cuenta, Long> {
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE cuentas c SET c.saldo_inicial = :nuevoSaldo WHERE c.id = :id AND c.saldo_inicial = :saldoAnterior", nativeQuery = true)
    int updateCuentaWithSaldoCheck(@Param("id") Long id,
                                   @Param("nuevoSaldo") Double nuevoSaldo,
                                   @Param("saldoAnterior") Double saldoAnterior);
}
