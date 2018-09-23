package com.example.subhamtandon.scbapp;

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

public class AdminPPTActivity extends AppCompatActivity {

    RecyclerView listOfPPT;
    FloatingActionButton addPPT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ppt);

        addPPT = findViewById(R.id.addPPT);
        listOfPPT = findViewById(R.id.recyclerViewListOfPPTs);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("PPTs").child("Thumbnail");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null) {


                    String url = dataSnapshot.getValue(String.class);
                    //String uploadPDFID = dataSnapshot.getKey();

                    ((AdapterForListOfPPTsAdmin) listOfPPT.getAdapter()).update(url);

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

                Toast.makeText(AdminPPTActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        listOfPPT.setLayoutManager(new LinearLayoutManager(AdminPPTActivity.this));
        AdapterForListOfPPTsAdmin adapterForListOfPPTsAdmin = new AdapterForListOfPPTsAdmin(listOfPPT, AdminPPTActivity.this,new ArrayList<String>());
        //adapterForRecordsList.notifyDataSetChanged();
        listOfPPT.setAdapter(adapterForListOfPPTsAdmin);

        addPPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
