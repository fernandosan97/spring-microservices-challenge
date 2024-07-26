package com.microservice.clientperson.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String contrasena;
    private Boolean estado;
    private PersonaDTO persona;
}
