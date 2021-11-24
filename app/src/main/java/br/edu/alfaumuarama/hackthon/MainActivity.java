package br.edu.alfaumuarama.hackthon;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ListActivity {

    ArrayList<Produtos> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listaProdutos = new br.edu.alfaumuarama.hackthon.BuscaApi().execute("http://www.marcosdiasvendramini.com.br/Get/Produtoss.aspx").get();

            /*
            String texto = "Cod: " + listaProdutos.get(0).codigo + "produto: " + listaProdutos.get(0).produto + "foto: " + listaProdutos.get(0).foto;
            Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
             */

            ListAdapter adapter = new SimpleAdapter(this,
                    getLista(),
                    R.layout.listview_produtos,
                    new String[] {"produto"},
                    new int[] {R.id.txtValor});

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
            item.put("codigo", String.valueOf(listaProdutos.get(i).codigo));
            item.put("produto", listaProdutos.get(i).produto);
            item.put("foto", listaProdutos.get(i).foto);

            listaRetorno.add(item);
        }

        return listaRetorno;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent telaDetalhe = new Intent(MainActivity.this, DetalhesActivity.class);

        Bundle params = new Bundle();
        params.putString("produto", listaProdutos.get(position).getproduto());
        params.putString("foto", listaProdutos.get(position).getfoto());
        params.putInt("codigo", listaProdutos.get(position).getCodigo());

        telaDetalhe.putExtras(params);

        startActivity(telaDetalhe);
    }
}