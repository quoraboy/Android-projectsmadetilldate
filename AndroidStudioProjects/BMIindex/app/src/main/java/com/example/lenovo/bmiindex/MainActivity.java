package com.example.lenovo.bmiindex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final EditText edit1=(EditText) findViewById(R.id.edit1);
       final EditText edit2=(EditText) findViewById(R.id.edit2);
        Button button1=(Button) findViewById(R.id.button1);
        Button button2=(Button) findViewById(R.id.button2);
        final TextView edittext2=(TextView) findViewById(R.id.editText2);



         button1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 float w=Float.parseFloat(edit1.getText().toString());
                 float h=Float.parseFloat(edit2.getText().toString());
                 final float a;
                 a=w/(h*h);
                 edittext2.setText(a+"");
             }
         });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,More.class);
                i.putExtra("a1",edittext2.getText().toString());
                startActivity(i);
            }
        });
    }

}
