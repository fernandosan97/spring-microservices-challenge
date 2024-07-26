package com.microservice.movimientos.mapper;

import com.microservice.movimientos.entities.Movimiento;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public CuentaMapper cuentaMapper() {
        return new CuentaMapper();
    }

    @Bean
    public MovimientoMapper movimientoMapper() {
        return new MovimientoMapper();
    }
}
