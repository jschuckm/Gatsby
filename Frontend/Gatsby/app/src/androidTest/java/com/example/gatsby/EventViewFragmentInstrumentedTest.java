package com.example.gatsby;

import android.util.EventLog;
import android.view.View;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gatsby.ui.eventListing.EventViewFragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.gatsby.ui.login.LoggedInUserView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventViewFragmentInstrumentedTest {
    @Mock
    View testRoot;
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createReqBodyRegister_test() throws Exception {
        EventViewFragment frag = new EventViewFragment();
        JSONObject result = frag.createReqBodyRegister();
        JSONObject expected = new JSONObject("{\"username\":"+MyApplication.getUser().getDisplayName()+"}");
        assertEquals(expected.toString(),result.toString());
    }
    @Test
    public void getApplicantsString_test() throws JSONException {
        EventViewFragment frag = new EventViewFragment();
        JSONObject input = new JSONObject("{\"name\":"+"TestParty, \"attendees\":[{\"name\":Name1},{\"name\":Name2}]}");
        String expected = "Name1,Name2";
        String result = frag.getApplicantsString(input);
        assertEquals(expected,result);
    }
    @Test
    public void createApplyRequest_test() throws JSONException {
        EventViewFragment frag = new EventViewFragment();
        LoggedInUserView testUser = new LoggedInUserView("test@gmail.com","auth");
        MyApplication.setUser(testUser);
        JSONObject expected = new JSONObject("{\"username\":"+MyApplication.getUser().getDisplayName()+"}");
        String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendee/getid";
        JsonObjectRequest result = frag.createApplyReqeust(url,expected);
        assertNotNull(result.getBody());
        assertEquals("application/json; charset=utf-8",result.getBodyContentType());
        assertEquals(url,result.getUrl());
    }
}
