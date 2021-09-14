package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tela_dos_pedidos extends AppCompatActivity {

    //Declarando Variáveis
    Button BtnVoltar_Pedidos, BtnEstoque_Pedidos, BtnEntrada_Pedidos,
            BtnSaída_Pedidos, BtnCadastrar_Pedidos, Aceitar;
    TextView Nome_almo, Nome_pegar, nome_hist, imagem_hist, prod_hist, quant_hist
            ,Codigo_hist, descrião_hist;
    String UsuarioID;
    RecyclerView List_tela_de_pedidos;
    FirebaseFirestore bd = FirebaseFirestore.getInstance();

    FirebaseDatabase FirebaseDatabase;
    DatabaseReference reference;
    Tela_dos_pedidos_Adapter Tela_dos_pedidos_Adapter;
    List<Tela_dos_pedidos_info1> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        IniciarComponentes();
        List_tela_de_pedidos.setLayoutManager(new LinearLayoutManager(this));

        Aceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List_tela_de_pedidos.setVisibility(View.INVISIBLE);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                reference = FirebaseDatabase.getInstance().getReference("Produtos Pedidos")
                        .child("Getulio");

                Log.d("dyww nome", String.valueOf(Nome_pegar));
                List_tela_de_pedidos.setHasFixedSize(true);

                List_tela_de_pedidos.setAdapter(Tela_dos_pedidos_Adapter);

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Tela_dos_pedidos_info1 user = dataSnapshot.getValue(Tela_dos_pedidos_info1.class);
                            list.add(user);
                        }
                        Tela_dos_pedidos_Adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        },1000);

        reference = FirebaseDatabase.getInstance().getReference("Produtos Pedidos")
                .child(Nome_almo.getText().toString());
        List_tela_de_pedidos.setHasFixedSize(true);
        List_tela_de_pedidos.setLayoutManager(new LinearLayoutManager(this));
        List_tela_de_pedidos.setAdapter(Tela_dos_pedidos_Adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Tela_dos_pedidos_info1 user = dataSnapshot.getValue(Tela_dos_pedidos_info1.class);
                    list.add(user);
                }
                Tela_dos_pedidos_Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

        Selecionarpedido();
    }

    private void Selecionarpedido() {
        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("Profuto_tela"))){
            Tela_dos_pedidos_info1 produto_tela = (Tela_dos_pedidos_info1)
                    bundle.getSerializable("Profuto_tela");

            nome_hist.setText(produto_tela.getNome());
            imagem_hist.setText(produto_tela.getImagem());
            prod_hist.setText(produto_tela.getProduto());
            quant_hist.setText(produto_tela.getQuantidade());
            Codigo_hist.setText(produto_tela.getCodigo());
            descrião_hist.setText(produto_tela.getDescrição());
        }




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
        List_tela_de_pedidos = findViewById(R.id.List_tela_de_pedidos);
        nome_hist = findViewById(R.id.nome_hist);
        imagem_hist = findViewById(R.id.imagem_hist);
        prod_hist = findViewById(R.id.prod_hist);
        quant_hist = findViewById(R.id.quant_hist);
        Codigo_hist = findViewById(R.id.Codigo_hist);
        descrião_hist = findViewById(R.id.descrião_hist);
        Aceitar = findViewById(R.id.Aceitar);

        list = new ArrayList<Tela_dos_pedidos_info1>();
        Tela_dos_pedidos_Adapter = new Tela_dos_pedidos_Adapter(this, list);


    }
}