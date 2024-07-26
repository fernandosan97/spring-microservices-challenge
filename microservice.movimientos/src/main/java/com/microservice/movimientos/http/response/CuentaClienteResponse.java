package com.microservice.movimientos.http.response;

import com.microservice.movimientos.dto.ClienteDTO;
import com.microservice.movimientos.enums.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaClienteResponse {
    private Long id;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private ClienteDTO cliente;
}
