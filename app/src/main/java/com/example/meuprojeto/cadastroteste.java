package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
<<<<<<< HEAD
import android.os.Handler;
=======
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
=======
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
=======
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Timer;
=======
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
=======
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2

public class cadastroteste extends AppCompatActivity {

    ////Declarando Variáveis

    private Button BtnVoltar_Cadastro, BtnCadastrar;
<<<<<<< HEAD
<<<<<<< HEAD
    private EditText EdtUsuario_Cadastro, EdtEmail_Cadastro, EdtMatricula_Cadastro,
            EdtTelefone_Cadastro, EdtSenha_Cadastro, EdtConfirmarSenha_Cadastro;
    private CheckBox CbMostrarSenha_Cadastro;
=======

    private EditText EdtUsuario_Cadastro, EdtEmail_Cadastro, EdtMatricula_Cadastro,
            EdtTelefone_Cadastro, EdtSenha_Cadastro, EdtConfirmarSenha_Cadastro;

    private CheckBox CbMostrarSenha_Cadastro;
=======

    private EditText EdtUsuario_Cadastro, EdtEmail_Cadastro, EdtMatricula_Cadastro,
            EdtTelefone_Cadastro, EdtSenha_Cadastro, EdtConfirmarSenha_Cadastro;

    private CheckBox CbMostrarSenha_Cadastro;
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2

    String[] mensagens = {"Preencha Todos os Campos","Cadastro Realizado com Sucesso",
            "Senhas não coincidem","Usuario já Cadastrado"};
    String usuarioID;

    private ProgressBar tProgressBar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase Database;
    private DatabaseReference Reference;
    private Cadastro_Info Cadastro_Info;
    private RadioButton Almoxarife, Solicitante;
    private RadioGroup Rgrp;
    private String Função;
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2

    String[] mensagens = {"Preencha Todos os Campos","Cadastro Realizado com Sucesso",
            "Senhas não coincidem","Usuario já Cadastrado"};

    String usuarioID;

    private ProgressBar tProgressBar;
    private RadioButton Almoxarife, Solicitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastroteste);

        IniciarComponentes();

        ////Mostrar Senha

        CbMostrarSenha_Cadastro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (CbMostrarSenha_Cadastro.isChecked()){
                    EdtSenha_Cadastro.setTransformationMethod
                            (HideReturnsTransformationMethod.getInstance());
                    EdtConfirmarSenha_Cadastro.setTransformationMethod
                            (HideReturnsTransformationMethod.getInstance());
                }
                else {
                    EdtSenha_Cadastro.setTransformationMethod
                            (PasswordTransformationMethod.getInstance());
                    EdtConfirmarSenha_Cadastro.setTransformationMethod
                            (PasswordTransformationMethod.getInstance());
                }
            }
        });

        ////Voltar tela Inicial

        BtnVoltar_Cadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(cadastroteste.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

<<<<<<< HEAD
<<<<<<< HEAD
        ////Cadastrar Usuario

        BtnCadastrar.setOnClickListener(new View.OnClickListener() {
=======
        BtnCadastrar.setOnClickListener(new View.OnClickListener() {
=======
        BtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nome = EdtUsuario_Cadastro.getText().toString();
                String Email = EdtEmail_Cadastro.getText().toString();
                String Matricula = EdtMatricula_Cadastro.getText().toString();
                String Telefone = EdtTelefone_Cadastro.getText().toString();
                String Senha = EdtSenha_Cadastro.getText().toString();
                String CSenha = EdtConfirmarSenha_Cadastro.getText().toString();


               if (Nome.isEmpty() || Email.isEmpty() || Matricula.isEmpty() || Telefone.isEmpty()
                       || Senha.isEmpty() || CSenha.isEmpty()) {

                   alert(mensagens[0]);

               }else {
                   CadastrarUsuario();

               }

            }
        });

        /*BtnCadastro.setOnClickListener(new View.OnClickListener() {
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
            @Override
            public void onClick(View v) {

                String Nome = EdtUsuario_Cadastro.getText().toString();
                String Email = EdtEmail_Cadastro.getText().toString();
                String Matricula = EdtMatricula_Cadastro.getText().toString();
                String Telefone = EdtTelefone_Cadastro.getText().toString();
                String Senha = EdtSenha_Cadastro.getText().toString();
                String CSenha = EdtConfirmarSenha_Cadastro.getText().toString();


               if (Nome.isEmpty() || Email.isEmpty() || Matricula.isEmpty() || Telefone.isEmpty()
                       || Senha.isEmpty() || CSenha.isEmpty()) {

                   alert(mensagens[0]);

               }else {
                   CadastrarUsuario();

               }

            }
        });

        /*BtnCadastro.setOnClickListener(new View.OnClickListener() {
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
            @Override
            public void onClick(View v) {
                String Nome = EdtUsuario_Cadastro.getText().toString();
                String Email = EdtEmail_Cadastro.getText().toString();
                String Matricula = EdtMatricula_Cadastro.getText().toString();
                String Telefone = EdtTelefone_Cadastro.getText().toString();
                String Senha = EdtSenha_Cadastro.getText().toString();
                String CSenha = EdtConfirmarSenha_Cadastro.getText().toString();

               if (Nome.isEmpty() || Email.isEmpty() || Matricula.isEmpty() || Telefone.isEmpty()
                       || Senha.isEmpty() || CSenha.isEmpty()) {

                   alert(mensagens[0]);

               }else {
                   CadastrarUsuario();
               }
            }
        });
    }

    private void LimparCampos() {
        EdtUsuario_Cadastro.getText().clear();
        EdtEmail_Cadastro.getText().clear();
        EdtMatricula_Cadastro.getText().clear();
        EdtTelefone_Cadastro.getText().clear();
        EdtSenha_Cadastro.getText().clear();
        EdtConfirmarSenha_Cadastro.getText().clear();
    }

<<<<<<< HEAD
    ////Metodo para cadastro

    private void CadastrarUsuario() {
        String Email = EdtEmail_Cadastro.getText().toString();
        String Senha = EdtSenha_Cadastro.getText().toString();
        String CSenha = EdtConfirmarSenha_Cadastro.getText().toString();

        if (Senha.equals(CSenha)){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email,Senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            tProgressBar.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()){
                                alert(mensagens[1]);
                                tProgressBar.setVisibility(View.INVISIBLE);

                                SalvarDadosUsuario();
                            }else{
                                String erro;
                                try {
                                    throw task.getException();
                                }catch (FirebaseAuthWeakPasswordException e) {
                                    erro = "Digite uma senha com no minimo 6 caracteres";
                                }catch (FirebaseAuthUserCollisionException e) {
                                    erro = "Email já cadastrado";
                                }catch (FirebaseAuthInvalidCredentialsException e){
                                    erro = "Email invalido";
                                }catch (Exception e){
                                    erro = "erro ao cadastrar usuario";
=======
                Cadastro_Info.setEmail (EdtEmail_Cadastro.getText().toString());
                Cadastro_Info.setUsuario (EdtUsuario_Cadastro.getText().toString());
                Cadastro_Info.setMatrícula (EdtMatricula_Cadastro.getText().toString());
                Cadastro_Info.setTelefone (EdtTelefone_Cadastro.getText().toString());
                String Senha = EdtSenha_Cadastro.getText().toString();
                String ConfirmarSenha = EdtConfirmarSenha_Cadastro.getText().toString();

                if (!TextUtils.isEmpty(Cadastro_Info.getEmail())
                        && !TextUtils.isEmpty(Cadastro_Info.getUsuario())
                        && !TextUtils.isEmpty(Cadastro_Info.getMatrícula())
                        && !TextUtils.isEmpty(Cadastro_Info.getTelefone())
                        && !TextUtils.isEmpty(Senha) && !TextUtils.isEmpty(ConfirmarSenha)){


                    if (Senha.equals(ConfirmarSenha)) {
                        tProgressBar.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(Cadastro_Info.getEmail(), Senha)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Cadastro_Info.setId(mAuth.getUid());
                                    Cadastro_Info.salvar();
                                    alert("Usuário Registrado com sucesso");
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
                                }
                                erro(erro);

                                tProgressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
        }else{
            alert(mensagens[2]);
        }
    }

    ////Envio de dados para Firebase

    private void SalvarDadosUsuario(){
        String Nome = EdtUsuario_Cadastro.getText().toString();
        String Email = EdtEmail_Cadastro.getText().toString();
        String Matricula = EdtMatricula_Cadastro.getText().toString();
        String Telefone = EdtTelefone_Cadastro.getText().toString();
        String Almo = Almoxarife.getText().toString();
        String Soli = Solicitante.getText().toString();

        FirebaseFirestore bd = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios = new HashMap<>();

        usuarios.put("Nome",Nome);
        usuarios.put("Email",Email);
        usuarios.put("Matricula",Matricula);
        usuarios.put("Telefone",Telefone);

        if (Almoxarife.isChecked()){
            usuarios.put("Rgrp",Almo);
        }else {
            usuarios.put("Rgrp",Soli);
        }

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = bd.collection("Usuarios")
                .document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("bd","sucesso ao salvar");
            }
<<<<<<< HEAD
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("bd_erro","erro ao salvar"+e.toString());
            }
<<<<<<< HEAD
        });
        LimparCampos();
    }

        ////Registrando Id das variáveis
=======
=======
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
        });*/

    }

    private void CadastrarUsuario() {

        String Email = EdtEmail_Cadastro.getText().toString();
        String Senha = EdtSenha_Cadastro.getText().toString();
        String CSenha = EdtConfirmarSenha_Cadastro.getText().toString();



        if (Senha.equals(CSenha)){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email,Senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            tProgressBar.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()){
                                alert(mensagens[1]);
                                tProgressBar.setVisibility(View.INVISIBLE);

                                SalvarDadosUsuario();

                            }else{

                                String erro;
                                try {
                                    throw task.getException();
                                }catch (FirebaseAuthWeakPasswordException e) {
                                    erro = "Digite uma senha com no minimo 6 caracteres";
                                }catch (FirebaseAuthUserCollisionException e) {
                                    erro = "Email já cadastrado";
                                }catch (FirebaseAuthInvalidCredentialsException e){
                                    erro = "Email invalido";
                                }catch (Exception e){
                                    erro = "erro ao cadastrar usuario";
                                }
                                erro(erro);

                                tProgressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
        }else{
            alert(mensagens[2]);
        }
<<<<<<< HEAD
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2

    private void IniciarComponentes(){
        BtnCadastrar = findViewById(R.id.BtnCadastrar);
        BtnVoltar_Cadastro = findViewById(R.id.BtnVoltar_Cadastro);
        EdtUsuario_Cadastro = findViewById(R.id.EdtUsuario_Cadastro);
        EdtEmail_Cadastro = findViewById(R.id.EdtEmail_Cadastro);
        EdtMatricula_Cadastro = findViewById(R.id.EdtMatricula_Cadastro);
        EdtTelefone_Cadastro = findViewById(R.id.EdtTelefone_Cadastro);
        EdtSenha_Cadastro = findViewById(R.id.EdtSenha_Cadastro);
        EdtConfirmarSenha_Cadastro = findViewById(R.id.EdtConfirmarSenha_Cadastro);
        CbMostrarSenha_Cadastro = findViewById(R.id.CbMostrarSenha_Cadastro);
        tProgressBar = findViewById(R.id.tProgressBar);
        Almoxarife = findViewById(R.id.RBtnAlmoxarife);
        Solicitante = findViewById(R.id.RBtnSolicitante);
    }

<<<<<<< HEAD
    ////Metodos de Alertas
=======
>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2

=======
    private void SalvarDadosUsuario(){
        String Nome = EdtUsuario_Cadastro.getText().toString();
        String Email = EdtEmail_Cadastro.getText().toString();
        String Matricula = EdtMatricula_Cadastro.getText().toString();
        String Telefone = EdtTelefone_Cadastro.getText().toString();
        String Almo = Almoxarife.getText().toString();
        String Soli = Solicitante.getText().toString();


        FirebaseFirestore bd = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios = new HashMap<>();

        usuarios.put("Nome",Nome);
        usuarios.put("Email",Email);
        usuarios.put("Matricula",Matricula);
        usuarios.put("Telefone",Telefone);

        if (Almoxarife.isChecked()){
            usuarios.put("Rgrp",Almo);
        }else {
            usuarios.put("Rgrp",Soli);
        }

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = bd.collection("Usuarios")
                .document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("bd","sucesso ao salvar");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("bd_erro","erro ao salvar"+e.toString());
            }
        });
    }

<<<<<<< HEAD
        ////Registrando Id das variáveis

    private void IniciarComponentes(){


        Reference = Database.getInstance().getReference().child("User");

        BtnCadastrar = findViewById(R.id.BtnCadastrar);
        BtnVoltar_Cadastro = findViewById(R.id.BtnVoltar_Cadastro);
        EdtUsuario_Cadastro = findViewById(R.id.EdtUsuario_Cadastro);
        EdtEmail_Cadastro = findViewById(R.id.EdtEmail_Cadastro);
        EdtMatricula_Cadastro = findViewById(R.id.EdtMatricula_Cadastro);
        EdtTelefone_Cadastro = findViewById(R.id.EdtTelefone_Cadastro);
        EdtSenha_Cadastro = findViewById(R.id.EdtSenha_Cadastro);
        EdtConfirmarSenha_Cadastro = findViewById(R.id.EdtConfirmarSenha_Cadastro);
        CbMostrarSenha_Cadastro = findViewById(R.id.CbMostrarSenha_Cadastro);
        tProgressBar = findViewById(R.id.tProgressBar);
        Rgrp = findViewById(R.id.RGrp);
        Almoxarife = findViewById(R.id.RBtnAlmoxarife);
        Solicitante = findViewById(R.id.RBtnSolicitante);
    }

>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
=======
    private void SalvarDadosUsuario(){
        String Nome = EdtUsuario_Cadastro.getText().toString();
        String Email = EdtEmail_Cadastro.getText().toString();
        String Matricula = EdtMatricula_Cadastro.getText().toString();
        String Telefone = EdtTelefone_Cadastro.getText().toString();
        String Almo = Almoxarife.getText().toString();
        String Soli = Solicitante.getText().toString();


        FirebaseFirestore bd = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios = new HashMap<>();

        usuarios.put("Nome",Nome);
        usuarios.put("Email",Email);
        usuarios.put("Matricula",Matricula);
        usuarios.put("Telefone",Telefone);

        if (Almoxarife.isChecked()){
            usuarios.put("Rgrp",Almo);
        }else {
            usuarios.put("Rgrp",Soli);
        }

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = bd.collection("Usuarios")
                .document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("bd","sucesso ao salvar");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("bd_erro","erro ao salvar"+e.toString());
            }
        });
    }

        ////Registrando Id das variáveis

    private void IniciarComponentes(){


        Reference = Database.getInstance().getReference().child("User");

        BtnCadastrar = findViewById(R.id.BtnCadastrar);
        BtnVoltar_Cadastro = findViewById(R.id.BtnVoltar_Cadastro);
        EdtUsuario_Cadastro = findViewById(R.id.EdtUsuario_Cadastro);
        EdtEmail_Cadastro = findViewById(R.id.EdtEmail_Cadastro);
        EdtMatricula_Cadastro = findViewById(R.id.EdtMatricula_Cadastro);
        EdtTelefone_Cadastro = findViewById(R.id.EdtTelefone_Cadastro);
        EdtSenha_Cadastro = findViewById(R.id.EdtSenha_Cadastro);
        EdtConfirmarSenha_Cadastro = findViewById(R.id.EdtConfirmarSenha_Cadastro);
        CbMostrarSenha_Cadastro = findViewById(R.id.CbMostrarSenha_Cadastro);
        tProgressBar = findViewById(R.id.tProgressBar);
        Rgrp = findViewById(R.id.RGrp);
        Almoxarife = findViewById(R.id.RBtnAlmoxarife);
        Solicitante = findViewById(R.id.RBtnSolicitante);
    }

>>>>>>> 3db9bc0e28fd5f6bdad7bf624412111a515742d2
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private void erro(String erro){
        Toast.makeText(this,erro,Toast.LENGTH_LONG).show();
    }
}