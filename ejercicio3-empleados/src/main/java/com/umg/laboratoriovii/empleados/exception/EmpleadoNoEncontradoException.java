package com.umg.laboratoriovii.empleados.exception;

public class EmpleadoNoEncontradoException extends RuntimeException {
    public EmpleadoNoEncontradoException(Long id) {
        super("Empleado no encontrado con ID: " + id);
    }
}
