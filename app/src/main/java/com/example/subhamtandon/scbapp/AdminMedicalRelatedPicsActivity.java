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

public class AdminMedicalRelatedPicsActivity extends AppCompatActivity {

    FloatingActionButton addFloatingActionButton;
    RecyclerView recyclerViewAdminMedicalRelatedPics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_medical_related_pics);
        addFloatingActionButton = findViewById(R.id.addMedicalRelatedPic);
        recyclerViewAdminMedicalRelatedPics = findViewById(R.id.recyclerViewAdminMedicalRelatedPics);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Medical Related Pictures");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null) {


                    String url = dataSnapshot.getValue(String.class);
                    String picKey = dataSnapshot.getKey();

                    ((AdapterForAdminMedicalRelatedPictures) recyclerViewAdminMedicalRelatedPics.getAdapter()).update(url, picKey);

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

                Toast.makeText(AdminMedicalRelatedPicsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewAdminMedicalRelatedPics.setLayoutManager(new LinearLayoutManager(AdminMedicalRelatedPicsActivity.this));
        AdapterForAdminMedicalRelatedPictures adapterForAdminMedicalRelatedPictures = new AdapterForAdminMedicalRelatedPictures(recyclerViewAdminMedicalRelatedPics, AdminMedicalRelatedPicsActivity.this,new ArrayList<String>(), new ArrayList<String>());
        //adapterForRecordsList.notifyDataSetChanged();
        recyclerViewAdminMedicalRelatedPics.setAdapter(adapterForAdminMedicalRelatedPictures);

        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(AdminMedicalRelatedPicsActivity.this,AddingMedicalRelatedPicAdminActivity.class ));
            }
        });
    }
    public void reloadActivity(){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
