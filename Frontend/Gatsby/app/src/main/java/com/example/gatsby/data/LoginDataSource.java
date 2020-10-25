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
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private Result<LoggedInUser> loginToBackend(String username, String password){
        Log.i("LoginToBackend","In login to backend");
        final RequestQueue queue = Volley.newRequestQueue(MyApplication.getAppContext());
        JSONObject temp;
        final LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setDisplayName(username);
        final String[] authToken = new String[1];
        /*BufferedReader in = null;
        HttpURLConnection urlConnection = null;
        byte [] result = new byte[1000];
        try{
            URL url = new URL("http://www.android.com/");
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }*/
        try {
            Log.i("LoginToBackend","InFirstTry");
            String volleyUrl ="http://coms-309-mc-07.cs.iastate.edu:8080/attendees";
            JSONObject object = new JSONObject();
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, volleyUrl, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        Log.i("LoginToBackend","In try within first try");
                        JSONArray jsonArray = new JSONArray(response.toString());
                        jsonArray.getJSONObject(0);
                        JSONObject first = jsonArray.getJSONObject(0);
                        Log.i("DataSource","Name: "+first.get("name").toString());

                        Log.i("DataSource",first.get("age").toString());
                        Log.i("DataSource",first.get("address").toString());
                        Log.i("DataSource",first.get("email").toString());
                        Log.i("DataSource",first.get("rating").toString());
                    }
                    catch(Exception e){
                        Log.e("DataSource",e.toString());
                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("DataSource",error.toString());
                }
            });
            queue.add(jsonArrayRequest);
            while(!jsonArrayRequest.hasHadResponseDelivered()){
                sleep(100);
            }
            Log.i("LoginToBackend","After first queue add");
        } catch (Exception e) {
            Log.e("DataSource",e.getStackTrace().toString());
        }
        try {
            temp = new JSONObject(" { \"username\":" + username + ", \"password\": " + password + "}");
            Log.i("LoginToBackend","Temp: "+temp.toString());
        }catch(JSONException e){
            Log.e("DataSource",e.toString());
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
                System.out.println("attendees:"+response);
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