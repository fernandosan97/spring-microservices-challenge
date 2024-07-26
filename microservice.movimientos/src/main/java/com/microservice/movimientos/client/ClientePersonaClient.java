package com.microservice.movimientos.client;

import com.microservice.movimientos.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-clientperson", url = "localhost:8080/api/clientes")
public interface ClientePersonaClient {
    @GetMapping("/client/{id}")
    ClienteDTO getClienteById(@PathVariable Long id);
}
