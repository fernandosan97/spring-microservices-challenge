package com.microservice.movimientos.service;

import com.microservice.movimientos.dto.ApiResponse;
import com.microservice.movimientos.dto.CuentaDTO;

public interface ICuentaService {
    ApiResponse findAll();
    ApiResponse findById(Long id);
    ApiResponse save(CuentaDTO cuentaDTO);
    ApiResponse deleteById(Long id);
}
