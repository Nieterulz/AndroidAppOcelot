package es.uca.hito4.ui.asistentes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import es.uca.hito4.Asistente;
import es.uca.hito4.AsistenteAdapter;
import es.uca.hito4.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsistentesFragment extends Fragment {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String SERVER = "http://192.168.8.100:8080/asistentes";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_asistentes, container, false);

        // Referenciamos al RecyclerView
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_asistentes);
        // Mejoramos rendimiento con esta configuración
        recyclerView.setHasFixedSize(true);
        // Creamos un LinearLayoutManager para gestionar el item_asistente.xml creado antes
        layoutManager = new LinearLayoutManager(getActivity()){};
        // Lo asociamos al RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        // Creamos un ArrayList de Asistentes
        ArrayList<Asistente> asistentes = new ArrayList<>();

        // Cargar asistentes
        asistentes.add(new Asistente("Antonio José Sánchez Muñoz", "44065328L",
                        "610989598", "19/12/1997", "13/05/2020"));
        asistentes.add(new Asistente("Luis de Celis Muñoz", "44065328L",
                "610989598", "19/12/1997", "13/05/2020"));
        asistentes.add(new Asistente("Alvaro Sánchez Muñoz", "44065328L",
                "610989598", "19/12/1997", "13/05/2020"));
        asistentes.add(new Asistente("Rosario Muñoz Salcedo", "44065328L",
                "610989598", "19/12/1997", "13/05/2020"));


        HttpGetRequest request = new HttpGetRequest();
        request.execute();

        // Creamos un AsistenteAdapter pasándole todos nuestros Asistentes
        adapter = new AsistenteAdapter(asistentes);
        // Asociamos el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);

        return v;
    }

    public class HttpGetRequest extends AsyncTask<Void, Void, String> {

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

        protected void onPostExecute(String result){
            super.onPostExecute(result);
            System.out.println(result);
            try {
                JSONArray array = new JSONArray(result);
                for(int i=0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    System.out.println(object.getString("nombre"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}