package com.example.gatsby.data;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gatsby.MyApplication;
import com.example.gatsby.MyRequest;
import com.example.gatsby.R;
import com.example.gatsby.RequestSingleton;
import com.example.gatsby.data.model.LoggedInUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static android.os.SystemClock.sleep;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    LoggedInUser loggedInUser;
    public Result<LoggedInUser> login(String username, String password) {
        Log.i("LoginMethod","In Login");
        try {
            // TODO: handle loggedInUser authentication
            return loginToBackend(username,password);
        } catch (Exception e) {
            System.out.println("Error loggin in");
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private Result<LoggedInUser> loginToBackend(String username, String password) throws Exception{
        Log.i("LoginToBackend","In login to backend");
        final RequestQueue queue = Volley.newRequestQueue(MyApplication.getAppContext());
        JSONObject temp;
        final LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setDisplayName(username);
        final String[] authToken = new String[1];
        final Boolean[] errorBool = {false};
            temp = new JSONObject(" { \"username\":" + username + ", \"password\": " + password + "}");
            Log.i("LoginToBackend","Temp: "+temp.toString());
        MyRequest myRequest = new MyRequest(Request.Method.POST,"http://coms-309-mc-07.cs.iastate.edu:8080/login", temp, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("response:"+response);
                    System.out.println("names: "+response.names());
                try {
                    System.out.println(response.get("Authorization"));
                    loggedInUser.setToken(response.get("Authorization").toString());
                }catch (JSONException e) {
                    e.printStackTrace();
                }

                    System.out.println(loggedInUser.getToken());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                System.out.println("OTHER ERROR");
                errorBool[0] = true;
            }
        });
        queue.add(myRequest);
        int count = 0;
        while(!myRequest.hasHadResponseDelivered()){
            sleep(100);
            count++;
            if(count == 40){
                myRequest.getErrorListener().onErrorResponse(new VolleyError("Timeout"));
                break;
            }
        }
        JSONObject reqBody;
        //try {
            reqBody = new JSONObject(" { \"username\": \"" + username + "\"}");
            Log.i("LoginToBackend","ReqBody: "+reqBody.toString());
        if(errorBool[0])throw new Exception();
        return new Result.Success<>(loggedInUser);
    }
}