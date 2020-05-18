package es.uca.hito4.ui.fechas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.uca.hito4.R;
import es.uca.hito4.asistentes.AnadirAsistente;
import es.uca.hito4.asistentes.Asistente;
import es.uca.hito4.asistentes.AsistenteAdapter;

public class FechasFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private static ArrayList<Fecha> fechas  = new ArrayList<Fecha>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_fechas, container, false);
        // Referenciamos al RecyclerView
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_fechas);
        // Mejoramos rendimiento con esta configuración
        //recyclerView.setHasFixedSize(true);
        // Creamos un LinearLayoutManager para gestionar el item_fecha.xml creado antes
        layoutManager = new LinearLayoutManager(getActivity()){};
        // Lo asociamos al RecyclerView
        recyclerView.setLayoutManager(layoutManager);

        // Creamos un AsistenteAdapter pasándole todos nuestros Asistentes
        adapter = new AsistenteAdapter(asistentes);
        // Asociamos el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);

        return root;
    }
}