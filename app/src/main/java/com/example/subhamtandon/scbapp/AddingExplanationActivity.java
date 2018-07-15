package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URI;

public class AddingExplanationActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 6;

    EditText editTextExplanation;
    Button buttonChooseImageExplanation, uploadImageExplanation, next5;
    TextView notificationExplanation;
    ImageView showImageExplanation;
    ProgressBar progressBar5;

    Uri imageExplanationUri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_explanation);

        editTextExplanation = (EditText) findViewById(R.id.editTextExplanation);
        buttonChooseImageExplanation = (Button) findViewById(R.id.buttonChooseImageExplanation);
        uploadImageExplanation = (Button) findViewById(R.id.uploadImageExplanation);
        next5 = (Button)findViewById(R.id.next5);
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

            }
        });

        next5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
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
        }

    }
}
