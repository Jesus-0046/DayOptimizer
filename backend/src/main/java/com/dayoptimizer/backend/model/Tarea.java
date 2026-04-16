package com.dayoptimizer.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "tareas")
@Data // Lombok: Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;

    private Integer duracionMinutos;

    private LocalDate fechaLimite;

    private Boolean completada = false;

    private Integer vecesPospuesta = 0;

    // Enum interno
    public enum Prioridad {
        ALTA, MEDIA, BAJA
    }
}