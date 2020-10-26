package com.example.gatsby;

import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.gatsby.data.LoginDataSource;
import com.example.gatsby.data.model.LoggedInUser;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class LoginDataSourceInstrumentedTest {
    private LoginDataSource loginDataSource= new LoginDataSource();
    @Mock
    MyRequest myRequest;
    @Mock
    Response.ErrorListener errorListener;

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
    public void  createRequest_test() throws JSONException {
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
    public void callErrorListener_ifTimeout(){
        when(myRequest.hasHadResponseDelivered()).thenReturn(false);
        when(myRequest.getErrorListener()).thenReturn(errorListener);
        loginDataSource.callErrorListenerIfTimeout(myRequest);


        verify(myRequest,times(1)).getErrorListener();
        verify(myRequest,times(41)).hasHadResponseDelivered();
    }
}
