package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EasyQuestionSetActivity extends AppCompatActivity {

    public void setOneClicked(View view){
        Intent i = new Intent(EasyQuestionSetActivity.this, QuestionsActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_question_set);
    }
}
