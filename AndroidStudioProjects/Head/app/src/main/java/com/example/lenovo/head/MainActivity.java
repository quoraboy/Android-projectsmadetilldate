package com.example.lenovo.head;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {
      SecureRandom sach= new SecureRandom();
       int r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView view1=(TextView)findViewById(R.id.view1);
        Button head=(Button) findViewById(R.id.head);
        Button tail=(Button) findViewById(R.id.tail);
        final Button toss =(Button) findViewById(R.id.Toss);
        final TextView result2=(TextView) findViewById(R.id.result2);
           final TextView result1=(TextView) findViewById(R.id.result1);
        view1.setText("Choose HEAD/TAIL");

             head.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     result1.setText("Head Selected");
                         result2.setText("");
                 }
             });
            tail.setOnClickListener(new View.OnClickListener() {
               @Override
                 public void onClick(View v) {

                   result1.setText("Tail Selected");
              result2.setText("");
               }
              });

        toss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // toss.setText("aa");


               for(int j=0;j<500;j++) {
                   r = 1 + sach.nextInt(2);
               }
                   if (r == 1) {
                       result1.setText("HEAD");

                   }
                   else {
                       result1.setText("TAIL");
                   }

                   result2.setText("Choose again...");
                             }
        });
    }

}
