package com.microservice.movimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HacerMovimientoDTO {
    private Long id;
    private String numeroCuenta;
    private Double valorMovimiento;
}
