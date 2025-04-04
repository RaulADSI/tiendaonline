package com.tiendaonline.inventario_servicio.exceptions;

public class ProductoServiceException extends RuntimeException{
    public ProductoServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
