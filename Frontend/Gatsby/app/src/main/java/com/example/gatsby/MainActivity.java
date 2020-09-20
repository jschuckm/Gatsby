package com.example.gatsby;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    EditText Age;
    EditText Email;
    EditText Name;
    EditText Rating;
    EditText Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Something");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Age = (EditText) findViewById(R.id.Age);
        Name = (EditText) findViewById(R.id.Name);
        Location = (EditText) findViewById(R.id.Location);
        Email = (EditText) findViewById(R.id.Email);
        Rating = (EditText) findViewById(R.id.Rating);
        Button Update = (Button) findViewById(R.id.Update);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");

                // Instantiate the RequestQueue.

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                try {
                    String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendee/1";
                    JSONObject object = new JSONObject();

                    Editable name = Name.getText();
                    Editable age = Age.getText();
                    Editable location = Location.getText();
                    Editable email = Email.getText();
                    Editable rating = Rating.getText();
                    JSONObject temp = new JSONObject(" { \"name\":"+name+", \"age\": "+age+", \"rating\":"+rating+", \"email\": "+email+", \"address\": "+location+" }");
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
                    });
                    requestQueue.add(jsonObjectRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        Button Post = (Button) findViewById(R.id.Post);
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button Get = (Button) findViewById(R.id.Get);
        Get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                Name.setText("Constantine ");
                // Instantiate the RequestQueue.

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                try {
                    String url ="http://coms-309-mc-07.cs.iastate.edu:8080/attendees";
                    JSONObject object = new JSONObject();
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response.toString());
                                jsonArray.getJSONObject(0);
                                JSONObject first = jsonArray.getJSONObject(0);

                                Name.setText(first.get("name").toString());
                                Age.setText(first.get("age").toString());
                                Location.setText(first.get("address").toString());
                                Email.setText(first.get("email").toString());
                                Rating.setText(first.get("rating").toString());
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
    }
}