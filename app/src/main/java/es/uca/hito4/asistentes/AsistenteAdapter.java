package es.uca.hito4.asistentes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.uca.hito4.R;

public class AsistenteAdapter extends RecyclerView.Adapter<AsistenteAdapter.MyViewHolder> {
    public ArrayList<Asistente> asistentes;

    public AsistenteAdapter(ArrayList<Asistente> myDataset) {
        this.asistentes = myDataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asistente,
                        parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.nombre.setText(asistentes.get(position).getNombre());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, FichaPersonal.class);
                String _id = asistentes.get(position).getId();
                intent.putExtra("_id", _id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return asistentes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nombre;
        private ImageButton button;
        public MyViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_asistente);
            button = (ImageButton) v.findViewById(R.id.ficha_personal);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
