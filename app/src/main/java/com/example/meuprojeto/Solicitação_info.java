package com.example.meuprojeto;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Solicitação_info {

    String Nome_Solicitante, Nome_Produto, Quantidade_Produto, Codigo_Produto, Imagem_Produto;

    public Solicitação_info(){
    }

    public Solicitação_info(String nome_Solicitante, String nome_Produto, String quantidade_Produto,
                            String codigo_Produto, String imagem_Produto){
        Nome_Solicitante = nome_Solicitante;
        Nome_Produto = nome_Produto;
        Quantidade_Produto = quantidade_Produto;
        Codigo_Produto = codigo_Produto;
        Imagem_Produto = imagem_Produto;
    }

    public String getNome_Produto() {
        return Nome_Produto;
    }

    public void setNome_Produto(String nome_Produto) {
        Nome_Produto = nome_Produto;
    }

    public String getQuantidade_Produto() {
        return Quantidade_Produto;
    }

    public void setQuantidade_Produto(String quantidade_Produto) {
        Quantidade_Produto = quantidade_Produto;
    }

    public String getCodigo_Produto() {
        return Codigo_Produto;
    }

    public void setCodigo_Produto(String codigo_Produto) {
        Codigo_Produto = codigo_Produto;
    }

    public String getImagem_Produto() {
        return Imagem_Produto;
    }

    public void setImagem_Produto(String imagem_Produto) {
        Imagem_Produto = imagem_Produto;
    }

    public String getNome_Solicitante() {
        return Nome_Solicitante;
    }

    public void setNome_Solicitante(String nome_Solicitante) {
        Nome_Solicitante = nome_Solicitante;
    }

    public void SalvarListaSolicitação(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos Lista Solicitação").child(getNome_Solicitante())
                .child(getNome_Produto()).setValue(this);
    }
}
