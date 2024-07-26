package com.microservice.movimientos.mapper;

import com.microservice.movimientos.dto.CuentaDTO;
import com.microservice.movimientos.entities.Cuenta;

public class CuentaMapper {

    public CuentaDTO toDTO(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }

        return CuentaDTO.builder()
                .id(cuenta.getId())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .tipoCuenta(cuenta.getTipoCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(cuenta.getEstado())
                .usuarioId(cuenta.getUsuarioId())
                .build();
    }

    public Cuenta toEntity(CuentaDTO cuentaDTO) {
        if (cuentaDTO == null) {
            return null;
        }

        return Cuenta.builder()
                .id(cuentaDTO.getId())
                .numeroCuenta(cuentaDTO.getNumeroCuenta())
                .tipoCuenta(cuentaDTO.getTipoCuenta())
                .saldoInicial(cuentaDTO.getSaldoInicial())
                .estado(cuentaDTO.getEstado())
                .usuarioId(cuentaDTO.getUsuarioId())
                .build();
    }
}
