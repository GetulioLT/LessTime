package com.example.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastro_Produtos extends AppCompatActivity {

    ////Declarando Variáveis

    Button BtnVoltar_CadastroP, BtnCadastrar, BtnCancelar;
    EditText EdtCadastro_Produto, EdtCadastro_Quant, EdtCadastro_Codigo,
            edtCadastro_Local, EdtCadastro_Descrição;
    ProgressBar ProgressBarP;
    ImageView ImagemProduto;

    Produtos_Info Produtos_Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        IniciarComponentes();

        Produtos_Info = new Produtos_Info();

        ////Voltar para tela de pedidos

        BtnVoltar_CadastroP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Cadastro_Produtos.this, Tela_dos_pedidos.class);
                startActivity(it);
                finish();
            }
        });

        ////Cadastro de produtos

        BtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CadastroProdutos();

                ProgressBarP.setVisibility(View.VISIBLE);

                if (!TextUtils.isEmpty(Produtos_Info.getProduto())
                        && !TextUtils.isEmpty(Produtos_Info.getCódigo())
                        && !TextUtils.isEmpty(Produtos_Info.getLocal())
                        && !TextUtils.isEmpty(Produtos_Info.getQuantidade())) {
                    ProgressBarP.setVisibility(View.INVISIBLE);
                    Produtos_Info.salvar();
                    alert("Produto Registrado com sucesso");
                }else {
                    ProgressBarP.setVisibility(View.INVISIBLE);
                    alert("Informações faltando");
                }
                LimparCampos();
            }
        });

        BtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EdtCadastro_Produto.getText().clear();
                EdtCadastro_Quant.getText().clear();
                EdtCadastro_Codigo.getText().clear();
                edtCadastro_Local.getText().clear();
                EdtCadastro_Descrição.getText().clear();
            }
        });
    }

    private void LimparCampos() {
        EdtCadastro_Produto.getText().clear();
        EdtCadastro_Quant.getText().clear();
        EdtCadastro_Codigo.getText().clear();
        edtCadastro_Local.getText().clear();
        EdtCadastro_Descrição.getText().clear();
    }

    private void CadastroProdutos() {
        Produtos_Info.setProduto(EdtCadastro_Produto.getText().toString());
        Produtos_Info.setQuantidade(EdtCadastro_Quant.getText().toString());
        Produtos_Info.setCódigo(EdtCadastro_Codigo.getText().toString());
        Produtos_Info.setLocal(edtCadastro_Local.getText().toString());
        Produtos_Info.setDescrição(EdtCadastro_Descrição.getText().toString());
    }

    private void IniciarComponentes() {
        BtnVoltar_CadastroP = findViewById(R.id.BtnVoltar_CadastroP);
        BtnCadastrar = findViewById(R.id.BtnCadastrar);
        BtnCancelar = findViewById(R.id.BtnCancelar);
        EdtCadastro_Produto = findViewById(R.id.EdtCadastro_Produto);
        EdtCadastro_Quant = findViewById(R.id.EdtCadastro_Quant);
        EdtCadastro_Codigo = findViewById(R.id.EdtCadastro_Codigo);
        edtCadastro_Local = findViewById(R.id.edtCadastro_Local);
        EdtCadastro_Descrição = findViewById(R.id.EdtCadastro_Descrição);
        ProgressBarP = findViewById(R.id.ProgressBarP);
        ImagemProduto = findViewById(R.id.InagemProduto);
    }

    private void
    alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}