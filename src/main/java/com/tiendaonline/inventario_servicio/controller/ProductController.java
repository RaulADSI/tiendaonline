package com.tiendaonline.inventario_servicio.controller;

import com.tiendaonline.inventario_servicio.entity.Product;
import com.tiendaonline.inventario_servicio.exceptions.BadRequestException;
import com.tiendaonline.inventario_servicio.exceptions.ResourceNotFoundExceptions;
import com.tiendaonline.inventario_servicio.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    //Obtener todos los productos
    @GetMapping
    public List<Product> productList(){
        return productService.listProduct();
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Product> agregarProducto(@RequestBody Product product) {
        Product nuevoProducto = productService.addProduct(product);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Crear un nuevo producto
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        if (id == null || id <= 0) {
            throw new BadRequestException("El ID proporcionado no es válido.");
        }
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Producto con ID " + id + " no encontrado"));
        return ResponseEntity.ok(product);

    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            Product updated = productService.updateExistingProduct(id, updatedProduct);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundExceptions ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
