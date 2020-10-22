package com.example.gatsby.ui.eventListing;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gatsby.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class EventListingFragment extends Fragment {

    private EventListingModel eventListingModel;
    EditText Host1;
    EditText Host2;
    EditText Host3;
    EditText Host4;


    EditText Event1;
    EditText Event2;
    EditText Event3;
    EditText Event4;

    Integer i = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventListingModel =
                ViewModelProviders.of(this).get(EventListingModel.class);
        final View root = inflater.inflate(R.layout.fragment_event_listing, container, false);
        final EditText Host1 = (EditText) root.findViewById(R.id.Host1);
        final EditText Host2 = (EditText) root.findViewById(R.id.Host2);
        final EditText Host3 = (EditText) root.findViewById(R.id.Host3);
        final EditText Host4 = (EditText) root.findViewById(R.id.Host4);

        final EditText Event1 = (EditText) root.findViewById(R.id.Event1);
        final EditText Event2 = (EditText) root.findViewById(R.id.Event2);
        final EditText Event3 = (EditText) root.findViewById(R.id.Event3);
        final EditText Event4 = (EditText) root.findViewById(R.id.Event4);

        RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());

        Button Next = (Button) root.findViewById(R.id.Next);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instantiate the RequestQueue.

                RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
                try {
                    String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendees";
                    JSONObject object = new JSONObject();
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response.toString());

                                JSONObject first = jsonArray.getJSONObject(i);
                                i++;
                                JSONObject second = jsonArray.getJSONObject(i);
                                i++;
                                JSONObject third = jsonArray.getJSONObject(i);
                                i++;
                                JSONObject fourth = jsonArray.getJSONObject(i);
                                i++;

                                Host1.setText(first.get("name").toString());
                                Event1.setText(first.get("address").toString());

                                Host2.setText(second.get("name").toString());
                                Event2.setText(second.get("address").toString());

                                Host3.setText(third.get("name").toString());
                                Event3.setText(third.get("address").toString());

                                Host4.setText(fourth.get("name").toString());
                                Event4.setText(fourth.get("address").toString());

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
                    });
                    requestQueue.add(jsonArrayRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            });





        return root;
    }

}