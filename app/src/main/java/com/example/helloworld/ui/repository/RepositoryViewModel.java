package com.example.helloworld.ui.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RepositoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RepositoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aqui van los ejercicios");
    }

    public LiveData<String> getText() {
        return mText;
    }
}