package es.uca.hito4.asistentes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import es.uca.hito4.R;

public class AnadirAsistente extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_asistente);

        setTitle("Añadir asistente");

    }
}
