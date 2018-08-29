package com.example.subhamtandon.scbapp;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MockTestActivity extends AppCompatActivity {

    TextView textViewUserQuestion, textViewUserOptionA, textViewUserOptionB, textViewUserOptionC, textViewUserOptionD, textViewUserExplanation;
    ImageView imageViewUserQuestion, imageViewUserOptionA, imageViewUserOptionB, imageViewUserOptionC, imageViewUserOptionD, imageViewUserExplanation;

    DatabaseReference databaseReference, databaseReference1, databaseReference2, databaseReference3, databaseReference4, databaseReference5, databaseReference6;

    String questionText, questionImageUrl, optionAText, optionAImageUrl, optionBText, optionBImageUrl, optionCText, optionCImageUrl, optionDText, optionDImageUrl, explanationText, explanationImageUrl;
    Uri questionUri, optionAUri, optionBUri, optionCUri, optionDUri, explanationUri;
    Boolean optionAValue , optionBValue, optionCValue, optionDValue, optionAChoosed = false, optionBChoosed = false, optionCChoosed = false, optionDChoosed = false;

    CardView optionACardView, optionBCardView, optionCCardView, optionDCardView;
    Button buttonSubmitAnswer;

    FloatingActionButton nextQuestion;
    String professional, subject, type, chapter, set;
    String[] subjects;

    ArrayList<String> idsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_test);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Mock Test");
        }

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String numberOfQuestions = getIntent().getStringExtra("NUMBER OF QUESTIONS");

        Toast.makeText(this, professional + ":" + numberOfQuestions, Toast.LENGTH_SHORT).show();

        textViewUserQuestion = findViewById(R.id.textViewUserQuestion);
        textViewUserOptionA = findViewById(R.id.textViewUserOptionA);
        textViewUserOptionB = findViewById(R.id.textViewUserOptionB);
        textViewUserOptionC = findViewById(R.id.textViewUserOptionC);
        textViewUserOptionD = findViewById(R.id.textViewUserOptionD);
        textViewUserExplanation = findViewById(R.id.textViewUserExplanation);

        imageViewUserQuestion = findViewById(R.id.imageViewUserQuestion);
        imageViewUserOptionA = findViewById(R.id.imageViewUserOptionA);
        imageViewUserOptionB = findViewById(R.id.imageViewUserOptionB);
        imageViewUserOptionC = findViewById(R.id.imageViewUserOptionC);
        imageViewUserOptionD = findViewById(R.id.imageViewUserOptionD);
        imageViewUserExplanation = findViewById(R.id.imageViewUserExplanation);

        optionACardView = findViewById(R.id.optionACardView);
        optionBCardView = findViewById(R.id.optionBCardView);
        optionCCardView = findViewById(R.id.optionCCardView);
        optionDCardView = findViewById(R.id.optionDCardView);

        buttonSubmitAnswer = findViewById(R.id.buttonSubmitAnswer);

        nextQuestion = findViewById(R.id.nextQuestion);

        buttonSubmitAnswer.setEnabled(false);

        if (professional.equalsIgnoreCase("First Professional")){
            subjects = getResources().getStringArray(R.array.firstProfessionalSubjects);
        }

        if (professional.equalsIgnoreCase("Second Professional")){
            subjects = getResources().getStringArray(R.array.secondProfessionalSubjects);
        }

        if (professional.equalsIgnoreCase("Third Professional Part-1")){
            subjects = getResources().getStringArray(R.array.thirdProfessionalPartOneSubjects);
        }

        if (professional.equalsIgnoreCase("Third Professional Part-2")){
            subjects = getResources().getStringArray(R.array.thirdProfessionalPartTwoSubjects);
        }

        subject = (subjects[new Random().nextInt(subjects.length)]);
        Toast.makeText(this, subject, Toast.LENGTH_SHORT).show();

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("App")
                .child("Study")
                .child(professional)
                .child(subject)
                .child("MCQs")
                .child("Questions");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String uniqueId = ds.getKey();
                        Log.d("uniqueId", uniqueId);
                        idsArrayList.add(uniqueId);
                    }

                    for (int i = 0; i < idsArrayList.size(); i++){
                        Log.d("idsArrayList",idsArrayList.get(i));
                    }

                    Log.d("idsArrayList size",idsArrayList.size()+"");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void exitQuestions(View view){

        exitDialog();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
