package com.microservice.movimientos.dto;

import com.microservice.movimientos.enums.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteMovimientosDTO {
    private Date fecha;  // Formato YYYY-MM-DD
    private String cliente;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Double valorMovimiento;
    private Double saldoDisponible;
}
