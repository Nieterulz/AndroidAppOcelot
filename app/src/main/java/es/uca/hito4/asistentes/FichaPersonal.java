package es.uca.hito4.asistentes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import es.uca.hito4.R;
import es.uca.hito4.UrlServer;

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
        get.execute();

        ImageButton editButton = findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, EditarFichaPersonal.class);

                intent.putExtra("_id", _id);
                intent.putExtra("nombre", ((TextView)findViewById(R.id.nombre)).getText().toString());
                intent.putExtra("dni", ((TextView)findViewById(R.id.dni)).getText().toString());
                intent.putExtra("telefono", ((TextView)findViewById(R.id.telefono)).getText().toString());
                intent.putExtra("fechaNacimiento", ((TextView)findViewById(R.id.fecha_nacimiento)).getText().toString());
                intent.putExtra("fechaInscripcion", ((TextView)findViewById(R.id.fecha_inscripcion)).getText().toString());

                context.startActivity(intent);
            }
        });

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

    public class Get extends AsyncTask<Void, Void, String> {
        private String _id;
        private final String SERVER = UrlServer.getSERVER();
        public Get(String _id)
        {
            this._id = _id;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String REQUEST_METHOD = "GET";
            int READ_TIMEOUT = 15000;
            int CONNECTION_TIMEOUT = 15000;

            String result;
            String inputLine;

            try {
                // connect to the server
                URL myUrl = new URL(SERVER + "/" + _id);
                HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.connect();

                // get the string from the input stream
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
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

            JSONArray array = null;
            try {
                array = new JSONArray(result);
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
