package com.microservice.movimientos;

import com.microservice.movimientos.dto.ApiResponse;
import com.microservice.movimientos.dto.HacerMovimientoDTO;
import com.microservice.movimientos.entities.Cuenta;
import com.microservice.movimientos.entities.Movimiento;
import com.microservice.movimientos.persistence.CuentaRepository;
import com.microservice.movimientos.persistence.MovimientoRepository;
import com.microservice.movimientos.service.MovimientoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
@Transactional
class ApplicationTests {

	@Autowired
	private MovimientoServiceImpl movimientoService;

	@Mock
	private CuentaRepository cuentaRepository;

	@Mock
	private MovimientoRepository movimientoRepository;

	@InjectMocks
	private MovimientoServiceImpl movimientoServiceImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveSuccess() {
		HacerMovimientoDTO movimientoDTO = HacerMovimientoDTO.builder()
				.numeroCuenta("495878")
				.valorMovimiento(100.0)
				.build();

		Cuenta cuenta = Cuenta.builder()
				.id(1L)
				.numeroCuenta("495878")
				.saldoInicial(500.0)
				.build();

		when(cuentaRepository.findByNumeroCuenta("495878")).thenReturn(Optional.of(cuenta));
		when(cuentaRepository.updateCuentaWithSaldoCheck(1L, 600.0, 500.0)).thenReturn(1);
		when(movimientoRepository.save(Mockito.any())).thenReturn(Movimiento.builder().build());

		ApiResponse response = movimientoService.save(movimientoDTO);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals("Movimiento realizado satisfactoriamente", response.getMessage());
	}

	@Test
	void testSaveFailure_AccountNotFound() {
		HacerMovimientoDTO movimientoDTO = HacerMovimientoDTO.builder()
				.numeroCuenta("123456")
				.valorMovimiento(100.0)
				.build();

		when(cuentaRepository.findByNumeroCuenta("123456")).thenReturn(Optional.empty());

		ApiResponse response = movimientoService.save(movimientoDTO);

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
		assertEquals("Numero de cuenta no encontrada", response.getMessage());
	}

	@Test
	void testSaveFailure_InsufficientFunds() {
		HacerMovimientoDTO movimientoDTO = HacerMovimientoDTO.builder()
				.numeroCuenta("495878")
				.valorMovimiento(-600.0)
				.build();

		Cuenta cuenta = Cuenta.builder()
				.id(1L)
				.numeroCuenta("495878")
				.saldoInicial(500.0)
				.build();

		when(cuentaRepository.findByNumeroCuenta("495878")).thenReturn(Optional.of(cuenta));

		ApiResponse response = movimientoService.save(movimientoDTO);

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
		assertEquals("Saldo no disponible", response.getMessage());
	}

}
