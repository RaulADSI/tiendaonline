package com.tiendaonline.inventario_servicio.service;

import com.tiendaonline.inventario_servicio.entity.Product;
import com.tiendaonline.inventario_servicio.repository.ProductRepository;

import java.util.List;

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
}
