package com.example.gatsby;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

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
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Name.setText("Resposne : " + response.toString());

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                           System.out.println(error);
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    });
    }
}