package com.example.gatsby.ui.eventListing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventListingModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EventListingModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the event listing fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}