package es.uca.hito4.ui.asistentes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import es.uca.hito4.Asistente;
import es.uca.hito4.AsistenteAdapter;
import es.uca.hito4.FichaPersonal;
import es.uca.hito4.R;

public class AsistentesFragment extends Fragment {
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

        // Creamos un AsistenteAdapter pasándole todos nuestros Asistentes
        adapter = new AsistenteAdapter(asistentes);
        // Asociamos el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);

        return v;
    }
}