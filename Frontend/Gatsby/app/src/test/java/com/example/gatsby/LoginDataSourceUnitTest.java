package com.example.gatsby;

import android.os.SystemClock;

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
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class LoginDataSourceUnitTest {
    private LoginDataSource loginDataSource = new LoginDataSource();

    @Mock
    private MyRequest myRequest;
    @Mock
    private Response.ErrorListener errorListener;
    @Mock
    private SystemClock clock;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dontcallErrorListener_ifNoTimeout() throws Exception {
        when(myRequest.hasHadResponseDelivered()).thenReturn(true);
        loginDataSource.callErrorListenerIfTimeout(myRequest);

        verify(myRequest,times(0)).getErrorListener();
    }
    @Test
    public void createReqBody_test() throws JSONException {
        JSONObject expected = new JSONObject(" { \"username\":" + "username@gmail.com" + ", \"password\": " + "password" + "}");
        assertEquals(loginDataSource.createReqBody("username@gmail.com","password").toString(),expected.toString());
    }
}
