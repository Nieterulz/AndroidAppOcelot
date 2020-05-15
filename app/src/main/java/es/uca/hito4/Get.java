package es.uca.hito4;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Get extends AsyncTask<Void, Void, String>  {
    private String _id;
    private JSONObject asistente;
    private static final String SERVER = "http://192.168.8.104:8080/asistentes";
    public Get(String _id, JSONObject asistente)
    {
        this._id = _id;
        this.asistente = asistente;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String REQUEST_METHOD = "GET";
        int READ_TIMEOUT = 15000;
        int CONNECTION_TIMEOUT = 15000;

        String result;
        String inputLine;

        try {
            // connect to the server
            URL myUrl = new URL(SERVER + "/" + _id);
            HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.connect();

            // get the string from the input stream
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
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
        JSONArray array;
        try {
            array = new JSONArray(result);
            asistente = array.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getAsistente(){
        return asistente;
    }
}