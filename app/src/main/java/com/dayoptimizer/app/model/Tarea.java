package com.dayoptimizer.app.model;

import com.google.gson.annotations.SerializedName;

public class Tarea implements java.io.Serializable {


    @SerializedName("id")
    private Long id;

    @SerializedName("titulo")
    private String titulo;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("prioridad")
    private String prioridad;

    @SerializedName("duracionMinutos")
    private Integer duracionMinutos;

    @SerializedName("fechaLimite")
    private String fechaLimite;

    @SerializedName("completada")
    private Boolean completada;

    @SerializedName("vecesPospuesta")
    private Integer vecesPospuesta;

    // Constructor vacío
    public Tarea() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public Integer getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public String getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(String fechaLimite) { this.fechaLimite = fechaLimite; }

    public Boolean getCompletada() { return completada; }
    public void setCompletada(Boolean completada) { this.completada = completada; }

    public Integer getVecesPospuesta() { return vecesPospuesta; }
    public void setVecesPospuesta(Integer vecesPospuesta) { this.vecesPospuesta = vecesPospuesta; }
}