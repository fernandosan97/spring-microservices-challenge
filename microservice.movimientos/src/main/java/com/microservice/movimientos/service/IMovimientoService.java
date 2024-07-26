package com.microservice.movimientos.service;

import com.microservice.movimientos.dto.ApiResponse;
import com.microservice.movimientos.dto.HacerMovimientoDTO;
import com.microservice.movimientos.dto.ReporteDTO;
import com.microservice.movimientos.dto.ReporteMovimientosDTO;

import java.time.LocalDate;
import java.util.List;

public interface IMovimientoService {
    ApiResponse save(HacerMovimientoDTO movimientoDTO);
    List<ReporteMovimientosDTO> generarReporte(String fecha, Long usuarioId);
}
