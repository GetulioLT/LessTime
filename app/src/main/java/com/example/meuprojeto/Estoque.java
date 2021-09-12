package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Estoque extends AppCompatActivity {

    //Declarando Variáveis
    Button btnVoltar_Estoque;
    RecyclerView produtos_list;
    FirebaseDatabase FirebaseDatabase;
    DatabaseReference reference;
    //Myadapter Myadapter;
    List<Estoque_info> list;

    private Estoque_Adapter estoque_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        IniciarComponentes();

        //Recuperação dos dados da Firebase
        reference = FirebaseDatabase.getInstance().getReference("Produtos");

        produtos_list.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        produtos_list.setLayoutManager(linearLayoutManager);
        produtos_list.setAdapter(estoque_adapter);


        //Geração da Lista do Estoque
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Estoque_info user = dataSnapshot.getValue(Estoque_info.class);
                    list.add(user);
                }
                estoque_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //Voltar para tela de pedidos
        btnVoltar_Estoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Estoque.this, Tela_dos_pedidos.class);
                startActivity(it);
                finish();
            }
        });
    }

    //Botão Voltar do Celular
    @Override
    public void onBackPressed() {
        Intent it = new Intent(Estoque.this, Tela_dos_pedidos.class);
        startActivity(it);
        finish();
    }

    //Registrando Id das variáveis
    private void IniciarComponentes() {
        produtos_list = findViewById(R.id.Estoque_list);
        list = new ArrayList<Estoque_info>();
        estoque_adapter = new Estoque_Adapter(this, list);
        btnVoltar_Estoque = findViewById(R.id.btnVoltar_Estoque);
    }
}