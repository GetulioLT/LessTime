package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Solicitacao extends AppCompatActivity {

    Button BtnVoltar_Solicitação;
    Button BtnAddP_Solicitação;
    Button BtnEnviar_Solicitação;
    Button BtnCancelar_Solicitação;
    Spinner Spinner_Produtos;
    EditText EdtQuantP_Solicitação;
    TextView TvCódigoP_Solicitação;
    TextView TvNomeP_Solicitação;
    TextView TvQuantE_Solicitação1;
    TextView TvQuantE_Solicitação2;
    TextView TvQuantE_Solicitação3;
    TextView TvNomeE_Solicitação1;
    TextView TvNomeE_Solicitação2;
    TextView TvNomeE_Solicitação3;
    TextView TvCódigoE_Solicitação1;
    TextView TvCódigoE_Solicitação2;
    TextView TvCódigoE_Solicitação3;
    DatabaseReference reference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);

        BtnVoltar_Solicitação = findViewById(R.id.Btn_Voltar_Solicitação);
        BtnAddP_Solicitação = findViewById(R.id.BtnAddP_Solicitação);
        BtnEnviar_Solicitação = findViewById(R.id.BtnEnviar_Solicitação);
        BtnCancelar_Solicitação = findViewById(R.id.BtnCancelar_Solicitação);
        Spinner_Produtos = findViewById(R.id.Spinner);
        EdtQuantP_Solicitação = findViewById(R.id.EdtQuantP_Solicitação);
        TvCódigoP_Solicitação = findViewById(R.id.TvCódigoP_Solicitação);
        TvNomeP_Solicitação = findViewById(R.id.TvNomeP_Solicitação);
        TvQuantE_Solicitação1 = findViewById(R.id.TvQuantE_Solicitação1);
        TvQuantE_Solicitação2 = findViewById(R.id.TvQuantE_Solicitação2);
        TvQuantE_Solicitação3 = findViewById(R.id.TvQuantE_Solicitação3);
        TvNomeE_Solicitação1 = findViewById(R.id.TvNomeE_Solicitação1);
        TvNomeE_Solicitação2 = findViewById(R.id.TvNomeE_Solicitação2);
        TvNomeE_Solicitação3 = findViewById(R.id.TvNomeE_Solicitação3);
        TvCódigoE_Solicitação1 = findViewById(R.id.TvCódigoE_Solicitação1);
        TvCódigoE_Solicitação2 = findViewById(R.id.TvCódigoE_Solicitação2);
        TvCódigoE_Solicitação3 = findViewById(R.id.TvCódigoE_Solicitação3);

        BtnVoltar_Solicitação.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent (Solicitacao.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

    }


}