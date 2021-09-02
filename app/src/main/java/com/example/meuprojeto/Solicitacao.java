package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

public class Solicitacao extends AppCompatActivity {

    Button BtnVoltar_Solicitação, BtnAddP_Solicitação, BtnEnviar_Solicitação,
            BtnCancelar_Solicitação;
    Spinner Spinner_Produtos;
    EditText EdtQuantP_Solicitação;
    TextView TvCódigoP_Solicitação, TvNomeP_Solicitação;
    DatabaseReference reference;
    FirebaseDatabase FirebaseDatabase;
    RecyclerView solicitação_list;
    Myadapter Myadapter;
    ArrayList<Produtos_Info> list;
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
=======

>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);

<<<<<<< HEAD
        IniciarComponentes();
=======
        BtnVoltar_Solicitação = findViewById(R.id.Btn_Voltar_Solicitação);
        BtnAddP_Solicitação = findViewById(R.id.BtnAddP_Solicitação);
        BtnEnviar_Solicitação = findViewById(R.id.BtnEnviar_Solicitação);
        BtnCancelar_Solicitação = findViewById(R.id.BtnCancelar_Solicitação);
        Spinner_Produtos = findViewById(R.id.Spinner);
        EdtQuantP_Solicitação = findViewById(R.id.EdtQuantP_Solicitação);
        TvCódigoP_Solicitação = findViewById(R.id.TvCódigoP_Solicitação);
        TvNomeP_Solicitação = findViewById(R.id.TvNomeP_Solicitação);
        solicitação_list = findViewById(R.id.solicitação_list);
<<<<<<< HEAD
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
=======
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2

        reference = FirebaseDatabase.getInstance().getReference("Produtos");
        solicitação_list.setHasFixedSize(true);
        solicitação_list.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Produtos_Info>();
        Myadapter = new Myadapter(this,list);
        solicitação_list.setAdapter(Myadapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
<<<<<<< HEAD
<<<<<<< HEAD
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Produtos_Info user = dataSnapshot.getValue(Produtos_Info.class);
                    list.add(user);
                }
                Myadapter.notifyDataSetChanged();
=======
=======
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Produtos_Info user = dataSnapshot.getValue(Produtos_Info.class);
                    list.add(user);


                }
                Myadapter.notifyDataSetChanged();

<<<<<<< HEAD
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
=======
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
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

    private void IniciarComponentes() {
        BtnVoltar_Solicitação = findViewById(R.id.Btn_Voltar_Solicitação);
        BtnAddP_Solicitação = findViewById(R.id.BtnAddP_Solicitação);
        BtnEnviar_Solicitação = findViewById(R.id.BtnEnviar_Solicitação);
        BtnCancelar_Solicitação = findViewById(R.id.BtnCancelar_Solicitação);
        Spinner_Produtos = findViewById(R.id.Spinner);
        EdtQuantP_Solicitação = findViewById(R.id.EdtQuantP_Solicitação);
        TvCódigoP_Solicitação = findViewById(R.id.TvCódigoP_Solicitação);
        TvNomeP_Solicitação = findViewById(R.id.TvNomeP_Solicitação);
        solicitação_list = findViewById(R.id.solicitação_list);
    }


}