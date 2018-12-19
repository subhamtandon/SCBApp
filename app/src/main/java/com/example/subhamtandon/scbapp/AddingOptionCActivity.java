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

public class AddingOptionCActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 4;
    private static int flag = 0;

    EditText editTextOption3;
    Button buttonChooseImageOption3, uploadImageOption3;
    TextView notificationOption3;
    ImageView showImageOption3;
    ProgressBar progressBar3;
    Switch switch3;

    Uri imageOption3Uri;

    Boolean optionCValue;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_option_c);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        //final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String id = getIntent().getStringExtra("ID");

        editTextOption3 = (EditText) findViewById(R.id.editTextOption3);
        buttonChooseImageOption3 = (Button) findViewById(R.id.buttonChooseImageOption3);
        uploadImageOption3= (Button) findViewById(R.id.uploadImageOption3);
        notificationOption3 = (TextView) findViewById(R.id.notificationOption3);
        showImageOption3 = (ImageView) findViewById(R.id.showImageOption3);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        switch3 = (Switch) findViewById(R.id.switch3);

        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("App").child("Study");

        buttonChooseImageOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();

            }
        });

        uploadImageOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ready = "true";
                if (editTextOption3.getText().toString().trim().equals("")){
                    editTextOption3.setError(getString(R.string.error_field_required));
                    ready="false";
                }

                if(ready.equals("true")) {

                    databaseReference.child(professional)
                            .child(subject)
                            .child("MCQs")
                            .child("Questions")
                            .child(id)
                            .child("Option C")
                            .child("optionCText")
                            .setValue(editTextOption3.getText().toString());

                    optionCValue = switch3.isChecked();

                    databaseReference.child(professional)
                            .child(subject)
                            .child("MCQs")
                            .child("Questions")
                            .child(id)
                            .child("Option C")
                            .child("optionCValue")
                            .setValue(optionCValue);

                    if (imageOption3Uri != null){

                        if (uploadTask != null && uploadTask.isInProgress()) {
                            Toast.makeText(AddingOptionCActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                        }else {

                            StorageReference fileReference = storageReference
                                    .child(professional)
                                    .child(subject)
                                    .child("MCQs")
                                    .child("Questions")
                                    .child(id)
                                    .child("Option C")
                                    .child(System.currentTimeMillis() + "." + getFileExtension(imageOption3Uri));

                            uploadTask = fileReference.putFile(imageOption3Uri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressBar3.setProgress(0);
                                                }
                                            }, 500);

                                            Toast.makeText(AddingOptionCActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                                            databaseReference.child(professional)
                                                    .child(subject)
                                                    .child("MCQs")
                                                    .child("Questions")
                                                    .child(id)
                                                    .child("Option C")
                                                    .child("optionCImageUrl");
                                                    //.setValue(taskSnapshot.getDownloadUrl().toString());

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(AddingOptionCActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    })
                                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                            progressBar3.setProgress((int) progress);

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
                                .child("Option C")
                                .child("optionCImageUrl")
                                .setValue("No Image Selected");
                    }

                    Intent next = new Intent(AddingOptionCActivity.this, AddingOptionDActivity.class);
                    next.putExtra("PROFESSIONAL", professional);
                    next.putExtra("SUBJECT", subject);
                    next.putExtra("CHAPTER", chapter);
                    //next.putExtra("MODE",mode);
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

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            imageOption3Uri = data.getData();
            showImageOption3.setImageURI(imageOption3Uri);
            notificationOption3.setText("A file is selected : " + data.getData().getLastPathSegment());
        }

    }
}
