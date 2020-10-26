package com.example.gatsby.data;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.gatsby.MyApplication;
import com.example.gatsby.MyRequest;
import com.example.gatsby.data.model.LoggedInUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
            Log.e("LoginDataSource",e.getMessage());
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    public Result<LoggedInUser> loginToBackend(String username, String password) throws Exception{
        Log.i("LoginToBackend","In login to backend");
        final RequestQueue queue = Volley.newRequestQueue(MyApplication.getAppContext());
        JSONObject temp;
        final LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setDisplayName(username);
        final Boolean[] errorBool = {false};

        temp = createReqBody(username,password);
        MyRequest myRequest = createLoginRequest(temp,loggedInUser,errorBool);
        queue.add(myRequest);
        callErrorListenerIfTimeout(myRequest);
        if(errorBool[0])throw new Exception();
        return new Result.Success<>(loggedInUser);
    }

    public JSONObject createReqBody(String username,String password) throws JSONException {
        return new JSONObject(" { \"username\":" + username + ", \"password\": " + password + "}");
    }
    public MyRequest createLoginRequest(JSONObject reqBody, final LoggedInUser user, final Boolean[] errorBool) throws Exception{
        return new MyRequest(Request.Method.POST,"http://coms-309-mc-07.cs.iastate.edu:8080/login", reqBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    user.setToken(response.get("Authorization").toString());
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LoginDataSourceVolley","LoginFailed");
                errorBool[0] = true;
            }
        });
    }
    public void callErrorListenerIfTimeout(MyRequest myRequest) throws Exception{
        int count = 0;
        //separate
        while(!myRequest.hasHadResponseDelivered()){
            sleep(100);
            count++;
            if(count == 40){
                myRequest.getErrorListener().onErrorResponse(new VolleyError("Timeout"));
                break;
            }
        }
    }
}