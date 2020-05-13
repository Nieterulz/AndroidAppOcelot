package es.uca.hito4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class AsistenteAdapter extends RecyclerView.Adapter<AsistenteAdapter.MyViewHolder> {
    public ArrayList<Asistente> asistentes;
    private Context context;

    public AsistenteAdapter(ArrayList<Asistente> myDataset) {
        this.asistentes = myDataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asistente,
                        parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        //context = parent.getContext();
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

                intent.putExtra("nombre", asistentes.get(position).getNombre());
                intent.putExtra("dni", asistentes.get(position).getDni());
                intent.putExtra("telefono", asistentes.get(position).getTelefono());
                intent.putExtra("fechaNacimiento", asistentes.get(position).getF_nac());
                intent.putExtra("fechaInscripcion", asistentes.get(position).getF_ins());

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
