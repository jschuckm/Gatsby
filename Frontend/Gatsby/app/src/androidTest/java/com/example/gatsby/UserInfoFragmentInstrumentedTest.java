package com.example.gatsby;

import android.view.View;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gatsby.ui.login.LoggedInUserView;
import com.example.gatsby.ui.userinfo.EditTextFields;
import com.example.gatsby.ui.userinfo.UserInfoFragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserInfoFragmentInstrumentedTest {
    @Mock
    View testRoot;
    @Mock
    EditTextFields mockEditTextFields;
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createReqBodyUpdate_test() throws Exception {
        UserInfoFragment frag = new UserInfoFragment();
        JSONObject result = frag.createReqBodyUpdate("Jared","21","5","test@gmail.com","Ames");
        JSONObject expected = new JSONObject(" { \"name\":"+"Jared"+", \"age\": "+"21"+", \"rating\":"+"5"+", \"email\": "+"test@gmail.com"+", \"address\": "+"Ames"+", \"username\": "+"test@gmail.com"+" }");
        assertEquals(expected.toString(),result.toString());
    }
    @Test
    public void createReqBodyGet_test() throws Exception {
        UserInfoFragment frag = new UserInfoFragment();
        LoggedInUserView testUser = new LoggedInUserView("username","auth");
        MyApplication.setUser(testUser);
        JSONObject result = frag.createReqBodyGet();
        JSONObject expected = new JSONObject("{\"username\":"+MyApplication.getUser().getDisplayName()+"}");
        assertEquals(expected.toString(),result.toString());
    }
    @Test
    public void createGetRequest_test() throws JSONException {
        UserInfoFragment frag = new UserInfoFragment();
        LoggedInUserView testUser = new LoggedInUserView("test@gmail.com","auth");
        MyApplication.setUser(testUser);
        JSONObject expected = new JSONObject("{\"username\":"+MyApplication.getUser().getDisplayName()+"}");
        String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendee/getid";
        JsonObjectRequest result = frag.createGetReq(url,expected);
        assertNotNull(result.getBody());
        assertEquals("application/json; charset=utf-8",result.getBodyContentType());
        assertEquals(url,result.getUrl());
    }
    @Test
    public void createUpdateRequest_test() throws JSONException {
        UserInfoFragment frag = new UserInfoFragment();
        LoggedInUserView testUser = new LoggedInUserView("test@gmail.com","auth");
        testUser.setId("1");
        MyApplication.setUser(testUser);
        JSONObject expected = new JSONObject(" { \"name\":"+"Jared"+", \"age\": "+"21"+", \"rating\":"+"5"+", \"email\": "+"test@gmail.com"+", \"address\": "+"Ames"+", \"username\": "+"test@gmail.com"+" }");
        String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendee/"+MyApplication.getUser().getId();
        JsonObjectRequest result = frag.createUpdateRequest(url,expected);
        assertNotNull(result.getBody());
        assertEquals("application/json; charset=utf-8",result.getBodyContentType());
        assertEquals(url,result.getUrl());
    }
}
