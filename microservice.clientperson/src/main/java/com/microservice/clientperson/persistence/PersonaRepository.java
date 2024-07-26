package com.microservice.clientperson.persistence;

import com.microservice.clientperson.entities.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends CrudRepository<Persona, Long> { }
