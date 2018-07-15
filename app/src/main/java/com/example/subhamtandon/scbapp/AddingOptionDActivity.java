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

public class AddingOptionDActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 5;

    EditText editTextOption4;
    Button buttonChooseImageOption4, uploadImageOption4, next4;
    TextView notificationOption4;
    ImageView showImageOption4;
    ProgressBar progressBar4;

    Uri imageOption4Uri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_option_d);

        editTextOption4 = (EditText) findViewById(R.id.editTextOption4);
        buttonChooseImageOption4 = (Button) findViewById(R.id.buttonChooseImageOption4);
        uploadImageOption4 = (Button) findViewById(R.id.uploadImageOption4);
        next4 = (Button)findViewById(R.id.next4);
        notificationOption4 = (TextView) findViewById(R.id.notificationOption4);
        showImageOption4 = (ImageView) findViewById(R.id.showImageOption4);
        progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);

        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("App").child("Study");

        buttonChooseImageOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();

            }
        });

        uploadImageOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddingExplanationActivity.class));

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

            imageOption4Uri = data.getData();
            showImageOption4.setImageURI(imageOption4Uri);
        }

    }
}
