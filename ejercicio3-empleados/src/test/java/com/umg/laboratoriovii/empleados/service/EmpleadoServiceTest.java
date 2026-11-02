package com.umg.laboratoriovii.empleados.service;

import com.umg.laboratoriovii.empleados.dto.EmpleadoRequest;
import com.umg.laboratoriovii.empleados.exception.EmpleadoNoEncontradoException;
import com.umg.laboratoriovii.empleados.model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpleadoServiceTest {
    private EmpleadoService servicio;

    @BeforeEach
    void preparar() {
        servicio = new EmpleadoService();
    }

    @Test
    void crearEmpleado() {
        Empleado creado = servicio.crear(new EmpleadoRequest(
                "Ana", "Lopez", "ana@example.com", "Tecnologia", true));
        assertEquals(1L, creado.id());
        assertEquals("Ana", creado.nombre());
        assertEquals(1, servicio.listar(null).size());
    }

    @Test
    void obtenerEmpleadoExistente() {
        Empleado creado = servicio.crear(new EmpleadoRequest(
                "Luis", "Perez", "luis@example.com", "Educacion", true));
        Empleado encontrado = servicio.buscarPorId(creado.id());
        assertEquals(creado, encontrado);
    }

    @Test
    void obtenerEmpleadoInexistente() {
        EmpleadoNoEncontradoException ex = assertThrows(
                EmpleadoNoEncontradoException.class,
                () -> servicio.buscarPorId(999L));
        assertTrue(ex.getMessage().contains("999"));
    }
}
