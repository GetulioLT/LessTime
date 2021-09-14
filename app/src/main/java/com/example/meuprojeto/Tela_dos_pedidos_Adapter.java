package com.example.meuprojeto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Tela_dos_pedidos_Adapter extends RecyclerView.Adapter
        <Tela_dos_pedidos_Adapter.ViewHolderTela_dos_pedidos> {

    private List<Tela_dos_pedidos_info1> dados;

    public Tela_dos_pedidos_Adapter(Tela_dos_pedidos tela_dos_pedidos, List<Tela_dos_pedidos_info1> dados){
        this.dados = dados;
    }

    @NonNull
    @Override
    public Tela_dos_pedidos_Adapter.ViewHolderTela_dos_pedidos onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.itens_historico, parent, false);

        ViewHolderTela_dos_pedidos holderTela_dos_pedidos = new ViewHolderTela_dos_pedidos
                (view, parent.getContext());

        return holderTela_dos_pedidos;
    }

    @Override
    public void onBindViewHolder(@NonNull Tela_dos_pedidos_Adapter.ViewHolderTela_dos_pedidos holder,
                                 int position) {
        if ((dados != null) && (dados.size() > 0)) {
            Tela_dos_pedidos_info1 produto_tela_de_pedidos = dados.get(position);

            holder.NomeSoli.setText(produto_tela_de_pedidos.getNome());
            holder.ProdutoSoli.setText(produto_tela_de_pedidos.getProduto());
            holder.Quantidadesoli.setText(produto_tela_de_pedidos.getQuantidade());
            holder.CodigoSoli.setText(produto_tela_de_pedidos.getCodigo());
            holder.DescriçãoSoli.setText(produto_tela_de_pedidos.getDescrição());

            Picasso.get().load(produto_tela_de_pedidos.getImagem()).into(holder.Image_soli);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderTela_dos_pedidos extends  RecyclerView.ViewHolder{

        public TextView NomeSoli, ProdutoSoli, Quantidadesoli, CodigoSoli, Local_soli, DescriçãoSoli,
                Nome_pegar;
        public ImageView Image_soli;

        public ViewHolderTela_dos_pedidos(@NonNull View itemView, Context context) {
            super(itemView);

            NomeSoli = itemView.findViewById(R.id.NomeSoli);
            ProdutoSoli = itemView.findViewById(R.id.ProdutoSoli);
            Quantidadesoli = itemView.findViewById(R.id.Quantidadesoli);
            CodigoSoli = itemView.findViewById(R.id.CodigoSoli);

            DescriçãoSoli = itemView.findViewById(R.id.DescriçãoSoli);
            Image_soli = itemView.findViewById(R.id.Image_soli);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dados.size()>0) {
                        Tela_dos_pedidos_info1 produto_tela = dados.get(getLayoutPosition());

                        /*Intent it = new Intent(context, Tela_dos_pedidos.class);
                        it.putExtra("Profuto_tela", produto_tela);
                        context.startActivity(it);*/
                    }
                }
            });

        }
    }
}
