package com.umg.laboratoriovii.proveedores.service;

import com.umg.laboratoriovii.proveedores.dto.ProveedorRequest;
import com.umg.laboratoriovii.proveedores.exception.ProveedorNoEncontradoException;
import com.umg.laboratoriovii.proveedores.model.Proveedor;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {
    // Lista en memoria, sin base de datos, segun el enunciado del laboratorio.
    private final List<Proveedor> proveedores = new CopyOnWriteArrayList<>();
    private final AtomicLong siguienteId = new AtomicLong(1);

    public Proveedor crear(ProveedorRequest solicitud) {
        Proveedor nuevo = new Proveedor(
                siguienteId.getAndIncrement(),
                solicitud.nombre().trim(),
                solicitud.apellido().trim(),
                solicitud.correo().trim(),
                solicitud.categoria() == null ? "" : solicitud.categoria().trim(),
                solicitud.estado());
        proveedores.add(nuevo);
        return nuevo;
    }

    public List<Proveedor> listar(String filtroCategoria) {
        if (filtroCategoria == null || filtroCategoria.isBlank()) {
            return List.copyOf(proveedores);
        }
        String criterio = filtroCategoria.trim();
        return proveedores.stream()
                .filter(item -> item.categoria().equalsIgnoreCase(criterio))
                .toList();
    }

    public Proveedor buscarPorId(Long id) {
        return proveedores.stream()
                .filter(item -> item.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProveedorNoEncontradoException(id));
    }
}
