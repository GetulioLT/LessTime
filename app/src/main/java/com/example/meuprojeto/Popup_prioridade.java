package com.example.meuprojeto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class Popup_prioridade extends AppCompatActivity {
    RadioButton RbtnExtrema, RbtnModerada, RbtnBaixa, RbtnNenhuma;
    TextView Textprio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_prioridade);

        if (RbtnExtrema.isChecked()){
            Textprio.setText("Extrema");

        }

        IniciarComponentes();
    }

    private void IniciarComponentes() {
        RbtnExtrema = findViewById(R.id.RbtnExtrema);
        RbtnModerada = findViewById(R.id.RbtnModerada);
        RbtnBaixa = findViewById(R.id.RbtnBaixa);
        RbtnNenhuma = findViewById(R.id.RbtnNenhuma);
        Textprio = findViewById(R.id.View_Prioridade);
    }
}