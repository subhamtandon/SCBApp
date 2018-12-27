package com.example.subhamtandon.scbapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class AdminQuestionDetailsActivity extends AppCompatActivity {

    TextView textViewQuestion, textViewOptionA, textViewOptionB, textViewOptionC, textViewOptionD, textViewExplanation;
    ImageView checkOptionA, checkOptionB, checkOptionC, checkOptionD;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_question_details);

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
        final String set = getIntent().getStringExtra("SET");
        final String id = getIntent().getStringExtra("ID");

        Toast.makeText(this, professional+subject+chapter+set+""+id, Toast.LENGTH_SHORT).show();

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("App")
                .child("Study")
                .child(professional)
                .child(subject)
                .child("MCQs")
                .child("Questions")
                .child(id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null) {
                    NewQuestion newQuestion = dataSnapshot.getValue(NewQuestion.class);
                    textViewQuestion.setText(newQuestion.getQuestion().getQuestionText());
                    textViewOptionA.setText(newQuestion.getOptionA().getOptionAText());
                    textViewOptionB.setText(newQuestion.getOptionB().getOptionBText());
                    textViewOptionC.setText(newQuestion.getOptionC().getOptionCText());
                    textViewOptionD.setText(newQuestion.getOptionD().getOptionDText());
                    textViewExplanation.setText(newQuestion.getExplanation().getExplanationText());
                    if (!newQuestion.getOptionA().getOptionAValue()) {
                        checkOptionA.setVisibility(View.INVISIBLE);
                    }
                    if (!newQuestion.getOptionB().getOptionBValue()) {
                        checkOptionB.setVisibility(View.INVISIBLE);
                    }
                    if (!newQuestion.getOptionC().getOptionCValue()) {
                        checkOptionC.setVisibility(View.INVISIBLE);
                    }
                    if (!newQuestion.getOptionD().getOptionDValue()) {
                        checkOptionD.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminQuestionDetailsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
