package es.uca.hito4.ui.fechas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FechasViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public FechasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Fechas Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
