package es.uca.hito4.asistentes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import es.uca.hito4.DatePickerFragment;
import es.uca.hito4.R;
import es.uca.hito4.UrlServer;

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
                    put.execute();
                } catch (JSONException e) {
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
    public class Put extends AsyncTask<Void, Void, String> {
        private String _id;
        private JSONObject asistente;
        private final String SERVER = UrlServer.getSERVER();

        public Put(String _id, JSONObject asistente)
        {
            this._id = _id;
            this.asistente = asistente;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String REQUEST_METHOD = "PUT";

            String result;
            String inputLine;

            try {
                URL url = new URL(SERVER + "/" + _id);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setRequestMethod(REQUEST_METHOD);
                httpCon.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                httpCon.setRequestProperty("Accept", "application/json");
                OutputStreamWriter out = new OutputStreamWriter(
                        httpCon.getOutputStream());
                out.write(asistente.toString());
                out.flush();
                out.close();
                httpCon.getInputStream();

                InputStreamReader streamReader = new InputStreamReader(httpCon.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                reader.close();
                streamReader.close();
                result = stringBuilder.toString();
            } catch(IOException e) {
                e.printStackTrace();
                result = "error";
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            System.out.println(result);

            Toast.makeText(getApplicationContext(),
                    "Asistente actualizado con éxito",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
