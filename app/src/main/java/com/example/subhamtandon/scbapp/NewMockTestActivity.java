package com.example.subhamtandon.scbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NewMockTestActivity extends AppCompatActivity {

    TextView questionCount, textViewUserQuestion, textViewUserOptionA, textViewUserOptionB, textViewUserOptionC, textViewUserOptionD, textViewUserExplanation, textViewUserResult;

    CardView optionACardView, optionBCardView, optionCCardView, optionDCardView;
    Button buttonSeeExplanation, exitQuestions;
    FloatingActionButton nextQuestion;

    DatabaseReference databaseReference, databaseReferenceRandom;
    String professional, explanation, randomElement, subjectName, randomElement1;
    int count = 0, rightAnswer = 0;
    ArrayAdapter<String> adapter;
    //ArrayList<String> idsArrayList = new ArrayList<>();
    //ArrayList<String> subjectsArrayList = new ArrayList<>();
    //ArrayList<Integer> questionShownList = new ArrayList<>();
    //ArrayList<String> newIdsArrayList = new ArrayList<>();
    //ArrayList<String> newSubjectsArrayList = new ArrayList<>();
    ArrayList<Lists> listsArrayList = new ArrayList<>();

    int n, found, index, index1;

    Boolean optionAValue, optionBValue, optionCValue, optionDValue;

    ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mock_test);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mock Test");
        }

        professional = getIntent().getStringExtra("PROFESSIONAL");
//        n = Integer.parseInt(getIntent().getStringExtra("NUMBER OF QUESTIONS"));
        //idsArrayList = getIntent().getStringArrayListExtra("IDSLIST");
        //subjectsArrayList = getIntent().getStringArrayListExtra("SUBJECTSLIST");
        listsArrayList = (ArrayList<Lists>)getIntent().getSerializableExtra("LISTSLIST");

        //Toast.makeText(this, idsArrayList.size() + ":" + subjectsArrayList.size(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "original size" + listsArrayList.size() + "", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, subjectsArrayList.size() + "", Toast.LENGTH_SHORT).show();
        //Log.d("newIdslist", idsArrayList + "");
        //Log.d("newSubjectslist", subjectsArrayList+"");
        Log.d("originalListsList", listsArrayList + "");

        questionCount = findViewById(R.id.questionCount);

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
        //loadingProgressBar = findViewById(R.id.loadingProgressBar);

        nextQuestion.setVisibility(View.GONE);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.activity_professionals_spinner, null);
        builder.setTitle("Choose number of Questions")
                .setCancelable(false);

        final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.numberOfQuestions));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("-Select-")) {
                    n= Integer.parseInt(mSpinner.getSelectedItem().toString());
                    Toast.makeText(NewMockTestActivity.this, "No of questions: " + n, Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    generateRandomShowQuestion();
                } else {
                    Toast.makeText(NewMockTestActivity.this, "Select number of Questions", Toast.LENGTH_SHORT).show();
                }
            }
        })/*.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        })*/;
        builder.setView(mView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        /*for (int i = 0; i < n; i++) {
            found = 0;
            while (true) {
                randomElement = (idsArrayList.get(new Random().nextInt(idsArrayList.size())));
                index = idsArrayList.indexOf(randomElement);
                subjectName = subjectsArrayList.get(index);
                for (int j = 0; j < questionShownList.size(); j++) {
                    if (index == questionShownList.get(j)) {
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
            questionShownList.add(index);
            newIdsArrayList.add(randomElement);
            newSubjectsArrayList.add(subjectName);
        }*/

        //Log.d("newIdsList", newIdsArrayList + "");
        //Log.d("newSubjectList", newSubjectsArrayList + "");

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
                count++;

                if (count < n) {
                    /*found = 0;
                    while (true) {
                        randomElement1 = (idsArrayList.get(new Random().nextInt(idsArrayList.size())));
                        index1 = idsArrayList.indexOf(randomElement);
                        for (int j = 0; j < questionShownList.size(); j++) {
                            if (index1 == questionShownList.get(j)) {
                                found = 1;
                                break;
                            }
                        }
                        if (found == 1) {
                            continue;
                        } else {
                            break;
                        }
                    }*/
                    //questionShownList.add(index1);
                    questionCount.setText(count + 1 + ". ");
                    showQuestion();
                } else {
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
                if (optionAValue) {
                    optionACardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
                    rightAnswer += 1;
                    textViewUserResult.setText("CORRECT");
                } else {
                    optionACardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
                    textViewUserResult.setText("INCORRECT");
                    showRightAnswer();
                }
                nextQuestion.setVisibility(View.VISIBLE);
                if(!explanation.equalsIgnoreCase("null")){
                    buttonSeeExplanation.setVisibility(View.VISIBLE);
                }
            }
        });

        optionBCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //optionACardView.setCardBackgroundColor(Color.parseColor("#ffcc00"));
                disableButtons();
                textViewUserResult.setVisibility(View.VISIBLE);
                if (optionBValue) {
                    optionBCardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
                    rightAnswer += 1;
                    textViewUserResult.setText("CORRECT");
                } else {
                    optionBCardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
                    textViewUserResult.setText("INCORRECT");
                    showRightAnswer();
                }
                nextQuestion.setVisibility(View.VISIBLE);
                if(!explanation.equalsIgnoreCase("null")){
                    buttonSeeExplanation.setVisibility(View.VISIBLE);
                }

            }
        });

        optionCCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //optionACardView.setCardBackgroundColor(Color.parseColor("#ffcc00"));
                disableButtons();
                textViewUserResult.setVisibility(View.VISIBLE);
                if (optionCValue) {
                    optionCCardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
                    rightAnswer += 1;
                    textViewUserResult.setText("CORRECT");
                } else {
                    optionCCardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
                    textViewUserResult.setText("INCORRECT");
                    showRightAnswer();
                }
                nextQuestion.setVisibility(View.VISIBLE);
                if(!explanation.equalsIgnoreCase("null")){
                    buttonSeeExplanation.setVisibility(View.VISIBLE);
                }

            }
        });

        optionDCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //optionACardView.setCardBackgroundColor(Color.parseColor("#ffcc00"));
                disableButtons();
                textViewUserResult.setVisibility(View.VISIBLE);
                if (optionDValue) {
                    optionDCardView.setCardBackgroundColor(Color.parseColor("#00cc00"));
                    rightAnswer += 1;
                    textViewUserResult.setText("CORRECT");
                } else {
                    optionDCardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
                    textViewUserResult.setText("INCORRECT");
                    showRightAnswer();
                }
                nextQuestion.setVisibility(View.VISIBLE);
                if(!explanation.equalsIgnoreCase("null")){
                    buttonSeeExplanation.setVisibility(View.VISIBLE);
                }
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


    private void generateRandomShowQuestion() {
        Collections.shuffle(listsArrayList);
        for (int i = listsArrayList.size() - 1; i >= n; --i)
            listsArrayList.remove(i);
        Toast.makeText(this, "newsize" + listsArrayList.size(), Toast.LENGTH_SHORT).show();
        Log.d("newListsList", listsArrayList + "");
        //randomElement = idsArrayList.get(new Random().nextInt(idsArrayList.size()));
        //index = idsArrayList.indexOf(randomElement);
        //questionShownList.add(index);
        showQuestion();
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

    private void showQuestion() {
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("App")
                .child("Study")
                .child(professional)
                .child(listsArrayList.get(count).getSubjectName())
                .child("MCQs")
                .child("Questions")
                .child(listsArrayList.get(count).getUniqueId());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
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

    public void exitDialog() {

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
    public void onBackPressed() {
        exitDialog();
    }
}
