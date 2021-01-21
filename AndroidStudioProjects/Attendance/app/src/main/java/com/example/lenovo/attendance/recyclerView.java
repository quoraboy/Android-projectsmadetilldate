package com.example.lenovo.attendance;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        recyclerView.setHasFixedSize(true); //RecyclerView can perform several optimizations if it can know in advance that RecyclerView's size is not affected by the adapter contents.
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listitems = new ArrayList<>();
        final JSONObject  jsonobject= new JSONObject();

        try {
            jsonobject.put("uid","B117044");
            jsonobject.put("pwd","iiit@1234");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                "http://14.139.198.171/api/hibi/attendence", jsonobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //System.out.println(response);
                            JSONArray array = response.getJSONArray("Notices");
                            for(int i=0;i<array.length();i++){
                              //  System.out.println(array.getJSONObject(i).getString("Subcode"));
                                JSONObject obj = ar
ray.getJSONObject(i);

                                String Subcode, Sub , name,attendance;
                                Subcode = obj.getString("subcode");
                                Sub=obj.getString("sub");
                                name=obj.getString("name");
                                attendance=obj.getString("attendance");

                                ListItem lis =new ListItem(Subcode,Sub,name,attendance); //single list

                                listitems.add(lis); //listitems is array of list
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

