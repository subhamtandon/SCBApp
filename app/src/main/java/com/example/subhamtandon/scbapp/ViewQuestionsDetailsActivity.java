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

import java.net.URI;

public class ViewQuestionsDetailsActivity extends AppCompatActivity {

    ImageView imageViewQuestion, imageViewOptionA, imageViewOptionB, imageViewOptionC, imageViewOptionD;
    TextView textViewQuestion, textViewOptionA, textViewOptionB, textViewOptionC, textViewOptionD, textViewExplanation;

    DatabaseReference databaseReference;
    private static int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions_details);

        imageViewQuestion = findViewById(R.id.imageViewQuestion);
        imageViewOptionA = findViewById(R.id.imageViewOptionA);
        imageViewOptionB = findViewById(R.id.imageViewOptionB);
        imageViewOptionC = findViewById(R.id.imageViewOptionC);
        imageViewOptionD = findViewById(R.id.imageViewOptionD);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewOptionA = findViewById(R.id.textViewOptionA);
        textViewOptionB = findViewById(R.id.textViewOptionB);
        textViewOptionC = findViewById(R.id.textViewOptionC);
        textViewOptionD = findViewById(R.id.textViewOptionD);
        textViewExplanation = findViewById(R.id.textViewExplanation);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String id = getIntent().getStringExtra("ID");
        Log.d("id",id);

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("App")
                .child("Study")
                .child(professional)
                .child(subject)
                .child("MCQs")
                .child("Questions")
                .child(id);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot != null){

                    String questionText = dataSnapshot.child("Question").child("questionText").getValue(String.class);
                    textViewQuestion.setText(questionText);

                    String questionImageUrl = dataSnapshot.child("Question").child("questionImageUrl").getValue(String.class);
                    try {
                        Log.d("question", questionImageUrl);
                    }
                    catch (Exception e){
                        Log.d("error", e.getMessage().toString());
                    }
                    if (questionImageUrl.equals("No Image Selected")){

                    } else{
                        Uri questionUri = Uri.parse(questionImageUrl);
                        imageViewQuestion.setVisibility(View.VISIBLE);
                        imageViewQuestion.setImageURI(questionUri);
                    }

                    String optionAText = dataSnapshot.child("Option A").child("optionAText").getValue(String.class);
                    textViewOptionA.setText(optionAText);

                    String optionAImageUrl = dataSnapshot.child("Option A").child("optionAImageUrl").getValue(String.class);
                    if (optionAImageUrl.equalsIgnoreCase("No Image Selected")){


                    }else {
                        Uri optionAUri = Uri.parse(optionAImageUrl);
                        imageViewOptionA.setVisibility(View.VISIBLE);
                        imageViewOptionA.setImageURI(optionAUri);
                    }

                    String optionAValue = dataSnapshot.child("Option A").child("optionAValue").getValue(String.class);

                    String optionBText = dataSnapshot.child("Option B").child("optionBText").getValue(String.class);
                    textViewOptionB.setText(optionBText);

                    String optionBImageUrl = dataSnapshot.child("Option B").child("optionBImageUrl").getValue(String.class);
                    if (optionBImageUrl.equalsIgnoreCase("No Image Selected")){

                    }else {
                        Uri optionBUri = Uri.parse(optionBImageUrl);
                        imageViewOptionB.setVisibility(View.VISIBLE);
                        imageViewOptionB.setImageURI(optionBUri);
                    }

                    String optionBValue = dataSnapshot.child("Option B").child("optionBValue").getValue(String.class);

                    String optionCText = dataSnapshot.child("Option C").child("optionCText").getValue(String.class);
                    textViewOptionC.setText(optionCText);

                    String optionCImageUrl = dataSnapshot.child("Option C").child("optionCImageUrl").getValue(String.class);
                    if (optionCImageUrl.equalsIgnoreCase("No Image Selected")){

                    }else {
                        Uri optionCUri = Uri.parse(optionCImageUrl);
                        imageViewOptionC.setVisibility(View.VISIBLE);
                        imageViewOptionA.setImageURI(optionCUri);
                    }

                    String optionCValue = dataSnapshot.child("Option C").child("optionCValue").getValue(String.class);

                    String optionDText = dataSnapshot.child("Option D").child("optionDText").getValue(String.class);
                    textViewOptionD.setText(optionDText);

                    String optionDImageUrl = dataSnapshot.child("Option D").child("optionDImageUrl").getValue(String.class);
                    if (optionDImageUrl.equalsIgnoreCase("No Image Selected")){

                    }else {
                        Uri optionDUri = Uri.parse(optionAImageUrl);
                        imageViewOptionD.setVisibility(View.VISIBLE);
                        imageViewOptionD.setImageURI(optionDUri);
                    }

                    String optionDValue = dataSnapshot.child("Option D").child("optionDValue").getValue(String.class);

                    String explanationText = dataSnapshot.child("Explanation").child("explanationText").getValue(String.class);
                    textViewExplanation.setText(explanationText);

                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ViewQuestionsDetailsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
