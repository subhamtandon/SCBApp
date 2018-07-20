package com.example.subhamtandon.scbapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
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

public class AddingOptionAActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 2;
    private static int flag = 0;

    EditText editTextOption1;
    Button buttonChooseImageOption1, uploadImageOption1;
    TextView notificationOption1;
    ImageView showImageOption1;
    ProgressBar progressBar1;
    Switch switch1;

    Uri imageOption1Uri;

    Boolean optionAValue;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_option_a);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String id = getIntent().getStringExtra("ID");

        Toast.makeText(this, professional + " : " + subject + " : " + chapter + " : "+ mode + " : " + set, Toast.LENGTH_SHORT).show();

        editTextOption1 = (EditText) findViewById(R.id.editTextOption1);
        buttonChooseImageOption1 = (Button) findViewById(R.id.buttonChooseImageOption1);
        uploadImageOption1 = (Button) findViewById(R.id.uploadImageOption1);
        notificationOption1 = (TextView) findViewById(R.id.notificationOption1);
        showImageOption1 = (ImageView) findViewById(R.id.showImageOption1);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        switch1 = (Switch) findViewById(R.id.switch1);

        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("App").child("Study");

        buttonChooseImageOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();

            }
        });

        uploadImageOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ready = "true";
                if (editTextOption1.getText().toString().trim().equals("")){
                    editTextOption1.setError(getString(R.string.error_field_required));
                    ready="false";
                }
                if(ready.equals("true")){

                    databaseReference.child(professional)
                            .child(subject)
                            .child("MCQs")
                            .child(id)
                            .child("Option A")
                            .child("optionAText")
                            .setValue(editTextOption1.getText().toString());

                    optionAValue = switch1.isChecked();

                    databaseReference.child(professional)
                            .child(subject)
                            .child("MCQs")
                            .child(id)
                            .child("Option A")
                            .child("optionAValue")
                            .setValue(optionAValue);

                    if (imageOption1Uri != null){

                        if (uploadTask != null && uploadTask.isInProgress()) {
                            Toast.makeText(AddingOptionAActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                        }else {

                            StorageReference fileReference = storageReference
                                    .child(professional)
                                    .child(subject)
                                    .child("MCQs")
                                    .child(id)
                                    .child("Option A")
                                    .child(System.currentTimeMillis() + "." + getFileExtension(imageOption1Uri));

                            uploadTask = fileReference.putFile(imageOption1Uri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressBar1.setProgress(0);
                                                }
                                            }, 500);

                                            Toast.makeText(AddingOptionAActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                                            databaseReference.child(professional)
                                                    .child(subject)
                                                    .child("MCQs")
                                                    .child(id)
                                                    .child("Option A")
                                                    .child("optionAImageUrl")
                                                    .setValue(taskSnapshot.getDownloadUrl().toString());

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(AddingOptionAActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    })
                                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                            progressBar1.setProgress((int) progress);

                                        }
                                    });

                        }

                    }
                    else {

                        databaseReference.child(professional)
                                .child(subject)
                                .child("MCQs")
                                .child(id)
                                .child("Option A")
                                .child("optionAImageUrl")
                                .setValue("No Image Selected");

                    }

                    Intent next = new Intent(AddingOptionAActivity.this, AddingOptionBActivity.class);
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

        /*


            }
        });*/
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

            imageOption1Uri = data.getData();
            showImageOption1.setImageURI(imageOption1Uri);
            notificationOption1.setText("A file is selected : " + data.getData().getLastPathSegment());
        }

    }
}
