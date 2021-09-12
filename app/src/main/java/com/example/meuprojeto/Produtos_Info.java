 package com.example.meuprojeto;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Produtos_Info {

        String Produto, Quantidade, Código, Local, Descrição, Imagem;

        public Produtos_Info() {
        }

        public Produtos_Info(String quantidade, String código, String local,
                             String descrição, String imagem, String produto) {
                Quantidade = quantidade;
                Imagem = imagem;
                Produto = produto;
                Código = código;
                Local = local;
                Descrição = descrição;
        }

        public void setImagem(String imagem){
                Imagem = imagem;
        }

        public String getImagem(){
                return Imagem;
        }

        public void setProduto(String produto){
                Produto = produto;
        }

        public String getProduto() {
                return Produto;
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

        public void salvar() {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                reference.child("Produtos").child(getProduto()).setValue(this);
        }
}
