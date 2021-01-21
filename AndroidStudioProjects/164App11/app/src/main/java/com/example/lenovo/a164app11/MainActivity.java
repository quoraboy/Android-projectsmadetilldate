package com.example.lenovo.a164app11;

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
import android.widget.TextView;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    private static final SecureRandom securerandom = new SecureRandom();

    private enum Status {Notstartedyet , proceed , lost , won};

     private static final int TIGER_CLAWS = 2;
     private static final int TREE=3;
     private static final int CLEVEN =7;
     private static final int WE_LEVEN=11;


     String oldtxtcalculationvalue= "";
     boolean firsttime = true;
     Status gamestatus=Status.Notstartedyet;
     int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView txtcal=(TextView) findViewById(R.id.txtcal);
        ImageView imagedice=(ImageView) findViewById(R.id.imagedice);
        Button btnrestart = (Button) findViewById(R.id.btnrestart);
        TextView txtgamestatus = (TextView) findViewById(R.id.txtgamestatus);




    }
}
