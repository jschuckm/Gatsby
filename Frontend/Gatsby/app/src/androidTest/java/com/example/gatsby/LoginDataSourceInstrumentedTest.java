package com.example.gatsby;

import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.gatsby.data.LoginDataSource;
import com.example.gatsby.data.Result;
import com.example.gatsby.data.model.LoggedInUser;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class LoginDataSourceInstrumentedTest {
    private LoginDataSource loginDataSource= new LoginDataSource();
    private LoginDataSource loginDataSource1 = Mockito.spy(loginDataSource);
    @Mock
    MyRequest myRequest;
    @Mock
    Response.ErrorListener errorListener;
    @Mock
    RequestQueue queue;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.gatsby", appContext.getPackageName());
    }
    @Test
    public void  createRequest_test() throws Exception {
        System.out.println("Hello");
        JSONObject reqBody = new JSONObject(" { \"username\":" + "username@gmail.com" + ", \"password\": " + "password" + "}");
        final LoggedInUser loggedInUser = new LoggedInUser();
        final Boolean[] errorBool = {false};
        MyRequest result = loginDataSource.createLoginRequest(reqBody,loggedInUser, errorBool);
        assertEquals(Request.Method.POST,result.getMethod());
        assertNotNull(result.getBody());
        assertEquals("application/json; charset=utf-8",result.getBodyContentType());
        assertEquals("http://coms-309-mc-07.cs.iastate.edu:8080/login",result.getUrl());
    }
    @Test
    public void callErrorListener_ifTimeout() throws Exception {
        when(myRequest.hasHadResponseDelivered()).thenReturn(false);
        when(myRequest.getErrorListener()).thenReturn(errorListener);
        loginDataSource.callErrorListenerIfTimeout(myRequest);


        verify(myRequest,times(1)).getErrorListener();
        verify(myRequest,times(40)).hasHadResponseDelivered();
    }
    @Test
    public void loginToBackend() throws Exception {
        Log.i("loginToBackend","test");
        when(queue.add(myRequest)).thenReturn(null );
        when(myRequest.hasHadResponseDelivered()).thenReturn(true);
        when(myRequest.getErrorListener()).thenReturn(errorListener);
        JSONObject reqBody = new JSONObject(" { \"username\":" + "username@gmail.com" + ", \"password\": " + "password" + "}");
        final LoggedInUser loggedInUser = new LoggedInUser();
        final Boolean[] errorBool = {false};
        when(loginDataSource1.createLoginRequest(reqBody,loggedInUser,errorBool)).thenReturn(myRequest);
        Result<LoggedInUser> result = loginDataSource.loginToBackend("test@gmail.com","password");

        assertEquals(((LoggedInUser)((Result.Success)result).getData()).getDisplayName(),"test@gmail.com");
    }

}
