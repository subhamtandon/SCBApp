package com.example.subhamtandon.scbapp;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserQuestionActivity extends AppCompatActivity {

    TextView textViewUserQuestion, textViewUserOptionA, textViewUserOptionB, textViewUserOptionC, textViewUserOptionD, textViewUserExplanation, pathChap, pathSet;

    CardView optionACardView, optionBCardView, optionCCardView, optionDCardView;
    Button buttonSubmitAnswer, exitQuestions;
    FloatingActionButton nextQuestion;

    DatabaseReference databaseReference;
    String professional, subject, type, chapter, set, explanation;
    int count = 0, rightAnswer = 0;
    ArrayList<String> idsArrayList;

    Boolean optionAValue , optionBValue, optionCValue, optionDValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_question);

        professional = getIntent().getStringExtra("PROFESSIONAL");
        subject = getIntent().getStringExtra("SUBJECT");
        type = getIntent().getStringExtra("TYPE");
        chapter = getIntent().getStringExtra("CHAPTER");
        set = getIntent().getStringExtra("SET");
        idsArrayList = getIntent().getStringArrayListExtra("IDS");
        count = getIntent().getIntExtra("COUNT", 0);

        Toast.makeText(this, professional + ":" + subject + ":" + type + ":" + chapter + ":" + set + ":" + idsArrayList.size(), Toast.LENGTH_SHORT).show();

        textViewUserQuestion = findViewById(R.id.textViewUserQuestion);
        textViewUserOptionA = findViewById(R.id.textViewUserOptionA);
        textViewUserOptionB = findViewById(R.id.textViewUserOptionB);
        textViewUserOptionC = findViewById(R.id.textViewUserOptionC);
        textViewUserOptionD = findViewById(R.id.textViewUserOptionD);
        textViewUserExplanation = findViewById(R.id.textViewUserExplanation);
        pathChap = findViewById(R.id.pathChap);
        pathSet = findViewById(R.id.pathSet);

        optionACardView = findViewById(R.id.optionACardView);
        optionBCardView = findViewById(R.id.optionBCardView);
        optionCCardView = findViewById(R.id.optionCCardView);
        optionDCardView = findViewById(R.id.optionDCardView);

        buttonSubmitAnswer = findViewById(R.id.buttonSubmitAnswer);
        exitQuestions = findViewById(R.id.exitQuestions);

        nextQuestion = findViewById(R.id.nextQuestion);

        pathChap.setText(chapter);
        pathSet.setText(set);

        buttonSubmitAnswer.setEnabled(false);

        showQuestion();

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count<idsArrayList.size()){
                    showQuestion();
                }
                else {
                    Toast.makeText(UserQuestionActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }
        });

        exitQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog();
            }
        });
    }

    private void showQuestion() {

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("App")
                .child("Study")
                .child(professional)
                .child(subject)
                .child(type)
                .child("Questions")
                .child(idsArrayList.get(count));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    NewQuestion newQuestion = dataSnapshot.getValue(NewQuestion.class);
                    textViewUserQuestion.setText(newQuestion.getQuestion().getQuestionText());
                    textViewUserOptionA.setText(newQuestion.getOptionA().getOptionAText());
                    optionAValue = newQuestion.getOptionA().getOptionAValue();
                    optionBValue = newQuestion.getOptionB().getOptionBValue();
                    optionCValue = newQuestion.getOptionC().getOptionCValue();
                    optionDValue = newQuestion.getOptionD().getOptionDValue();
                    textViewUserOptionB.setText(newQuestion.getOptionB().getOptionBText());
                    textViewUserOptionC.setText(newQuestion.getOptionC().getOptionCText());
                    textViewUserOptionD.setText(newQuestion.getOptionD().getOptionDText());
                    explanation = newQuestion.getExplanation().getExplanationText();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserQuestionActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed(){

        Log.d("CDA", "onBackPressed Called");
        exitDialog();

    }

    public void exitDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
