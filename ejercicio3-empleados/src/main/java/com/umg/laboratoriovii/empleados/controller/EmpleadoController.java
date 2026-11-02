package com.umg.laboratoriovii.empleados.controller;

import com.umg.laboratoriovii.empleados.dto.EmpleadoRequest;
import com.umg.laboratoriovii.empleados.model.Empleado;
import com.umg.laboratoriovii.empleados.service.EmpleadoService;
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
@RequestMapping("/api/empleados")
public class EmpleadoController {
    private final EmpleadoService servicio;

    public EmpleadoController(EmpleadoService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Empleado> crear(@Valid @RequestBody EmpleadoRequest solicitud) {
        Empleado nuevo = servicio.crear(solicitud);
        return ResponseEntity.created(URI.create("/api/empleados/" + nuevo.id())).body(nuevo);
    }

    @GetMapping
    public List<Empleado> listar(@RequestParam(required = false) String especialidad) {
        return servicio.listar(especialidad);
    }

    @GetMapping("/{id}")
    public Empleado buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id);
    }
}
