package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListOfSetsActivity extends AppCompatActivity {

    FloatingActionButton addSet;
    RecyclerView recyclerViewSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_sets);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");

        Toast.makeText(this, professional + " : " + subject + " : " + chapter + " : "+ mode, Toast.LENGTH_SHORT).show();

        addSet = (FloatingActionButton) findViewById(R.id.addSet);
        addSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("floating button","clicked");

                Intent next = new Intent(ListOfSetsActivity.this, SetsAlteringActivity.class);
                next.putExtra("PROFESSIONAL", professional);
                next.putExtra("SUBJECT", subject);
                next.putExtra("CHAPTER", chapter);
                next.putExtra("MODE",mode);
                startActivity(next);

            }
        });

        recyclerViewSets = findViewById(R.id.recyclerViewSets);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("MCQs").child(chapter).child(mode);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot != null){

                    String setName = dataSnapshot.getKey();

                    ((AdapterForSetsList) recyclerViewSets.getAdapter()).update(setName);
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

                Toast.makeText(ListOfSetsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        recyclerViewSets.setLayoutManager(new LinearLayoutManager(this));
        AdapterForSetsList adapterForSetsList = new AdapterForSetsList(recyclerViewSets, ListOfSetsActivity.this, new ArrayList<String>(), professional, subject, chapter, mode);
        recyclerViewSets.setAdapter(adapterForSetsList);

    }
}
