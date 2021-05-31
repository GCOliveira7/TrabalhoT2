package com.example.trabalhot2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BDSQLiteContatos bd;
    ArrayList<Contato> listaContatos;
    private FloatingActionButton addContato;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContato = (FloatingActionButton) findViewById(R.id.addcontato);
        addContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddContato();
            }
        });

        bd = new BDSQLiteContatos(this);

        recyclerView = findViewById(R.id.rvContatos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    public void openAddContato(){
        Intent intent = new Intent(this, AddContato.class);
        startActivity(intent);
    }
}