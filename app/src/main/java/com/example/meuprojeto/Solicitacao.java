package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solicitacao extends AppCompatActivity {

    //Declarando Variáveis
    Button BtnDeslogar_Solicitação, BtnAddP_Solicitação, BtnEnviar_Solicitação,
            BtnCancelar_Solicitação, Btn_popup;
    TextView Nome_Solicitante, Nome_Produto_Soli, Codigo_Produto_Soli, Quantidade_Estoque,
            Url_Produto_Solicitação, descrição_prod;
    EditText Quantidade_Soli;
    ImageView Imagem_Produto_Soli;
    RecyclerView solicitação_list;
    AlertDialog.Builder dialogbuilder;
    AlertDialog dialog;
    FirebaseFirestore bd = FirebaseFirestore.getInstance();
    String UsuarioID;
    View codigo;
    final String[] mensagens = {"Produto não Selecionado", "Quantidade Maior que no Estoque"
            , "Adicione um Valor Para Solicitar"};
    Solicitação_info Solicitação_info;
    List<Solicitação_info> list;
    Solicitação_Adapter solicitação_adapter;
    DatabaseReference reference;
    Tela_dos_pedidos_info1 Tela_dos_pedidos_info1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);
        Tela_dos_pedidos_info1 = new Tela_dos_pedidos_info1();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                reference = FirebaseDatabase.getInstance().getReference("Produtos Lista Solicitação")
                        .child(Nome_Solicitante.getText().toString());

                Solicitação_info = new Solicitação_info();

                solicitação_list.setHasFixedSize(true);
                solicitação_list.setLayoutManager(linearLayoutManager);
                solicitação_list.setAdapter(solicitação_adapter);

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Solicitação_info user = dataSnapshot.getValue(Solicitação_info.class);
                            list.add(user);
                        }
                        solicitação_adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        },1000);

        IniciarComponentes();


        //Voltar Tela Inicial
        BtnDeslogar_Solicitação.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent it = new Intent(Solicitacao.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        //Ir para a tela de Pesquisa de Produto
        Btn_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent (Solicitacao.this, Popup.class);
                startActivity(it);
                finish();

                /*Criarpopup();*/
            }
        });

        ProdutoSelecionado();

        BtnAddP_Solicitação.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nome_produto = Nome_Produto_Soli.getText().toString();
                String Codigo_produto = Codigo_Produto_Soli.getText().toString();
                String Quantidade_solicitada = Quantidade_Soli.getText().toString();
                String Quantidade_estoque = Quantidade_Estoque.getText().toString();

                if (Nome_produto.isEmpty() || Codigo_produto.isEmpty()
                        || Quantidade_estoque.isEmpty()){
                    alert(mensagens[0]);
                }else{
                    if (Quantidade_solicitada.isEmpty()){
                        alert(mensagens[2]);
                    }else {
                        if (Quantidade_solicitada.equals(Quantidade_estoque + 1)) {
                            alert(mensagens[1]);
                        } else {
                            ListaProduto();

                            if (!TextUtils.isEmpty(Solicitação_info.getNome_Produto())
                                    && !TextUtils.isEmpty(Solicitação_info.getQuantidade_Produto())
                                    && !TextUtils.isEmpty(Solicitação_info.getCodigo_Produto())
                                    && !TextUtils.isEmpty(Solicitação_info.getImagem_Produto())
                                    && !TextUtils.isEmpty(Solicitação_info.getNome_Solicitante())){
                                Solicitação_info.SalvarListaSolicitação();
                                Tela_dos_pedidos_info1.salvarTelaDePedidos();
                                Intent it = new Intent(Solicitacao.this, Solicitacao.class);
                                startActivity(it);
                                finish();
                            }
                        }
                    }
                }
            }
        });

        BtnCancelar_Solicitação.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Produtos Lista Solicitação")
                        .child(Nome_Solicitante.getText().toString()).removeValue();

                Intent it = new Intent(Solicitacao.this, Solicitacao.class);
                startActivity(it);
                finish();
            }
        });

        BtnEnviar_Solicitação.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Produtos Lista Solicitação")
                        .child(Nome_Solicitante.getText().toString()).removeValue();

                Intent it = new Intent(Solicitacao.this, Solicitacao.class);
                startActivity(it);
                finish();
            }
        });
    }

    private void EviarAlmo() {

    }

    private void ListaProduto() {
        Solicitação_info.setNome_Produto(Nome_Produto_Soli.getText().toString());
        Solicitação_info.setQuantidade_Produto(Quantidade_Soli.getText().toString());
        Solicitação_info.setCodigo_Produto(Codigo_Produto_Soli.getText().toString());
        Solicitação_info.setImagem_Produto(Url_Produto_Solicitação.getText().toString());
        Solicitação_info.setNome_Solicitante(Nome_Solicitante.getText().toString());

        Tela_dos_pedidos_info1.setNome(Nome_Solicitante.getText().toString());
        Tela_dos_pedidos_info1.setImagem(Url_Produto_Solicitação.getText().toString());
        Tela_dos_pedidos_info1.setProduto(Nome_Produto_Soli.getText().toString());
        Tela_dos_pedidos_info1.setQuantidade(Quantidade_Soli.getText().toString());
        Tela_dos_pedidos_info1.setCodigo(Codigo_Produto_Soli.getText().toString());
        Tela_dos_pedidos_info1.setDescrição(descrição_prod.getText().toString());

        Log.d("dyww salvo", Solicitação_info.getCodigo_Produto());
    }

    private void ProdutoSelecionado() {
        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("Produto_pesq"))){
            Pesquisa_info produto_pesq = (Pesquisa_info) bundle.getSerializable("Produto_pesq");
            Nome_Produto_Soli.setText(produto_pesq.Produto);
            Codigo_Produto_Soli.setText(produto_pesq.Código);
            Quantidade_Estoque.setText(produto_pesq.Quantidade);
            Url_Produto_Solicitação.setText(produto_pesq.Imagem);
            descrição_prod.setText(produto_pesq.Descrição);

            Picasso.get().load(produto_pesq.Imagem).into(Imagem_Produto_Soli);
        }
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
                    Nome_Solicitante.setText(documentSnapshot.getString("Nome"));

                    Log.d("dyww nome", Nome_Solicitante.getText().toString());
                }
            }
        });
    }

    /*private void Criarpopup() {
        dialogbuilder = new AlertDialog.Builder(this);
        final View contactpopupView = getLayoutInflater().inflate(R.layout.popup, null);

        Nome_popup = contactpopupView.findViewById(R.id.Nome_popup);
        Nome_prod_popup = contactpopupView.findViewById(R.id.Nome_prod_popup);

        dialogbuilder.setView(contactpopupView);
        dialog = dialogbuilder.create();
        dialog.show();

    }*/

    //Botão Voltar do Celular
    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        Intent it = new Intent(Solicitacao.this, MainActivity.class);
        startActivity(it);
        finish();
    }

    //Inicilialização de Componetes/Registro de ID´s
    private void IniciarComponentes() {
        BtnDeslogar_Solicitação = findViewById(R.id.Btn_Deslogar_Solicitação);
        BtnAddP_Solicitação = findViewById(R.id.BtnAddP_Solicitação);
        BtnEnviar_Solicitação = findViewById(R.id.BtnEnviar_Solicitação);
        BtnCancelar_Solicitação = findViewById(R.id.BtnCancelar_Solicitação);
        solicitação_list = findViewById(R.id.Solicitação_list);
        Btn_popup = findViewById(R.id.Btn_popup);
        Nome_Solicitante = findViewById(R.id.Nome_Solicitante);
        Nome_Produto_Soli = findViewById(R.id.Nome_Produto_Soli);
        Codigo_Produto_Soli = findViewById(R.id.Codigo_Produto_Soli);
        Quantidade_Soli = findViewById(R.id.Quantidade_Soli);
        Imagem_Produto_Soli = findViewById(R.id.Imagem_Produto_Soli);
        Quantidade_Estoque = findViewById(R.id.Quantidade_Estoque);
        Url_Produto_Solicitação = findViewById(R.id.Url_Produto_Solicitação);
        descrição_prod = findViewById(R.id.descrição_prod);

        list = new ArrayList<Solicitação_info>();
        solicitação_adapter = new Solicitação_Adapter(this, list);

        codigo = findViewById(R.id.Codigo_list_solicitação);
    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}