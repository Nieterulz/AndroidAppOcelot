package es.uca.hito4.fechas;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.uca.hito4.MainActivity;
import es.uca.hito4.R;
import es.uca.hito4.localizacion.LocalizacionFragment;

public class FechaAdapter extends RecyclerView.Adapter<FechaAdapter.MyViewHolder> {
    private ArrayList<Fecha> fechas;
    private static Context context;
    private static final int NOTIF_ID = 1;

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
        holder.fecha.setText(fechas.get(position).toString());
        holder.evento.setText(fechas.get(position).getEvento());
        holder.fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fechas.get(position).validarFecha())
                {
                    int X = fechas.get(position).getDifDias();
                    Snackbar.make(holder.itemView,"Faltan " + X + " días hasta la fecha",Snackbar.LENGTH_LONG)
                            .setAction("Entendido", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            })
                            .show();
                }
                else
                {
                    Toast.makeText(context,"Ha pasado el plazo",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "default")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(holder.evento.getText().toString())
                        .setContentText(holder.fecha.getText().toString())
                        .setSubText("Pulsar para abrir localización")
                        .setTicker("Abrir localización")
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setAutoCancel (true)
                        .setVibrate(new long[]{0, 250,250,250});

                Intent intent = new Intent(context, MainActivity.class);
                if(intent.hasExtra("changeFragment"))
                    intent.removeExtra("changeFragment");
                intent.putExtra("changeFragment", "localizacion");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent contIntent = PendingIntent.getActivity(context,
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(contIntent);

                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                // Since android Oreo (26) notification channel is needed.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("default",
                            "Default channel",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    mNotificationManager.createNotificationChannel(channel);
                }
                mNotificationManager.notify(NOTIF_ID, notification.build());
            }
        });
    }

    @Override
    public int getItemCount() {
        return fechas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView fecha;
        private TextView evento;
        private ImageButton button;
        public MyViewHolder(View v) {
            super(v);
            fecha = v.findViewById(R.id.text_fecha);
            evento = v.findViewById(R.id.text_evento);
            button = v.findViewById(R.id.button_fecha);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
