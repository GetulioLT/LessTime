package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Historico_Entrada extends AppCompatActivity {

    Button BtnVoltar_HistóricoE;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_entrada);

        BtnVoltar_HistóricoE = findViewById(R.id.BtnVoltar_HistóricoE);

        BtnVoltar_HistóricoE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Historico_Entrada.this, Tela_dos_pedidos.class);
                startActivity(it);
                finish();
            }
        });
    }
}