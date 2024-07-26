package com.microservice.movimientos.service;

import com.microservice.movimientos.client.ClientePersonaClient;
import com.microservice.movimientos.dto.ApiResponse;
import com.microservice.movimientos.dto.ClienteDTO;
import com.microservice.movimientos.dto.CuentaDTO;
import com.microservice.movimientos.entities.Cuenta;
import com.microservice.movimientos.enums.TipoCuenta;
import com.microservice.movimientos.http.response.CuentaClienteResponse;
import com.microservice.movimientos.mapper.CuentaMapper;
import com.microservice.movimientos.persistence.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CuentaServiceImpl implements ICuentaService{
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClientePersonaClient clientePersonaClient;

    @Autowired
    private CuentaMapper cuentaMapper;
    @Override
    public ApiResponse findAll() {
        List<CuentaClienteResponse> cuentasClienteResponseList = StreamSupport
                .stream(this.cuentaRepository.findAll().spliterator(), false)
                .map(cuenta -> {
                    CuentaDTO cuentaDTO = this.cuentaMapper.toDTO(cuenta);
                    ClienteDTO clienteDTO = this.clientePersonaClient.getClienteById(cuenta.getUsuarioId());

                    return CuentaClienteResponse.builder()
                            .id(cuentaDTO.getId())
                            .numeroCuenta(cuentaDTO.getNumeroCuenta())
                            .tipoCuenta(cuentaDTO.getTipoCuenta())
                            .saldoInicial(cuentaDTO.getSaldoInicial())
                            .estado(cuentaDTO.getEstado())
                            .cliente(clienteDTO)
                            .build();
                })
                .collect(Collectors.toList());

        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Listado de Cuentas")
                .data(cuentasClienteResponseList)
                .build();
    }

    @Override
    public ApiResponse findById(Long id) {
        Optional<Cuenta> cuenta = this.cuentaRepository.findById(id);

        if (cuenta.isEmpty()) {
            return ApiResponse.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Cuenta no encontrado")
                    .data(null)
                    .build();
        }

        ClienteDTO clienteDTO = this.clientePersonaClient.getClienteById(cuenta.get().getUsuarioId());
        CuentaDTO cuentaDTO = this.cuentaMapper.toDTO(cuenta.get());

        CuentaClienteResponse cuentaClienteResponse = CuentaClienteResponse
                .builder()
                .id(cuentaDTO.getId())
                .numeroCuenta(cuentaDTO.getNumeroCuenta())
                .tipoCuenta(cuentaDTO.getTipoCuenta())
                .saldoInicial(cuentaDTO.getSaldoInicial())
                .estado(cuentaDTO.getEstado())
                .cliente(clienteDTO)
                .build();

        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Cuenta encontrada")
                .data(cuentaClienteResponse)
                .build();
    }

    @Override
    public ApiResponse save(CuentaDTO cuentaDTO) {
        ClienteDTO clienteDTO = this.clientePersonaClient.getClienteById(cuentaDTO.getUsuarioId());

        if(clienteDTO == null) {
            return ApiResponse.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("El usuario no existe con ID: " + cuentaDTO.getUsuarioId() + " no existe!")
                    .data(null)
                    .build();
        }

        try {
            Cuenta cuenta = this.cuentaMapper.toEntity(cuentaDTO);
            this.cuentaRepository.save(cuenta);
            return this.findById(cuenta.getId());
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
            if(!this.cuentaRepository.existsById(id)) {
                return ApiResponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("La cuenta que deseas eliminar no existe")
                        .data(false)
                        .build();
            }

            this.cuentaRepository.deleteById(id);
            return ApiResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Cuenta eliminada satisfactoriamente")
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
}
