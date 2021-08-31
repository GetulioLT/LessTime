package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meuprojeto.Info.Produtos_Info;
import com.example.meuprojeto.Info.SpinnerP_Info;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Solicitacao extends AppCompatActivity {

    Button BtnVoltar_Solicitação, BtnAddP_Solicitação, BtnEnviar_Solicitação,
            BtnCancelar_Solicitação;
    Spinner Spinner_Produtos;
    EditText EdtQuantP_Solicitação;
    TextView TvCódigoP_Solicitação, TvNomeP_Solicitação, Produto_Spinner;
    DatabaseReference reference;
    FirebaseDatabase FirebaseDatabase;
    RecyclerView solicitação_list;
    Myadapter Myadapter;
    ArrayList<Produtos_Info> list;
    SpinnerP_Info SpinnerP_Info;

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
        solicitação_list = findViewById(R.id.solicitação_list);


        reference = FirebaseDatabase.getInstance().getReference("Produtos");
        solicitação_list.setHasFixedSize(true);
        solicitação_list.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Produtos_Info>();
        Myadapter = new Myadapter(this,list);
        SpinnerP_Info = new SpinnerP_Info();
        solicitação_list.setAdapter(Myadapter);

        Carregar_Produtos();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Produtos_Info user = dataSnapshot.getValue(Produtos_Info.class);
                    list.add(user);


                }
                Myadapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        BtnVoltar_Solicitação.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent (Solicitacao.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void Carregar_Produtos (){
        final List<SpinnerP_Info> SpinnerP = new ArrayList<>();
        reference.child("Produtos").child("SpinnerP").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                        String Produto = dataSnapshot.child("Produto").getValue().toString();
                        SpinnerP.add(new SpinnerP_Info(Produto));
                    }

                    ArrayAdapter<SpinnerP_Info> arrayAdapter = new ArrayAdapter<>(Solicitacao.this, android.R.layout.simple_dropdown_item_1line, SpinnerP);
                    Spinner_Produtos.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}