package com.umg.laboratoriovii.profesores.service;

import com.umg.laboratoriovii.profesores.dto.ProfesorRequest;
import com.umg.laboratoriovii.profesores.exception.ProfesorNoEncontradoException;
import com.umg.laboratoriovii.profesores.model.Profesor;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService {
    // Lista en memoria, sin base de datos, segun el enunciado del laboratorio.
    private final List<Profesor> profesores = new CopyOnWriteArrayList<>();
    private final AtomicLong siguienteId = new AtomicLong(1);

    public Profesor crear(ProfesorRequest solicitud) {
        Profesor nuevo = new Profesor(
                siguienteId.getAndIncrement(),
                solicitud.nombre().trim(),
                solicitud.apellido().trim(),
                solicitud.correo().trim(),
                solicitud.especialidad() == null ? "" : solicitud.especialidad().trim(),
                solicitud.estado());
        profesores.add(nuevo);
        return nuevo;
    }

    public List<Profesor> listar(String filtroEspecialidad) {
        if (filtroEspecialidad == null || filtroEspecialidad.isBlank()) {
            return List.copyOf(profesores);
        }
        String criterio = filtroEspecialidad.trim();
        return profesores.stream()
                .filter(item -> item.especialidad().equalsIgnoreCase(criterio))
                .toList();
    }

    public Profesor buscarPorId(Long id) {
        return profesores.stream()
                .filter(item -> item.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProfesorNoEncontradoException(id));
    }
}
