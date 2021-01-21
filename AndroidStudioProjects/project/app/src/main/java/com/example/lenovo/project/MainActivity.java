package com.example.lenovo.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JSONObject obj =new JSONObject();
        try {
            obj.put("uid","b117044");
            obj.put("pwd","iiit@1234");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://14.139.198.171/api/hibi/notice", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           // System.out.println(response);

                            JSONArray jsonArray = response.getJSONArray("Notices");
                        for (int i=0;i<jsonArray.length();i++) {
                            System.out.println(jsonArray.getJSONObject(i).getString("title"));
                        }
                        }
                        catch (JSONException e) {
                          e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               System.out.println(error);
            }
        });
             /*   JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.POST, "http://14.139.198.171/api/hibi/notice", obj
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                try {
                    // JSONArray obj2= new JSONArray("response");
                    JSONArray obj2 = r;
                    for (int i = 0; i < obj2.length(); i++) {
                        System.out.println(obj2.getJSONObject(i).getString("title"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        });*/
        Mysingleton.getInstance(this).addToRequestqueue(jsonObjectRequest);
    }
}
