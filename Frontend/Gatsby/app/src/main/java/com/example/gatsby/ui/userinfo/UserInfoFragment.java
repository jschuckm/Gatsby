package com.example.gatsby.ui.userinfo;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.util.Log;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserInfoFragment extends Fragment {

    private UserInfoViewModel UserInfoViewModel;
    public final EditTextFields editTextFields = new EditTextFields();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserInfoViewModel =
                ViewModelProviders.of(this).get(UserInfoViewModel.class);
        final View root = inflater.inflate(R.layout.user_info, container, false);
        editTextFields.setAge((EditText) root.findViewById(R.id.Age));
        editTextFields.setName((EditText) root.findViewById(R.id.Name));
        editTextFields.setLocation((EditText) root.findViewById(R.id.Location));
        editTextFields.setEmail((EditText) root.findViewById(R.id.Email));
        editTextFields.setRating((EditText) root.findViewById(R.id.Rating));
        Button Update = (Button) root.findViewById(R.id.Update);

        Log.i("UserInfoFragment","InOnCreateView");
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                sendUpdateRequest(root);
            }

        });

        Button Get = (Button) root.findViewById(R.id.Get);
        Get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                sendGetRequest(root);
            }
        });

        return root;
    }
    public void sendUpdateRequest(View root){
        RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
        try {
            String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendee/"+MyApplication.getUser().getId();
            System.out.println("url:"+url);
            JSONObject object = new JSONObject();

            Editable name = editTextFields.getName().getText();
            Editable age = editTextFields.getAge().getText();
            Editable location = editTextFields.getLocation().getText();
            Editable email = editTextFields.getEmail().getText();
            Editable rating = editTextFields.getRating().getText();

            JSONObject temp = createReqBodyUpdate(name.toString(),age.toString(),rating.toString(),email.toString(),location.toString());
            JsonObjectRequest jsonObjectRequest = createUpdateRequest(url,temp);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public JSONObject createReqBodyUpdate(String name, String age, String rating, String email, String location) throws JSONException {
        return new JSONObject(" { \"name\":"+name+", \"age\": "+age+", \"rating\":"+rating+", \"email\": "+email+", \"address\": "+location+", \"username\": "+email+" }");
    }
    public JsonObjectRequest createUpdateRequest(String url, JSONObject temp){
        return new JsonObjectRequest(Request.Method.PUT, url, temp, new Response.Listener<JSONObject>() {
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
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                System.out.println(MyApplication.getUser().getAuthToken());
                headers.put("Authorization", MyApplication.getUser().getAuthToken());
                return headers;
            }};
    }
    public void sendGetRequest(View root){
        RequestQueue queue = Volley.newRequestQueue(root.getContext());
        try {
            String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendee/getid";
            final JSONObject object = createReqBodyGet();
            JsonObjectRequest jsonObjectRequest = createGetReq(url,object);
            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public JSONObject createReqBodyGet() throws JSONException {
        return new JSONObject("{\"username\":"+MyApplication.getUser().getDisplayName()+"}");
    }
    public JsonObjectRequest createGetReq(String url, JSONObject object){
        return new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println(response);
                    MyApplication.getUser().setId(response.get("id").toString());
                    editTextFields.getName().setText(response.get("name").toString());
                    editTextFields.getAge().setText(response.get("age").toString());
                    editTextFields.getLocation().setText(response.get("address").toString());
                    editTextFields.getEmail().setText(response.get("email").toString());
                    editTextFields.getRating().setText(response.get("rating").toString());
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
    }

}