package com.example.meuprojeto;

import java.io.Serializable;

public class Pesquisa_info implements Serializable {

    String Produto, Quantidade, Código, Local,
            Descrição, Imagem;

    public Pesquisa_info() {
    }

    public Pesquisa_info (String quantidade, String código, String local,
                         String descrição, String imagem, String produto) {
        Quantidade = quantidade;
        Imagem = imagem;
        Produto = produto;
        Código = código;
        Local = local;
        Descrição = descrição;
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

    public String getCódigo() {
        return Código;
    }

    public void setCódigo(String código) {
        Código = código;
    }

    public String getDescrição() {
        return Descrição;
    }

    public void setDescrição(String descrição) {
        Descrição = descrição;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        Imagem = imagem;
    }
}
