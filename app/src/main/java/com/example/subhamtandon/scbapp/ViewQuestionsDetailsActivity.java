package com.example.subhamtandon.scbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewQuestionsDetailsActivity extends AppCompatActivity {

    ImageView imageViewQuestion, imageViewOptionA, imageViewOptionB, imageViewOptionC, imageViewOptionD;
    TextView textViewQuestion, textViewOptionA, textViewOptionB, textViewOptionC, textViewOptionD, textViewExplanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions_details);

        imageViewQuestion = findViewById(R.id.imageViewQuestion);
        imageViewOptionA = findViewById(R.id.imageViewOptionA);
        imageViewOptionB = findViewById(R.id.imageViewOptionB);
        imageViewOptionC = findViewById(R.id.imageViewOptionC);
        imageViewOptionD = findViewById(R.id.imageViewOptionD);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewOptionA = findViewById(R.id.textViewOptionA);
        textViewOptionB = findViewById(R.id.textViewOptionB);
        textViewOptionC = findViewById(R.id.textViewOptionC);
        textViewOptionD = findViewById(R.id.textViewOptionD);
        textViewExplanation = findViewById(R.id.textViewExplanation);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String id = getIntent().getStringExtra("ID");

    }
}
