package es.uca.hito4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AsistenteAdapter extends RecyclerView.Adapter {
    public static ArrayList<Asistente> asistentes;
    private Context context;

    public AsistenteAdapter(ArrayList<Asistente> myDataset) {
        asistentes = myDataset;
    }

    @NonNull
    @Override
    public AsistenteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asistente,
                        parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return asistentes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nombre;
        public MyViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_asistente);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position)
        {
            this.nombre.setText(asistentes.get(position).getNombre());
        }

        @Override
        public void onClick(View v) {

        }
    }
}
