package com.example.gatsby.data;

import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gatsby.R;
import com.example.gatsby.data.model.LoggedInUser;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private Result<LoggedInUser> loginToBackend(String username, String password){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {
            String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendee";


            Editable name = Name.getText();
            Editable age = Age.getText();
            Editable location = Location.getText();
            Editable email = Email.getText();
            Editable rating = Rating.getText();

            JSONObject temp = new JSONObject(" { \"name\":"+name+", \"age\": "+age+", \"rating\":"+rating+", \"email\": "+email+", \"address\": "+location+" }");

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, temp, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                    System.out.println("OTHER ERROR");
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoggedInUser fakeUser =
                new LoggedInUser(
                        java.util.UUID.randomUUID().toString(),
                        "Jane Doe");
        return new Result.Success<>(fakeUser);
    }
}