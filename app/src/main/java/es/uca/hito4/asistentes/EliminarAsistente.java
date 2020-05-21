package es.uca.hito4.asistentes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import es.uca.hito4.R;
import es.uca.hito4.UrlServer;

public class EliminarAsistente extends DialogFragment {
    private String _id;

    public EliminarAsistente(String _id)
    {
        this._id = _id;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.eliminar_asistente);
        builder.setMessage("Esta operación es irreversible")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Delete delete = new Delete(_id);
                        delete.execute();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cancelar acción
                    }
                });
        return builder.create();
    }
    public class Delete extends AsyncTask<Void, Void, String> {
        private String _id;
        private final String SERVER = UrlServer.getSERVER();

        public Delete(String _id)
        {
            this._id = _id;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String REQUEST_METHOD = "DELETE";

            String result;
            String inputLine;

            try {
                URL url = new URL(SERVER + "/" + _id);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setRequestProperty(
                        "Content-Type", "application/x-www-form-urlencoded" );
                httpCon.setRequestMethod(REQUEST_METHOD);
                httpCon.connect();

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getContext(),
                    "Asistente eliminado con éxito.",
                    Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }
}