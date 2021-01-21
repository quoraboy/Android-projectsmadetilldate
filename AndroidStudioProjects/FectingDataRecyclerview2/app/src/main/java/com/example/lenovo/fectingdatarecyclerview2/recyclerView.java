package com.example.lenovo.fectingdatarecyclerview2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class recyclerView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listitems = new ArrayList<>();
        final JSONObject  jsonobject= new JSONObject();

        try {
            jsonobject.put("uid","B117044");
            jsonobject.put("pwd","");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://14.139.198.171/api/hibi/notice", jsonobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);
                            JSONArray array = response.getJSONArray("Notices");
                            for(int i=0;i<array.length();i++){
                                System.out.println(array.getJSONObject(i).getString("title"));
                                JSONObject obj = array.getJSONObject(i);

                                String title, date , id;
                                title = obj.getString("title");
                                date=obj.getString("date");
                                id=obj.getString("id");

                                ListItem lis =new ListItem(title,date,id);

                                listitems.add(lis);
                            }

                            adapter = new MyAdapter(listitems,getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                Toast.makeText(recyclerView.this, "Someting went wrong", Toast.LENGTH_SHORT).show();



            }
        });

        Mysingleton.getInstance(this).addToRequestqueue(jsonObjectRequest);



    }
}

