package com.example.meuprojeto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class Cadastro_Produtos extends AppCompatActivity {

    //Declarando Variáveis
    Button BtnVoltar_CadastroP, BtnCadastrar, BtnCancelar;
    EditText EdtCadastro_Produto, EdtCadastro_Quant, EdtCadastro_Codigo,
            EdtCadastro_Local, EdtCadastro_Descrição;
    ProgressBar ProgressBarP;
    ImageView ImagemProduto;
    String Url;
    String[] mensagens = {"Produto Registrado com sucesso", "Informações faltando"};
    private Uri mSelectedUri;
    Produtos_Info Produtos_Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        IniciarComponentes();

        Produtos_Info = new Produtos_Info();

        //Voltar para tela de pedidos
        BtnVoltar_CadastroP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Cadastro_Produtos.this, Tela_dos_pedidos.class);
                startActivity(it);
                finish();
            }
        });

        //Seleção de Imagem
        ImagemProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelecionarImg();
            }
        });

        //Cadastro de produtos
        BtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String produto = EdtCadastro_Produto.getText().toString();
                String quantidade = EdtCadastro_Quant.getText().toString();
                String codigo = EdtCadastro_Codigo.getText().toString();
                String local = EdtCadastro_Local.getText().toString();
                String descrição = EdtCadastro_Descrição.getText().toString();

                //Condição de Verificação de Campos Vazios
                if (produto.isEmpty() || quantidade.isEmpty() || codigo.isEmpty() ||
                        local.isEmpty() || descrição.isEmpty() || mSelectedUri == null){
                    alert(mensagens[1]);
                }else{
                    ProgressBarP.setVisibility(View.VISIBLE);

                    SalvarImage();

                    //Temporizador para Espera de Geração da Url
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            CadastroProdutos();

                            if (!TextUtils.isEmpty(Produtos_Info.getProduto())
                                    && !TextUtils.isEmpty(Produtos_Info.getCódigo())
                                    && !TextUtils.isEmpty(Produtos_Info.getLocal())
                                    && !TextUtils.isEmpty(Produtos_Info.getQuantidade())
                                    && !TextUtils.isEmpty(Produtos_Info.getProduto())
                                    && !TextUtils.isEmpty(Produtos_Info.getImagem())){

                                Produtos_Info.salvar();
                                alert(mensagens[0]);
                            }
                            LimparCampos();
                            ProgressBarP.setVisibility(View.INVISIBLE);
                        }
                    },4000);
                }
            }
        });

        //Cancelar Cadastro
        BtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LimparCampos();
            }
        });
    }

    //Metodo de Seleção de Imagem da Galeria, e transforma em Uri
    private void SelecionarImg() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0){
            mSelectedUri = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mSelectedUri);
                ImagemProduto.setImageDrawable(new BitmapDrawable(bitmap));
                Log.d("dyww sucesso", String.valueOf(ImagemProduto));
            } catch (IOException e) {
            }
        }
    }

    //Geração da Url e salvamento no Storage
    private void SalvarImage() {
        String filename = UUID.randomUUID().toString();
        final StorageReference ref = FirebaseStorage.getInstance()
                .getReference("/image/" + filename);

        Log.d("dyww salvo", String.valueOf(ref));
        ref.putFile(mSelectedUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Url = uri.toString();
                        Log.d("dyww ulr", uri.toString());
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    //Metódo para Limpar Campos
    private void LimparCampos() {
        EdtCadastro_Produto.getText().clear();
        EdtCadastro_Quant.getText().clear();
        EdtCadastro_Codigo.getText().clear();
        EdtCadastro_Local.getText().clear();
        EdtCadastro_Descrição.getText().clear();
    }

    //Metódo para Enviar as Informações ao Produtos_info
    private void CadastroProdutos() {
        Produtos_Info.setProduto(EdtCadastro_Produto.getText().toString());
        Produtos_Info.setQuantidade(EdtCadastro_Quant.getText().toString());
        Produtos_Info.setCódigo(EdtCadastro_Codigo.getText().toString());
        Produtos_Info.setLocal(EdtCadastro_Local.getText().toString());
        Produtos_Info.setDescrição(EdtCadastro_Descrição.getText().toString());
        Produtos_Info.setImagem(Url);
    }

    //Botão Voltar do Celular
    @Override
    public void onBackPressed() {
        Intent it = new Intent(Cadastro_Produtos.this, Tela_dos_pedidos.class);
        startActivity(it);
        finish();
    }

    //Registrando Id das variáveis
    private void IniciarComponentes() {
        BtnVoltar_CadastroP = findViewById(R.id.BtnVoltar_CadastroP);
        BtnCadastrar = findViewById(R.id.BtnCadastrar);
        BtnCancelar = findViewById(R.id.BtnCancelar);
        EdtCadastro_Produto = findViewById(R.id.EdtCadastro_Produto);
        EdtCadastro_Quant = findViewById(R.id.EdtCadastro_Quant);
        EdtCadastro_Codigo = findViewById(R.id.EdtCadastro_Codigo);
        EdtCadastro_Local = findViewById(R.id.EdtCadastro_Local);
        EdtCadastro_Descrição = findViewById(R.id.EdtCadastro_Descrição);
        ProgressBarP = findViewById(R.id.ProgressBarP);
        ImagemProduto = findViewById(R.id.ImagemPro);
        ProgressBarP.setVisibility(View.INVISIBLE);
    }

    //Metodos de Alertas
    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}