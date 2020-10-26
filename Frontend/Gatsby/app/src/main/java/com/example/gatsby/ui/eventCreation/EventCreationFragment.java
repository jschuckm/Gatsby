package com.example.gatsby.ui.eventCreation;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gatsby.MyApplication;
import com.example.gatsby.R;

import org.json.JSONObject;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class EventCreationFragment extends Fragment {
    private com.example.gatsby.ui.eventCreation.EventCreationModel EventCreationModel;


    Integer i = 1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EventCreationModel =
                ViewModelProviders.of(this).get(com.example.gatsby.ui.eventCreation.EventCreationModel.class);
        final View root = inflater.inflate(R.layout.fragment_event_creation, container, false);
        final EditText Capacity = (EditText) root.findViewById(R.id.age1);
        final EditText Name1 = (EditText) root.findViewById(R.id.name1);
        final EditText Address = (EditText) root.findViewById(R.id.location1);
        final EditText Date1 = (EditText) root.findViewById(R.id.email1);
        final EditText Fee = (EditText) root.findViewById(R.id.rating1);
         Boolean pub = false;
        final CheckBox checkBox = (CheckBox) root.findViewById(R.id.checkBox2);
        if (checkBox.isChecked()) {
            pub = true;
        }
        final Boolean finalPub = pub;
        Button Update = (Button) root.findViewById(R.id.update1);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");

                // Instantiate the RequestQueue.

                RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
                try {
                    String url ="http://coms-309-mc-07.cs.iastate.edu:8080/event/"+i;
                    i++;

                    String date = Date1.getText().toString();
                    String dateMonth = date.substring(0,1);
                    String dateDay = date.substring(3,4);
                    String dateYear = date.substring(6,9);
                    Integer month = Integer.parseInt(dateMonth);
                    Integer day = Integer.parseInt(dateDay);
                    Integer year = Integer.parseInt(dateYear);

                    //{ "name": "Halloween Party", "date": null, "fee": 8, "address": "Ames", "isPublic": false, "capacity": 50 }
                    final Date date1 = new GregorianCalendar(year,month - 1, day).getTime();


                    Editable name = Name1.getText();
                    Editable fee = Fee.getText();
                    Editable location = Address.getText();
                    Editable capacity = Capacity.getText();

                    JSONObject temp = new JSONObject(" { \"name\": "+'"'+name+'"'+", \"date\": "+null+", \"fee\": "+fee+", \"address\": "+'"'+location+'"'+", \"isPublic\": "+finalPub+", \"capacity\": "+capacity+" }");
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, temp, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("Works");


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(error);
                            System.out.println("OTHER ERROR");
                        }
                    })
                    {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        System.out.println(MyApplication.getUser().getAuthToken());
                        headers.put("Authorization", MyApplication.getUser().getAuthToken());
                    /*headers.put("Content-Type","application/json");
                    headers.put("Content-Length",""+object.length());
                    headers.put("accept","*///*");
                    /*headers.put("Accept-Encoding","gzip,deflate,br");
                    headers.put("Connection","keep-alive");*/
                        return headers;
                    }};
                    requestQueue.add(jsonObjectRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        Button Post = (Button) root.findViewById(R.id.post1);

        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
                try {
                    String url ="http://coms-309-mc-07.cs.iastate.edu:8080/event";
                    /*
                    String date = Date1.getText().toString();
                    String dateMonth = date.substring(0,2);
                    String dateDay = date.substring(3,5);
                    String dateYear = date.substring(6,10);

                    Integer month = Integer.parseInt(dateMonth);
                    Integer day = Integer.parseInt(dateDay);
                    Integer year = Integer.parseInt(dateYear);


                    final Date date1 = new GregorianCalendar(year,month - 1, day).getTime();
                       */

                    Editable name = Name1.getText();
                    Editable fee = Fee.getText();
                    Editable location = Address.getText();
                    Editable capacity = Capacity.getText();

                    JSONObject temp = new JSONObject(" { \"name\": "+'"'+name+'"'+", \"date\": "+null+", \"fee\": "+fee+", \"address\": "+'"'+location+'"'+", \"isPublic\": "+finalPub+", \"capacity\": "+capacity+" }");



                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, temp, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);


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
                    requestQueue.add(jsonObjectRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        return root;
    }
}