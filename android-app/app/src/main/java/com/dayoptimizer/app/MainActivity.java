package com.dayoptimizer.app;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dayoptimizer.app.adapter.TareaAdapter;
import com.dayoptimizer.app.model.Tarea;
import com.dayoptimizer.app.network.ApiService;
import com.dayoptimizer.app.network.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TareaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Botón flotante (+)
        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v -> mostrarDialogoNuevaTarea());

        cargarPlanDiario();
    }

    private void cargarPlanDiario() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Tarea>> call = apiService.getPlanDiario();

        call.enqueue(new Callback<List<Tarea>>() {
            @Override
            public void onResponse(Call<List<Tarea>> call, Response<List<Tarea>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Tarea> tareas = response.body();
                    Log.d("DAYOPTIMIZER", "Tareas recibidas: " + tareas.size());
                    adapter = new TareaAdapter(tareas);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("DAYOPTIMIZER", "Error código: " + response.code());
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Tarea>> call, Throwable t) {
                Log.e("DAYOPTIMIZER", "Error de conexión: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrarDialogoNuevaTarea() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nueva Tarea");

        final EditText inputTitulo = new EditText(this);
        inputTitulo.setHint("Título");
        final EditText inputDuracion = new EditText(this);
        inputDuracion.setHint("Duración (minutos)");
        inputDuracion.setInputType(InputType.TYPE_CLASS_NUMBER);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20);
        layout.addView(inputTitulo);
        layout.addView(inputDuracion);

        builder.setView(layout);

        builder.setPositiveButton("Crear", (dialog, which) -> {
            String titulo = inputTitulo.getText().toString();
            String duracionStr = inputDuracion.getText().toString();

            if (!titulo.isEmpty() && !duracionStr.isEmpty()) {
                crearTareaEnBackend(titulo, Integer.parseInt(duracionStr));
            } else {
                Toast.makeText(MainActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void crearTareaEnBackend(String titulo, int duracion) {
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setTitulo(titulo);
        nuevaTarea.setDescripcion("");
        nuevaTarea.setDuracionMinutos(duracion);
        nuevaTarea.setPrioridad("MEDIA");
        nuevaTarea.setFechaLimite("2026-04-30");
        nuevaTarea.setCompletada(false);
        nuevaTarea.setVecesPospuesta(0);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Tarea> call = apiService.crearTarea(nuevaTarea);

        call.enqueue(new Callback<Tarea>() {
            @Override
            public void onResponse(Call<Tarea> call, Response<Tarea> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "¡Tarea creada!", Toast.LENGTH_SHORT).show();
                    cargarPlanDiario();
                } else {
                    Toast.makeText(MainActivity.this, "Error al crear: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Tarea> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}