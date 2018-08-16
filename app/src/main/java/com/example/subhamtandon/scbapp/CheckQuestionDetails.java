package com.example.subhamtandon.scbapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CheckQuestionDetails extends AppCompatActivity {

    ImageView imageViewQuestion, imageViewOptionA, imageViewOptionB, imageViewOptionC, imageViewOptionD, imageViewExplanation, checkOptionA, checkOptionB, checkOptionC, checkOptionD;
    TextView textViewQuestion, textViewOptionA, textViewOptionB, textViewOptionC, textViewOptionD, textViewExplanation;

    DatabaseReference databaseReference, databaseReference1, databaseReference2, databaseReference3, databaseReference4, databaseReference5, databaseReference6;

    String questionText, questionImageUrl, optionAText, optionAImageUrl, optionAValue, optionBText, optionBImageUrl, optionBValue, optionCText, optionCImageUrl, optionCValue, optionDText, optionDValue, optionDImageUrl, explanationText, explanationImageUrl;
    Uri questionUri, optionAUri, optionBUri, optionCUri, optionDUri, explanationUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_question_details);

        imageViewQuestion = findViewById(R.id.imageViewQuestion);
        imageViewOptionA = findViewById(R.id.imageViewOptionA);
        imageViewOptionB = findViewById(R.id.imageViewOptionB);
        imageViewOptionC = findViewById(R.id.imageViewOptionC);
        imageViewOptionD = findViewById(R.id.imageViewOptionD);
        imageViewExplanation = findViewById(R.id.imageViewExplanation);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewOptionA = findViewById(R.id.textViewOptionA);
        textViewOptionB = findViewById(R.id.textViewOptionB);
        textViewOptionC = findViewById(R.id.textViewOptionC);
        textViewOptionD = findViewById(R.id.textViewOptionD);
        textViewExplanation = findViewById(R.id.textViewExplanation);

        checkOptionA = findViewById(R.id.checkOptionA);
        checkOptionB = findViewById(R.id.checkOptionB);
        checkOptionC = findViewById(R.id.checkOptionC);
        checkOptionD = findViewById(R.id.checkOptionD);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        //final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String id = getIntent().getStringExtra("ID");

        Log.d("TAG", professional);
        Log.d("TAG", subject);
        Log.d("TAG", chapter);
        //Log.d("TAG", mode);
        Log.d("TAG", set);
        Log.d("TAG", id);

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("App")
                .child("Study")
                .child(professional)
                .child(subject)
                .child("MCQs")
                .child("Questions")
                .child(id);

        databaseReference1 = databaseReference.child("Question");
        Log.e("Dta Reference",databaseReference+"");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Log.e("tostring",dataSnapshot1.getValue().toString());
                    Log.e("tostringkey",dataSnapshot1.getKey().toString());

                    if (dataSnapshot1.getKey().toString().equalsIgnoreCase("questionText")){

                        questionText = dataSnapshot1.getValue(String.class);
                        Log.e("tostringkey",questionText);
                        textViewQuestion.setText(questionText);
                    }

                    if (dataSnapshot1.getKey().toString().equalsIgnoreCase("questionImageUrl")){

                        if (! dataSnapshot1.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            questionImageUrl = dataSnapshot1.getValue(String.class);
                            questionUri = Uri.parse(questionImageUrl);
                            imageViewQuestion.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(questionUri)
                                    .resize(720,720)
                                    .into(imageViewQuestion);
                        }else {
                            Toast.makeText(CheckQuestionDetails.this, "image not present", Toast.LENGTH_SHORT).show();
                        }
                    }


                    Log.e("questiontext",questionText+"");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference2 = databaseReference.child("Option A");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){

                    if (dataSnapshot2.getKey().toString().equalsIgnoreCase("optionAText")){

                        optionAText = dataSnapshot2.getValue(String.class);
                        Log.e("tostringkey",optionAText);
                        textViewOptionA.setText(optionAText);
                    }

                    if (dataSnapshot2.getKey().toString().equalsIgnoreCase("optionAImageUrl")){

                        if (! dataSnapshot2.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            optionAImageUrl = dataSnapshot2.getValue(String.class);
                            optionAUri = Uri.parse(optionAImageUrl);
                            imageViewOptionA.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(optionAUri)
                                    .resize(720,720)
                                    .into(imageViewOptionA);
                        }

                    }

                    if (dataSnapshot2.getKey().toString().equalsIgnoreCase("optionAValue")){

                        if (! dataSnapshot2.getValue(Boolean.class)){
                            checkOptionA.setVisibility(View.INVISIBLE);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference3 = databaseReference.child("Option B");
        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot3:dataSnapshot.getChildren()){

                    if (dataSnapshot3.getKey().toString().equalsIgnoreCase("optionBText")){

                        optionBText = dataSnapshot3.getValue(String.class);
                        Log.e("tostringkey",optionBText);
                        textViewOptionB.setText(optionBText);
                    }

                    if (dataSnapshot3.getKey().toString().equalsIgnoreCase("optionBImageUrl")){

                        if (! dataSnapshot3.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            optionBImageUrl = dataSnapshot3.getValue(String.class);
                            optionBUri = Uri.parse(optionBImageUrl);
                            imageViewOptionB.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(optionBUri)
                                    .resize(720,720)
                                    .into(imageViewOptionB);
                        }

                    }

                    if (dataSnapshot3.getKey().toString().equalsIgnoreCase("optionBValue")){

                        if (! dataSnapshot3.getValue(Boolean.class)){
                            checkOptionB.setVisibility(View.INVISIBLE);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference4 = databaseReference.child("Option C");
        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot4:dataSnapshot.getChildren()){

                    if (dataSnapshot4.getKey().toString().equalsIgnoreCase("optionCText")){

                        optionCText = dataSnapshot4.getValue(String.class);
                        Log.e("tostringkey",optionCText);
                        textViewOptionC.setText(optionCText);
                    }

                    if (dataSnapshot4.getKey().toString().equalsIgnoreCase("optionCImageUrl")){

                        if (! dataSnapshot4.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            optionCImageUrl = dataSnapshot4.getValue(String.class);
                            optionCUri = Uri.parse(optionCImageUrl);
                            imageViewOptionC.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(optionCUri)
                                    .resize(720,720)
                                    .into(imageViewOptionC);
                        }

                    }

                    if (dataSnapshot4.getKey().toString().equalsIgnoreCase("optionCValue")){

                        if (! dataSnapshot4.getValue(Boolean.class)){
                            checkOptionC.setVisibility(View.INVISIBLE);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference5 = databaseReference.child("Option D");
        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot5:dataSnapshot.getChildren()){

                    if (dataSnapshot5.getKey().toString().equalsIgnoreCase("optionDText")){

                        optionDText = dataSnapshot5.getValue(String.class);
                        Log.e("toStringKey",optionDText);
                        textViewOptionD.setText(optionDText);
                    }

                    if (dataSnapshot5.getKey().toString().equalsIgnoreCase("optionDImageUrl")){

                        if (! dataSnapshot5.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            optionDImageUrl = dataSnapshot5.getValue(String.class);
                            optionDUri = Uri.parse(optionDImageUrl);
                            imageViewOptionD.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(optionDUri)
                                    .resize(720,720)
                                    .into(imageViewOptionD);
                        }

                    }

                    if (dataSnapshot5.getKey().toString().equalsIgnoreCase("optionDValue")){

                        if (! dataSnapshot5.getValue(Boolean.class)){
                            checkOptionD.setVisibility(View.INVISIBLE);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference6 = databaseReference.child("Explanation");
        databaseReference6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot6:dataSnapshot.getChildren())
                {
                    if (dataSnapshot6.getKey().toString().equalsIgnoreCase("explanationText")){

                        explanationText = dataSnapshot6.getValue(String.class);
                        Log.e("tostringkey",explanationText);
                        textViewExplanation.setText(explanationText);
                    }

                    if (dataSnapshot6.getKey().toString().equalsIgnoreCase("explanationImageUrl")){

                        if (! dataSnapshot6.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            explanationImageUrl = dataSnapshot6.getValue(String.class);
                            explanationUri = Uri.parse(explanationImageUrl);
                            imageViewExplanation.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(explanationUri)
                                    .resize(720,720)
                                    .into(imageViewExplanation);
                        }else {
                            Toast.makeText(CheckQuestionDetails.this, "image not present", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
