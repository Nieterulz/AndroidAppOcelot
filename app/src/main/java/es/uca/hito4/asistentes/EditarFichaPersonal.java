package es.uca.hito4.asistentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import es.uca.hito4.R;
import es.uca.hito4.operaciones.Put;

public class EditarFichaPersonal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ficha_personal);

        this.setTitle("Editar ficha");

        Intent intent = getIntent();
        final String _id = intent.getExtras().getString("_id");
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
                JSONObject asistente = new JSONObject();
                try {
                    String nombre = ((TextView)findViewById(R.id.nombre)).getText().toString();
                    String dni = ((TextView)findViewById(R.id.dni)).getText().toString();
                    String telefono = ((TextView)findViewById(R.id.telefono)).getText().toString();
                    String fechaNacimiento = ((TextView)findViewById(R.id.fecha_nacimiento)).getText().toString();
                    String fechaInscripcion = ((TextView)findViewById(R.id.fecha_inscripcion)).getText().toString();

                    asistente.put("nombre", nombre);
                    asistente.put("dni", dni);
                    asistente.put("telefono", telefono);
                    asistente.put("f_nac", fechaNacimiento);
                    asistente.put("f_ins", fechaInscripcion);
                    Put put = new Put(_id, asistente);
                    put.execute().get();
                    Toast.makeText(getApplicationContext(),
                        "Asistente actualizado con éxito",
                        Toast.LENGTH_SHORT).show();
                    finish();

                } catch (JSONException | InterruptedException | ExecutionException e) {
                    Toast.makeText(getApplicationContext(),
                            "Ha ocurrido un error al actualizar los datos",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
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
