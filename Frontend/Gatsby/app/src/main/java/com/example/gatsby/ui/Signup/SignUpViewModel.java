package com.example.gatsby.ui.Signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignUpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SignUpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is signup fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
