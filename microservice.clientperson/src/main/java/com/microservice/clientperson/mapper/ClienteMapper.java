package com.microservice.clientperson.mapper;

import com.microservice.clientperson.dto.ClienteDTO;
import com.microservice.clientperson.dto.PersonaDTO;
import com.microservice.clientperson.entities.Cliente;
import com.microservice.clientperson.entities.Persona;

public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        PersonaDTO personaDTO = toPersonaDTO(cliente.getPersona());

        return ClienteDTO.builder()
                .id(cliente.getId())
                .contrasena(cliente.getContrasena())
                .estado(cliente.getEstado())
                .persona(personaDTO)
                .build();
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            return null;
        }

        Persona persona = toPersonaEntity(clienteDTO.getPersona());

        return Cliente.builder()
                .id(clienteDTO.getId())
                .contrasena(clienteDTO.getContrasena())
                .estado(clienteDTO.getEstado())
                .persona(persona)
                .build();
    }

    private PersonaDTO toPersonaDTO(Persona persona) {
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

    private Persona toPersonaEntity(PersonaDTO personaDTO) {
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
