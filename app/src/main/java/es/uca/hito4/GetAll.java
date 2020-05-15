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
import java.util.ArrayList;

public class GetAll extends AsyncTask<Void, Void, String> {
    private static ArrayList<Asistente> asistentes;
    private static final String SERVER = "http://192.168.8.104:8080/asistentes";
    public GetAll(ArrayList<Asistente> asistentes)
    {
        this.asistentes = asistentes;
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
            URL myUrl = new URL(SERVER);
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
        try {
            JSONArray array = new JSONArray(result);
            String _id, nombre;
            asistentes.clear();
            for(int i=0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                _id = object.getString("_id");
                nombre = object.getString("nombre");
                asistentes.add(i, new Asistente(_id, nombre));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Asistente> getAsistentes(){
        return asistentes;
    }
}