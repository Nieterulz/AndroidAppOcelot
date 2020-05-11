package es.uca.hito4.ui.localizacion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocalizacionViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public LocalizacionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Localizacion Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
