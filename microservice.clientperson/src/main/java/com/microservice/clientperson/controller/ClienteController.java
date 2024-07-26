package com.microservice.clientperson.controller;

import com.microservice.clientperson.dto.ApiResponse;
import com.microservice.clientperson.dto.ClienteDTO;
import com.microservice.clientperson.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll() {
        ApiResponse result = clienteService.findAll();
        return new ResponseEntity<ApiResponse>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        ApiResponse result = clienteService.findById(id);
        return new ResponseEntity<ApiResponse>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCliente(@RequestBody ClienteDTO clienteDTO) {
        ApiResponse result = clienteService.save(clienteDTO);
        return new ResponseEntity<ApiResponse>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCliente(@PathVariable Long id) {
        ApiResponse result = clienteService.deleteById(id);
        return new ResponseEntity<ApiResponse>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @GetMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO getClienteById(@PathVariable Long id) {
        ClienteDTO result = clienteService.getClientById(id);
        return result;
    }
}
