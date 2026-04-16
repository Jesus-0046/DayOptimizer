package com.dayoptimizer.app.network;

import com.dayoptimizer.app.model.Tarea;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/api/tareas/plan-diario")
    Call<List<Tarea>> getPlanDiario();

    @POST("/api/tareas")
    Call<Tarea> crearTarea(@Body Tarea tarea);
}