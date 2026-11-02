package com.umg.laboratoriovii.profesores.controller;

import com.umg.laboratoriovii.profesores.dto.ProfesorRequest;
import com.umg.laboratoriovii.profesores.model.Profesor;
import com.umg.laboratoriovii.profesores.service.ProfesorService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    private final ProfesorService servicio;

    public ProfesorController(ProfesorService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Profesor> crear(@Valid @RequestBody ProfesorRequest solicitud) {
        Profesor nuevo = servicio.crear(solicitud);
        return ResponseEntity.created(URI.create("/api/profesores/" + nuevo.id())).body(nuevo);
    }

    @GetMapping
    public List<Profesor> listar(@RequestParam(required = false) String especialidad) {
        return servicio.listar(especialidad);
    }

    @GetMapping("/{id}")
    public Profesor buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id);
    }
}
