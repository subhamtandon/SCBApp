package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BasicQuestionSetActivity extends AppCompatActivity {

    RecyclerView recyclerViewSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_question_set);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Sets");
        }

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String type = getIntent().getStringExtra("TYPE");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        //final String mode = getIntent().getStringExtra("MODE");

        Toast.makeText(BasicQuestionSetActivity.this, professional + ":" + subject + ":" + type + ":" + chapter, Toast.LENGTH_SHORT).show();

        recyclerViewSets = findViewById(R.id.recyclerViewSets);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child(type).child(chapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot != null){

                    String setName = dataSnapshot.getKey();

                    ((AdapterForBasicSets) recyclerViewSets.getAdapter()).update(setName);
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

            }
        });

        recyclerViewSets.setLayoutManager(new LinearLayoutManager(this));
        AdapterForBasicSets adapterForBasicSets = new AdapterForBasicSets(recyclerViewSets, BasicQuestionSetActivity.this, new ArrayList<String>(), professional, subject, type, chapter);
        recyclerViewSets.setAdapter(adapterForBasicSets);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
