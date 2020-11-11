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

    /**
     * Returns a result object that is either success or error and contains a message or the LoggedInUser.
     * @param username the username of the user
     * @param password the password of the user
     * @return Result<LoggedInUser>
     */
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

    /**
     * Helper method created a volley queue and calls other helper method in order to send a login
     * request to backend.
     * @param username the username of the user
     * @param password the password of the user
     * @return Result<LoggedInUser>
     * @throws Exception
     */
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

    /**
     * Returns a JSONObject which is the body of the request to login.
     * @param username the username of the user
     * @param password the password of the user
     * @return JSONObject
     * @throws JSONException
     */
    public JSONObject createReqBody(String username,String password) throws JSONException {
        return new JSONObject(" { \"username\":" + username + ", \"password\": " + password + "}");
    }

    /**
     * Returns a MyRequest object which is just a custom request object similar to JSONObjectRequest. This request calls the /login method on backend.
     * @param reqBody the body of the request created in createReqBody
     * @param user the LoggedInUser object to be updated
     * @param errorBool the Boolean array to be updated if an error occurs
     * @return MyRequest
     * @throws Exception
     */
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

    /**
     * Throws an exception if it takes too long to login.
     * @param myRequest the MyRequest object used to make the request to backend
     * @throws Exception
     */
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