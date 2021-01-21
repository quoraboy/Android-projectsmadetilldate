package com.example.lenovo.sharedpreferances;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText username;
EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    username=(EditText)findViewById(R.id.username);
    password=(EditText)findViewById(R.id.password);
    }

 public void save(View view)
 {
     SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
     SharedPreferences.Editor editor=sharedPreferences.edit();
     editor.putString("Name",username.getText().toString());
     editor.putString("password",password.getText().toString());
     editor.commit();
     Toast.makeText(this,"Data Save Sucessfully",Toast.LENGTH_LONG).show();
 }
 public  void next(View view)
 {
     Toast.makeText(this, "next activity", Toast.LENGTH_SHORT).show();
     Intent intent=new Intent(this,Main2Activity.class);
     startActivity(intent);


 }
}
