package es.uca.hito4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditarFichaPersonal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ficha_personal);

        this.setTitle("Editar ficha");

        Intent intent = getIntent();
        String nombre = intent.getExtras().getString("nombre");
        String dni = intent.getExtras().getString("dni");
        String telefono = intent.getExtras().getString("telefono");
        String fechaNacimiento = intent.getExtras().getString("fechaNacimiento");
        String fechaInscripcion = intent.getExtras().getString("fechaInscripcion");

        ((TextView)findViewById(R.id.nombre)).setText(nombre);
        ((TextView)findViewById(R.id.dni)).setText(dni);
        ((TextView)findViewById(R.id.telefono)).setText(telefono);
        ((TextView)findViewById(R.id.fecha_nacimiento)).setText(fechaNacimiento);
        ((TextView)findViewById(R.id.fecha_inscripcion)).setText(fechaInscripcion);

        ImageButton confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(),
                        "Asistente actualizado con éxito",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
