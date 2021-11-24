package br.edu.alfaumuarama.hackthon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalhesActivity extends AppCompatActivity {

    TextView txtNome;
    ImageView fotoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtNome = findViewById(R.id.txtNome);
        fotoProduto = findViewById(R.id.imgProduto);

        Intent caminhoTela = getIntent();

        Bundle params = caminhoTela.getExtras();

        if (params != null) {
            String nome = params.getString("nome");
            String foto = params.getString("foto");
            String codigo = params.getString("codigo");

            txtNome.setText(nome);

            new br.edu.alfaumuarama.hackthon.BuscaImgApi(fotoProduto).execute("http://www.marcosdiasvendramini.com.br/fotoProduto/" + foto);

        }
    }
}