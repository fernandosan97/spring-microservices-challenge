package com.microservice.clientperson.service;

import com.microservice.clientperson.dto.ApiResponse;
import com.microservice.clientperson.dto.ClienteDTO;
import com.microservice.clientperson.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    ApiResponse findAll();
    ApiResponse findById(Long id);
    ApiResponse save(ClienteDTO clienteDTO);
    ApiResponse deleteById(Long id);
    ClienteDTO getClientById(Long id);
}
