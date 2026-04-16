package com.dayoptimizer.backend.service;

import com.dayoptimizer.backend.model.Tarea;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptimizadorService {

    /**
     * Ordena las tareas según un algoritmo de puntuación inteligente
     */
    public List<Tarea> optimizarDia(List<Tarea> tareas) {
        return tareas.stream()
                .sorted(Comparator.comparingInt(this::calcularPuntuacion).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Calcula la puntuación de una tarea basada en:
     * - Urgencia (fecha límite)
     * - Prioridad manual
     * - Historial de procrastinación
     */
    private int calcularPuntuacion(Tarea tarea) {
        int puntuacion = 0;

        // 1. URGENCIA por fecha límite
        if (tarea.getFechaLimite() != null) {
            long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), tarea.getFechaLimite());
            if (diasRestantes <= 0) {
                puntuacion += 100; // ¡Vence HOY o ya venció!
            } else if (diasRestantes <= 1) {
                puntuacion += 80;  // Vence mañana
            } else if (diasRestantes <= 3) {
                puntuacion += 50;  // Esta semana
            } else {
                puntuacion += 20;  // Largo plazo
            }
        }

        // 2. PRIORIDAD manual
        if (tarea.getPrioridad() != null) {
            switch (tarea.getPrioridad()) {
                case ALTA:   puntuacion += 40; break;
                case MEDIA:  puntuacion += 20; break;
                case BAJA:   puntuacion += 5;  break;
            }
        }

        // 3. DURACIÓN (las tareas cortas suman puntos - "efecto quick win")
        if (tarea.getDuracionMinutos() != null) {
            if (tarea.getDuracionMinutos() <= 15) {
                puntuacion += 30;
            } else if (tarea.getDuracionMinutos() <= 45) {
                puntuacion += 15;
            }
        }

        // 4. HISTORIAL (si la has pospuesto antes, hoy tiene prioridad)
        if (tarea.getVecesPospuesta() != null) {
            puntuacion += tarea.getVecesPospuesta() * 10;
        }

        return puntuacion;
    }
}