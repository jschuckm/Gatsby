package com.example.gatsby.data;

import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gatsby.MyApplication;
import com.example.gatsby.MyRequest;
import com.example.gatsby.R;
import com.example.gatsby.RequestSingleton;
import com.example.gatsby.data.model.LoggedInUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    LoggedInUser loggedInUser;
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            return loginToBackend(username,password);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private Result<LoggedInUser> loginToBackend(String username, String password) {
        RequestQueue queue = RequestSingleton.getInstance(MyApplication.getAppContext()).getRequestQueue();
        JSONObject temp;
        final LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setDisplayName(username);
        final String[] authToken = new String[1];
        try {
            temp = new JSONObject(" { \"username\":" + username + ", \"password\": " + password + "}");
        }catch(JSONException e){
            System.out.println(e);
            return new Result.Error(e);
        }
        MyRequest myRequest = new MyRequest(Request.Method.POST,"http://10.0.2.2:8080/login", temp, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("response:"+response);
                    System.out.println("names: "+response.names());
                try {
                    System.out.println(response.get("Authorization"));
                    loggedInUser.setToken(response.get("Authorization").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                    System.out.println(loggedInUser.getToken());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                System.out.println("OTHER ERROR");
            }
        });
        queue.add(myRequest);
        while(!myRequest.hasHadResponseDelivered()){
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2:8080/attendees",
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                loggedInUser.setUserId("fake");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                System.out.println("OTHER ERROR");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                System.out.println(loggedInUser.getToken());
                headers.put("Authorization", loggedInUser.getToken());
                return headers;
            }
        };
        queue.add(req);
        while(!req.hasHadResponseDelivered());
        return new Result.Success<>(loggedInUser);
    }
}