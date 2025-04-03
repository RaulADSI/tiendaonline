package com.tiendaonline.inventario_servicio.repository;

import com.tiendaonline.inventario_servicio.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
