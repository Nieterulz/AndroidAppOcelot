package es.uca.hito4.ui.localizacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.uca.hito4.R;

public class LocalizacionFragment extends Fragment {
    private LocalizacionViewModel localizacionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        localizacionViewModel =
                ViewModelProviders.of(this).get(LocalizacionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_localizacion, container, false);
        final TextView textView = root.findViewById(R.id.text_localizacion);
        localizacionViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}