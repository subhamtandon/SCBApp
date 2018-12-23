package com.example.subhamtandon.scbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
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
import java.util.Random;

public class NewMockTestActivity extends AppCompatActivity {

    TextView textViewUserQuestion, textViewUserOptionA, textViewUserOptionB, textViewUserOptionC, textViewUserOptionD, textViewUserExplanation, textViewUserResult;

    CardView optionACardView, optionBCardView, optionCCardView, optionDCardView;
    Button buttonSeeExplanation, exitQuestions;
    FloatingActionButton nextQuestion;

    DatabaseReference databaseReference;
    String professional, explanation, randomElement1;
    int count = 0, rightAnswer = 0;
    ArrayList<String> idsArrayList = new ArrayList<>();
    ArrayList<String> subjectsArrayList = new ArrayList<>();
    ArrayList<Integer> questionShownList = new ArrayList<>();

    int n, found, index1;

    Boolean optionAValue , optionBValue, optionCValue, optionDValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mock_test);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mock Test");
        }

        professional = getIntent().getStringExtra("PROFESSIONAL");
        n = Integer.parseInt(getIntent().getStringExtra("NUMBER OF QUESTIONS"));
        idsArrayList = getIntent().getStringArrayListExtra("IDSLIST");
        subjectsArrayList = getIntent().getStringArrayListExtra("SUBJECTSLIST");

        Toast.makeText(this, professional + ":"+ n+ ":" + idsArrayList.size() + ":" + subjectsArrayList.size(), Toast.LENGTH_SHORT).show();

        textViewUserQuestion = findViewById(R.id.textViewUserQuestion);
        textViewUserOptionA = findViewById(R.id.textViewUserOptionA);
        textViewUserOptionB = findViewById(R.id.textViewUserOptionB);
        textViewUserOptionC = findViewById(R.id.textViewUserOptionC);
        textViewUserOptionD = findViewById(R.id.textViewUserOptionD);
        textViewUserExplanation = findViewById(R.id.textViewUserExplanation);
        textViewUserResult = findViewById(R.id.textViewUserResult);

        optionACardView = findViewById(R.id.optionACardView);
        optionBCardView = findViewById(R.id.optionBCardView);
        optionCCardView = findViewById(R.id.optionCCardView);
        optionDCardView = findViewById(R.id.optionDCardView);

        buttonSeeExplanation = findViewById(R.id.buttonSeeExplanation);
        exitQuestions = findViewById(R.id.exitQuestions);

        nextQuestion = findViewById(R.id.nextQuestion);

        nextQuestion.setVisibility(View.GONE);

        String randomElement = (idsArrayList.get(new Random().nextInt(idsArrayList.size())));
        int index = idsArrayList.indexOf(randomElement);
        questionShownList.add(index);

        showQuestion(index);

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionACardView.setClickable(true);
                optionBCardView.setClickable(true);
                optionCCardView.setClickable(true);
                optionDCardView.setClickable(true);
                optionACardView.setCardBackgroundColor(Color.parseColor("#4e0000"));
                optionBCardView.setCardBackgroundColor(Color.parseColor("#4e0000"));
                optionCCardView.setCardBackgroundColor(Color.parseColor("#4e0000"));
                optionDCardView.setCardBackgroundColor(Color.parseColor("#4e0000"));
                textViewUserResult.setVisibility(View.GONE);
                buttonSeeExplanation.setVisibility(View.GONE);
                textViewUserExplanation.setVisibility(View.GONE);
                nextQuestion.setVisibility(View.GONE);

                if (questionShownList.size() < n) {

                    found = 0;
                    while (true) {
                        randomElement1 = (idsArrayList.get(new Random().nextInt(idsArrayList.size())));
                        index1 = idsArrayList.indexOf(randomElement1);
                        for (int i = 0; i < questionShownList.size(); i++) {
                            if (index1 == questionShownList.get(i)) {
                                found = 1;
                                break;
                            }
                        }
                        if (found == 1) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    questionShownList.add(index1);
                    showQuestion(index1);
                }
                else {
                    Toast.makeText(NewMockTestActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewMockTestActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.scorecard, null);

                    TextView correctAnswers = mView.findViewById(R.id.correctAnswers);
                    TextView totalQuestions = mView.findViewById(R.id.totalQuestions);

                    String count1 = Integer.toString(n);
                    String rightAnswer1 = Integer.toString(rightAnswer);

                    correctAnswers.setText(rightAnswer1);
                    totalQuestions.setText(count1);
                    builder.setTitle("SCORECARD")
                            .setCancelable(false)
                            .setView(mView)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    onBackPressed();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        optionACardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //optionACardView.setCardBackgroundColor(Color.parseColor("#ffcc00"));
                disableButtons();
                textViewUserResult.setVisibility(View.VISIBLE);
                if (optionAValue){
                    optionACardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
                    rightAnswer+=1;
                    textViewUserResult.setText("CORRECT");
                }else {
                    optionACardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
                    textViewUserResult.setText("INCORRECT");
                    showRightAnswer();
                }
                buttonSeeExplanation.setVisibility(View.VISIBLE);
                nextQuestion.setVisibility(View.VISIBLE);
            }
        });

        optionBCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //optionACardView.setCardBackgroundColor(Color.parseColor("#ffcc00"));
                disableButtons();
                textViewUserResult.setVisibility(View.VISIBLE);
                if (optionBValue){
                    optionBCardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
                    rightAnswer+=1;
                    textViewUserResult.setText("CORRECT");
                }else {
                    optionBCardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
                    textViewUserResult.setText("INCORRECT");
                    showRightAnswer();
                }
                buttonSeeExplanation.setVisibility(View.VISIBLE);
                nextQuestion.setVisibility(View.VISIBLE);

            }
        });

        optionCCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //optionACardView.setCardBackgroundColor(Color.parseColor("#ffcc00"));
                disableButtons();
                textViewUserResult.setVisibility(View.VISIBLE);
                if (optionCValue){
                    optionCCardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
                    rightAnswer+=1;
                    textViewUserResult.setText("CORRECT");
                }else {
                    optionCCardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
                    textViewUserResult.setText("INCORRECT");
                    showRightAnswer();
                }
                buttonSeeExplanation.setVisibility(View.VISIBLE);
                nextQuestion.setVisibility(View.VISIBLE);

            }
        });

        optionDCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //optionACardView.setCardBackgroundColor(Color.parseColor("#ffcc00"));
                disableButtons();
                textViewUserResult.setVisibility(View.VISIBLE);
                if (optionDValue){
                    optionDCardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
                    rightAnswer+=1;
                    textViewUserResult.setText("CORRECT");
                }else {
                    optionDCardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
                    textViewUserResult.setText("INCORRECT");
                    showRightAnswer();
                }
                buttonSeeExplanation.setVisibility(View.VISIBLE);
                nextQuestion.setVisibility(View.VISIBLE);
            }
        });

        buttonSeeExplanation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewUserExplanation.setVisibility(View.VISIBLE);
                textViewUserExplanation.setText(explanation);
            }
        });

        exitQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog();
            }
        });
    }

    private void showRightAnswer() {
        if (optionAValue) {
            optionACardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
        }
        if (optionBValue) {
            optionBCardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
        }
        if (optionCValue) {
            optionCCardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
        }
        if (optionDValue) {
            optionDCardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
        }
    }

    private void disableButtons() {
        optionACardView.setClickable(false);
        optionBCardView.setClickable(false);
        optionCCardView.setClickable(false);
        optionDCardView.setClickable(false);
    }

    private void showQuestion(int index) {
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("App")
                .child("Study")
                .child(professional)
                .child(subjectsArrayList.get(index))
                .child("MCQs")
                .child("Questions")
                .child(idsArrayList.get(index));

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
                Toast.makeText(NewMockTestActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void exitDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        onBackPressed();
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

    @Override
    public void onBackPressed(){
        exitDialog();
    }
}
