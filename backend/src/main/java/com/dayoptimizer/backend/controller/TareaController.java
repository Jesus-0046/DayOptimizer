package com.dayoptimizer.backend.controller;

import com.dayoptimizer.backend.model.Tarea;
import com.dayoptimizer.backend.repository.TareaRepository;
import com.dayoptimizer.backend.service.OptimizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*") // Permite conexión desde Android y Web
public class TareaController {

    @Autowired
    private TareaRepository repository;

    @Autowired
    private OptimizadorService optimizador;

    // POST /api/tareas - Crear nueva tarea
    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = repository.save(tarea);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

    // GET /api/tareas - Obtener TODAS las tareas
    @GetMapping
    public List<Tarea> obtenerTodas() {
        return repository.findAll();
    }

    // GET /api/tareas/pendientes - Obtener solo NO completadas
    @GetMapping("/pendientes")
    public List<Tarea> obtenerPendientes() {
        return repository.findByCompletadaFalse();
    }

    // GET /api/tareas/plan-diario - ¡EL ENDPOINT MÁGICO!
    @GetMapping("/plan-diario")
    public List<Tarea> obtenerPlanDiarioOptimizado() {
        List<Tarea> pendientes = repository.findByCompletadaFalse();
        return optimizador.optimizarDia(pendientes);
    }

    // PUT /api/tareas/{id}/completar - Marcar como completada
    @PutMapping("/{id}/completar")
    public ResponseEntity<Tarea> completarTarea(@PathVariable Long id) {
        return repository.findById(id)
                .map(tarea -> {
                    tarea.setCompletada(true);
                    return ResponseEntity.ok(repository.save(tarea));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/tareas/{id}/posponer - Marcar como pospuesta
    @PutMapping("/{id}/posponer")
    public ResponseEntity<Tarea> posponerTarea(@PathVariable Long id) {
        return repository.findById(id)
                .map(tarea -> {
                    tarea.setVecesPospuesta(tarea.getVecesPospuesta() + 1);
                    return ResponseEntity.ok(repository.save(tarea));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}