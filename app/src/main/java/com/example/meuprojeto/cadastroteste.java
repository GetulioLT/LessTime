package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class cadastroteste extends AppCompatActivity {

    //Declarando Variáveis
    private Button BtnVoltar_Cadastro, BtnCadastrar;
    private EditText EdtUsuario_Cadastro, EdtEmail_Cadastro, EdtMatricula_Cadastro,
            EdtTelefone_Cadastro, EdtSenha_Cadastro, EdtConfirmarSenha_Cadastro;
    private CheckBox CbMostrarSenha_Cadastro;
    private ProgressBar tProgressBar;
    private RadioButton Almoxarife, Solicitante;
    final String[] mensagens = {"Preencha Todos os Campos","Cadastro Realizado com Sucesso",
            "Senhas não coincidem","Usuario já Cadastrado", "Digite uma senha com no minimo 6 caracteres",
            "Email já cadastrado", "Email invalido", "erro ao cadastrar usuario"};
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastroteste);

        IniciarComponentes();

        //Mostrar Senha
        CbMostrarSenha_Cadastro
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        //Voltar Tela Inicial
        BtnVoltar_Cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(cadastroteste.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        //Cadastrar Usúario
        BtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nome = EdtUsuario_Cadastro.getText().toString();
                String Email = EdtEmail_Cadastro.getText().toString();
                String Matricula = EdtMatricula_Cadastro.getText().toString();
                String Telefone = EdtTelefone_Cadastro.getText().toString();
                String Senha = EdtSenha_Cadastro.getText().toString();
                String CSenha = EdtConfirmarSenha_Cadastro.getText().toString();

                //Condição para Cadastro de Usúario
                if (Nome.isEmpty() || Email.isEmpty() || Matricula.isEmpty() || Telefone.isEmpty()
                       || Senha.isEmpty() || CSenha.isEmpty()) {
                   alert(mensagens[0]);
                }else {
                   CadastrarUsuario();
                }
            }
        });
    }

    //Metódo de Cadastro de Usúario
    private void CadastrarUsuario() {
        String Email = EdtEmail_Cadastro.getText().toString();
        String Senha = EdtSenha_Cadastro.getText().toString();
        String CSenha = EdtConfirmarSenha_Cadastro.getText().toString();

        //Condição que as Senhas Sejam Iguais
        if (Senha.equals(CSenha)){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email,Senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    tProgressBar.setVisibility(View.VISIBLE);

                    //Condição para Salvar os Usuarios para caso as Senhas estajam Iguais
                    if (task.isSuccessful()){
                        alert(mensagens[1]);
                        tProgressBar.setVisibility(View.INVISIBLE);

                        SalvarDadosUsuario();
                    }else{
                        //Condições para cada tipo de Erro
                        String erro;
                        try {
                            throw Objects.requireNonNull(task.getException());
                        }catch (FirebaseAuthWeakPasswordException e) {
                            erro = mensagens[4];
                        }catch (FirebaseAuthUserCollisionException e) {
                            erro = mensagens[5];
                        }catch (FirebaseAuthInvalidCredentialsException e){
                            erro = mensagens[6];
                        }catch (Exception e){
                            erro = mensagens[7];
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

    //Envio de dados para Firebase
    private void SalvarDadosUsuario(){

        //Recuperação do que Foi Escrito
        String Nome = EdtUsuario_Cadastro.getText().toString();
        String Email = EdtEmail_Cadastro.getText().toString();
        String Matricula = EdtMatricula_Cadastro.getText().toString();
        String Telefone = EdtTelefone_Cadastro.getText().toString();
        String Almo = Almoxarife.getText().toString();
        String Soli = Solicitante.getText().toString();

        FirebaseFirestore bd = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios = new HashMap<>();

        //Envio de cada Informação de Acordo com o "nome" do Campo
        usuarios.put("Nome",Nome);
        usuarios.put("Email",Email);
        usuarios.put("Matricula",Matricula);
        usuarios.put("Telefone",Telefone);

        if (Almoxarife.isChecked()){
            usuarios.put("Rgrp",Almo);
        }else {
            usuarios.put("Rgrp",Soli);
        }

        //Recuperação do ID do Usúario
        usuarioID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        //Criação das Coleções(Usuarios) e o Documento(ID) no FireStore
        DocumentReference documentReference = bd.collection("Usuarios")
            .document(usuarioID);
        //Retorno de um Log caso seja um Sucesso ou não
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
        LimparCampos();
    }

    //Metódo para Limpar os Campos
    private void LimparCampos() {
        EdtUsuario_Cadastro.getText().clear();
        EdtEmail_Cadastro.getText().clear();
        EdtMatricula_Cadastro.getText().clear();
        EdtTelefone_Cadastro.getText().clear();
        EdtSenha_Cadastro.getText().clear();
        EdtConfirmarSenha_Cadastro.getText().clear();
        Almoxarife.setChecked(false);
        Solicitante.setChecked(false);
        CbMostrarSenha_Cadastro.setChecked(false);
    }

    //Registrando Id das variáveis
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

    //Metodos de Alertas
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
    private void erro(String erro){
        Toast.makeText(this,erro,Toast.LENGTH_LONG).show();
    }
}