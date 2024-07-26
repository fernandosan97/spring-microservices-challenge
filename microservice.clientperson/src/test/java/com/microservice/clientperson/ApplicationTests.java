package com.microservice.clientperson;

import com.microservice.clientperson.entities.Cliente;
import com.microservice.clientperson.entities.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTests {

	@Test
	void testClienteCreation() {
		Persona persona = Persona.builder()
				.id(1L)
				.nombre("Fernando Sanchez")
				.genero("Masculino")
				.edad(30)
				.identificacion("123456789")
				.direccion("Calle Falsa 123")
				.telefono("123456789")
				.build();

		Cliente cliente = Cliente.builder()
				.id(1L)
				.contrasena("password")
				.estado(true)
				.persona(persona)
				.build();

		// Assertions
		assertNotNull(cliente);
		assertEquals(1L, cliente.getId());
		assertEquals("password", cliente.getContrasena());
		assertTrue(cliente.getEstado());
		assertNotNull(cliente.getPersona());
		assertEquals("Fernando Sanchez", cliente.getPersona().getNombre());
	}
}
