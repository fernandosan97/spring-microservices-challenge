package com.microservice.clientperson.service;

import com.microservice.clientperson.entities.Persona;
import com.microservice.clientperson.persistence.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements IPersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> findAll() {
        return (List<Persona>) this.personaRepository.findAll();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return this.personaRepository.findById(id);
    }

    @Override
    public Persona save(Persona persona) {
        return this.personaRepository.save(persona);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean result = false;
        if(this.personaRepository.existsById(id)) {
            this.personaRepository.deleteById(id);
            result = true;
        }

        return result;
    }
}
