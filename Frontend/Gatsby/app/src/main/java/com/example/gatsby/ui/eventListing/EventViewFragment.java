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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EventViewFragment extends Fragment {
    private EventViewModel eventViewModel;

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

        final Integer i = Integer.parseInt(j);

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

                                EventName.setText(first.get("name").toString());
                                size.setText(first.get("capacity").toString());
                               // applicants.setText(first.get("applicants").toString());
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


}
