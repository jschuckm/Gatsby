package com.example.gatsby.ui.login;

import java.io.Serializable;

/**
 * Class exposing authenticated user details to the UI.
 */
public class LoggedInUserView implements Serializable {
    private String displayName;
    private String authToken;
    private String id;
    //... other data fields that may be accessible to the UI

    public LoggedInUserView(String displayName, String authToken) {
        this.authToken = authToken;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAuthToken() {
        return authToken;
    }
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
}