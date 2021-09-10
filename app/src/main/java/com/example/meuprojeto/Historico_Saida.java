package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Historico_Saida extends AppCompatActivity {

    Button BtnVoltar_HistóricoS;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_saida);

        BtnVoltar_HistóricoS = findViewById(R.id.BtnVoltar_HistóricoS);

        BtnVoltar_HistóricoS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Historico_Saida.this, Tela_dos_pedidos.class);
                startActivity(it);
                finish();
            }
        });
    }

    //Botão Voltar do Celular
    @Override
    public void onBackPressed() {
        Intent it = new Intent(Historico_Saida.this, Tela_dos_pedidos.class);
        startActivity(it);
        finish();
    }
}