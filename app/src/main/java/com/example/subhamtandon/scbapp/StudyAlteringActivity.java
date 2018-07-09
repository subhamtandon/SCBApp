package com.example.subhamtandon.scbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudyAlteringActivity extends AppCompatActivity {

    TextView detailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_altering);

        String professional = getIntent().getStringExtra("PROFESSIONAL");
        String subject = getIntent().getStringExtra("SUBJECT");
        String type = getIntent().getStringExtra("TYPE");
        String chapter = getIntent().getStringExtra("CHAPTER");

        detailView = (TextView)findViewById(R.id.detailView);

        detailView.append(professional+ "\n");
        detailView.append(subject+ "\n");
        detailView.append(type+ "\n");
        detailView.append(chapter+ "\n");
    }
}
