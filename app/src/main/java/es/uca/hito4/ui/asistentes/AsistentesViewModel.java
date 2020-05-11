package es.uca.hito4.ui.asistentes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AsistentesViewModel  extends ViewModel {
    private MutableLiveData<String> mText;

    public AsistentesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Asistentes Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
