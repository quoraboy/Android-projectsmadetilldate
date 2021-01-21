package com.example.lenovo.sharedpreferances;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
       public static final String Default="";
      EditText username2;
      EditText password2;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       username2=(EditText)findViewById(R.id.username2);
       password2=(EditText)findViewById(R.id.password2);

       }

    public void load(View view)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("Name",Default);     //Default value get displayed when no data is entered in main activity
        String pass=sharedPreferences.getString("password",Default);
        if(name.equals(Default) || pass.equals(Default))
        {
            Toast.makeText(this, "NO DATA FOUND", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Data Loaded Succesfully", Toast.LENGTH_SHORT).show();
             username2.setText(name);
             password2.setText(pass);
        }

    }
    public void previous(View view)
    {
        Toast.makeText(this, "Previous", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
