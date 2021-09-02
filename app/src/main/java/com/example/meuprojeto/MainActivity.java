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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private EditText tEmail, tSenha;
    private Button btLogin;
    private ProgressBar tProgressBar;
    private CheckBox tlMostrar_Senha;
    FirebaseFirestore bd = FirebaseFirestore.getInstance();
    String usuarioID;
    String Rgrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarComponentes();

        ////Botão de Login

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = tEmail.getText().toString();
                String Senha = tSenha.getText().toString();

                if (Email.isEmpty() || Senha.isEmpty()){
                    alert("Preencha todos os Campos");
                }else {
                    tProgressBar.setVisibility(View.VISIBLE);

                    ////Usuario padrão para cadastro

                    if ((Email.equals("admin@admin.com") && Senha.equals("123456"))) {
                        alert("Login Realizado com Sucesso");
<<<<<<< HEAD
                        Intent it = new Intent(MainActivity.this, cadastroteste.class);
=======
                        Intent it = new Intent(MainActivity.this, Tela_dos_pedidos.class);
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
                        startActivity(it);
                        finish();
                    }else{
                        AutenticarUsuario();
                    }
                }
            }
        });

        ////checkbox de mostrar senha

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

    ////Metodo de Login de usuario

    private void AutenticarUsuario(){
        String Email = tEmail.getText().toString();
        String Senha = tSenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(Email,Senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    tProgressBar.setVisibility(View.VISIBLE);

                    ////Captura do Rgrp do usuario após autenticação do email

                    usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DocumentReference documentReference = bd.collection("Usuarios")
                            .document(usuarioID);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot documentSnapshot = task.getResult();
                                if (documentSnapshot.exists()){
                                    Rgrp = (String) documentSnapshot.getData().get("Rgrp");
                                    Log.d("dyww Rgrp", Rgrp);
                                }
                            }
                        }
                    });

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (Rgrp.equals("Almoxarife")){
                                Intent intent = new Intent(MainActivity.this, Tela_dos_pedidos.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Intent intent = new Intent(MainActivity.this, Solicitacao.class);
                                startActivity(intent);
                                finish();
                            }
                        }
<<<<<<< HEAD
                    },2000);
=======
                    },3000);
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
                }else{
                    alert("Informações Incorretas");
                }
            }
        });

    }

    ////Metodo para iniciar todos os Componentes

    private void IniciarComponentes(){
        tEmail = findViewById(R.id.tEmail);
        tSenha = findViewById(R.id.tSenha);
        btLogin = findViewById(R.id.btLogin);
        tProgressBar = findViewById(R.id.tProgressBar);
        tlMostrar_Senha = findViewById(R.id.tlMostrar_Senha);

    }

    ////Gerador do alerta

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}