package com.example.gatsby;

import com.example.gatsby.ui.eventListing.EventListingFragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTests {

    @Mock
    private JSONObject mockJSON;

    @Mock
    private JSONObject mockSecond;

    @Mock
    private JSONObject mockThird;

    @Mock
    private JSONObject mockFourth;

    @Test
    public void testParseJson() throws JSONException {
        EventListingFragment event = new EventListingFragment();

        when(mockJSON.getString("name")).thenReturn("test");
        when(mockSecond.getString("name")).thenReturn("test2");
        when(mockThird.getString("name")).thenReturn("test3");
        when(mockFourth.getString("name")).thenReturn("test4");


        String sum = event.parseJson(mockJSON,mockSecond,mockThird,mockFourth);

        System.out.println(sum);

        assertEquals("testtest2test3test4",sum);
    }

    @Test
    public void testParseFee() throws JSONException {
        EventListingFragment event = new EventListingFragment();

        when(mockJSON.getDouble("fee")).thenReturn(1.1);
        when(mockSecond.getDouble("fee")).thenReturn(2.0);
        when(mockThird.getDouble("fee")).thenReturn(1.5);
        when(mockFourth.getDouble("fee")).thenReturn(0.4);


        String sum = "" + (float) event.parseFee(mockJSON,mockSecond,mockThird,mockFourth);
        String test = "" +sum;


        assertEquals("5.0", sum);
    }


    @Test
    public void testParseUser() throws JSONException {
        EventListingFragment event = new EventListingFragment();

        when(mockJSON.getString("age")).thenReturn("5");
        when(mockJSON.getString("name")).thenReturn("Bob");
        when(mockJSON.getString("address")).thenReturn("Ames");
        when(mockJSON.getString("email")).thenReturn("cmantas@iastate.edu");
        when(mockJSON.getString("rating")).thenReturn("1");




       String sum = event.parseUser(mockJSON);

        System.out.println(sum);

        assertEquals("5BobAmescmantas@iastate.edu1",sum);
    }

    @Test
    public void testParseEvent() throws JSONException {
        EventListingFragment event = new EventListingFragment();

        when(mockJSON.getString("eventName")).thenReturn("poolparty");
        when(mockJSON.getString("eventLocation")).thenReturn("Ames");
        when(mockJSON.getString("capacity")).thenReturn("3");
        when(mockJSON.getString("fee")).thenReturn("1");
        when(mockJSON.getString("isPublic")).thenReturn("true");




        String sum = event.parseEvent(mockJSON);

        System.out.println(sum);

        assertEquals("poolpartyAmes31true",sum);
    }
    
    @Test
    public void testingParseFee() throws JSONException {
        EventListingFragment event = new EventListingFragment();

        when(mockJSON.getDouble("fee")).thenReturn(31.1);
        when(mockSecond.getDouble("fee")).thenReturn(52.0);
        when(mockThird.getDouble("fee")).thenReturn(1.5);
        when(mockFourth.getDouble("fee")).thenReturn(0.4989);


        String sum = "" + (float) event.parseFee(mockJSON,mockSecond,mockThird,mockFourth);
        String test = "" +sum;


        assertEquals("85.0989", sum);
    }


    @Test
    public void testingParseUser() throws JSONException {
        EventListingFragment event = new EventListingFragment();

        when(mockJSON.getString("age")).thenReturn("34");
        when(mockJSON.getString("name")).thenReturn("Jared");
        when(mockJSON.getString("address")).thenReturn("Ames");
        when(mockJSON.getString("email")).thenReturn("cmantas@iastate.edu");
        when(mockJSON.getString("rating")).thenReturn("5");




        String sum = event.parseUser(mockJSON);

        System.out.println(sum);

        assertEquals("34JaredAmescmantas@iastate.edu5",sum);
    }

}
