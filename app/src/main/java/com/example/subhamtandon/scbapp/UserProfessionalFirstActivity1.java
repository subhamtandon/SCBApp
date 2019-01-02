package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserProfessionalFirstActivity1 extends AppCompatActivity {

    CardView AnatomyCard, PhysiologyCard, BiochemistryCard, firstProfessionalMockTestCard;
    String[] subjects;
    String uniqueId;
    String subjectName;
    DatabaseReference databaseReferenceRandom;
    ArrayList<String> idsArrayList = new ArrayList<>();
    ArrayList<String> subjectsArrayList = new ArrayList<>();
    ArrayList<Lists> listsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_professional_first1);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("First Professional");
        }

        subjects = getResources().getStringArray(R.array.firstProfessionalSubjects);
        Log.d("subjects_length", subjects.length + "");

        AnatomyCard = findViewById(R.id.AnatomyCard);
        PhysiologyCard = findViewById(R.id.PhysiologyCard);
        BiochemistryCard = findViewById(R.id.BiochemistryCard);
        firstProfessionalMockTestCard = findViewById(R.id.firstProfessionalMockTestCard);

        AnatomyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Anatomy");
                startActivity(intent);
            }
        });
        PhysiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Physiology");
                startActivity(intent);
            }
        });
        BiochemistryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Biochemistry");
                startActivity(intent);
            }
        });
        firstProfessionalMockTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(UserProfessionalFirstActivity1.this, "clicked", Toast.LENGTH_SHORT).show();
                databaseReferenceRandom = FirebaseDatabase.getInstance().getReference()
                        .child("App")
                        .child("Study")
                        .child("Random")
                        .child(professional)
                        .child("Questions");

                databaseReferenceRandom.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            uniqueId = ds.getKey();
                            Log.d("uniqueId", uniqueId);
                            //idsArrayList.add(uniqueId);

                            if (ds.getKey().equals(uniqueId)) {
                                subjectName = ds.getValue(String.class);
                                Log.d("subjectName", subjectName);
                                //subjectsArrayList.add(subjectName);
                            }
                            Lists lists = new Lists(uniqueId, subjectName);
                            listsArrayList.add(lists);
                        }
                        //Log.d("IdsList", idsArrayList + "");
                        //Log.d("SubjectList", subjectsArrayList + "");
                        Log.d("ListsList", listsArrayList + "");
                        //Log.d("Listssize", idsArrayList.size() + ":" + subjectsArrayList.size());
                        Log.d("ListsSize", listsArrayList.size() + "");
                        Intent intent = new Intent(UserProfessionalFirstActivity1.this, NewMockTestActivity.class);
                        intent.putExtra("PROFESSIONAL", professional);
                        intent.putExtra("LISTSLIST", listsArrayList);
                        //intent.putStringArrayListExtra("IDSLIST", idsArrayList);
                        //intent.putStringArrayListExtra("SUBJECTSLIST", subjectsArrayList);
                        //Toast.makeText(UserProfessionalFirstActivity1.this, "passed", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(UserProfessionalFirstActivity1.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
