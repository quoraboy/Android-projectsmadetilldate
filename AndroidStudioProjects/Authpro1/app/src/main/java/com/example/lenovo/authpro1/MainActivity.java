package com.example.lenovo.authpro1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar prom;
     EditText userm;            //MainActivity=LoginPage
     EditText passm;
     FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        userm =(EditText) findViewById(R.id.userm);
        passm =(EditText)findViewById(R.id.passm);
        prom =(ProgressBar)findViewById(R.id.prom);


        findViewById(R.id.signupbtn).setOnClickListener(this);
        findViewById(R.id.loginbtn).setOnClickListener(this);
    }

    private void userlogin() {
        prom.setVisibility(View.VISIBLE);
        String ema= userm.getText().toString().trim();
        String pas= passm.getText().toString().trim();
        if(ema.isEmpty())
        {   prom.setVisibility(View.INVISIBLE);
            userm.setError("Email is required...");
            userm.requestFocus();
            return;   //returning to calling funtion
        }
        if(pas.isEmpty())
        {   prom.setVisibility(View.INVISIBLE);
            passm.setError("Password is required...");
            passm.requestFocus();
            return;   //returing to calling functoin
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(ema).matches())
        {   prom.setVisibility(View.INVISIBLE);
            prom.setVisibility(View.INVISIBLE);
            userm.setError("Enter a vadit email address");
            userm.requestFocus();
            return;
        }
        if(passm.length()<6)
        {   prom.setVisibility(View.INVISIBLE);
            passm.setError("Mini. required password length is 6");
            passm.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(ema,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                prom.setVisibility(View.VISIBLE);
                if(task.isSuccessful())
                {prom.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this,"Login",Toast.LENGTH_LONG).show();

                  // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(new Intent(MainActivity.this,Profile.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }

        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupbtn:
                startActivity(new Intent(this,Signup.class));
                break;
            case R.id.loginbtn:
                userlogin();
                break;
        }
    }


}
