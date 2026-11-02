package com.umg.laboratoriovii.proveedores.model;

public record Proveedor(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String categoria,
        Boolean estado
) {}
