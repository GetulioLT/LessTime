package com.example.meuprojeto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Solicitação_Adapter extends RecyclerView.Adapter<Solicitação_Adapter.ViewHolderSolicitação> {

    private List<Solicitação_info> dados;

    public Solicitação_Adapter(Solicitacao solicitacao, List<Solicitação_info> dados){
        this.dados = dados;
    }

    @NonNull
    @Override
    public Solicitação_Adapter.ViewHolderSolicitação onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.itens_solicitacao, parent, false);

        ViewHolderSolicitação holderSolicitação = new ViewHolderSolicitação(view);

        return holderSolicitação;
    }

    @Override
    public void onBindViewHolder(@NonNull Solicitação_Adapter.ViewHolderSolicitação holder, int position) {

        if ((dados !=null) && (dados.size() > 0)){
            Solicitação_info produto_solicitação = dados.get(position);

            holder.Nome_list_solicitação.setText(produto_solicitação.getNome_Produto());
            holder.Quantidade_list_solicitação.setText(produto_solicitação.getQuantidade_Produto());
            holder.Codigo_list_solicitação.setText(produto_solicitação.getCodigo_Produto());

            Picasso.get().load(produto_solicitação.getImagem_Produto()).into(holder.Imagem_list_solicitação);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
    public class ViewHolderSolicitação extends RecyclerView.ViewHolder{

        public TextView Nome_list_solicitação,Quantidade_list_solicitação, Codigo_list_solicitação,
                Nome_Solicitante;
        public ImageView Imagem_list_solicitação;
        public ImageButton Delete_produto;

        public ViewHolderSolicitação(@NonNull View itemView) {
            super(itemView);

            Nome_list_solicitação = itemView.findViewById(R.id.nome_p_almo);
            Quantidade_list_solicitação = itemView.findViewById(R.id.quantidade_p_almo);
            Codigo_list_solicitação = itemView.findViewById(R.id.codigo_p_almo);
            Imagem_list_solicitação = itemView.findViewById(R.id.imagem_p_almo);
            Delete_produto = itemView.findViewById(R.id.Delete_produto);
            Nome_Solicitante = itemView.findViewById(R.id.Nome_Solicitante);
        }
    }
}
