package com.microservice.movimientos.dto;

import com.microservice.movimientos.entities.Cuenta;
import com.microservice.movimientos.enums.TipoMovimiento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDTO {
    private Long id;
    private LocalDateTime fecha;
    private TipoMovimiento tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Cuenta cuenta;
}
