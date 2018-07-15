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

public class AddingOptionAActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 2;

    EditText editTextOption1;
    Button buttonChooseImageOption1, uploadImageOption1, next1;
    TextView notificationOption1;
    ImageView showImageOption1;
    ProgressBar progressBar1;

    Uri imageOption1Uri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_option_a);

        editTextOption1 = (EditText) findViewById(R.id.editTextOption1);
        buttonChooseImageOption1 = (Button) findViewById(R.id.buttonChooseImageOption1);
        uploadImageOption1 = (Button) findViewById(R.id.uploadImageOption1);
        next1 = (Button)findViewById(R.id.next1);
        notificationOption1 = (TextView) findViewById(R.id.notificationOption1);
        showImageOption1 = (ImageView) findViewById(R.id.showImageOption1);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);

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

            }
        });

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddingOptionBActivity.class));

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

            imageOption1Uri = data.getData();
            showImageOption1.setImageURI(imageOption1Uri);
        }

    }
}
