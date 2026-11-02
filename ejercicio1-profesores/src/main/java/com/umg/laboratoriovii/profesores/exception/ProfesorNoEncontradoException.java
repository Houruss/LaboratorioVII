package com.umg.laboratoriovii.profesores.exception;

public class ProfesorNoEncontradoException extends RuntimeException {
    public ProfesorNoEncontradoException(Long id) {
        super("Profesor no encontrado con ID: " + id);
    }
}
