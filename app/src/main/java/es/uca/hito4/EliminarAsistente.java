package es.uca.hito4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import es.uca.hito4.operaciones.Delete;

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
                        try {
                            delete.execute().get();
                            Toast.makeText(getContext(),
                                    "Asistente eliminado con éxito.",
                                    Toast.LENGTH_SHORT).show();
                            Objects.requireNonNull(getActivity()).finish();
                        } catch (ExecutionException | InterruptedException e) {
                            Toast.makeText(getContext(),
                                    "Ha ocurrido un error al eliminar el asistente",
                                    Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cancelar acción
                    }
                });
        return builder.create();
    }
}