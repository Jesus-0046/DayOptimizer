package com.dayoptimizer.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayoptimizer.app.DetalleActivity;
import com.dayoptimizer.app.R;
import com.dayoptimizer.app.model.Tarea;
import java.util.List;
import android.content.Intent;
import com.dayoptimizer.app.DetalleActivity;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.ViewHolder> {

    private List<Tarea> tareaList;

    public TareaAdapter(List<Tarea> tareaList) {
        this.tareaList = tareaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarea tarea = tareaList.get(position);
        holder.tvTitulo.setText(tarea.getTitulo());
        holder.tvPrioridad.setText(tarea.getPrioridad());
        holder.tvDuracion.setText(tarea.getDuracionMinutos() + " min");
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalleActivity.class);
            intent.putExtra("tarea", tarea);
            v.getContext().startActivity(intent);
        });


        if (tarea.getPrioridad() != null) {
            int color;
            switch (tarea.getPrioridad()) {
                case "ALTA":
                    color = holder.itemView.getContext().getColor(R.color.priority_high);
                    break;
                case "MEDIA":
                    color = holder.itemView.getContext().getColor(R.color.priority_medium);
                    break;
                case "BAJA":
                    color = holder.itemView.getContext().getColor(R.color.priority_low);
                    break;
                default:
                    color = holder.itemView.getContext().getColor(android.R.color.darker_gray);
                    break;
            }
            holder.tvPrioridad.setTextColor(color);
        }
    }

    @Override
    public int getItemCount() {
        return tareaList != null ? tareaList.size() : 0;
    }

    public void updateList(List<Tarea> nuevasTareas) {
        this.tareaList = nuevasTareas;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvPrioridad, tvDuracion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrioridad = itemView.findViewById(R.id.tvPrioridad);
            tvDuracion = itemView.findViewById(R.id.tvDuracion);
        }
    }
}