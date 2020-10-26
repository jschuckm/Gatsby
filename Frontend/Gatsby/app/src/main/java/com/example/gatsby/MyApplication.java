package com.example.gatsby;

import android.app.Application;
import android.content.Context;

import com.example.gatsby.data.model.LoggedInUser;
import com.example.gatsby.ui.login.LoggedInUserView;

public class MyApplication extends Application {

    private static Context context;
    private static LoggedInUserView model;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public static LoggedInUserView getUser(){
        return MyApplication.model;
    }
    public static void setUser(LoggedInUserView model){
        MyApplication.model = model;
    }
}
