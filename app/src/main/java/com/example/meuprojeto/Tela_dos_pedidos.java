package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tela_dos_pedidos extends AppCompatActivity {

    Button BtnVoltar_Pedidos, BtnEstoque_Pedidos, BtnEntrada_Pedidos, BtnSaída_Pedidos, BtnCadastrar_Pedidos;

    FirebaseDatabase Database ;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        IniciarComponentes();

        BtnVoltar_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        BtnEstoque_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, Estoque.class);
                startActivity(it);
                finish();
            }
        });

        BtnEntrada_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, Historico_Entrada.class);
                startActivity(it);
                finish();
            }
        });

        BtnSaída_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, Historico_Saida.class);
                startActivity(it);
                finish();
            }
        });

        BtnCadastrar_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, Cadastro_Produtos.class);
                startActivity(it);
                finish();
            }
        });

    }

    private void IniciarComponentes() {
        BtnVoltar_Pedidos = findViewById(R.id.BtnVoltar_Pedidos);
        BtnEstoque_Pedidos = findViewById(R.id.BtnEstoque_Pedidos);
        BtnEntrada_Pedidos = findViewById(R.id.BtnEntrada_Pedidos);
        BtnSaída_Pedidos = findViewById(R.id.BtnSaída_Pedidos);
        BtnCadastrar_Pedidos = findViewById(R.id.BtnCadastrar_Pedidos);
    }
}