package com.tiendaonline.inventario_servicio.controller;

import com.tiendaonline.inventario_servicio.entity.Product;
import com.tiendaonline.inventario_servicio.service.ProductService;
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
        return productService.listProduct().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.listProduct().stream()
                .filter(existingProduct -> existingProduct.getId().equals(id))  // Filtrar por el ID que se pasa
                .findFirst()                                                      // Obtener el producto si existe
                .map(existingProduct -> {
                    // Actualiza los campos del producto con los datos nuevos
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setStock(updatedProduct.getStock());
                    productService.addProduct(existingProduct);           // Guarda los cambios en la base de datos
                    return ResponseEntity.ok(existingProduct);                 // Responde con el producto actualizado
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
