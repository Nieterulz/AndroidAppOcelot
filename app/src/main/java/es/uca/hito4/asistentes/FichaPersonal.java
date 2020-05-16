package es.uca.hito4.asistentes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import es.uca.hito4.R;
import es.uca.hito4.operaciones.Get;

public class FichaPersonal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_personal);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        final String _id = Objects.requireNonNull(intent.getExtras()).getString("_id");
        Get get = new Get(_id);
        try {
            String result = get.execute().get();

        JSONArray array = new JSONArray(result);
        JSONObject asistente = array.getJSONObject(0);

        final String nombre = asistente.getString("nombre");
        final String dni = asistente.getString("dni");
        final String telefono = asistente.getString("telefono");
        final String fechaNacimiento = asistente.getString("f_nac");
        final String fechaInscripcion = asistente.getString("f_ins");

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
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        ImageButton deleteButton = findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                EliminarAsistente eliminarAsistente = new EliminarAsistente(_id);
                eliminarAsistente.show(fm, "");
            }
        });
    }
}
