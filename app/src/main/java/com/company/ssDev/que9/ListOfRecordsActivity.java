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

public class ListOfRecordsActivity extends AppCompatActivity {

    FloatingActionButton addRecord;
    RecyclerView recyclerViewRecords;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_records);
        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");

        addRecord = findViewById(R.id.addRecord);
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ListOfRecordsActivity.this, RecordsAlteringActivity.class);
                next.putExtra("PROFESSIONAL",professional);
                next.putExtra("SUBJECT",subject);
                startActivity(next);
            }
        });

        recyclerViewRecords= findViewById(R.id.recyclerViewRecords);

        progressBar = findViewById(R.id.progressBarForRecordList);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("Records");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null) {

                    String recordName = dataSnapshot.child("mName").getValue(String.class);
                    String url = dataSnapshot.child("mURL").getValue(String.class);
                    String uploadPDFID = dataSnapshot.getKey();

                    ((AdapterForRecordsList) recyclerViewRecords.getAdapter()).update(recordName, url, uploadPDFID);

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

                Toast.makeText(ListOfRecordsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);

            }
        });

        recyclerViewRecords.setLayoutManager(new LinearLayoutManager(ListOfRecordsActivity.this));
        AdapterForRecordsList adapterForRecordsList = new AdapterForRecordsList(recyclerViewRecords, ListOfRecordsActivity.this,new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(), professional, subject);
        //adapterForRecordsList.notifyDataSetChanged();
        recyclerViewRecords.setAdapter(adapterForRecordsList);


    }

}
