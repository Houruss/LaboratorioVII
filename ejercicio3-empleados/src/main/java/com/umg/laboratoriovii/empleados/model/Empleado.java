package com.umg.laboratoriovii.empleados.model;

public record Empleado(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String especialidad,
        Boolean estado
) {}
