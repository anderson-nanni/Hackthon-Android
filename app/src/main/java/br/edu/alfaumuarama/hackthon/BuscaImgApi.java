package br.edu.alfaumuarama.hackthon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class BuscaImgApi extends AsyncTask<String, Void, Bitmap> {

    ImageView imagem;

    public BuscaImgApi(ImageView imagem) {
        this.imagem = imagem;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        String link = strings[0];

        try {
            URL url = new URL(link);

            InputStream inputStream = url.openStream();

            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imagem.setImageBitmap(bitmap);
    }
}