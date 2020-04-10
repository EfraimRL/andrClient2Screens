package com.example.testclientsocket.ui.tools;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToolsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ToolsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Ingresa los datos del servidor [IP, Puerto]:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}