package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListOfQuestionsActivity extends AppCompatActivity {

    FloatingActionButton addQuestion;
    RecyclerView recyclerViewQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_questions);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");

        Toast.makeText(this, professional + " : " + subject + " : " + chapter + " : "+ mode + " : " + set, Toast.LENGTH_SHORT).show();

        addQuestion = (FloatingActionButton) findViewById(R.id.addQuestion);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(ListOfQuestionsActivity.this, AddingQuestionActivity.class);
                next.putExtra("PROFESSIONAL", professional);
                next.putExtra("SUBJECT", subject);
                next.putExtra("CHAPTER", chapter);
                next.putExtra("MODE",mode);
                next.putExtra("SET",set);
                startActivity(next);
            }
        });

        recyclerViewQuestions = (RecyclerView) findViewById(R.id.recyclerViewQuestions);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("MCQs").child(chapter).child(mode).child(set);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot != null){

                    String questionName = dataSnapshot.getValue(String.class);

                    ((AdapterForQuestionsList) recyclerViewQuestions.getAdapter()).update(questionName);
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

                Toast.makeText(ListOfQuestionsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(this));
        AdapterForQuestionsList adapterForQuestionsList = new AdapterForQuestionsList(recyclerViewQuestions, ListOfQuestionsActivity.this, new ArrayList<String>(), professional, subject, chapter, mode, set);
        recyclerViewQuestions.setAdapter(adapterForQuestionsList);
    }
}
