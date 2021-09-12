package com.example.meuprojeto;

public class Estoque_info {

    String Produto, Quantidade, Código, Local,
            Descrição, Imagem;

    public Estoque_info() {
    }

    public Estoque_info (String quantidade, String código, String local,
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

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        Imagem = imagem;
    }
}
