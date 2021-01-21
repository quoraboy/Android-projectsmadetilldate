package com.example.lenovo.rollanddice;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {


    SecureRandom SecureRandomNumber = new SecureRandom();

    int number1=0;
    int number2=0;
    int number3=0;
    int number4=0;
    int number5=0;
    int number6=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       final TextView Number1 = (TextView) findViewById(R.id.txtNumber1);
        final TextView Number2 = (TextView) findViewById(R.id.txtNumber2);
        final TextView Number3 = (TextView) findViewById(R.id.txtNumber3);
        final TextView Number4 = (TextView) findViewById(R.id.txtNumber4);
        final TextView Number5 = (TextView) findViewById(R.id.txtNumber5);
        final TextView Number6 = (TextView) findViewById(R.id.txtNumber6);

        ImageView img1 = (ImageView) findViewById(R.id.img1);
        ImageView img2 = (ImageView) findViewById(R.id.img2);
        ImageView img3 = (ImageView) findViewById(R.id.img3);
        ImageView img4 = (ImageView) findViewById(R.id.img4);
        ImageView img5 = (ImageView) findViewById(R.id.img5);
        ImageView img6 = (ImageView) findViewById(R.id.img6);

        img1.setImageResource(R.drawable.dice1);
        img2.setImageResource(R.drawable.dice2);
        img3.setImageResource(R.drawable.dice3);
        img4.setImageResource(R.drawable.dice4);
        img5.setImageResource(R.drawable.dice5);
        img6.setImageResource(R.drawable.dice6);



        Button btn = (Button) findViewById(R.id.btn);
             btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     for(int i=0;i<12;i++)
                     {
                     int j=1+SecureRandomNumber.nextInt(6);
                         switch (j)
                         {
                           case 1:
                               ++number1;
                               break;
                             case 2:
                                 ++number2;
                             case 3:
                                 ++number3;
                             case 4:
                                 ++number4;
                             case 5:
                                 ++number5;
                             case 6:
                                 ++number6;

                         }
                     }
                      Number1.setText(number1+"");
                      Number2.setText(number2+"");
                      Number3.setText(number3+"");
                      Number4.setText(number4+"");
                      Number5.setText(number5+"");
                      Number6.setText(number6+"");


                 }
             });

    }

}
