package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class Solicitacao extends AppCompatActivity {

    //Declarando Variáveis
    Button BtnDeslogar_Solicitação, BtnAddP_Solicitação, BtnEnviar_Solicitação,
            BtnCancelar_Solicitação, Btn_popup;
    EditText EdtQuantP_Solicitação;
    TextView TvCódigoP_Solicitação, TvNomeP_Solicitação;
    RecyclerView solicitação_list;
    AlertDialog.Builder dialogbuilder;
    AlertDialog dialog;
    FirebaseFirestore bd = FirebaseFirestore.getInstance();
    String UsuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);

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
                    TvNomeP_Solicitação.setText(documentSnapshot.getString("Nome"));
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
        EdtQuantP_Solicitação = findViewById(R.id.EdtQuantP_Solicitação);
        TvCódigoP_Solicitação = findViewById(R.id.TvCódigoP_Solicitação);
        TvNomeP_Solicitação = findViewById(R.id.TvNomeP_Solicitação);
        solicitação_list = findViewById(R.id.Solicitação_list);
        Btn_popup = findViewById(R.id.Btn_popup);
    }
}