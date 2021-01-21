package com.example.lenovo.fetchingdatausingrecyclerview;

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

public class MainActivity extends AppCompatActivity {

      RecyclerView recyclerView1;
       RecyclerView.Adapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       recyclerView1=(RecyclerView)findViewById(R.id.recyclerview);
       recyclerView1.setHasFixedSize(true);
       recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        final List<ListItem> Lis = new ArrayList<>();  //array of class ListItem, similar to array of integer
        JSONObject obj =new JSONObject();
        try {
            obj.put("uid","b117044");
            obj.put("pwd","iiit@1234");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://14.139.198.171/api/hibi/notice", obj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                       //     System.out.println(response);

                            JSONArray jsonArray = response.getJSONArray("Notices");
                            for (int i=0;i<jsonArray.length();i++) {
                            //    System.out.println(jsonArray.getJSONObject(i).getString("title"));

                                    ListItem l =new ListItem(jsonArray.getJSONObject(i).getString("title"));// Information is pass to ListItem class //Constructor is called
                                    Lis.add(l);
                                    adapter1 = new Myadaptor(Lis, this); // array(Lis) is passed in adapter construtor
                                   recyclerView1.setAdapter(adapter1);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /*ListItem l = new ListItem("Error..");
                Lis.add(l);
                adapter1=new Myadaptor(Lis,this);
                recyclerView1.setAdapter(adapter1);
                //System.out.println(error);*/
                Toast.makeText(MainActivity.this, "Someting went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        Mysingleton.getInstance(this).addToRequestqueue(jsonObjectRequest);


    }
}
