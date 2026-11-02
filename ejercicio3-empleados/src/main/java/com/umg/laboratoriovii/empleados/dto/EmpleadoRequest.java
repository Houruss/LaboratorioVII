package com.umg.laboratoriovii.empleados.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpleadoRequest(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "El apellido es obligatorio") String apellido,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe tener un formato valido") String correo,
        String especialidad,
        @NotNull(message = "El estado es obligatorio") Boolean estado
) {}
