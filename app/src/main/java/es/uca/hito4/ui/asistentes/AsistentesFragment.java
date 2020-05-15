package es.uca.hito4.ui.asistentes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import es.uca.hito4.Asistente;
import es.uca.hito4.AsistenteAdapter;
import es.uca.hito4.GetAll;
import es.uca.hito4.R;

public class AsistentesFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    public static ArrayList<Asistente> asistentes = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_asistentes, container, false);

        // Referenciamos al RecyclerView
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_asistentes);
        // Mejoramos rendimiento con esta configuración
        //recyclerView.setHasFixedSize(true);
        // Creamos un LinearLayoutManager para gestionar el item_asistente.xml creado antes
        layoutManager = new LinearLayoutManager(getActivity()){};
        // Lo asociamos al RecyclerView
        recyclerView.setLayoutManager(layoutManager);

        // Cargar asistentes

        GetAll getAll = new GetAll(asistentes);
        try {
            getAll.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        asistentes = getAll.getAsistentes();

        // Creamos un AsistenteAdapter pasándole todos nuestros Asistentes
        adapter = new AsistenteAdapter(asistentes);
        // Asociamos el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);

        return v;
    }
}