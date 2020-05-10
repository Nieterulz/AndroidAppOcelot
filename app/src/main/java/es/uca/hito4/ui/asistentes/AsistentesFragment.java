package es.uca.hito4.ui.asistentes;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.uca.hito4.R;

public class AsistentesFragment extends Fragment {
    private AsistentesViewModel asistentesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        asistentesViewModel =
                ViewModelProviders.of(this).get(AsistentesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_asistentes, container, false);
        final TextView textView = root.findViewById(R.id.text_asistentes);
        asistentesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
