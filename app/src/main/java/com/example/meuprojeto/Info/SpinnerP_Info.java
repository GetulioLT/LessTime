package com.example.meuprojeto.Info;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SpinnerP_Info {

    String Produto;

    public SpinnerP_Info() {
    }

    public SpinnerP_Info(String produto) {
        Produto = produto;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String produto) {
        Produto = produto;
    }

    public void Salvar(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("SpinnerP").child(getProduto());
    }
}

