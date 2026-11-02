package com.umg.laboratoriovii.empleados.service;

import com.umg.laboratoriovii.empleados.dto.EmpleadoRequest;
import com.umg.laboratoriovii.empleados.exception.EmpleadoNoEncontradoException;
import com.umg.laboratoriovii.empleados.model.Empleado;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    // Lista en memoria, sin base de datos, segun el enunciado del laboratorio.
    private final List<Empleado> empleados = new CopyOnWriteArrayList<>();
    private final AtomicLong siguienteId = new AtomicLong(1);

    public Empleado crear(EmpleadoRequest solicitud) {
        Empleado nuevo = new Empleado(
                siguienteId.getAndIncrement(),
                solicitud.nombre().trim(),
                solicitud.apellido().trim(),
                solicitud.correo().trim(),
                solicitud.especialidad() == null ? "" : solicitud.especialidad().trim(),
                solicitud.estado());
        empleados.add(nuevo);
        return nuevo;
    }

    public List<Empleado> listar(String filtroEspecialidad) {
        if (filtroEspecialidad == null || filtroEspecialidad.isBlank()) {
            return List.copyOf(empleados);
        }
        String criterio = filtroEspecialidad.trim();
        return empleados.stream()
                .filter(item -> item.especialidad().equalsIgnoreCase(criterio))
                .toList();
    }

    public Empleado buscarPorId(Long id) {
        return empleados.stream()
                .filter(item -> item.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }
}
