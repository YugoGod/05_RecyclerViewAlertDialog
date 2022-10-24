package com.example.a05_recyclerviewalertdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.a05_recyclerviewalertdialog.adapters.Adapter;
import com.example.a05_recyclerviewalertdialog.modelos.ToDo;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a05_recyclerviewalertdialog.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<ToDo> toDoList;
    private Adapter adapter1;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        toDoList = new ArrayList<>();
        // crearToDo();

        adapter1 = new Adapter(toDoList, R.layout.todo_view_model, MainActivity.this);
        binding.contentMain.contenedor.setAdapter(adapter1);
        // layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager = new GridLayoutManager(MainActivity.this, 2);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createToDo("Nueva tarea").show();
            }
        });
    }

    private AlertDialog createToDo(String titulo){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(titulo);
        View contenido = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_todo_alertdialog, null);
        EditText txtTitulo = contenido.findViewById(R.id.txtTituloAddToDo);
        EditText txtContenido = contenido.findViewById(R.id.txtContenidoAddToDo);

        builder.setView(contenido);

        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("AGREGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ToDo toDo = new ToDo(txtTitulo.getText().toString(), txtContenido.getText().toString());
                toDoList.add(toDo);
                adapter1.notifyDataSetChanged();
            }
        });
        return builder.create();
    }

    private void crearToDo() {
        for (int i = 0; i < 100000; i++) {
            toDoList.add(new ToDo("Título " + i, "Contenido " + i));
        }
    }
}