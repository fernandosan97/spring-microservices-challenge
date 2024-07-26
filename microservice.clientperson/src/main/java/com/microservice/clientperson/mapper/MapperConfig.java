package com.microservice.clientperson.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ClienteMapper clienteMapper() {
        return new ClienteMapper();
    }

    @Bean
    public PersonaMapper personaMapper() {
        return new PersonaMapper();
    }
}
