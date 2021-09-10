package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class Tela_dos_pedidos extends AppCompatActivity {

    //Declarando Variáveis
    Button BtnVoltar_Pedidos, BtnEstoque_Pedidos, BtnEntrada_Pedidos,
            BtnSaída_Pedidos, BtnCadastrar_Pedidos;
    TextView Nome_almo;
    String UsuarioID;
    FirebaseFirestore bd = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        IniciarComponentes();

        //Desloga Usuario
        BtnVoltar_Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
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

    //Botão Voltar do Celular
    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        Intent it = new Intent(Tela_dos_pedidos.this, MainActivity.class);
        startActivity(it);
        finish();
    }

    //Obtenção do Nome do Usúario Logado
    @Override
    protected void onStart() {
        super.onStart();

        UsuarioID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        DocumentReference documentReference = bd
                .collection("Usuarios").document(UsuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot,
                                @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null){
                    Nome_almo.setText(documentSnapshot.getString("Nome"));
                }
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
        Nome_almo = findViewById(R.id.Nome_almo);
    }
}