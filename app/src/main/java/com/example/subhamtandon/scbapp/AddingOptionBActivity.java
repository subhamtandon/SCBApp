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

public class AddingOptionBActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 3;

    EditText editTextOption2;
    Button buttonChooseImageOption2, uploadImageOption2, next2;
    TextView notificationOption2;
    ImageView showImageOption2;
    ProgressBar progressBar2;

    Uri imageOption2Uri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_option_b);

        editTextOption2 = (EditText) findViewById(R.id.editTextOption2);
        buttonChooseImageOption2 = (Button) findViewById(R.id.buttonChooseImageOption2);
        uploadImageOption2 = (Button) findViewById(R.id.uploadImageOption2);
        next2 = (Button)findViewById(R.id.next2);
        notificationOption2 = (TextView) findViewById(R.id.notificationOption2);
        showImageOption2 = (ImageView) findViewById(R.id.showImageOption2);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("App").child("Study");

        buttonChooseImageOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();

            }
        });

        uploadImageOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddingOptionCActivity.class));

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

            imageOption2Uri = data.getData();
            showImageOption2.setImageURI(imageOption2Uri);
        }

    }
}
