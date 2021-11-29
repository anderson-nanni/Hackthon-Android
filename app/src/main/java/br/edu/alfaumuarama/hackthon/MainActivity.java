package br.edu.alfaumuarama.hackthon;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ListActivity {

    ArrayList<Produtos> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            listaProdutos = new br.edu.alfaumuarama.hackthon.BuscaApi().execute("http://localhost:8080/produtos").get();



            /*
            String texto = "produtos: " + listaProdutos.get(0).produto + "descricao: " + listaProdutos.get(0).descricao + "valor: " + listaProdutos.get(0).valor;
            Toast.makeText(this, texto, Toast.LENGTH_LONG).show();


             */



            ListAdapter adapter = new SimpleAdapter(this,
                    getLista(),
                    R.layout.listview_produtos,
                    new String[] {"produto"},
                    new int[] {R.id.txtDescricao, R.id.txtValor});

            setListAdapter(adapter);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<HashMap<String, String>> getLista() {
        ArrayList<HashMap<String, String>> listaRetorno = new ArrayList<>();

        for(int i = 0; i < listaProdutos.size(); i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("id", String.valueOf(listaProdutos.get(i).getProdutoId()));
            item.put("produto", listaProdutos.get(i).produto);
            item.put("categoria_id", listaProdutos.get(i).categoriaId);
            item.put("descricao", listaProdutos.get(i).descricao);
            item.put("base64", listaProdutos.get(i).base64);
            item.put("valor", listaProdutos.get(i).valor);
            item.put("empresa_id", listaProdutos.get(i).empresaId);

            listaRetorno.add(item);
        }

        return listaRetorno;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent telaDetalhe = new Intent(MainActivity.this, DetalhesActivity.class);

        Bundle params = new Bundle();
        params.putString("produto", listaProdutos.get(position).getProduto());
        params.putString("valor", listaProdutos.get(position).getValor());
        params.putString("descricao", listaProdutos.get(position).getDescricao());

        telaDetalhe.putExtras(params);

        startActivity(telaDetalhe);
    }
}