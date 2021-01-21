package com.example.lenovo.authpro1;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class photoretrive extends AppCompatActivity {
    StorageReference mstoreref;
    Button btnR;
    ImageView imageViewR;
    DatabaseReference mdataRef;
    Uri uriprofileimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoretrive);
        mstoreref = FirebaseStorage.getInstance().getReference("uploads");
        mdataRef = FirebaseDatabase.getInstance().getReference("uploads");
     imageViewR=findViewById(R.id.ima);
        btnR=findViewById(R.id.btnR);
        btnR=findViewById(R.id.btnR);
          imageViewR=findViewById(R.id.ima);
              btnR.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {


                      mdataRef.addValueEventListener(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  String url = (String) dataSnapshot.getValue();
                              Toast.makeText(photoretrive.this, "image displayed", Toast.LENGTH_SHORT).show();
                              Glide.with(photoretrive.this).load(url).into(imageViewR);
                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError databaseError) {

                          }
                      });

                  }
              });
    }
}
