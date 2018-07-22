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

public class AddingQuestionActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 1;

    EditText editTextQuestion;
    Button buttonChooseImageQuestion, uploadImageQuestion;
    TextView notificationQuestion;
    ImageView showImageQuestion;
    ProgressBar progressBar;

    Uri imageQuestionUri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_question);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");

        Toast.makeText(this, professional + " : " + subject + " : " + chapter + " : "+ mode + " : " + set, Toast.LENGTH_SHORT).show();

        editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        buttonChooseImageQuestion = (Button) findViewById(R.id.buttonChooseImageQuestion);
        uploadImageQuestion = (Button) findViewById(R.id.uploadImageQuestion);
        notificationQuestion = (TextView) findViewById(R.id.notificationQuestion);
        showImageQuestion = (ImageView) findViewById(R.id.showImageQuestion);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("App").child("Study");

        final String id = databaseReference.push().getKey();
        databaseReference.child(professional).child(subject).child("MCQs").child("Questions").child(id).child("Chapter").setValue(chapter);
        databaseReference.child(professional).child(subject).child("MCQs").child("Questions").child(id).child("Mode").setValue(mode);
        databaseReference.child(professional).child(subject).child("MCQs").child("Questions").child(id).child("Set").setValue(set);

        buttonChooseImageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                openFileChooser();

            }
        });

        uploadImageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ready = "true";
                if (editTextQuestion.getText().toString().trim().equals("")){
                    editTextQuestion.setError(getString(R.string.error_field_required));
                    ready="false";
                }
                if(ready.equals("true")){

                    databaseReference.child(professional)
                            .child(subject)
                            .child("MCQs")
                            .child("Questions")
                            .child(id)
                            .child("Question")
                            .child("questionText")
                            .setValue(editTextQuestion.getText().toString());

                    databaseReference.child(professional)
                            .child(subject)
                            .child("MCQs")
                            .child(chapter)
                            .child(mode)
                            .child(set)
                            .child(id)
                            .setValue(editTextQuestion.getText().toString());

                    if (imageQuestionUri != null){
                        if (uploadTask != null && uploadTask.isInProgress()) {

                            Toast.makeText(AddingQuestionActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();

                        }else {

                            //uploadFile(id);
                            StorageReference fileReference = storageReference
                                    .child(professional)
                                    .child(subject)
                                    .child("MCQs")
                                    .child("Questions")
                                    .child(id)
                                    .child("Question")
                                    .child(System.currentTimeMillis() + "." + getFileExtension(imageQuestionUri));

                            uploadTask = fileReference.putFile(imageQuestionUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressBar.setProgress(0);
                                                }
                                            }, 500);

                                            Toast.makeText(AddingQuestionActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                                            databaseReference.child(professional)
                                                    .child(subject)
                                                    .child("MCQs")
                                                    .child("Questions")
                                                    .child(id)
                                                    .child("Question")
                                                    .child("questionImageUrl")
                                                    .setValue(taskSnapshot.getDownloadUrl().toString());

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(AddingQuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    })
                                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                            progressBar.setProgress((int) progress);

                                        }
                                    });
                        }
                    }
                    else{

                        databaseReference.child(professional)
                                .child(subject)
                                .child("MCQs")
                                .child("Questions")
                                .child(id)
                                .child("Question")
                                .child("questionImageUrl")
                                .setValue("No Image Selected");

                    }

                    Intent next = new Intent(AddingQuestionActivity.this, AddingOptionAActivity.class);
                    next.putExtra("PROFESSIONAL", professional);
                    next.putExtra("SUBJECT", subject);
                    next.putExtra("CHAPTER", chapter);
                    next.putExtra("MODE",mode);
                    next.putExtra("SET",set);
                    next.putExtra("ID",id);
                    startActivity(next);
                }

            }
        });

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    /*private void uploadFile(final String id) {

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");

        if (imageQuestionUri != null){

            StorageReference fileReference = storageReference
                    .child(professional)
                    .child(subject)
                    .child("MCQs")
                    .child(id)
                    .child("Question")
                    .child(System.currentTimeMillis() + "." + getFileExtension(imageQuestionUri));

            uploadTask = fileReference.putFile(imageQuestionUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(AddingQuestionActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                            //String uploadId = databaseReference.push().getKey();
                            databaseReference.child(professional)
                                    .child(subject)
                                    .child("MCQs")
                                    .child(id)
                                    .child("Question")
                                    .child("questionImageUrl")
                                    .setValue(taskSnapshot.getDownloadUrl().toString());

                            Intent next = new Intent(AddingQuestionActivity.this, AddingOptionAActivity.class);
                            next.putExtra("PROFESSIONAL", professional);
                            next.putExtra("SUBJECT", subject);
                            next.putExtra("CHAPTER", chapter);
                            next.putExtra("MODE",mode);
                            next.putExtra("SET",set);
                            next.putExtra("ID",id);
                            startActivity(next);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(AddingQuestionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);

                        }
                    });

        }else {
            imageQuestionUri = null;
            Intent next = new Intent(AddingQuestionActivity.this, AddingOptionAActivity.class);
            next.putExtra("PROFESSIONAL", professional);
            next.putExtra("SUBJECT", subject);
            next.putExtra("CHAPTER", chapter);
            next.putExtra("MODE",mode);
            next.putExtra("SET",set);
            next.putExtra("ID",id);
            startActivity(next);
        }
    }*/

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            imageQuestionUri = data.getData();
            showImageQuestion.setImageURI(imageQuestionUri);
            notificationQuestion.setText("A file is selected : " + data.getData().getLastPathSegment());
        }

    }
}
