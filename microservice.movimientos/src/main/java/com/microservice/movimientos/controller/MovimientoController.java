package com.microservice.movimientos.controller;

import com.microservice.movimientos.dto.*;
import com.microservice.movimientos.service.ICuentaService;
import com.microservice.movimientos.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    @Autowired
    private IMovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCliente(@RequestBody HacerMovimientoDTO movimientoDTO) {
        ApiResponse result = movimientoService.save(movimientoDTO);
        return new ResponseEntity<ApiResponse>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @GetMapping("/reportes")
    public ResponseEntity<List<ReporteMovimientosDTO>> obtenerReporte(
            @RequestParam(value = "fecha", required = false) String fecha,
            @RequestParam(value = "usuarioId", required = false) Long usuarioId) {
        List<ReporteMovimientosDTO> reporte = movimientoService.generarReporte(fecha, usuarioId);
        return ResponseEntity.ok(reporte);
    }
}
