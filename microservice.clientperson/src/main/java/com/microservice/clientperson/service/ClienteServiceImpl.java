package com.microservice.clientperson.service;

import com.microservice.clientperson.dto.ApiResponse;
import com.microservice.clientperson.dto.ClienteDTO;
import com.microservice.clientperson.entities.Cliente;
import com.microservice.clientperson.entities.Persona;
import com.microservice.clientperson.mapper.ClienteMapper;
import com.microservice.clientperson.mapper.PersonaMapper;
import com.microservice.clientperson.persistence.ClienteRepository;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public ApiResponse findAll() {
        List<ClienteDTO> clientesDTOList = StreamSupport
                .stream(this.clienteRepository.findAll().spliterator(), false)
                .map(this.clienteMapper::toDTO)
                .toList();

        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Listado de Clientes")
                .data(clientesDTOList)
                .build();
    }

    @Override
    public ApiResponse findById(Long id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return ApiResponse.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Cliente no encontrado")
                    .data(null)
                    .build();
        }

        ClienteDTO clienteDTO = this.clienteMapper.toDTO(cliente.get());
        return ApiResponse.builder()
            .statusCode(HttpStatus.OK.value())
            .message("Cliente encontrado")
            .data(clienteDTO)
            .build();
    }

    @Override
    public ApiResponse save(ClienteDTO clienteDTO) {
        try {
            Persona persona = this.personaMapper.toEntity(clienteDTO.getPersona());
            Persona savedPersona = this.personaService.save(persona);

            Cliente cliente = this.clienteMapper.toEntity(clienteDTO);
            cliente.setPersona(savedPersona);
            Cliente savedCliente = this.clienteRepository.save(cliente);

            ClienteDTO clienteToDTO = this.clienteMapper.toDTO(savedCliente);
            return ApiResponse.builder()
                    .statusCode(HttpStatus.CREATED.value())
                    .message("Cliente guardado satisfactoriamente")
                    .data(clienteToDTO)
                    .build();
        } catch (Exception ex) {
            return ApiResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .data(null)
                .build();
        }
    }

    @Override
    public ApiResponse deleteById(Long id) {
       try {
           if(!this.clienteRepository.existsById(id)) {
               return ApiResponse.builder()
                       .statusCode(HttpStatus.NOT_FOUND.value())
                       .message("El cliente que deseas eliminar no existe")
                       .data(false)
                       .build();
           }

           this.clienteRepository.deleteById(id);
           return ApiResponse.builder()
                   .statusCode(HttpStatus.OK.value())
                   .message("Cliente eliminado satisfactoriamente")
                   .data(true)
                   .build();
       } catch (Exception ex) {
           return ApiResponse.builder()
                   .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                   .message(ex.getMessage())
                   .data(null)
                   .build();
       }
    }

    @Override
    public ClienteDTO getClientById(Long id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return cliente.map(value -> this.clienteMapper.toDTO(value)).orElse(null);
    }
}
