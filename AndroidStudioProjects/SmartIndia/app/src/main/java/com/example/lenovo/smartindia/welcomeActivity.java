package com.example.lenovo.smartindia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

       }

     public void onClick(View v)
     {
         Intent intent=new Intent(this,UserAuthority.class);

         startActivity(intent);



     }
}
