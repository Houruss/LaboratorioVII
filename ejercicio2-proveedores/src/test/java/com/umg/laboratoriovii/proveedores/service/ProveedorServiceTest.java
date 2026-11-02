package com.umg.laboratoriovii.proveedores.service;

import com.umg.laboratoriovii.proveedores.dto.ProveedorRequest;
import com.umg.laboratoriovii.proveedores.exception.ProveedorNoEncontradoException;
import com.umg.laboratoriovii.proveedores.model.Proveedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProveedorServiceTest {
    private ProveedorService servicio;

    @BeforeEach
    void preparar() {
        servicio = new ProveedorService();
    }

    @Test
    void crearProveedor() {
        Proveedor creado = servicio.crear(new ProveedorRequest(
                "Ana", "Lopez", "ana@example.com", "Tecnologia", true));
        assertEquals(1L, creado.id());
        assertEquals("Ana", creado.nombre());
        assertEquals(1, servicio.listar(null).size());
    }

    @Test
    void obtenerProveedorExistente() {
        Proveedor creado = servicio.crear(new ProveedorRequest(
                "Luis", "Perez", "luis@example.com", "Educacion", true));
        Proveedor encontrado = servicio.buscarPorId(creado.id());
        assertEquals(creado, encontrado);
    }

    @Test
    void obtenerProveedorInexistente() {
        ProveedorNoEncontradoException ex = assertThrows(
                ProveedorNoEncontradoException.class,
                () -> servicio.buscarPorId(999L));
        assertTrue(ex.getMessage().contains("999"));
    }
}
