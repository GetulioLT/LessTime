package com.example.meuprojeto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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

    ////Declarando Variáveis

    Button BtnVoltar_CadastroP, BtnCadastrar, BtnCancelar;
    EditText EdtCadastro_Produto, EdtCadastro_Quant, EdtCadastro_Codigo,
            edtCadastro_Local, EdtCadastro_Descrição;
    ProgressBar ProgressBarP;
    ImageView ImagemProduto;

    Produtos_Info Produtos_Info;


    String Url;

    private Uri mSelectedUri;


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

        ImagemProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelecionarImg();
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
                        && !TextUtils.isEmpty(Produtos_Info.getQuantidade())
                        && !TextUtils.isEmpty(Produtos_Info.getProduto())){
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
                SalvarImage();
            } catch (IOException e) {
            }
        }
    }

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
        Produtos_Info.setImagem(Url);
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
        ImagemProduto = findViewById(R.id.ImagemPro);
    }

    private void
    alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}