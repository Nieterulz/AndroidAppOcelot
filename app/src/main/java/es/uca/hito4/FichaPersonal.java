package es.uca.hito4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class FichaPersonal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_personal);

        Intent intent = getIntent();
        final String _id = intent.getExtras().getString("_id");
        final String nombre = intent.getExtras().getString("nombre");
        final String dni = intent.getExtras().getString("dni");
        final String telefono = intent.getExtras().getString("telefono");
        final String fechaNacimiento = intent.getExtras().getString("fechaNacimiento");
        final String fechaInscripcion = intent.getExtras().getString("fechaInscripcion");

        ((TextView)findViewById(R.id.nombre)).setText(nombre);
        ((TextView)findViewById(R.id.dni)).setText(dni);
        ((TextView)findViewById(R.id.telefono)).setText(telefono);
        ((TextView)findViewById(R.id.fecha_nacimiento)).setText(fechaNacimiento);
        ((TextView)findViewById(R.id.fecha_inscripcion)).setText(fechaInscripcion);

        ImageButton editButton = findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, EditarFichaPersonal.class);

                intent.putExtra("_id", _id);
                intent.putExtra("nombre", nombre);
                intent.putExtra("dni", dni);
                intent.putExtra("telefono", telefono);
                intent.putExtra("fechaNacimiento", fechaNacimiento);
                intent.putExtra("fechaInscripcion", fechaInscripcion);

                context.startActivity(intent);
            }
        });
    }
}
