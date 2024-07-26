package com.microservice.movimientos.service;

import com.microservice.movimientos.client.ClientePersonaClient;
import com.microservice.movimientos.dto.*;
import com.microservice.movimientos.entities.Cuenta;
import com.microservice.movimientos.entities.Movimiento;
import com.microservice.movimientos.enums.TipoCuenta;
import com.microservice.movimientos.enums.TipoMovimiento;
import com.microservice.movimientos.http.response.CuentaClienteResponse;
import com.microservice.movimientos.mapper.CuentaMapper;
import com.microservice.movimientos.mapper.MovimientoMapper;
import com.microservice.movimientos.persistence.CuentaRepository;
import com.microservice.movimientos.persistence.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovimientoServiceImpl implements IMovimientoService{
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Autowired
    private ClientePersonaClient clientePersonaClient;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Override
    @Transactional
    public ApiResponse save(HacerMovimientoDTO movimientoDTO) {
        Optional<Cuenta> cuentaOpt = this.cuentaRepository.findByNumeroCuenta(movimientoDTO.getNumeroCuenta());

        if(cuentaOpt.isEmpty()) {
            return ApiResponse.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Numero de cuenta no encontrada")
                    .data(null)
                    .build();
        }

        Cuenta cuenta = cuentaOpt.get();
        Double saldoAnterior = cuenta.getSaldoInicial();
        Double valorMovimiento = movimientoDTO.getValorMovimiento();
        Double nuevoSaldo = saldoAnterior + valorMovimiento;

        TipoMovimiento tipoMovimiento = valorMovimiento < 0
                ? TipoMovimiento.RETIRO
                : TipoMovimiento.DEPOSITO;

        if (tipoMovimiento.equals(TipoMovimiento.RETIRO) && saldoAnterior < Math.abs(valorMovimiento)) {
            return ApiResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Saldo no disponible")
                    .data(null)
                    .build();
        }

        // Actualizar cuenta con verificación del saldo anterior
        int updatedRows = this.cuentaRepository.updateCuentaWithSaldoCheck(
                cuenta.getId(),
                nuevoSaldo,
                saldoAnterior
        );

        if (updatedRows == 0) {
            return ApiResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Por favor, inténtelo de nuevo.")
                    .data(null)
                    .build();
        }


        Movimiento movimiento = Movimiento.builder()
                .fecha(LocalDateTime.now())
                .tipoMovimiento(tipoMovimiento)
                .valor(valorMovimiento)
                .saldo(saldoAnterior)
                .cuenta(cuenta)
                .build();

        Optional<Cuenta> cuentaWithNewInfo = this.cuentaRepository.findByNumeroCuenta(movimientoDTO.getNumeroCuenta());
        movimiento.setCuenta(cuentaWithNewInfo.get());
        Movimiento movimientoGuardado = this.movimientoRepository.save(movimiento);
        MovimientoDTO movimientoToDTO = this.movimientoMapper.toDTO(movimientoGuardado);

        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Movimiento realizado satisfactoriamente")
                .data(movimientoToDTO)
                .build();
    }

    private List<ReporteDTO> getCuentaMovimientos(String fecha, Long usuarioId) {
        List<Object[]> results;

        if (fecha == null && usuarioId != null) {
            results = this.movimientoRepository.findReportesByUsuario(usuarioId);
        } else if (fecha != null && usuarioId == null) {
            LocalDate fechaLocalDate = LocalDate.parse(fecha);
            results = this.movimientoRepository.findReportesByFecha(fechaLocalDate);
        } else if (fecha == null) {
            results = this.movimientoRepository.findReportes();
        } else {
            LocalDate fechaLocalDate = LocalDate.parse(fecha);
            results = this.movimientoRepository.findReportesByFechaAndUsuario(fechaLocalDate, usuarioId);
        }

        return results.stream().map(result -> {
            Date fechaResult = (Date) result[0];
            Long clienteId = (Long) result[1];
            String numeroCuenta = (String) result[2];
            TipoCuenta tipoCuenta = (TipoCuenta) result[3];
            Double saldoInicial = (Double) result[4];
            Boolean estado = (Boolean) result[5];
            Double valorMovimiento = (Double) result[6];
            Double saldoDisponible = (Double) result[7];

            return ReporteDTO.builder()
                    .fecha(fechaResult)
                    .clienteId(clienteId)
                    .numeroCuenta(numeroCuenta)
                    .tipoCuenta(tipoCuenta)
                    .saldoInicial(saldoInicial)
                    .estado(estado)
                    .valorMovimiento(valorMovimiento)
                    .saldoDisponible(saldoDisponible)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<ReporteMovimientosDTO> generarReporte(String fecha, Long usuarioId) {
        List<ReporteDTO> results = this.getCuentaMovimientos(fecha, usuarioId);

        return results.stream()
                .map(movimiento -> {
                    ClienteDTO clienteDTO = this.clientePersonaClient.getClienteById(movimiento.getClienteId());

                    return ReporteMovimientosDTO.builder()
                            .fecha(movimiento.getFecha())
                            .cliente(clienteDTO.getPersona().getNombre())
                            .numeroCuenta(movimiento.getNumeroCuenta())
                            .tipoCuenta(movimiento.getTipoCuenta())
                            .saldoInicial(movimiento.getSaldoInicial())
                            .estado(movimiento.getEstado())
                            .valorMovimiento(movimiento.getValorMovimiento())
                            .saldoDisponible(movimiento.getSaldoDisponible())
                            .build();
                })
                .toList();
    }
}
