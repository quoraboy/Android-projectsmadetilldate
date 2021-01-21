package com.example.lenovo.intentservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(v);
            }
        });

    findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stopService(v);
        }
    });
    }
   public void startService(View view)
   {
       Intent intent= new Intent(this,Myservices.class);
       startService(intent);
   }
   public void stopService(View view)
   {
       Intent intent1= new Intent(this,Myservices.class);
       stopService(intent1);

   }
}
