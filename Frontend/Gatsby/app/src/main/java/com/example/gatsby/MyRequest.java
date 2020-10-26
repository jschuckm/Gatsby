package com.example.gatsby;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;


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
