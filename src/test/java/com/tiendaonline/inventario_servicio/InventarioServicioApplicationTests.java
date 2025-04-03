package com.tiendaonline.inventario_servicio;

import com.tiendaonline.inventario_servicio.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals; // Para usar assertEquals
import org.springframework.http.HttpStatus; // Para HttpStatus
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventarioServicioApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testGetProductos() {
		ResponseEntity<Product[]> response = restTemplate.getForEntity("/api/productos", Product[].class);

		// Verificar que el estado HTTP es 200 OK
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Verificar que los productos se devuelven correctamente
		Product[] products = response.getBody();
		assertNotNull(products);
		assertEquals(3, products.length);

		// Validar el contenido del primer producto
		assertEquals("Aire Acondicionado", products[0].getName());
		assertEquals(1500.0, products[0].getPrice());
		assertEquals(15, products[0].getStock());

		// Validar el contenido del segundo producto
		assertEquals("Celular Motorolla G22", products[1].getName());
		assertEquals(19.99, products[1].getPrice());
		assertEquals(100, products[1].getStock());

		// Validar el contenido del tercer producto
		assertEquals("Ventilador Samurai", products[2].getName());
		assertEquals(1200.0, products[2].getPrice());
		assertEquals(150, products[2].getStock());

	}


	@Test
	void contextLoads() {
	}

}
