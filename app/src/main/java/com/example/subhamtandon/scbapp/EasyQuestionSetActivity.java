package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EasyQuestionSetActivity extends AppCompatActivity {

    public void setOneClicked(View view){

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String type = getIntent().getStringExtra("TYPE");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");

        Intent intent = new Intent(EasyQuestionSetActivity.this, QuestionsActivity.class);
        intent.putExtra("PROFESSIONAL", professional);
        intent.putExtra("SUBJECT", subject);
        intent.putExtra("TYPE", type);
        intent.putExtra("CHAPTER", chapter);
        intent.putExtra("MODE", mode);
        intent.putExtra("SET", "Set 01");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_question_set);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String type = getIntent().getStringExtra("TYPE");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");

        Toast.makeText(EasyQuestionSetActivity.this, professional + ":" + subject + ":" + type + ":" + chapter + ":" + mode, Toast.LENGTH_SHORT).show();
    }
}
