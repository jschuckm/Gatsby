package com.example.gatsby.ui.login;

import java.io.Serializable;

/**
 * Class exposing authenticated user details to the UI.
 */
public class LoggedInUserView implements Serializable {
    private String displayName;
    private String authToken;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, String authToken) {
        this.authToken = authToken;
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }

    public String getAuthToken() {
        return authToken;
    }
}