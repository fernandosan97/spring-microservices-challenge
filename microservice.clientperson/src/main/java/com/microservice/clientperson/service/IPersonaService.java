package com.microservice.clientperson.service;

import com.microservice.clientperson.entities.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    List<Persona> findAll();
    Optional<Persona> findById(Long id);
    Persona save(Persona persona);
    boolean deleteById(Long id);
}
