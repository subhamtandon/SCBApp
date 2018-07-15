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

public class AddingOptionCActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 4;

    EditText editTextOption3;
    Button buttonChooseImageOption3, uploadImageOption3, next3;
    TextView notificationOption3;
    ImageView showImageOption3;
    ProgressBar progressBar3;

    Uri imageOption3Uri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_option_c);

        editTextOption3 = (EditText) findViewById(R.id.editTextOption3);
        buttonChooseImageOption3 = (Button) findViewById(R.id.buttonChooseImageOption3);
        uploadImageOption3= (Button) findViewById(R.id.uploadImageOption3);
        next3 = (Button)findViewById(R.id.next3);
        notificationOption3 = (TextView) findViewById(R.id.notificationOption2);
        showImageOption3 = (ImageView) findViewById(R.id.showImageOption3);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);

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

            }
        });

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddingOptionDActivity.class));

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

            imageOption3Uri = data.getData();
            showImageOption3.setImageURI(imageOption3Uri);
        }

    }
}
