package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

public class AddingMedicalRelatedPicAdminActivity extends AppCompatActivity {

    Button choose, submit;
    ImageView imageViewMedicalRelatedPic;
    ProgressBar progressBar;
    Uri mImageUri;

    private static int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_medical_related_pic_admin);
        choose = findViewById(R.id.chooseMedicalRelatedPic);
        submit = findViewById(R.id.addMedicalRelatedPic);
        imageViewMedicalRelatedPic = findViewById(R.id.imageViewMedicalRelatedPic);
        progressBar = findViewById(R.id.uploadingMedicalRelatedPic);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();
            }
        });
    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData() != null){
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(imageViewMedicalRelatedPic);
        }
    }
}
