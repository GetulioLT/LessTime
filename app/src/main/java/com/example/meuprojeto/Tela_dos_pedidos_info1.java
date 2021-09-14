package com.example.meuprojeto;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Tela_dos_pedidos_info1 implements Serializable {

    String Nome;
    String Imagem;
    String Produto;
    String Quantidade;
    String Codigo;
    String Local;
    String Descrição;

    public Tela_dos_pedidos_info1(){
    }

    public Tela_dos_pedidos_info1(String nome, String imagem, String produto,
                                  String quantidade, String codigo, String local,
                                  String descrição){
        Nome = nome;
        Imagem = imagem;
        Produto = produto;
        Quantidade = quantidade;
        Codigo = codigo;
        Local = local;
        Descrição = descrição;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        Imagem = imagem;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String produto) {
        Produto = produto;
    }

    public String getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(String quantidade) {
        Quantidade = quantidade;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getDescrição() {
        return Descrição;
    }

    public void setDescrição(String descrição) {
        Descrição = descrição;
    }

    public void salvarTelaDePedidos() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos Pedidos").child(getNome()).child(getProduto()).setValue(this);
    }
}
