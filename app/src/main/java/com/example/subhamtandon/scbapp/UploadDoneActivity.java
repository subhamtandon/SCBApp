package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UploadDoneActivity extends AppCompatActivity {

    Button doneAdding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_done);

        doneAdding = findViewById(R.id.doneAdding);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String type = getIntent().getStringExtra("TYPE");

        doneAdding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equalsIgnoreCase("Practicals")){
                    Intent backToList = new Intent(UploadDoneActivity.this, ListOfPracticalsActivity.class);
                    backToList.putExtra("PROFESSIONAL", professional);
                    backToList.putExtra("SUBJECT",subject);
                    startActivity(backToList);
                }
                else if(type.equalsIgnoreCase("Records")){
                    Intent backToList = new Intent(UploadDoneActivity.this, ListOfRecordsActivity.class);
                    backToList.putExtra("PROFESSIONAL", professional);
                    backToList.putExtra("SUBJECT",subject);
                    startActivity(backToList);
                }
                else if(type.equalsIgnoreCase("PYQs")){
                    Intent backToList = new Intent(UploadDoneActivity.this, ListOfPYQsActivity.class);
                    backToList.putExtra("PROFESSIONAL", professional);
                    backToList.putExtra("SUBJECT",subject);
                    startActivity(backToList);
                }

            }
        });

    }
}
