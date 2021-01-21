package com.example.lenovo.testandroid1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView Animal=(TextView) findViewById(R.id.txtAnimal);
        TextView Cat = (TextView) findViewById(R.id.txtCat);

        Animal animal=new Animal("Tiger", "orange",60,80 );
         Animal.setText(animal.toString());

         Cat cat1=new Cat("My cat","white",40,60,4,true);
         Cat.setText(cat1.toString());



    }
             }
