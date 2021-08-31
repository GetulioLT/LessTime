package com.example.meuprojeto.Info;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro_Info {

    private String Id;
    private String usuario;
    private String Email;
    private String Matrícula;
    private String Telefone;
    private String Rgrp;

    public Cadastro_Info() {
    }

    public Cadastro_Info(String id, String usuario, String email, String matrícula, String telefone, String almoxarife, String solicitante, String rgrp) {
        Id = id;
        this.usuario = usuario;
        Email = email;
        Matrícula = matrícula;
        Telefone = telefone;
        Rgrp = rgrp;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMatrícula() {
        return Matrícula;
    }

    public void setMatrícula(String matrícula) {
        Matrícula = matrícula;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getRgrp() {
        return Rgrp;
    }

    public void setRgrp(String rgrp) {
        Rgrp = rgrp;
    }

    public void salvar(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Usuários").child(getId()).setValue(this);
    }
}
