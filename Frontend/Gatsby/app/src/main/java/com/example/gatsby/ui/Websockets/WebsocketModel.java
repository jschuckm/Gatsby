package com.example.gatsby.ui.Websockets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WebsocketModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WebsocketModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Websocket fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}