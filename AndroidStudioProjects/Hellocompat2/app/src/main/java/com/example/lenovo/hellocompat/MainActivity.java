package com.example.lenovo.hellocompat;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int l=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView textView1 = (TextView) findViewById(R.id.textView1);
        final Button colorbuttom1 = (Button) findViewById(R.id.colorbutton1);

                            colorbuttom1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch(l) {
                            case 0:
                                textView1.setTextColor(Color.parseColor("red"));
                                l=1;
                                break;
                            case 1:
                                textView1.setTextColor(Color.parseColor("yellow"));
                                l=2;
                                break;
                            case 2:
                                textView1.setTextColor(Color.parseColor("blue"));
                                l=3;
                               break;
                            case 3:
                                textView1.setTextColor(Color.parseColor("purple"));
                                 l=4;
                                break;
                            case 4:
                                textView1.setTextColor(Color.parseColor("brown"));
                                l=0;

                        }
                    }
                });
            }
        }


