package com.example.meuprojeto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Popup extends AppCompatActivity {
    TextView Nome_popup;
    EditText Nome_prod_popup;
    RecyclerView List_popup;
    Button Btn_voltar_popup;

    FirebaseDatabase FirebaseDatabase;
    DatabaseReference reference;
    private Pesquisa_Adapter pesquisa_adapter;
    List<Pesquisa_info> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        IniciarComponentes();

        reference = FirebaseDatabase.getInstance().getReference("Produtos");
        List_popup = findViewById(R.id.List_popup);
        List_popup.setHasFixedSize(true);
        List_popup.setLayoutManager(new LinearLayoutManager(this));
        List_popup.setAdapter(pesquisa_adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Pesquisa_info user = dataSnapshot.getValue(Pesquisa_info.class);
                    list.add(user);
                }
                pesquisa_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("dyww erro", String.valueOf(error));
            }
        });
        
        IniciarFirebase();

        Btn_voltar_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Popup.this, Solicitacao.class);
                startActivity(it);
                finish();
            }
        });
    }

    //Botão Voltar do Celular
    @Override
    public void onBackPressed() {
        Intent it = new Intent(Popup.this, Solicitacao.class);
        startActivity(it);
        finish();
    }

    private void IniciarFirebase() {
        FirebaseApp.initializeApp(Popup.this);
        FirebaseDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getReference();
    }

    private void IniciarComponentes() {
        Nome_popup = findViewById(R.id.Nome_popup);
        Nome_prod_popup = findViewById(R.id.Nome_prod_popup);
        Btn_voltar_popup = findViewById(R.id.Btn_voltar_popup);
        list = new ArrayList<Pesquisa_info>();
        pesquisa_adapter = new Pesquisa_Adapter(this, list);
    }
}