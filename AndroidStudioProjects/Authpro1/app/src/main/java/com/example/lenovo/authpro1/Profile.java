package com.example.lenovo.authpro1;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Profile extends AppCompatActivity {
    private static final int PassAnyInteger = 12;//set any value you want
    ImageView imageView;
    EditText name;
    Uri uriprofileimage;
    String profilepictureurl;
    ProgressBar progressbar;
DatabaseReference mdataRef;
    StorageReference mstoreref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
     name=findViewById(R.id.editname);
     imageView=findViewById(R.id.image);
     progressbar=findViewById(R.id.progressbar);
         mstoreref = FirebaseStorage.getInstance().getReference("uploads");
        mdataRef = FirebaseDatabase.getInstance().getReference("uploads");

        imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           Showimagechooser();
         }
     });
     
     findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             uploadImageToFirebaseStorage();
             Intent intent=new Intent(Profile.this,photoretrive.class);
             startActivity(intent);
         }
     });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==PassAnyInteger && resultCode==RESULT_OK && data!=null && data.getData()!=null)
    {
        uriprofileimage=data.getData();
        try {
            Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uriprofileimage);
            imageView.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    private String getFileExtention(Uri uri)
    {
        ContentResolver CR=getContentResolver();
        MimeTypeMap mine=MimeTypeMap.getSingleton();
        return  mine.getExtensionFromMimeType(CR.getType(uri));
    }

    private void uploadImageToFirebaseStorage() {
        if(uriprofileimage!=null)
        {
             //Toast.makeText(this, "UploadImageToFirebase", Toast.LENGTH_SHORT).show();
        //    mdataRef.child("UploadId").setValue("uriprofileimage");
            mstoreref=mstoreref.child(System.currentTimeMillis()+"."+getFileExtention(uriprofileimage));
            mstoreref.putFile(uriprofileimage).addOnCompleteListener(new OnCompleteListener <UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task <UploadTask.TaskSnapshot> task) {
                    mdataRef.setValue(task.getResult().toString());

                }
            });
//            mstoreref.putFile(uriprofileimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(Profile.this, "Uploaded successful", Toast.LENGTH_SHORT).show();
//                 //  String UploadId=mdataRef.push().getKey();
//                  //  Toast.makeText(Profile.this,String.valueOf(taskSnapshot.getMetadata().getReference().getDownloadUrl()), Toast.LENGTH_SHORT).show();
//                  mdataRef.setValue(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(Profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//
//                }
//            });
        }
        else{
            Toast.makeText(this, "Please select an Image", Toast.LENGTH_SHORT).show();
        }

    }


    private  void Showimagechooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),PassAnyInteger);//in last parameter you have to pass any intergral value so, I pass "12" in the form of passanyvalue
        //Above line will open a new activity from where we will choose a image
    }
}
