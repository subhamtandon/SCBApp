package com.example.subhamtandon.scbapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
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
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddingMedicalRelatedPicAdminActivity extends AppCompatActivity {

    Button choose, submit;
    ImageView imageViewMedicalRelatedPic;
    ProgressBar progressBar;
    Uri mImageUri;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    StorageTask mUploadTask;

    ProgressDialog progressDialog;


    private static int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_medical_related_pic_admin);
        choose = findViewById(R.id.chooseMedicalRelatedPic);
        submit = findViewById(R.id.addMedicalRelatedPic);
        imageViewMedicalRelatedPic = findViewById(R.id.imageViewMedicalRelatedPic);
        progressBar = findViewById(R.id.uploadingMedicalRelatedPic);

        storageReference = FirebaseStorage.getInstance().getReference().child("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Medical Related Pictures");


        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(AddingMedicalRelatedPicAdminActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                    openFileChooser();

                }else {

                    ActivityCompat.requestPermissions(AddingMedicalRelatedPicAdminActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(AddingMedicalRelatedPicAdminActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadFile();
                }

            }
        });
    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData() != null){
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(imageViewMedicalRelatedPic);
        }
    }
    private String getFileExtention(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            openFileChooser();

        }else
            Toast.makeText(AddingMedicalRelatedPicAdminActivity.this, "please provide permission", Toast.LENGTH_SHORT).show();



    }
    private void uploadFile(){

        if(mImageUri !=null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Uploading File...");
            progressDialog.setProgress(0);
            progressDialog.show();progressDialog = new ProgressDialog(this);


            StorageReference storageReference1 = storageReference.child("Medical Related Pictures").child(System.currentTimeMillis()+"."+getFileExtention(mImageUri));
            mUploadTask = storageReference1.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    String id = databaseReference.push().getKey();
                    databaseReference.child(id).setValue(mImageUri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(AddingMedicalRelatedPicAdminActivity.this, "File successfully uploaded", Toast.LENGTH_SHORT).show();

                            }

                            else{
                                Toast.makeText(AddingMedicalRelatedPicAdminActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();


                            }

                        }
                    });
                    progressDialog.dismiss();
                    onBackPressed();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(AddingMedicalRelatedPicAdminActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setProgress(currentProgress);

                }
            });

        }
        else {
            Toast.makeText(this, "No Image selected", Toast.LENGTH_SHORT).show();
        }
    }
}
