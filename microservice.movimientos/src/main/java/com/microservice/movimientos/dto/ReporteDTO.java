package com.microservice.movimientos.dto;

import com.microservice.movimientos.enums.TipoCuenta;
import com.microservice.movimientos.enums.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {
    private Date fecha;  // Formato YYYY-MM-DD
    private Long clienteId;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Double valorMovimiento;
    private Double saldoDisponible;
}
