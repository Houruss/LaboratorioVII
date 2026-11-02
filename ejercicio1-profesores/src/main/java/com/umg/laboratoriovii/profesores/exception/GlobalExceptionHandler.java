package com.umg.laboratoriovii.profesores.exception;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProfesorNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> noEncontrado(ProfesorNoEncontradoException error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("status", 404, "mensaje", error.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validacion(MethodArgumentNotValidException error) {
        Map<String, String> campos = new TreeMap<>();
        error.getBindingResult().getFieldErrors().forEach(f ->
                campos.putIfAbsent(f.getField(), f.getDefaultMessage()));
        return ResponseEntity.badRequest().body(Map.of(
                "status", 400,
                "mensaje", "Datos invalidos",
                "errores", campos));
    }
}
