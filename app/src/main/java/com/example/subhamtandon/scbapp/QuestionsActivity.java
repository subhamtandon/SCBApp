package com.example.subhamtandon.scbapp;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
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

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    TextView textViewUserQuestion, textViewUserOptionA, textViewUserOptionB, textViewUserOptionC, textViewUserOptionD, textViewUserExplanation, pathChap, pathSet;
    ImageView imageViewUserQuestion, imageViewUserOptionA, imageViewUserOptionB, imageViewUserOptionC, imageViewUserOptionD, imageViewUserExplanation;

    DatabaseReference databaseReference, databaseReferenceQuestion, databaseReference1, databaseReference2, databaseReference3, databaseReference4, databaseReference5, databaseReference6;

    String questionText, questionImageUrl, optionAText, optionAImageUrl, optionAValue, optionBText, optionBImageUrl, optionBValue, optionCText, optionCImageUrl, optionCValue, optionDText, optionDValue, optionDImageUrl, explanationText, explanationImageUrl;
    Uri questionUri, optionAUri, optionBUri, optionCUri, optionDUri, explanationUri;

    //ArrayList<String> idsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String type = getIntent().getStringExtra("TYPE");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        //final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final ArrayList<String> idsArrayList = getIntent().getStringArrayListExtra("IDS");

        Toast.makeText(this, professional + ":" + subject + ":" + type + ":" + chapter + ":" + set, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < idsArrayList.size(); i++){
            Log.d("idsArrayList",idsArrayList.get(i));
        }

        textViewUserQuestion = findViewById(R.id.textViewUserQuestion);
        textViewUserOptionA = findViewById(R.id.textViewUserOptionA);
        textViewUserOptionB = findViewById(R.id.textViewUserOptionB);
        textViewUserOptionC = findViewById(R.id.textViewUserOptionC);
        textViewUserOptionD = findViewById(R.id.textViewUserOptionD);
        textViewUserExplanation = findViewById(R.id.textViewUserExplanation);
        pathChap = findViewById(R.id.pathChap);
        pathSet = findViewById(R.id.pathSet);

        imageViewUserQuestion = findViewById(R.id.imageViewUserQuestion);
        imageViewUserOptionA = findViewById(R.id.imageViewUserOptionA);
        imageViewUserOptionB = findViewById(R.id.imageViewUserOptionB);
        imageViewUserOptionC = findViewById(R.id.imageViewUserOptionC);
        imageViewUserOptionD = findViewById(R.id.imageViewUserOptionD);
        imageViewUserExplanation = findViewById(R.id.imageViewUserExplanation);

        pathChap.setText(chapter);
        pathSet.setText(set);

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("App")
                .child("Study")
                .child(professional)
                .child(subject)
                .child(type)
                .child("Questions")
                .child(idsArrayList.get(0));

        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    String questionId = ds.getKey();

                    if (ds.child(questionId).getKey().toString().equalsIgnoreCase("Question")){

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

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
                        textViewUserQuestion.setText(questionText);
                    }

                    if (dataSnapshot1.getKey().toString().equalsIgnoreCase("questionImageUrl")){

                        if (! dataSnapshot1.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            questionImageUrl = dataSnapshot1.getValue(String.class);
                            questionUri = Uri.parse(questionImageUrl);
                            imageViewUserQuestion.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(questionUri)
                                    .resize(720,720)
                                    .into(imageViewUserQuestion);
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
                        textViewUserOptionA.setText(optionAText);
                    }

                    if (dataSnapshot2.getKey().toString().equalsIgnoreCase("optionAImageUrl")){

                        if (! dataSnapshot2.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            optionAImageUrl = dataSnapshot2.getValue(String.class);
                            optionAUri = Uri.parse(optionAImageUrl);
                            imageViewUserOptionA.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(optionAUri)
                                    .resize(720,720)
                                    .into(imageViewUserOptionA);
                        }

                    }

                    /*if (dataSnapshot2.getKey().toString().equalsIgnoreCase("optionAValue")){

                        if (! dataSnapshot2.getValue(Boolean.class)){
                            checkOptionA.setVisibility(View.INVISIBLE);
                        }
                    }*/

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
                        textViewUserOptionB.setText(optionBText);
                    }

                    if (dataSnapshot3.getKey().toString().equalsIgnoreCase("optionBImageUrl")){

                        if (! dataSnapshot3.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            optionBImageUrl = dataSnapshot3.getValue(String.class);
                            optionBUri = Uri.parse(optionBImageUrl);
                            imageViewUserOptionB.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(optionBUri)
                                    .resize(720,720)
                                    .into(imageViewUserOptionB);
                        }

                    }

                    /*if (dataSnapshot3.getKey().toString().equalsIgnoreCase("optionBValue")){

                        if (! dataSnapshot3.getValue(Boolean.class)){
                            checkOptionB.setVisibility(View.INVISIBLE);
                        }
                    }*/

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
                        textViewUserOptionC.setText(optionCText);
                    }

                    if (dataSnapshot4.getKey().toString().equalsIgnoreCase("optionCImageUrl")){

                        if (! dataSnapshot4.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            optionCImageUrl = dataSnapshot4.getValue(String.class);
                            optionCUri = Uri.parse(optionCImageUrl);
                            imageViewUserOptionC.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(optionCUri)
                                    .resize(720,720)
                                    .into(imageViewUserOptionC);
                        }

                    }

                    /*if (dataSnapshot4.getKey().toString().equalsIgnoreCase("optionCValue")){

                        if (! dataSnapshot4.getValue(Boolean.class)){
                            checkOptionC.setVisibility(View.INVISIBLE);
                        }
                    }*/

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
                        textViewUserOptionD.setText(optionDText);
                    }

                    if (dataSnapshot5.getKey().toString().equalsIgnoreCase("optionDImageUrl")){

                        if (! dataSnapshot5.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            optionDImageUrl = dataSnapshot5.getValue(String.class);
                            optionDUri = Uri.parse(optionDImageUrl);
                            imageViewUserOptionD.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(optionDUri)
                                    .resize(720,720)
                                    .into(imageViewUserOptionD);
                        }

                    }

                    /*if (dataSnapshot5.getKey().toString().equalsIgnoreCase("optionDValue")){

                        if (! dataSnapshot5.getValue(Boolean.class)){
                            checkOptionD.setVisibility(View.INVISIBLE);
                        }
                    }*/

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
                        textViewUserExplanation.setText(explanationText);
                    }

                    if (dataSnapshot6.getKey().toString().equalsIgnoreCase("explanationImageUrl")){

                        if (! dataSnapshot6.getValue(String.class).equalsIgnoreCase("No Image Selected")){

                            explanationImageUrl = dataSnapshot6.getValue(String.class);
                            explanationUri = Uri.parse(explanationImageUrl);
                            imageViewUserExplanation.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(explanationUri)
                                    .resize(720,720)
                                    .into(imageViewUserExplanation);
                        }
                    }
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
