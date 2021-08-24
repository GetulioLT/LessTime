package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Estoque extends AppCompatActivity {

    Button btnVoltar_Estoque;

    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        btnVoltar_Estoque = findViewById(R.id.btnVoltar_Estoque);

        btnVoltar_Estoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Estoque.this, Tela_dos_pedidos.class);
                startActivity(it);
                finish();
            }
        });

    }
}