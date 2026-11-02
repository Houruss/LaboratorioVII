package com.umg.laboratoriovii.profesores.model;

public record Profesor(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String especialidad,
        Boolean estado
) {}
