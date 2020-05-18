package es.uca.hito4.fechas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.uca.hito4.R;

public class FechaAdapter extends RecyclerView.Adapter<FechaAdapter.MyViewHolder> {
    private ArrayList<Fecha> fechas;
    private Context context;

    public FechaAdapter(ArrayList<Fecha> myDataset) {
        this.fechas = myDataset;
    }

    @NonNull
    @Override
    public FechaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fecha,
                parent, false);
        context = parent.getContext();
        FechaAdapter.MyViewHolder vh = new FechaAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final FechaAdapter.MyViewHolder holder, final int position) {
        holder.nombre.setText(fechas.get(position).toString());
        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fechas.get(position).validarFecha())
                {

                }
                else
                {
                    Toast.makeText(context,"La fecha seleccionada esta pasada",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return fechas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nombre;
        private ImageButton button;
        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.text_fecha);
            button = v.findViewById(R.id.button_fecha);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    * Mandar notificación a barra de estado que diga:
                    *   dd/mm/aaaa
                    *   Abrir localizacion
                    * Redirige a localización
                    * */
                }
            });
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {


            /*
             * if ha pasado el plazo
             *   Toast ha pasado del plazo
             * else
             *   Snackbar faltan X dias
             *
             * */
        }
    }
}
