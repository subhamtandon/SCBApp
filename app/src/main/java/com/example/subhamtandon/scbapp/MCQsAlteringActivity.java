package com.example.subhamtandon.scbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MCQsAlteringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcqs_altering);

        String professional = getIntent().getStringExtra("PROFESSIONAL");
        String subject = getIntent().getStringExtra("SUBJECT");
        String chapter = getIntent().getStringExtra("CHAPTER");
        String mode = getIntent().getStringExtra("MODE");

        Toast.makeText(this, professional + " : " + subject + " : " + chapter + " "+ mode, Toast.LENGTH_SHORT).show();
    }
}
