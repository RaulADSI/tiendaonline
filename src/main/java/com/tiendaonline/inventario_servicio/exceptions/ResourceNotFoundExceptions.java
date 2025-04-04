package com.tiendaonline.inventario_servicio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptions extends RuntimeException {

    public ResourceNotFoundExceptions(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public static class ProductoNotFoundException extends ResourceNotFoundExceptions{
        public ProductoNotFoundException(String message){
            super(message);
        }
    }
}
