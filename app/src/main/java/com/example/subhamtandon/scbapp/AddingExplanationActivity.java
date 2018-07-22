package com.example.subhamtandon.scbapp;

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

import java.net.URI;

public class AddingExplanationActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 6;

    EditText editTextExplanation;
    Button buttonChooseImageExplanation, uploadImageExplanation;
    TextView notificationExplanation;
    ImageView showImageExplanation;
    ProgressBar progressBar5;

    Uri imageExplanationUri;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_explanation);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String id = getIntent().getStringExtra("ID");

        editTextExplanation = (EditText) findViewById(R.id.editTextExplanation);
        buttonChooseImageExplanation = (Button) findViewById(R.id.buttonChooseImageExplanation);
        uploadImageExplanation = (Button) findViewById(R.id.uploadImageExplanation);
        notificationExplanation = (TextView) findViewById(R.id.notificationExplanation);
        showImageExplanation = (ImageView) findViewById(R.id.showImageExplanation);
        progressBar5 = (ProgressBar) findViewById(R.id.progressBar5);

        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("App").child("Study");

        buttonChooseImageExplanation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();

            }
        });

        uploadImageExplanation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ready = "true";
                if (editTextExplanation.getText().toString().trim().equals("")){
                    editTextExplanation.setError(getString(R.string.error_field_required));
                    ready="false";
                }
                if(ready.equals("true")) {

                    databaseReference.child(professional)
                            .child(subject)
                            .child("MCQs")
                            .child("Questions")
                            .child(id)
                            .child("Explanation")
                            .child("explanationText")
                            .setValue(editTextExplanation.getText().toString());

                    if (imageExplanationUri != null){

                        if (uploadTask != null && uploadTask.isInProgress()) {
                            Toast.makeText(AddingExplanationActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                        }else {

                            StorageReference fileReference = storageReference
                                    .child(professional)
                                    .child(subject)
                                    .child("MCQs")
                                    .child("Questions")
                                    .child(id)
                                    .child("Explanation")
                                    .child(System.currentTimeMillis() + "." + getFileExtension(imageExplanationUri));

                            uploadTask = fileReference.putFile(imageExplanationUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressBar5.setProgress(0);
                                                }
                                            }, 500);

                                            Toast.makeText(AddingExplanationActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                                            databaseReference.child(professional)
                                                    .child(subject)
                                                    .child("MCQs")
                                                    .child("Questions")
                                                    .child(id)
                                                    .child("Explanation")
                                                    .child("explanationImageUrl")
                                                    .setValue(taskSnapshot.getDownloadUrl().toString());

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(AddingExplanationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    })
                                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                            progressBar5.setProgress((int) progress);

                                        }
                                    });

                        }

                    }
                    else {

                        databaseReference.child(professional)
                                .child(subject)
                                .child("MCQs")
                                .child("Questions")
                                .child(id)
                                .child("Explanation")
                                .child("explanationImageUrl")
                                .setValue("No Image Selected");
                    }

                    Intent done = new Intent(AddingExplanationActivity.this, UploadDoneActivity.class);
                    done.putExtra("TYPE","MCQs");
                    done.putExtra("PROFESSIONAL",professional);
                    done.putExtra("SUBJECT",subject);
                    done.putExtra("CHAPTER", chapter);
                    done.putExtra("MODE",mode);
                    done.putExtra("SET",set);
                    startActivity(done);
                }
            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            imageExplanationUri= data.getData();
            showImageExplanation.setImageURI(imageExplanationUri);
            notificationExplanation.setText("A file is selected : " + data.getData().getLastPathSegment());
        }

    }
}
