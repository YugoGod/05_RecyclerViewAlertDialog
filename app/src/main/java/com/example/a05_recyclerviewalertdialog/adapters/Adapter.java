package com.example.a05_recyclerviewalertdialog.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a05_recyclerviewalertdialog.R;
import com.example.a05_recyclerviewalertdialog.modelos.ToDo;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ToDoVH> {


    private List<ToDo> objects;
    private int resources;
    private Context context;

    public Adapter(List<ToDo> objects, int resources, Context context) {
        this.objects = objects;
        this.resources = resources;
        this.context = context;
    }

    /**
     * ALGO NO ME IMPORTA QUE. Llamará a este método para crear una nueva FILA
     * @param parent
     * @param viewType
     * @return un ObjetoViewHolder
     */
    @NonNull
    @Override
    public ToDoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(context).inflate(resources,null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        todoView.setLayoutParams(lp);
        return new ToDoVH(todoView);
    }

    /**
     * A partir de un ViewHolder -> Asignar valores a los elementos
     * @param holder -> Fila a configurar
     * @param position -> Elemento a la vista a mostrar
     */

    @Override
    public void onBindViewHolder(@NonNull ToDoVH holder, int position) {
        ToDo toDo = objects.get(position);
        holder.lblTitulo.setText(toDo.getTitulo());
        holder.lblContenido.setText(toDo.getContenido());
        holder.lblFecha.setText(toDo.getFecha().toString());
        if (toDo.isCompletado()){
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_on_background);
        }else{
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_off_background);
        }
        holder.btnCompletado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaCambioEstado("¿Estás seguro de cambiar el estado?", toDo).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    private AlertDialog confirmaCambioEstado(String mensaje, ToDo toDo){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(mensaje);
        builder.setCancelable(false);

        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                toDo.setCompletado(!toDo.isCompletado());
                notifyDataSetChanged();
            }
        });
        return builder.create();
    }

    public class ToDoVH extends  RecyclerView.ViewHolder{
        TextView lblTitulo,lblContenido,lblFecha;
        ImageButton btnCompletado;

        public ToDoVH(@NonNull View itemView) {
            super(itemView);
            lblTitulo = itemView.findViewById(R.id.lblTituloToDoModelView);
            lblContenido = itemView.findViewById(R.id.lblContenidoToDoModelView);
            lblFecha = itemView.findViewById(R.id.lblFechaToDoModelView);
            btnCompletado = itemView.findViewById(R.id.btnCompletadoToDoModelView);
        }
    }
}
