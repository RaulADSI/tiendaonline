package com.tiendaonline.inventario_servicio.controller;

import com.tiendaonline.inventario_servicio.entity.Product;
import com.tiendaonline.inventario_servicio.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testProductList() throws Exception {
        Product product1 = new Product(1L, "Producto 1", "Descripción 1", 10.0, 50);
        Product product2 = new Product(2L, "Producto 2", "Descripción 2", 20.0, 30);
        List<Product> productList = Arrays.asList(product1, product2);

        Mockito.when(productService.listProduct()).thenReturn(productList);

        mockMvc.perform(get("/api/productos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Producto 1"))
                .andExpect(jsonPath("$[1].name").value("Producto 2"));
    }

    @Test
    void testAgregarProducto() throws Exception {
        Product newProduct = new Product(null, "Nuevo Producto", "Descripción nueva", 15.0, 100);
        Product savedProduct = new Product(3L, "Nuevo Producto", "Descripción nueva", 15.0, 100);

        Mockito.when(productService.addProduct(Mockito.any(Product.class))).thenReturn(savedProduct);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Nuevo Producto\",\"description\":\"Descripción nueva\",\"price\":15.0,\"stock\":100}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Nuevo Producto"));
    }


}