package com.example.lenovo.urbanclappartnercopy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("OnClick")
public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
    next=(Button) findViewById(R.id.next);

    next.setEnabled(false);
   //locationbtn.setOnClickListener(new View.OnClickListener() {

    }

    void  onClick(View v)
    {
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId())
     {
         case R.id.r1:
             if(checked)
             {
                 next.setEnabled(true);
                 break;

                 }
         case R.id.r2:
             if(checked)
             {
                 next.setEnabled(true);
                 break;

             }
         case R.id.r3:
             if(checked)
             {
                 next.setEnabled(true);
                 break;

             }
         case R.id.r4:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r5:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r6:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r7:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r8:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r9:
             if(checked)
             {   next.setEnabled(true);
                 break;
             }
         case R.id.r10:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r11:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r12:
             if(checked)
             {                  next.setEnabled(true);
                 break;}
         case R.id.r13:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r14:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r15:
             if(checked)
             {   next.setEnabled(true);

                 break;
             }
         case R.id.r16:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r17:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r18:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r19:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r20:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r21:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r22:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r23:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r24:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }
         case R.id.r25:
             if(checked)
             {
                 next.setEnabled(true);
                 break;
             }


     }


     next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //Toast.makeText(MainActivity.this,"aaaaa",Toast.LENGTH_SHORT).show();
             Intent intent=new Intent(MainActivity.this,createaccount.class);
             startActivity(intent);
             finish();
         }
     });
    }



    @Override
    public void onStart() //when the app start onstart meathod is called
    {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
     if (currentUser ==null)
     {
         Intent intent = new Intent(MainActivity.this,Login.class);
         startActivity(intent);
         finish();
     }

     }

}

