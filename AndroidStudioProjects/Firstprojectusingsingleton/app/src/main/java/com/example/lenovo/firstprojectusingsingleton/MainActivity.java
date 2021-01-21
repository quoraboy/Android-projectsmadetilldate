package com.example.lenovo.firstprojectusingsingleton;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
String server="http://14.139.198.171/api/hibi/notice";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        final TextView txt = (TextView) findViewById(R.id.txt);
        Button btn=(Button)findViewById(R.id.btn);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               StringRequest stringRequest= new StringRequest(Request.Method.POST, server,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                            txt.setText(response);

                           }
                       }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                          txt.setText("Error...");
                          error.printStackTrace();
                   }
               });
               Mysingleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);
           }
       });
    }
}
