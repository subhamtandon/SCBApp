package com.company.ssDev.que9;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
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
    String uniqueId;
    String subjectName;
    DatabaseReference databaseReferenceRandom;
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
                intent.putExtra("SUBJECT", "BioChemistry");
                startActivity(intent);
            }
        });
        firstProfessionalMockTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                            if (ds.getKey().equals(uniqueId)) {
                                subjectName = ds.getValue(String.class);
                                Log.d("subjectName", subjectName);
                            }
                            Lists lists = new Lists(uniqueId, subjectName);
                            listsArrayList.add(lists);
                        }
                        Log.d("ListsList", listsArrayList + "");
                        Log.d("ListsSize", listsArrayList.size() + "");
                        Intent intent = new Intent(UserProfessionalFirstActivity1.this, NewMockTestActivity.class);
                        intent.putExtra("PROFESSIONAL", professional);
                        intent.putExtra("LISTSLIST", listsArrayList);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
