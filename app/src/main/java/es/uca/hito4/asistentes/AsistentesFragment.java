package es.uca.hito4.asistentes;

import android.content.Context;
import android.content.Intent;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import es.uca.hito4.R;
import es.uca.hito4.UrlServer;

public class AsistentesFragment extends Fragment {
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private static LinearLayoutManager layoutManager;
    private static ArrayList<Asistente> asistentes = new ArrayList<>();
    private static View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_asistentes, container, false);

        // Referenciamos al RecyclerView
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_asistentes);
        // Mejoramos rendimiento con esta configuración
        //recyclerView.setHasFixedSize(true);
        // Creamos un LinearLayoutManager para gestionar el item_asistente.xml creado antes
        layoutManager = new LinearLayoutManager(getActivity()){};
        // Lo asociamos al RecyclerView
        recyclerView.setLayoutManager(layoutManager);

        v.findViewById(R.id.anadir_asistente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, AnadirAsistente.class);

                context.startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetAll getAll = new GetAll();
        getAll.execute();
    }

    public class GetAll extends AsyncTask<Void, Void, String> {
        private final String SERVER = UrlServer.getSERVER();

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

            JSONArray array = null;
            try {
                array = new JSONArray(result);
                String _id, nombre;
                asistentes.clear();
                for(int i=0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    _id = object.getString("_id");
                    nombre = object.getString("nombre");
                    asistentes.add(i, new Asistente(_id, nombre));
                }

                // Creamos un AsistenteAdapter pasándole todos nuestros Asistentes
                adapter = new AsistenteAdapter(asistentes);
                // Asociamos el adaptador al RecyclerView
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}