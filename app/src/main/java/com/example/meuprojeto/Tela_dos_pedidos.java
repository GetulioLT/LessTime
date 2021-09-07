package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Tela_dos_pedidos extends AppCompatActivity {

    //Declarando Variáveis
    Button BtnVoltar_Pedidos, BtnEstoque_Pedidos, BtnEntrada_Pedidos,
            BtnSaída_Pedidos, BtnCadastrar_Pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        IniciarComponentes();

        //Voltar Tela Inicial
        BtnVoltar_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        //Direcionamento para Tela de Estoque
        BtnEstoque_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, Estoque.class);
                startActivity(it);
                finish();
            }
        });

        //Direcionamento para Tela de His.Entada
        BtnEntrada_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, Historico_Entrada.class);
                startActivity(it);
                finish();
            }
        });

        //Direcionamento para Tela de Hist.Saídas
        BtnSaída_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, Historico_Saida.class);
                startActivity(it);
                finish();
            }
        });

        //Direcionamento para Tela de Cadastro de Pedidos
        BtnCadastrar_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Tela_dos_pedidos.this, Cadastro_Produtos.class);
                startActivity(it);
                finish();
            }
        });
    }

    //Inicilialização de Componetes/Registro de ID´s
    private void IniciarComponentes() {
        BtnVoltar_Pedidos = findViewById(R.id.BtnVoltar_Pedidos);
        BtnEstoque_Pedidos = findViewById(R.id.BtnEstoque_Pedidos);
        BtnEntrada_Pedidos = findViewById(R.id.BtnEntrada_Pedidos);
        BtnSaída_Pedidos = findViewById(R.id.BtnSaída_Pedidos);
        BtnCadastrar_Pedidos = findViewById(R.id.BtnCadastrar_Pedidos);
    }
}