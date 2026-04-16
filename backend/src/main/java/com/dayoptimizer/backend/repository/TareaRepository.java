package com.dayoptimizer.backend.repository;

import com.dayoptimizer.backend.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Método personalizado: Buscar solo tareas NO completadas
    List<Tarea> findByCompletadaFalse();

    // Método personalizado: Buscar por prioridad
    List<Tarea> findByPrioridad(Tarea.Prioridad prioridad);
}