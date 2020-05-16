package es.uca.hito4.ui.asistentes;

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

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import es.uca.hito4.Asistente;
import es.uca.hito4.AsistenteAdapter;
import es.uca.hito4.operaciones.GetAll;
import es.uca.hito4.R;

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

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetAll getAll = new GetAll();
        try {
            String result = getAll.execute().get();
            JSONArray array = new JSONArray(result);
            String _id, nombre;
            asistentes.clear();
            for(int i=0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                _id = object.getString("_id");
                nombre = object.getString("nombre");
                asistentes.add(i, new Asistente(_id, nombre));
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        // Creamos un AsistenteAdapter pasándole todos nuestros Asistentes
        adapter = new AsistenteAdapter(asistentes);
        // Asociamos el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);
    }


}