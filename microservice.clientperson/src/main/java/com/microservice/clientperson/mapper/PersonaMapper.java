package com.microservice.clientperson.mapper;

import com.microservice.clientperson.dto.PersonaDTO;
import com.microservice.clientperson.entities.Persona;

public class PersonaMapper {

    public PersonaDTO toDTO(Persona persona) {
        if (persona == null) {
            return null;
        }

        return PersonaDTO.builder()
                .id(persona.getId())
                .nombre(persona.getNombre())
                .genero(persona.getGenero())
                .edad(persona.getEdad())
                .identificacion(persona.getIdentificacion())
                .direccion(persona.getDireccion())
                .telefono(persona.getTelefono())
                .build();
    }

    public Persona toEntity(PersonaDTO personaDTO) {
        if (personaDTO == null) {
            return null;
        }

        return Persona.builder()
                .id(personaDTO.getId())
                .nombre(personaDTO.getNombre())
                .genero(personaDTO.getGenero())
                .edad(personaDTO.getEdad())
                .identificacion(personaDTO.getIdentificacion())
                .direccion(personaDTO.getDireccion())
                .telefono(personaDTO.getTelefono())
                .build();
    }
}
