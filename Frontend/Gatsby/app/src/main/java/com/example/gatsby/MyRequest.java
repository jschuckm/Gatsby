package com.example.gatsby;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gatsby.data.model.LoggedInUser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class MyRequest extends JsonObjectRequest {
    Response.Listener myListener;
    Response.ErrorListener myErrorListener;
    public MyRequest(int method, java.lang.String url, org.json.JSONObject jsonRequest, Response.Listener<org.json.JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url,jsonRequest,listener, errorListener);
        this.myListener = listener;
    }

    public Response.ErrorListener getErrorListener(){
        return this.myErrorListener;
    }

    @Override
    protected Response<org.json.JSONObject> parseNetworkResponse(NetworkResponse response) {
        System.out.println(response.allHeaders);
        System.out.println(response.statusCode);
        System.out.println(response.data);
        System.out.println(response.headers);
        System.out.println(response.networkTimeMs);
        System.out.println(new JSONObject(response.headers));
        this.myListener.onResponse(new JSONObject(response.headers));
        return Response.success(
                new JSONObject(response.headers), HttpHeaderParser.parseCacheHeaders(response));
    }
}
