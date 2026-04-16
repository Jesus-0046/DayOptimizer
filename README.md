# 🧠 DayOptimizer

**Planificador diario inteligente con motor de reglas heurísticas.**

[![Java](https://img.shields.io/badge/Java-17-red)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen)](https://spring.io)
[![Android](https://img.shields.io/badge/Android-Java-green)](https://developer.android.com)
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)

## 📖 Descripción

DayOptimizer es una aplicación full‑stack que no solo gestiona tareas, sino que **optimiza automáticamente la agenda del usuario** basándose en:
- ⏰ **Urgencia** (fecha límite)
- 🔴 **Prioridad manual**
- ⏱️ **Duración estimada**
- 🔄 **Historial de procrastinación** (penaliza tareas pospuestas)

El backend expone una API REST documentada y la app cliente (Android nativa) consume el endpoint `/plan-diario` para mostrar las tareas en el orden óptimo calculado.

## 🛠️ Tecnologías

| Backend | Frontend | Base de Datos |
|---------|----------|---------------|
| Java 17 | Android (Java) | H2 (desarrollo) |
| Spring Boot 3 | Retrofit | PostgreSQL (ready) |
| Spring Data JPA | Material Design | |
| Hibernate | MVVM | |
| Lombok | | |

## 🚀 Cómo Ejecutar Localmente

### Backend (Spring Boot)
1. Clonar el repositorio.
2. Abrir la carpeta `backend` con IntelliJ IDEA.
3. Ejecutar `BackendApplication.java`.
4. Acceder a `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:dayoptimizer`, user: `sa`).

### App Android
1. Abrir la carpeta raíz con Android Studio.
2. Esperar la sincronización de Gradle.
3. Ejecutar en emulador o dispositivo físico (asegurar que `RetrofitClient.java` apunta a `10.0.2.2:8080`).

## 📂 Estructura del Proyecto
