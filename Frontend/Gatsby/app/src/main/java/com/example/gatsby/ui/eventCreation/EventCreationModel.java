package com.example.gatsby.ui.eventCreation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventCreationModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EventCreationModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Event Creation fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}