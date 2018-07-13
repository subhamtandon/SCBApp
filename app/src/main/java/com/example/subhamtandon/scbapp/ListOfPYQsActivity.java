package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListOfPYQsActivity extends AppCompatActivity {

    FloatingActionButton addPYQ;
    RecyclerView recyclerViewPYQs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_pyqs);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");

        addPYQ = findViewById(R.id.addPYQ);
        addPYQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ListOfPYQsActivity.this, PYQsAlteringActivity.class);
                next.putExtra("PROFESSIONAL",professional);
                next.putExtra("SUBJECT",subject);
                startActivity(next);
            }
        });

        recyclerViewPYQs= findViewById(R.id.recyclerViewPYQs);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("PYQs");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null) {

                    String PYQName = dataSnapshot.getKey();
                    String url = dataSnapshot.getValue(String.class);

                    ((AdapterForPYQsList) recyclerViewPYQs.getAdapter()).update(PYQName, url);
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

        recyclerViewPYQs.setLayoutManager(new LinearLayoutManager(ListOfPYQsActivity.this));
        AdapterForPYQsList adapterForPYQsList = new AdapterForPYQsList(recyclerViewPYQs, ListOfPYQsActivity.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerViewPYQs.setAdapter(adapterForPYQsList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(ListOfPYQsActivity.this, AdminStudyActivity.class);
        startActivity(back);
    }
}
