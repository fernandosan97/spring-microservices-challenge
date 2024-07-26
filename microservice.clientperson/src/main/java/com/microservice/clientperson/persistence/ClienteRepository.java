package com.microservice.clientperson.persistence;

import com.microservice.clientperson.dto.ClienteDTO;
import com.microservice.clientperson.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
