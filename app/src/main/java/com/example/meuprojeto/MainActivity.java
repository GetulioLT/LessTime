package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.meuprojeto.Info.Cadastro_Info;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText tEmail, tSenha;
    private Button btLogin;
    private FirebaseAuth mAuth;
    private FirebaseDatabase Database;
    private DatabaseReference reference;
    private ProgressBar tProgressBar;
    private CheckBox tlMostrar_Senha;
    private com.example.meuprojeto.Info.Cadastro_Info Cadastro_Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Usuário logado ou não

       /* if (mAuth.getCurrentUser() != null) {
            Log.i("CreateUser", "Usuário logado");
        }else {
            Log.i("CreateUser", "Usuário não logado");
        }*/

        tEmail = findViewById(R.id.tEmail);
        tSenha = findViewById(R.id.tSenha);
        btLogin = findViewById(R.id.btLogin);
        tProgressBar = findViewById(R.id.tProgressBar);
        tlMostrar_Senha = findViewById(R.id.tlMostrar_Senha);
        mAuth = FirebaseAuth.getInstance();

        Cadastro_Info Usuário = new Cadastro_Info();

        reference = FirebaseDatabase.getInstance().getReference();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = tEmail.getText().toString();
                String Senha = tSenha.getText().toString();


                if (!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Senha)) {
                    tProgressBar.setVisibility(view.VISIBLE);

                    mAuth.signInWithEmailAndPassword(Email, Senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                if ((Email.equals("jose@teste.com") && Senha.equals("123456"))) {
                                    alert("Login Realizado com Sucesso");
                                    Intent it = new Intent(MainActivity.this, Solicitacao.class);
                                    startActivity(it);
                                    finish();
                                }

                                if ((Email.equals("joao@teste.com") && Senha.equals("123456"))) {
                                    alert("Login Realizado com Sucesso");
                                    Intent it = new Intent(MainActivity.this, Tela_dos_pedidos.class);
                                    startActivity(it);
                                    finish();
                                }

                                if ((Email.equals("admin@admin.com") && Senha.equals("123456"))) {
                                    alert("Login Realizado com Sucesso");
                                    Intent it = new Intent(MainActivity.this, cadastroteste.class);
                                    startActivity(it);
                                    finish();
                                }else {
                                    reference.child("Usuários").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Cadastro_Info Dados = snapshot.getValue(Cadastro_Info.class);
                                            //snapshot.getValue().toString();

                                            //Gambiarra pra mandar pra tela

                                         /*   alert("Login Realizado com Sucesso");
                                            Intent mi = new Intent(MainActivity.this, Tela_dos_pedidos.class);
                                            startActivity(mi);
                                            finish();*/

                                           /*if (Dados.getRgrp().equalsIgnoreCase("Almoxarife")) {
                                                alert("Login Realizado com Sucesso");
                                                Intent mi = new Intent(MainActivity.this, Tela_dos_pedidos.class);
                                                startActivity(mi);
                                                finish();
                                           } else {
                                                alert("Login Realizado com Sucesso");
                                                Intent intent = new Intent(MainActivity.this, Solicitacao.class);
                                                startActivity(intent);
                                                finish();
                                           }*/
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            alert("Algo deu errado, tente novamente");
                                            tProgressBar.setVisibility(view.INVISIBLE);
                                        }
                                    });
                                }
                            } else {
                                alert("Login ou senha incorreto");
                                tProgressBar.setVisibility(view.INVISIBLE);
                            }
                        }
                    });
                }
                else {
                    alert("Login ou senha incorreto");
                    tProgressBar.setVisibility(view.INVISIBLE);
                }
            }
        });

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

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
}


}