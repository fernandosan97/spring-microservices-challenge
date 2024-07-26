package com.microservice.movimientos.mapper;

import com.microservice.movimientos.dto.MovimientoDTO;
import com.microservice.movimientos.entities.Movimiento;

public class MovimientoMapper {

    public MovimientoDTO toDTO(Movimiento movimiento) {
        if (movimiento == null) {
            return null;
        }

        return MovimientoDTO.builder()
                .id(movimiento.getId())
                .fecha(movimiento.getFecha())
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .valor(movimiento.getValor())
                .saldo(movimiento.getSaldo())
                .cuenta(movimiento.getCuenta())
                .build();
    }

    public Movimiento toEntity(MovimientoDTO movimientoDTO) {
        if (movimientoDTO == null) {
            return null;
        }

        return Movimiento.builder()
                .id(movimientoDTO.getId())
                .fecha(movimientoDTO.getFecha())
                .tipoMovimiento(movimientoDTO.getTipoMovimiento())
                .valor(movimientoDTO.getValor())
                .saldo(movimientoDTO.getSaldo())
                .cuenta(movimientoDTO.getCuenta())
                .build();
    }
}
