package br.edu.alfaumuarama.hackthon;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BuscaApi extends AsyncTask<String, Void, ArrayList<Produtos>> {

    @Override
    protected ArrayList<Produtos> doInBackground(String... params) {
        ArrayList<Produtos> listaRetorno = new ArrayList<>();

        String link = params[0];
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha;
            while((linha = bufferedReader.readLine()) != null) {
                JSONArray ja = new JSONArray(linha);

                for(int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);

                    Produtos produtos = new Produtos();
                    produtos.produtoId = jo.getInt("id");
                    produtos.produto = jo.getString("produto");
                    produtos.categoriaId = jo.getString("categoriaId");
                    produtos.descricao = jo.getString("descricao");
                    produtos.base64 = jo.getString("base64");
                    produtos.valor = jo.getString("valor");
                    produtos.empresaId = jo.getString("empresaId");

                    listaRetorno.add(produtos);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaRetorno;
    }
}
