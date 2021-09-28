package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Historico_Saida extends AppCompatActivity {

    Button BtnVoltar_HistóricoS;

    TextView Hist_Nsoli, Hist_Nalmo, Hist_Produto, Hist_Quantidade, Hist_Codigo, Hist_Local;
    ImageView Hist_Imagem;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_saida);

        Hist_Nsoli = findViewById(R.id.Hist_Nsoli);
        Hist_Nalmo = findViewById(R.id.Hist_Nalmo);
        Hist_Produto = findViewById(R.id.Hist_Produto);
        Hist_Quantidade = findViewById(R.id.Hist_Quantidade);
        Hist_Codigo = findViewById(R.id.Hist_Codigo);
        Hist_Local = findViewById(R.id.Hist_Local);
        Hist_Imagem = findViewById(R.id.Hist_Imagem);

        BtnVoltar_HistóricoS = findViewById(R.id.BtnVoltar_HistóricoS);

        BtnVoltar_HistóricoS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Historico_Saida.this, Tela_dos_pedidos.class);
                startActivity(it);
                finish();
            }
        });

        database = FirebaseDatabase.getInstance();

        Pedido();

    }

    private void Pedido() {
        DatabaseReference reference = database.getReference().child("Produtos Aceitos");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String NomeAlmo = snapshot.child("Nome Almoxarife").getValue(String.class);
                String NomeSoli = snapshot.child("Nome Solicitante").getValue(String.class);
                String Produto = snapshot.child("Produto").getValue(String.class);
                String Quantidade = snapshot.child("Quantidade").getValue(String.class);
                String Codigo = snapshot.child("Código").getValue(String.class);
                String Imagem = snapshot.child("Imagem").getValue(String.class);
                String Local = snapshot.child("Local").getValue(String.class);

                Hist_Nsoli.setText(NomeSoli);
                Hist_Nalmo.setText(NomeAlmo);
                Hist_Produto.setText(Produto);
                Hist_Quantidade.setText(Quantidade);
                Hist_Codigo.setText(Codigo);
                Hist_Local.setText(Local);

                Picasso.get().load(Imagem).into(Hist_Imagem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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