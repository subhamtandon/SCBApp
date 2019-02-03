package com.company.ssDev.que9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UploadDoneActivity extends AppCompatActivity {

    Button doneAdding, addNewQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_done);

        doneAdding = findViewById(R.id.doneAdding);
        addNewQuestion = findViewById(R.id.addNewQuestion);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String type = getIntent().getStringExtra("TYPE");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        //final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");

        if (type.equalsIgnoreCase("MCQs")){
            addNewQuestion.setVisibility(View.VISIBLE);
        }

        addNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newQuestion = new Intent(UploadDoneActivity.this,AddingQuestionActivity.class);
                newQuestion.putExtra("PROFESSIONAL", professional);
                newQuestion.putExtra("SUBJECT",subject);
                newQuestion.putExtra("CHAPTER", chapter);
                //newQuestion.putExtra("MODE", mode);
                newQuestion.putExtra("SET", set);
                startActivity(newQuestion);
            }
        });

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
                else if (type.equalsIgnoreCase("MCQs")){
                    Intent backToList = new Intent(UploadDoneActivity.this,ListOfSetsActivity.class);
                    backToList.putExtra("PROFESSIONAL", professional);
                    backToList.putExtra("SUBJECT",subject);
                    backToList.putExtra("CHAPTER", chapter);
                    //backToList.putExtra("MODE", mode);
                    startActivity(backToList);
                }

            }
        });


    }

}
