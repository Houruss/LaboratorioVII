package com.umg.laboratoriovii.proveedores.controller;

import com.umg.laboratoriovii.proveedores.dto.ProveedorRequest;
import com.umg.laboratoriovii.proveedores.model.Proveedor;
import com.umg.laboratoriovii.proveedores.service.ProveedorService;
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
@RequestMapping("/api/proveedores")
public class ProveedorController {
    private final ProveedorService servicio;

    public ProveedorController(ProveedorService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@Valid @RequestBody ProveedorRequest solicitud) {
        Proveedor nuevo = servicio.crear(solicitud);
        return ResponseEntity.created(URI.create("/api/proveedores/" + nuevo.id())).body(nuevo);
    }

    @GetMapping
    public List<Proveedor> listar(@RequestParam(required = false) String categoria) {
        return servicio.listar(categoria);
    }

    @GetMapping("/{id}")
    public Proveedor buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id);
    }
}
