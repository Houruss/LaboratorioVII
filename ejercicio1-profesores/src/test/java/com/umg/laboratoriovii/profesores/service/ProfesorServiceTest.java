package com.umg.laboratoriovii.profesores.service;

import com.umg.laboratoriovii.profesores.dto.ProfesorRequest;
import com.umg.laboratoriovii.profesores.exception.ProfesorNoEncontradoException;
import com.umg.laboratoriovii.profesores.model.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfesorServiceTest {
    private ProfesorService servicio;

    @BeforeEach
    void preparar() {
        servicio = new ProfesorService();
    }

    @Test
    void crearProfesor() {
        Profesor creado = servicio.crear(new ProfesorRequest(
                "Ana", "Lopez", "ana@example.com", "Tecnologia", true));
        assertEquals(1L, creado.id());
        assertEquals("Ana", creado.nombre());
        assertEquals(1, servicio.listar(null).size());
    }

    @Test
    void obtenerProfesorExistente() {
        Profesor creado = servicio.crear(new ProfesorRequest(
                "Luis", "Perez", "luis@example.com", "Educacion", true));
        Profesor encontrado = servicio.buscarPorId(creado.id());
        assertEquals(creado, encontrado);
    }

    @Test
    void obtenerProfesorInexistente() {
        ProfesorNoEncontradoException ex = assertThrows(
                ProfesorNoEncontradoException.class,
                () -> servicio.buscarPorId(999L));
        assertTrue(ex.getMessage().contains("999"));
    }
}
