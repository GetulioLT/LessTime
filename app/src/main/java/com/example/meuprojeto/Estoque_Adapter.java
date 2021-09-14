package com.example.meuprojeto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Estoque_Adapter extends RecyclerView.Adapter<Estoque_Adapter.ViewHolderEstoque> {

    private List<Estoque_info> dados;

    public Estoque_Adapter(Historico_Entrada estoque, List<Estoque_info> dados){
        this.dados = dados;
    }
    public Estoque_Adapter(Estoque estoque, List<Estoque_info> dados){
        this.dados = dados;
    }

    @NonNull
    @Override
    public Estoque_Adapter.ViewHolderEstoque onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.itens, parent, false);

        ViewHolderEstoque holderEstoque = new ViewHolderEstoque(view);

        return holderEstoque;
    }

    @Override
    public void onBindViewHolder(@NonNull Estoque_Adapter.ViewHolderEstoque holder, int position) {

        if ((dados != null) && (dados.size()>0)){
            Estoque_info produto_estoque = dados.get(position);

            holder.list_Produto.setText(produto_estoque.getProduto());
            holder.list_Quant.setText(produto_estoque.getQuantidade());
            holder.list_Codigo.setText(produto_estoque.getCódigo());
            holder.list_Local.setText(produto_estoque.getLocal());
            holder.list_Descrição.setText(produto_estoque.getDescrição());

            Picasso.get().load(produto_estoque.getImagem()).into(holder.list_imagem);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderEstoque extends RecyclerView.ViewHolder{

        public TextView list_Produto, list_Quant, list_Codigo, list_Local, list_Descrição;
        public ImageView list_imagem;

        public ViewHolderEstoque(@NonNull View itemView) {
            super(itemView);

            list_Produto = itemView.findViewById(R.id.list_Produto);
            list_Quant = itemView.findViewById(R.id.list_Quant);
            list_Codigo = itemView.findViewById(R.id.list_Codigo);
            list_Local = itemView.findViewById(R.id.list_Local);
            list_Descrição = itemView.findViewById(R.id.list_Descrição);
            list_imagem = itemView.findViewById(R.id.list_imagem);
        }
    }

}
