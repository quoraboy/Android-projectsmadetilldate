package com.example.lenovo.firebaseproject2nddatabase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView ed1=(TextView)findViewById(R.id.ed1);
        final TextView ed2=(TextView)findViewById(R.id.ed2);
        final TextView ed3=(TextView)findViewById(R.id.ed3);
        Button btn=(Button) findViewById(R.id.btn);
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(); //databaseReference is pointing "fir-project2nddatabase"
                                                                                                  // go to firebase>>database
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ed1= ed1.getText().toString();
                String Ed2=ed2.getText().toString();

                // Integer Ed3=Integer.parseFloat(ed3.getText().toString());
//             databaseReference.child("Name").setValue(Ed1);
//             databaseReference.child("E-mail").setValue(Ed2);
                //databaseReference.push().setValue(Ed1);

                //databaseReference.push().setValue(Ed2);
             //databaseReference.child("Mob no.").setValue(Dl);

                //databaseReference.child("User01").push().setValue(Ed1);
               // databaseReference.child("User01").push().setValue(Ed2);

                HashMap<String,String> map=new HashMap<String, String>();
               map.put("Name",Ed1);
                map.put("E-Mail",Ed2);
                databaseReference.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"data uploaded",Toast.LENGTH_LONG).show();
                            }
                            else{

                            Toast.makeText(MainActivity.this,"Error...data not uploaded",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


    }
}
