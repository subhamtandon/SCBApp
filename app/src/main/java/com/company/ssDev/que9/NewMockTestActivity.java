package com.company.ssDev.que9;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class NewMockTestActivity extends AppCompatActivity {

    LinearLayout timerLinearLayout;

    TextView questionCount, textViewUserQuestion, textViewUserOptionA, textViewUserOptionB, textViewUserOptionC, textViewUserOptionD, textViewUserExplanation, textViewUserResult, countDownText;

    CardView optionACardView, optionBCardView, optionCCardView, optionDCardView;
    Button buttonSeeExplanation, exitQuestions;
    FloatingActionButton nextQuestion;
    CountDownTimer countDownTimer;

    DatabaseReference databaseReference;
    String professional, explanation;
    int count = 0, rightAnswer = 0;
    ArrayAdapter<String> adapter;
    ArrayList<Lists> listsArrayList = new ArrayList<>();

    int n, found, flag = 0;
    long timeLeftInMilliSeconds;

    Boolean optionAValue, optionBValue, optionCValue, optionDValue, timerRunning;

    ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mock_test);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mock Test");
        }

        professional = getIntent().getStringExtra("PROFESSIONAL");
        listsArrayList = (ArrayList<Lists>)getIntent().getSerializableExtra("LISTSLIST");

        Toast.makeText(this, "original size" + listsArrayList.size() + "", Toast.LENGTH_SHORT).show();
        Log.d("originalListsList", listsArrayList + "");

        questionCount = findViewById(R.id.questionCount);

        timerLinearLayout = findViewById(R.id.timerLinearLayout);

        textViewUserQuestion = findViewById(R.id.textViewUserQuestion);
        textViewUserOptionA = findViewById(R.id.textViewUserOptionA);
        textViewUserOptionB = findViewById(R.id.textViewUserOptionB);
        textViewUserOptionC = findViewById(R.id.textViewUserOptionC);
        textViewUserOptionD = findViewById(R.id.textViewUserOptionD);
        textViewUserExplanation = findViewById(R.id.textViewUserExplanation);
        textViewUserResult = findViewById(R.id.textViewUserResult);
        countDownText = findViewById(R.id.countDownText);

        optionACardView = findViewById(R.id.optionACardView);
        optionBCardView = findViewById(R.id.optionBCardView);
        optionCCardView = findViewById(R.id.optionCCardView);
        optionDCardView = findViewById(R.id.optionDCardView);

        buttonSeeExplanation = findViewById(R.id.buttonSeeExplanation);
        exitQuestions = findViewById(R.id.exitQuestions);

        nextQuestion = findViewById(R.id.nextQuestion);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);

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
                    n= Integer.parseInt(mSpinner.getSelectedItem().toString());
                    Toast.makeText(NewMockTestActivity.this, "No of questions: " + n, Toast.LENGTH_SHORT).show();
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    dialog.cancel();
                    generateRandomShowQuestion();
            }
        });
        builder.setView(mView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        /*nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

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
        startTimer();
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
                    loadingProgressBar.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(NewMockTestActivity.this, "No questions", Toast.LENGTH_SHORT).show();
                    finish();
                    loadingProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(NewMockTestActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startTimer() {
        timerLinearLayout.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(45000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliSeconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                nextClicked(nextQuestion);
            }
        }.start();
        timerRunning = true;
    }

    private void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
    }

    private void updateTimer() {
        int seconds = (int) timeLeftInMilliSeconds/1000;
        String timeLeftText = "";
        if (seconds<10) timeLeftText += "0";
        timeLeftText += seconds;

        countDownText.setText(timeLeftText);
    }

    private void startStop() {
        if (timerRunning) {
            stopTimer();
        }else {
            startTimer();
        }
    }

    public void nextClicked(View view){
        loadingProgressBar.setVisibility(View.VISIBLE);
        startStop();
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
            questionCount.setText(count + 1 + ". ");
            showQuestion();
        } else {
            loadingProgressBar.setVisibility(View.GONE);
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
                            dialog.cancel();
                            finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public void exitDialog() {

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

    @Override
    public void onBackPressed() {
        exitDialog();
    }
}
