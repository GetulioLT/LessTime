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

import java.util.ArrayList;
import java.util.List;

public class Historico_Entrada extends AppCompatActivity {

    Button BtnVoltar_HistóricoE;
    RecyclerView List_hist_entrada;

    FirebaseDatabase FirebaseDatabase;
    DatabaseReference reference;
    List<Estoque_info> list;

    private Estoque_Adapter estoque_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_entrada);
        List_hist_entrada = findViewById(R.id.List_hist_entrada);
        list = new ArrayList<Estoque_info>();
        estoque_adapter = new Estoque_Adapter(this, list);

        reference = FirebaseDatabase.getInstance().getReference("Produtos");

        List_hist_entrada.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        List_hist_entrada.setLayoutManager(linearLayoutManager);
        List_hist_entrada.setAdapter(estoque_adapter);

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

    //Botão Voltar do Celular
    @Override
    public void onBackPressed() {
        Intent it = new Intent(Historico_Entrada.this, Tela_dos_pedidos.class);
        startActivity(it);
        finish();
    }
}