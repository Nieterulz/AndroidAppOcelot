package es.uca.hito4.ui.programa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.uca.hito4.R;

public class ProgramaFragment extends Fragment {
    private ProgramaViewModel programaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        programaViewModel =
                ViewModelProviders.of(this).get(ProgramaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_programa, container, false);
        final TextView textView = root.findViewById(R.id.text_programa);
        programaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}