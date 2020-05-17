package es.uca.hito4.asistentes;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;
import org.w3c.dom.Text;

import es.uca.hito4.DatePickerFragment;
import es.uca.hito4.R;
import es.uca.hito4.operaciones.Post;

public class AnadirAsistente extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_asistente);

        setTitle("Añadir asistente");

        findViewById(R.id.fecha_nacimiento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // +1 because January is zero
                        final String selectedDate = day + "/" + (month+1) + "/" + year;
                        EditText text = findViewById(R.id.fecha_nacimiento);
                        text.setText(selectedDate);
                    }
                });

                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        findViewById(R.id.fecha_inscripcion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // +1 because January is zero
                        final String selectedDate = day + "/" + (month+1) + "/" + year;
                        EditText text = findViewById(R.id.fecha_inscripcion);
                        text.setText(selectedDate);
                    }
                });

                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = ((TextView)findViewById(R.id.nombre)).getText().toString();
                String dni = ((TextView)findViewById(R.id.dni)).getText().toString();
                String telefono = ((TextView)findViewById(R.id.telefono)).getText().toString();
                String f_nac = ((TextView)findViewById(R.id.fecha_nacimiento)).getText().toString();
                String f_ins = ((TextView)findViewById(R.id.fecha_inscripcion)).getText().toString();

                try{
                    checkNull(nombre, dni, telefono, f_nac, f_ins);

                    JSONObject asistente = new JSONObject();
                    asistente.put("nombre", nombre);
                    asistente.put("dni", dni);
                    asistente.put("telefono", telefono);
                    asistente.put("f_nac", f_nac);
                    asistente.put("f_ins", f_ins);

                    Post post = new Post(asistente);
                    post.execute().get();

                    Toast.makeText(getApplicationContext(),
                            "Asistente añadido con éxito",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_SHORT).show();
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

    private void checkNull(String nombre, String dni, String telefono, String f_nac, String f_ins) throws Exception {
        if(nombre.equals("") || dni.equals("") || telefono.equals("") || f_nac.equals("") || f_ins.equals(""))
        {
            throw new Exception("Error, no pueden existir campos vacíos");
        }
    }
}
