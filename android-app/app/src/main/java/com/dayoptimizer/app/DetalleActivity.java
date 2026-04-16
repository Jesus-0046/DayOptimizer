package com.dayoptimizer.app;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.dayoptimizer.app.model.Tarea;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        TextView tvTitulo = findViewById(R.id.tvDetalleTitulo);
        TextView tvPrioridad = findViewById(R.id.tvDetallePrioridad);
        TextView tvDuracion = findViewById(R.id.tvDetalleDuracion);
        TextView tvFechaLimite = findViewById(R.id.tvDetalleFechaLimite);
        TextView tvDescripcion = findViewById(R.id.tvDetalleDescripcion);
        TextView tvVecesPospuesta = findViewById(R.id.tvDetalleVecesPospuesta);

        Tarea tarea = (Tarea) getIntent().getSerializableExtra("tarea");

        if (tarea != null) {
            tvTitulo.setText(tarea.getTitulo());
            tvPrioridad.setText("Prioridad: " + tarea.getPrioridad());
            tvDuracion.setText("⏱️ " + tarea.getDuracionMinutos() + " minutos");
            tvFechaLimite.setText("📅 Vence: " + tarea.getFechaLimite());
            tvDescripcion.setText("📝 " + tarea.getDescripcion());
            tvVecesPospuesta.setText("🔄 Pospuesta " + tarea.getVecesPospuesta() + " veces");
        }
    }
}