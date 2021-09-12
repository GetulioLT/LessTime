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

public class Pesquisa_Adapter extends RecyclerView.Adapter<Pesquisa_Adapter.ViewHolderPesquisa> {

    private List<Pesquisa_info> dados;

    public Pesquisa_Adapter(Popup pesquisa, List<Pesquisa_info> dados){
        this.dados = dados;
    }

    @NonNull
    @Override
    public Pesquisa_Adapter.ViewHolderPesquisa onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.itens_pesquisa, parent, false);

        ViewHolderPesquisa holderPesquisa = new ViewHolderPesquisa(view);

        return holderPesquisa;
    }

    @Override
    public void onBindViewHolder(@NonNull Pesquisa_Adapter.ViewHolderPesquisa holder, int position) {

        if ((dados != null) && (dados.size()>0)){
            Pesquisa_info produto_pesquisa = dados.get(position);

            holder.list_Produto_pesq.setText(produto_pesquisa.getProduto());
            holder.list_Quant_pesq.setText(produto_pesquisa.getQuantidade());
            holder.list_Codigo_pesq.setText(produto_pesquisa.getCódigo());
            holder.list_Descrição_pesq.setText(produto_pesquisa.getDescrição());

            Picasso.get().load(produto_pesquisa.getImagem()).into(holder.list_imagem_pesq);
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderPesquisa extends RecyclerView.ViewHolder{

        public TextView list_Produto_pesq, list_Quant_pesq, list_Codigo_pesq, list_Descrição_pesq;
        public ImageView list_imagem_pesq;

        public ViewHolderPesquisa(@NonNull View itemView) {
            super(itemView);
            list_Produto_pesq = itemView.findViewById(R.id.list_Produto_pesq);
            list_Quant_pesq = itemView.findViewById(R.id.list_Quant_pesq);
            list_Codigo_pesq = itemView.findViewById(R.id.list_Codigo_pesq);
            list_Descrição_pesq = itemView.findViewById(R.id.list_Descrição_pesq);
            list_imagem_pesq = itemView.findViewById(R.id.list_imagem_pesq);
        }
    }
}
