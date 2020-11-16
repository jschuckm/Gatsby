package com.example.gatsby.ui.eventListing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gatsby.MyApplication;
import com.example.gatsby.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class HostEventListingFragment extends Fragment {

    private EventListingModel eventListingModel;

    Integer i = 0;

    private float Host1;
    private float Host2;
    private float Host3;
    private float Host4;

    Integer event1ID ;
    Integer event2ID ;
    Integer event3ID ;
    Integer event4ID ;

    private String Event1;
    private String Event2;
    private String Event3;
    private String Event4;
    private String age, name, location, email,rating;
    private String eventName, eventLocation, capacity, fee,isPublic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        eventListingModel =
                ViewModelProviders.of(this).get(EventListingModel.class);
        final View root = inflater.inflate(R.layout.fragment_event_listing, container, false);

        final TextView Host1 = (TextView) root.findViewById(R.id.Host1);
        final TextView Host2 = (TextView) root.findViewById(R.id.Host2);
        final TextView Host3 = (TextView) root.findViewById(R.id.Host3);
        final TextView Host4 = (TextView) root.findViewById(R.id.Host4);

        final TextView Event1 = (TextView) root.findViewById(R.id.Event1);
        final TextView Event2 = (TextView) root.findViewById(R.id.Event2);
        final TextView Event3 = (TextView) root.findViewById(R.id.Event3);
        final TextView Event4 = (TextView) root.findViewById(R.id.Event4);



        RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());

        Button Next = (Button) root.findViewById(R.id.Next);

        Button View1 = (Button) root.findViewById(R.id.button4);
        Button View2 = (Button) root.findViewById(R.id.button5);
        Button View3 = (Button) root.findViewById(R.id.button6);
        Button View4 = (Button) root.findViewById(R.id.button7);

        View1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventViewHostFragment e = new EventViewHostFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key",""+ (event1ID) );
                e.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, e).commit();

            }
        });

        View2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventViewHostFragment e = new EventViewHostFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key",""+ (event2ID) );
                e.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, e).commit();

            }
        });

        View3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventViewHostFragment e = new EventViewHostFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key",""+ (event3ID) );
                e.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, e).commit();

            }
        });

        View4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventViewHostFragment e = new EventViewHostFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key",""+ (event4ID) );
                e.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, e).commit();

            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
                try {
                    String url ="http://coms-309-mc-07.cs.iastate.edu:8080/events/host/"+MyApplication.getUser().getId();
                    JSONObject object = new JSONObject();
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response.toString());
                                if(i<jsonArray.length()) {
                                    JSONObject first = jsonArray.getJSONObject(i);
                                    Host1.setText(first.get("fee").toString());
                                    Event1.setText(first.get("name").toString());
                                    event1ID = parseInt(first.get("id").toString());
                                }else{
                                    Host1.setText("");
                                    Event1.setText("");
                                    event1ID = null;
                                }
                                i++;
                                if(i<jsonArray.length()) {
                                    JSONObject second = jsonArray.getJSONObject(i);
                                    Host2.setText(second.get("fee").toString());
                                    Event2.setText(second.get("name").toString());
                                    event2ID = parseInt(second.get("id").toString());
                                }else{
                                    Host2.setText("");
                                    Event2.setText("");
                                    event2ID = null;
                                }
                                i++;
                                if(i<jsonArray.length()) {
                                    JSONObject third = jsonArray.getJSONObject(i);
                                    Host3.setText(third.get("fee").toString());
                                    Event3.setText(third.get("name").toString());
                                    event3ID = parseInt(third.get("id").toString());

                                }else{
                                    Host3.setText("");
                                    Event3.setText("");
                                    event3ID = null;
                                }
                                i++;
                                if(i<jsonArray.length()) {
                                    JSONObject fourth = jsonArray.getJSONObject(i);
                                    Host4.setText(fourth.get("fee").toString());
                                    Event4.setText(fourth.get("name").toString());
                                    event4ID = parseInt(fourth.get("id").toString());
                                }else{
                                    Host4.setText("");
                                    Event4.setText("");
                                    event4ID = null;
                                }
                                i++;


                            }
                            catch(Exception e){
                                System.out.println(e);
                                System.out.println("ERROR");
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(error);
                            System.out.println("OTHER ERROR");
                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            System.out.println(MyApplication.getUser().getAuthToken());
                            headers.put("Authorization", MyApplication.getUser().getAuthToken());

                            return headers;
                        }};
                    requestQueue.add(jsonArrayRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Next.performClick();

        return root;
    }

    public String parseJson(JSONObject first, JSONObject second, JSONObject third, JSONObject fourth){
        try {

            Event1 = (first.getString("name"));


            Event2 = (second.getString("name"));


            Event3 = (third.getString("name"));


            Event4 = (fourth.getString("name"));
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR");
        }

        return Event1+Event2+Event3+Event4;
    }

    public float parseFee(JSONObject first, JSONObject second, JSONObject third, JSONObject fourth){
        try {

            Host1 = (float)(first.getDouble("fee"));


            Host2 = (float)(second.getDouble("fee"));


            Host3 = (float)(third.getDouble("fee"));


            Host4 = (float)(fourth.getDouble("fee"));
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR");
        }

        return Host1+Host2+Host3+Host4;
    }

    public String parseUser(JSONObject first){
        try {

            age = (first.getString("age"));


            name = (first.getString("name"));


            location = (first.getString("address"));


            email = (first.getString("email"));

            rating = (first.getString("rating"));
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR");
        }

        return age+name+location+email+rating;
    }

    public String parseEvent(JSONObject first){
        try {

            eventName = (first.getString("eventName"));


            eventLocation = (first.getString("eventLocation"));


            capacity = (first.getString("capacity"));


            fee = (first.getString("fee"));

            isPublic = (first.getString("isPublic"));
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR");
        }

        return eventName+eventLocation+capacity+fee+isPublic;
    }
}