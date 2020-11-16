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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gatsby.MyApplication;
import com.example.gatsby.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class EventViewFragment extends Fragment {
    private EventViewModel eventViewModel;
    int eventId;
    String j = "0";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventViewModel =
                ViewModelProviders.of(this).get(EventViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_eventview, container, false);
        Bundle bundle = getArguments();
        if(bundle != null){
             j = bundle.getString("key");
        }

        final Integer i = parseInt(j);

        final TextView EventName = (TextView) root.findViewById(R.id.EventName);
        final TextView size = (TextView) root.findViewById(R.id.size);
        final TextView applicants = (TextView) root.findViewById(R.id.applicants);
        final TextView fee = (TextView) root.findViewById(R.id.fee);

        final TextView location2 = (TextView) root.findViewById(R.id.location2);
        final TextView hostname = (TextView) root.findViewById(R.id.hostname);



        Button Apply = (Button) root.findViewById(R.id.apply);
        Button Back = (Button) root.findViewById(R.id.back);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventListingFragment e = new EventListingFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, e).commit();

            }
        });
        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendApplyRequest(eventId, view);

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
                try {
                    String url ="http://coms-309-mc-07.cs.iastate.edu:8080/event/"+i;
                    JSONObject object = new JSONObject();
                    JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject first) {
                            try {

                                String pub = "";
                                if(first.get("isPublic").toString().equals("true")){
                                    pub = "Public";
                                }
                                else{
                                    pub = "Private";
                                }
                                eventId = parseInt(first.get("id").toString());
                                EventName.setText(first.get("name").toString());
                                size.setText(first.get("capacity").toString());
                               applicants.setText(getApplicantsString(first));
                                fee.setText(first.get("fee").toString());
                                location2.setText(first.get("address").toString());
                                hostname.setText(pub);

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



        return root;
    }

    private void sendApplyRequest(int eventId, View root) {
        RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
        try {
            String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendee/registerevent/"+eventId;
            JSONObject object = createReqBodyRegister();
            JsonObjectRequest jsonArrayRequest = createApplyReqeust(url,object);
            requestQueue.add(jsonArrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JsonObjectRequest createApplyReqeust(String url, JSONObject object) {
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject first) {
                try {
                    System.out.println("apply response"+first);
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
        return jsonArrayRequest;
    }

    /**
     * Returns JSONObject for the body of the request to the register method.
     * @return JSONObject
     * @throws JSONException
     */
    public JSONObject createReqBodyRegister() throws JSONException {
        return new JSONObject("{\"username\":"+MyApplication.getUser().getDisplayName()+"}");
    }

    public String getApplicantsString(JSONObject input) throws JSONException {
        JSONArray array = new JSONArray(input.get("attendees").toString());
        System.out.println("getApplicants "+array);
        String result = "";
        for(int i = 0;i<array.length();i++){
            JSONObject object = new JSONObject(array.get(i).toString());
            System.out.println("get applicants object "+ object);
            String name = object.get("name").toString();
            System.out.println("get applicants name "+ name);
            if(i==0) {
                result = result.concat(name);
            }else{
                result = result.concat(","+name);
            }
            System.out.println("result "+ result);
        }
        return result;
    }

}
