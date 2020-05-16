package es.uca.hito4.operaciones;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Delete extends AsyncTask<Void, Void, String> {
    private String _id;
    private JSONObject asistente;
    private static final String SERVER = "http://192.168.8.104:8080/asistentes";

    public Delete(String _id)
    {
        this._id = _id;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String REQUEST_METHOD = "DELETE";

        String result;
        String inputLine;

        try {
            URL url = new URL(SERVER + "/" + _id);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty(
                    "Content-Type", "application/x-www-form-urlencoded" );
            httpCon.setRequestMethod(REQUEST_METHOD);
            httpCon.connect();

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

}