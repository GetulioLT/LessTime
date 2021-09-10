package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //Declarando Variáveis
    private EditText tEmail, tSenha;
    private Button btLogin;
    private ProgressBar tProgressBar;
    private CheckBox tlMostrar_Senha;
    final FirebaseFirestore bd = FirebaseFirestore.getInstance();
    String usuarioID;
    String Rgrp;
    String RgrpLog;
    final String[] mensagens = {"Preencha todos os Campos", "Usúario de Cadastro Logado com Sucesso",
            "Usúario Logado com Sucesso", "Usúario não Cadastrado"};
    private String IDLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarComponentes();

        //Botão de Login
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = tEmail.getText().toString();
                String Senha = tSenha.getText().toString();

                if (Email.isEmpty() || Senha.isEmpty()){
                    alert(mensagens[0]);
                }else {
                    tProgressBar.setVisibility(View.VISIBLE);

                    //Usuario padrão para cadastro
                    if ((Email.equals("admin@admin.com") && Senha.equals("123456"))) {
                        alert(mensagens[1]);
                        Intent it = new Intent(MainActivity.this, cadastroteste.class);
                        startActivity(it);
                        finish();
                    }else{
                        AutenticarUsuario();
                    }
                }
            }
        });

        //Mostrar Senha
        tlMostrar_Senha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (tlMostrar_Senha.isChecked()){
                    tSenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    tSenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    //Metódo de Login de usuario
    private void AutenticarUsuario(){
        String Email = tEmail.getText().toString();
        String Senha = tSenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(Email,Senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    tProgressBar.setVisibility(View.VISIBLE);

                    //Captura do Cargo no Firebase
                    usuarioID = Objects.requireNonNull(FirebaseAuth.getInstance()
                            .getCurrentUser()).getUid();
                    DocumentReference documentReference = bd.collection("Usuarios")
                            .document(usuarioID);
                    documentReference.get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot documentSnapshot = task.getResult();
                                if (Objects.requireNonNull(documentSnapshot).exists()){
                                    Rgrp = (String) Objects.requireNonNull(documentSnapshot
                                            .getData()).get("Rgrp");
                                    Log.d("dyww Rgrp", Rgrp);
                                }
                            }
                        }
                    });

                    //Temporização para Login
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (Rgrp.equals("Almoxarife")){
                                Almoxarife();
                                alert(mensagens[2]);
                            }else {
                                alert(mensagens[2]);
                                Solicitante();
                            }
                        }
                    },1500);
                }else{
                    alert(mensagens[3]);
                    tProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    //Método para deixar Usúario Logado
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        Log.d("dyww , log", String.valueOf(usuarioAtual));
        if (usuarioAtual !=null){
            IDLog = usuarioAtual.getUid();

            DocumentReference documentReference = bd.collection("Usuarios")
                    .document(IDLog);
            Log.d("dyww , idlogado", IDLog);

            documentReference.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (Objects.requireNonNull(documentSnapshot).exists()){
                            RgrpLog = (String) Objects.requireNonNull(documentSnapshot
                                    .getData()).get("Rgrp");
                            Log.d("dyww RgrpLog", RgrpLog);
                            if (RgrpLog.equals("Almoxarife")){
                                Almoxarife();
                            }else {
                                Solicitante();
                            }
                        }
                    }
                }
            });
        }
    }

    //Direcionamento Almoxarife
    private void Almoxarife() {
        Intent intent = new Intent
                (MainActivity.this, Tela_dos_pedidos.class);
        startActivity(intent);
        finish();
    }

    //Direcionamento Solicitante
    private void Solicitante() {
        Intent intent = new Intent
                (MainActivity.this, Solicitacao.class);
        startActivity(intent);
        finish();
    }

    //Inicilialização de Componetes/Registro de ID´s
    private void IniciarComponentes(){
        tEmail = findViewById(R.id.tEmail);
        tSenha = findViewById(R.id.tSenha);
        btLogin = findViewById(R.id.btLogin);
        tProgressBar = findViewById(R.id.tProgressBar);
        tlMostrar_Senha = findViewById(R.id.tlMostrar_Senha);
    }

    //Metodos de Alertas
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}