package es.uca.hito4.operaciones;


import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Put extends AsyncTask<Void, Void, String> {
    private String _id;
    private JSONObject asistente;
    private static final String SERVER = "http://192.168.8.104:8080/asistentes";

    public Put(String _id, JSONObject asistente)
    {
        this._id = _id;
        this.asistente = asistente;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String REQUEST_METHOD = "PUT";

        String result;
        String inputLine;

        try {
            URL url = new URL(SERVER + "/" + _id);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod(REQUEST_METHOD);
            httpCon.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpCon.setRequestProperty("Accept", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write(asistente.toString());
            out.flush();
            out.close();
            httpCon.getInputStream();

            InputStreamReader streamReader = new InputStreamReader(httpCon.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            result = stringBuilder.toString();
        } catch(IOException e) {
            e.printStackTrace();
            result = "error";
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        System.out.println(result);
    }
}