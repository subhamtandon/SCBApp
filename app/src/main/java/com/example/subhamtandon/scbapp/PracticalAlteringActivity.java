package com.example.subhamtandon.scbapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class PracticalAlteringActivity extends AppCompatActivity {

    Button selectFile,upload;
    TextView notification;
    TextInputEditText practicalFileName;
    Uri pdfUri;

    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_altering);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        selectFile = findViewById(R.id.selectFile);
        upload = findViewById(R.id.upload);
        notification = findViewById(R.id.notification);
        practicalFileName = findViewById(R.id.practicalFileName);

        String professional = getIntent().getStringExtra("PROFESSIONAL");
        String subject = getIntent().getStringExtra("SUBJECT");

        Toast.makeText(this, professional + " : " + subject, Toast.LENGTH_SHORT).show();

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(PracticalAlteringActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                    selectPdf();

                }else {

                    ActivityCompat.requestPermissions(PracticalAlteringActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

                }

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pdfUri != null){
                    String ready = "true";
                    if(practicalFileName.getText().toString().trim().equals("")){
                        practicalFileName.setError(getString(R.string.error_field_required));
                        ready="false";
                    }
                    if(ready.equals("true")) {
                        uploadFile(pdfUri);
                    }
                }else
                    Toast.makeText(PracticalAlteringActivity.this, "Select a file", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void uploadFile(Uri pdfUri) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();

        StorageReference storageReference = storage.getReference();
        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        //final String fileName =  System.currentTimeMillis()+".pdf";
        //final String fileName1 = System.currentTimeMillis()+"";

        final String fileName =  practicalFileName.getText().toString()+".pdf";
        final String fileName1 = practicalFileName.getText().toString()+"";

        storageReference.child("Uploads").child(professional).child(subject).child("Practicals").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();

                        DatabaseReference reference = database.getReference();

                        reference.child("App").child("Study").child(professional).child(subject).child("Practicals").child(fileName1).setValue(url)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful())
                                            Toast.makeText(PracticalAlteringActivity.this, "File successfully uploaded", Toast.LENGTH_SHORT).show();
                                        else
                                            Toast.makeText(PracticalAlteringActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();

                                    }
                                });
                        progressDialog.dismiss();

                        Intent done = new Intent(PracticalAlteringActivity.this, UploadDoneActivity.class);
                        done.putExtra("TYPE","Practicals");
                        done.putExtra("PROFESSIONAL",professional);
                        done.putExtra("SUBJECT",subject);
                        startActivity(done);


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(PracticalAlteringActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);

            }
        });

    }

    private void selectPdf() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 86 && resultCode == RESULT_OK && data != null){

            pdfUri = data.getData();
            notification.setText("A file is selected : " + data.getData().getLastPathSegment());

        }else
            Toast.makeText(PracticalAlteringActivity.this, "please select a file", Toast.LENGTH_SHORT).show();

    }
}
