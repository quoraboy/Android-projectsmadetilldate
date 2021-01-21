package com.example.lenovo.bmiindex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class More extends AppCompatActivity {
    String response="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        TextView t1 = (TextView) findViewById(R.id.t1);
        Intent i = getIntent();
        String a1 = i.getStringExtra("a1");
        t1.setText(advice(a1));

    }

    public String advice(String a1) {

        double d = Double.parseDouble(a1);
        if (d < 18.5)
            response= "you are under weight";
    else if(d<24.9)
        response="your bmi is normal";
    else if(d<29.9)
        response="you are over weight";
    else
        response="you are obese, go and talk to doctor";
    return response;
    }
}