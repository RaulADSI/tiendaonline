package com.tiendaonline.inventario_servicio.service;

import com.tiendaonline.inventario_servicio.entity.Product;
import com.tiendaonline.inventario_servicio.exceptions.ResourceNotFoundExceptions;
import com.tiendaonline.inventario_servicio.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> listProduct(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateExistingProduct(Long id, Product updatedProduct) {
        Product existingProduct = listProduct().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundExceptions.ProductoNotFoundException("Producto: " + id + " no encontrado" ));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        addProduct(existingProduct);
        return existingProduct;
    }
}
