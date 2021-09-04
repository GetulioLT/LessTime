package com.example.meuprojeto;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

    Context context;

    ArrayList<Produtos_Info> list;


    public Myadapter(Context context, ArrayList<Produtos_Info> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itens,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Produtos_Info user = list.get(position);
        holder.Produto.setText(user.getProduto());
        holder.Quantidade.setText(user.getQuantidade());
        holder.Código.setText(user.getCódigo());
        holder.Local.setText(user.getLocal());
        holder.Descrição.setText(user.getDescrição());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Produto, Quantidade, Código, Local, Descrição;
        ImageView Imagem;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Produto = itemView.findViewById(R.id.list_Produto);
            Quantidade = itemView.findViewById(R.id.list_Quant);
            Código = itemView.findViewById(R.id.list_Codigo);
            Local = itemView.findViewById(R.id.list_Local);
            Descrição = itemView.findViewById(R.id.list_Descrição);
            Imagem = itemView.findViewById(R.id.list_imagem);

        }
    }

}
