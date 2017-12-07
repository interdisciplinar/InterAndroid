package interdisciplinar.com.br.interandroid.WebService;


import android.util.Log;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 30/11/2017.
 */

public class WebServicesJSON {

    public String consultaDadosWEB(String url) {

        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            }

        } catch (ClientProtocolException e) {
            Log.i("JSON", "ClienteProtocolExcpetion " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("JSON", "IOException= " + e.getMessage());
            e.printStackTrace();
        }
        return builder.toString();
    }

    public String enviarDadosSite(String url, String nome)
            throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpGet = new HttpPost(url);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
        nameValuePairs.add(new BasicNameValuePair("Nome", nome));
        nameValuePairs.add(new BasicNameValuePair("Idade", "10"));


        httpGet.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    public JSONObject processarRETORNOJSON(String retorno) throws JSONException {
        //Log.i("Retorno", "Retorno = "+retorno);


        JSONObject jsonObject = new JSONObject(retorno);
        return jsonObject;

//        Log.i("ibge" , jsonObject.getString("ibge"));
//        Log.i("uf" , jsonObject.getString("UF"));
//        Log.i("bairro" , jsonObject.getString("bairro"));

    }


    public void processarRETORNOJSONArray(String retorno) throws JSONException {

        Log.i("JSON", "JSON="+retorno);
        JSONArray array = new JSONArray(retorno);
        Log.i("JSON", "Qtidade de linhas "+ array.length());
        for (int x =0 ; x< array.length(); x++){
            JSONObject linh =array.getJSONObject(x);
            Log.i("codPiada", linh.getString("codPiada"));
            Log.i("codCategoria", linh.getString("codCategoria"));
            Log.i("tituloPiada", linh.getString("tituloPiada"));
            Log.i("dscPiada", linh.getString("dscPiada"));
        }
        /*//JSONArray jsonArray = OBJ.getJSONArray("linha");
        //Log.i("JSON", "qtidade " + jsonArray.length());
        if (array.length() > 1) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    Log.i("objeto", linh.getString("ESTADO"));
                    Log.i("objeto", linh.getString("nome"));
                    Log.i("cidade", linh.getString("CIDADE"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/
    }



}
