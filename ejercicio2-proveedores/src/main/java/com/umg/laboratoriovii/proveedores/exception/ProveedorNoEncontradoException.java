package com.umg.laboratoriovii.proveedores.exception;

public class ProveedorNoEncontradoException extends RuntimeException {
    public ProveedorNoEncontradoException(Long id) {
        super("Proveedor no encontrado con ID: " + id);
    }
}
