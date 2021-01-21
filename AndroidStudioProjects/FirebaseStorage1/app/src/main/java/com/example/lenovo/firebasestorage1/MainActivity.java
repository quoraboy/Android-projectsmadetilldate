package com.example.lenovo.firebasestorage1;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private Button choosefile;
    private EditText filename;
    private Button uploads;
    private ImageView image;
    private TextView showuploads;
    private ProgressBar progressBar;
    private Uri imageuri;
    private int pickanynumber=101;
    private StorageReference mStorageref;
    private DatabaseReference mDatabaseref;
    private StorageTask muploadtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choosefile= findViewById(R.id.choosefile);
        filename=findViewById(R.id.filename);
        uploads=findViewById(R.id.uploads);
        image=findViewById(R.id.image);
        showuploads=findViewById(R.id.showuploads);
        progressBar=findViewById(R.id.progress_horizontal);
        mStorageref=FirebaseStorage.getInstance().getReference("uploadss");
        mDatabaseref=FirebaseDatabase.getInstance().getReference("uploadss");
    choosefile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        openfilechooser();
        }
    });
    uploads.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         if(muploadtask !=null && muploadtask.isInProgress()){
       Toast.makeText(MainActivity.this,"Upload is in progress, please wait",Toast.LENGTH_LONG).show();
         }
         else{
             uploadfile();
        }}
    });
      showuploads.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });
    }

    private void openfilechooser() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,pickanynumber);
        //startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),pickanynumber);//in last parameter you have to pass any intergral value so, I pass "12" in the form of passanyvalue

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==pickanynumber && resultCode==RESULT_OK &&data!=null&& data.getData()!=null)
       {
           imageuri=data.getData();
       //Picasso.with(this).load(String.valueOf(imageuri)).into(image);
       image.setImageURI(imageuri);
       }
    }

    private String getFileExtention(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private  void uploadfile(){
    if(imageuri!=null)
    {
     StorageReference fileref=mStorageref.child(System.currentTimeMillis()+"."+getFileExtention(Uri.parse("imageuri")));
     muploadtask = fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
         @Override
         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
             double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
             progressBar.setProgress((int) progress);

             Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(0);
                }
            },500);
             Toast.makeText(MainActivity.this, "upload successfull", Toast.LENGTH_SHORT).show();
             upload upload=new upload(filename.getText().toString().trim(), mStorageref.getDownloadUrl().toString());
             String uploadId=mDatabaseref.push().getKey();  //(1/2) this lines make our database
             mDatabaseref.child(uploadId).setValue(upload); //(2/2) this lines make our database
//addOnsuccessListener is not working,add on progress listner is working
         }
     }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             Toast.makeText(MainActivity.this,"I am in add on failure listner", Toast.LENGTH_LONG).show();
         }
     });
    }
    else{
        Toast.makeText(this, "No file selected, please select a file to continue", Toast.LENGTH_LONG).show();
    }
    }
}
