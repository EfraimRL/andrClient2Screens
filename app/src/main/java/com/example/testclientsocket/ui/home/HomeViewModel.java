package com.example.testclientsocket.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testclientsocket.ui.Variables;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        if (Variables.connected){
            mText.setValue("Media actual: "+Variables.mediaActual);
        }
        else{
            mText.setValue("Bienvenido!");
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}