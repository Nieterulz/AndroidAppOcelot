package es.uca.hito4.ui.programa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgramaViewModel  extends ViewModel {
    private MutableLiveData<String> mText;

    public ProgramaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Programa Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
