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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadastroteste extends AppCompatActivity {

    ////Declarando Variáveis

    private Button BtnVoltar_Cadastro;
    private EditText EdtUsuario_Cadastro;
    private EditText EdtEmail_Cadastro;
    private EditText EdtMatricula_Cadastro;
    private EditText EdtTelefone_Cadastro;
    private EditText EdtSenha_Cadastro;
    private EditText EdtConfirmarSenha_Cadastro;
    private CheckBox CbMostrarSenha_Cadastro;
    private Button BtnCadastro;
    private ProgressBar tProgressBar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase Database;
    private DatabaseReference Reference;
    private Cadastro_Info Cadastro_Info;
    private RadioButton Almoxarife, Solicitante;
    private RadioGroup Rgrp;
    private String Função;

    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastroteste);

        ////Registrando Id das variáveis

        mAuth = FirebaseAuth.getInstance();
        Reference = Database.getInstance().getReference().child("User");

        BtnVoltar_Cadastro = findViewById(R.id.BtnVoltar_Cadastro);
        EdtUsuario_Cadastro = findViewById(R.id.EdtUsuario_Cadastro);
        EdtEmail_Cadastro = findViewById(R.id.EdtEmail_Cadastro);
        EdtMatricula_Cadastro = findViewById(R.id.EdtMatricula_Cadastro);
        EdtTelefone_Cadastro = findViewById(R.id.EdtTelefone_Cadastro);
        EdtSenha_Cadastro = findViewById(R.id.EdtSenha_Cadastro);
        EdtConfirmarSenha_Cadastro = findViewById(R.id.EdtConfirmarSenha_Cadastro);
        CbMostrarSenha_Cadastro = findViewById(R.id.CbMostrarSenha_Cadastro);
        BtnCadastro = findViewById(R.id.BtnCadastro);
        tProgressBar = findViewById(R.id.tProgressBar);
        Rgrp = findViewById(R.id.RGrp);
        Almoxarife = findViewById(R.id.RBtnAlmoxarife);
        Solicitante = findViewById(R.id.RBtnSolicitante);

        Cadastro_Info = new Cadastro_Info();

        ////Mostrar Senha

        CbMostrarSenha_Cadastro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (CbMostrarSenha_Cadastro.isChecked()){
                    EdtSenha_Cadastro.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    EdtConfirmarSenha_Cadastro.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    EdtSenha_Cadastro.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    EdtConfirmarSenha_Cadastro.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        BtnVoltar_Cadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(cadastroteste.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        BtnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ////Salvando infos do RadioGroup

                String Almo = Almoxarife.getText().toString();
                String Soli = Solicitante.getText().toString();

                if (Almoxarife.isChecked()){
                    Cadastro_Info.setRgrp(Almo);
                }else {
                    Cadastro_Info.setRgrp(Soli);
                }

                ////Criando variaveis e definindo valores a algumas outras

                Cadastro_Info.setEmail (EdtEmail_Cadastro.getText().toString());
                Cadastro_Info.setUsuario (EdtUsuario_Cadastro.getText().toString());
                Cadastro_Info.setMatrícula (EdtMatricula_Cadastro.getText().toString());
                Cadastro_Info.setTelefone (EdtTelefone_Cadastro.getText().toString());
                String Senha = EdtSenha_Cadastro.getText().toString();
                String ConfirmarSenha = EdtConfirmarSenha_Cadastro.getText().toString();

                if (!TextUtils.isEmpty(Cadastro_Info.getEmail()) && !TextUtils.isEmpty(Cadastro_Info.getUsuario()) && !TextUtils.isEmpty(Cadastro_Info.getMatrícula()) && !TextUtils.isEmpty(Cadastro_Info.getTelefone()) && !TextUtils.isEmpty(Senha) && !TextUtils.isEmpty(ConfirmarSenha)){
                    if (Senha.equals(ConfirmarSenha)) {
                        tProgressBar.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(Cadastro_Info.getEmail(), Senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Cadastro_Info.setId(mAuth.getUid());
                                    Cadastro_Info.salvar();
                                    alert("Usuário Registrado com sucesso");
                                }
                            }
                        });
                    }else {
                        alert("Senhas não coincidem");
                        tProgressBar.setVisibility(View.INVISIBLE);
                    }
               } else {
                    alert("Informações faltando");
                }
            }
        });

    }


    private void
    alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

    }
}