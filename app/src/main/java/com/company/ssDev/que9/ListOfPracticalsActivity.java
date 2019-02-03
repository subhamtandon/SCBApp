package com.company.ssDev.que9;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListOfPracticalsActivity extends AppCompatActivity {

    FloatingActionButton addPractical;
    RecyclerView recyclerViewPracticals;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_practicals);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");

        addPractical = findViewById(R.id.addPractical);
        addPractical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ListOfPracticalsActivity.this, PracticalAlteringActivity.class);
                next.putExtra("PROFESSIONAL",professional);
                next.putExtra("SUBJECT",subject);
                startActivity(next);
            }
        });

        recyclerViewPracticals= findViewById(R.id.recyclerViewPracticals);

        progressBar = findViewById(R.id.progressBarForPracticalList);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("Practicals");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null) {

                    String practicalName = dataSnapshot.child("mName").getValue(String.class);
                    String url = dataSnapshot.child("mURL").getValue(String.class);
                    String uploadPDFID = dataSnapshot.getKey();

                    ((AdapterForPracticalsList) recyclerViewPracticals.getAdapter()).update(practicalName, url, uploadPDFID);


                }
                progressBar.setVisibility(View.INVISIBLE);
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

                Toast.makeText(ListOfPracticalsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);

            }
        });

        recyclerViewPracticals.setLayoutManager(new LinearLayoutManager(ListOfPracticalsActivity.this));
        AdapterForPracticalsList adapterForPracticalsList = new AdapterForPracticalsList(recyclerViewPracticals, ListOfPracticalsActivity.this,new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(), professional, subject);
        recyclerViewPracticals.setAdapter(adapterForPracticalsList);
    }

}
