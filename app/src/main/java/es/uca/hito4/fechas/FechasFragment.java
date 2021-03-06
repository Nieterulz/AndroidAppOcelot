package es.uca.hito4.fechas;

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

        fechas.clear();
        fechas.add(new Fecha(1, 5, 2020, "Venta de tickets"));
        fechas.add(new Fecha(15,5,2020, "Nuevas confirmaciones"));
        fechas.add(new Fecha(20,6,2020, "Primer día del evento"));
        fechas.add(new Fecha(21,6,2020, "Segundo día del evento"));

        // Creamos un FechaAdapter pasándole todos nuestras Fechas
        adapter = new FechaAdapter(fechas);
        // Asociamos el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);

        return root;
    }
}