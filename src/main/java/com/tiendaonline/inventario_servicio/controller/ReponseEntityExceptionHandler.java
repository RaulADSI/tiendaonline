package com.tiendaonline.inventario_servicio.controller;

import com.tiendaonline.inventario_servicio.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        // Create response body with details about the exception
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now()); // Changed to LocalDateTime for better precision
        body.put("message", ex.getMessage());
        body.put("error", HttpStatus.BAD_REQUEST.value()); // Display numeric status code
        body.put("path", request.getDescription(false).replace("uri=", "")); // Capture request URI

        // Return response entity with HTTP 400 (Bad Request)
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }
}
