package com.microservice.movimientos.controller;

import com.microservice.movimientos.dto.ApiResponse;
import com.microservice.movimientos.dto.CuentaDTO;
import com.microservice.movimientos.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private ICuentaService cuentaService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll() {
        ApiResponse result = cuentaService.findAll();
        return new ResponseEntity<ApiResponse>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getClienteById(@PathVariable Long id) {
        ApiResponse result = cuentaService.findById(id);
        return new ResponseEntity<ApiResponse>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCliente(@RequestBody CuentaDTO cuentaDTO) {
        ApiResponse result = cuentaService.save(cuentaDTO);
        return new ResponseEntity<ApiResponse>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCliente(@PathVariable Long id) {
        ApiResponse result = cuentaService.deleteById(id);
        return new ResponseEntity<ApiResponse>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }
}
