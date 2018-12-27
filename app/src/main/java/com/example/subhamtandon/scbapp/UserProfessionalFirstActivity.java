package com.example.subhamtandon.scbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserProfessionalFirstActivity extends AppCompatActivity {

    FloatingActionButton changeProfession;

    CardView AnatomyCard, PhysiologyCard, BiochemistryCard, firstProfessionalMockTestCard;
    ArrayAdapter<String> adapter;
    String[] subjects;
    DatabaseReference databaseReferenceRandom;
    ArrayList<String> idsArrayList = new ArrayList<>();
    ArrayList<String> subjectsArrayList = new ArrayList<>();
    AlertDialog alertDialog;
    //ProgressBar loaderProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_professional_first);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("First Professional");
        }

        //loaderProgressBar = findViewById(R.id.loaderProgressBar);

        subjects = getResources().getStringArray(R.array.firstProfessionalSubjects);
        Log.d("subjects_length", subjects.length + "");

        changeProfession = (FloatingActionButton)findViewById(R.id.changeProfession);
        changeProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfessionalFirstActivity.this);

                View mView = getLayoutInflater().inflate(R.layout.activity_professionals_spinner, null);

                builder.setTitle("Choose your Professional");

                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserProfessionalFirstActivity.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.from1stProfessional));

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                mSpinner.setAdapter(adapter);

                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final Intent intent;
                        switch (position){
                            case 1:
                                intent = new Intent(UserProfessionalFirstActivity.this, UserProfessionalSecondActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(UserProfessionalFirstActivity.this, UserProfessionalThirdOneActivity.class);
                                startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(UserProfessionalFirstActivity.this, UserProfessionalThirdTwoActivity.class);
                                startActivity(intent);
                                break;
                            default:
                                Toast.makeText(UserProfessionalFirstActivity.this, "Choose Your Professional", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                        Toast.makeText(UserProfessionalFirstActivity.this, "Choose Your Professional", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setView(mView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        AnatomyCard =(CardView) findViewById(R.id.AnatomyCard);
        PhysiologyCard=(CardView) findViewById(R.id.PhysiologyCard);
        BiochemistryCard=(CardView) findViewById(R.id.BiochemistryCard);
        firstProfessionalMockTestCard=(CardView) findViewById(R.id.firstProfessionalMockTestCard);

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
//                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfessionalFirstActivity.this);
//                View mView = getLayoutInflater().inflate(R.layout.activity_professionals_spinner, null);
//                builder.setTitle("Choose number of Questions")
//                        .setCancelable(false);
//
//                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);
//
//                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.numberOfQuestions));
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                mSpinner.setAdapter(adapter);
//                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("-Select-")) {
//                            dialog.cancel();
//                            loaderProgressBar.setVisibility(View.VISIBLE);
//                            databaseReferenceRandom = FirebaseDatabase.getInstance().getReference()
//                                    .child("App")
//                                    .child("Study")
//                                    .child("Random")
//                                    .child(professional)
//                                    .child("Questions");
//
//                            databaseReferenceRandom.addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                        String uniqueId = ds.getKey();
//                                        Log.d("uniqueId", uniqueId);
//                                        idsArrayList.add(uniqueId);
//
//                                        if (ds.getKey().toString().equalsIgnoreCase(uniqueId)) {
//                                            String subjectName = ds.getValue(String.class);
//                                            Log.d("subjectName", subjectName);
//                                            subjectsArrayList.add(subjectName);
//                                        }
//                                    }
//                                    Log.d("IdsList", idsArrayList + "");
//                                    Log.d("SubjectList", subjectsArrayList + "");
//                                    if (idsArrayList.size()>=Integer.parseInt(mSpinner.getSelectedItem().toString())){
//                                        loaderProgressBar.setVisibility(View.GONE);
//                                        Intent intent = new Intent(UserProfessionalFirstActivity.this, NewMockTestActivity.class);
//                                        intent.putExtra("PROFESSIONAL", professional);
//                                        intent.putExtra("NUMBER OF QUESTIONS", mSpinner.getSelectedItem().toString());
//                                        intent.putStringArrayListExtra("IDSLIST", idsArrayList);
//                                        intent.putStringArrayListExtra("SUBJECTSLIST", subjectsArrayList);
//                                        Toast.makeText(UserProfessionalFirstActivity.this, "passed", Toast.LENGTH_SHORT).show();
//                                        startActivity(intent);
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                }
//                            });
//                        } else {
//                            Toast.makeText(UserProfessionalFirstActivity.this, "Select number of Questions", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                builder.setView(mView);
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();

                /*readData(new FirebaseCallback() {
                    @Override
                    public void onCallBack(final ArrayList<String> idsList, final ArrayList<String> subjectsList) {
                        Log.d("idsArrayList", idsList.toString());
                        Log.d("idssize", idsList.size() + "");
                        Log.d("subjectsArrayList", subjectsList.toString());
                        Log.d("subjectssize", subjectsList.size() + "");
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfessionalFirstActivity.this);
                        View mView = getLayoutInflater().inflate(R.layout.activity_professionals_spinner, null);
                        builder.setTitle("Choose number of Questions")
                                .setCancelable(false);

                        final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);

                        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.numberOfQuestions));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        mSpinner.setAdapter(adapter);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("-Select-")) {
                                    //Intent intent = new Intent(UserProfessionalFirstActivity.this, MockTestActivity.class);
                                    //intent.putExtra("PROFESSIONAL", professional);
                                    //intent.putExtra("NUMBER OF QUESTIONS", mSpinner.getSelectedItem().toString());
                                    //startActivity(intent);
                                    //finish();
                                    Intent intent = new Intent(UserProfessionalFirstActivity.this, NewMockTestActivity.class);
                                    intent.putExtra("PROFESSIONAL", professional);
                                    intent.putExtra("NUMBER OF QUESTIONS", mSpinner.getSelectedItem().toString());
                                    intent.putStringArrayListExtra("IDSLIST", idsList);
                                    intent.putStringArrayListExtra("SUBJECTSLIST", subjectsList);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(UserProfessionalFirstActivity.this, "Select number of Questions", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.setView(mView);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });*/
                databaseReferenceRandom = FirebaseDatabase.getInstance().getReference()
                        .child("App")
                        .child("Study")
                        .child("Random")
                        .child(professional)
                        .child("Questions");

                databaseReferenceRandom.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String uniqueId = ds.getKey();
                            Log.d("uniqueId", uniqueId);
                            idsArrayList.add(uniqueId);

                            if (ds.getKey().toString().equalsIgnoreCase(uniqueId)) {
                                String subjectName = ds.getValue(String.class);
                                Log.d("subjectName", subjectName);
                                subjectsArrayList.add(subjectName);
                            }
                        }
                        Log.d("IdsList", idsArrayList + "");
                        Log.d("SubjectList", subjectsArrayList + "");
//                        if (idsArrayList.size()>=Integer.parseInt(mSpinner.getSelectedItem().toString())){
//                            loaderProgressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(UserProfessionalFirstActivity.this, NewMockTestActivity.class);
                            intent.putExtra("PROFESSIONAL", professional);
//                            intent.putExtra("NUMBER OF QUESTIONS", mSpinner.getSelectedItem().toString());
                            intent.putStringArrayListExtra("IDSLIST", idsArrayList);
                            intent.putStringArrayListExtra("SUBJECTSLIST", subjectsArrayList);
                            Toast.makeText(UserProfessionalFirstActivity.this, "passed", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
//                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    /*private void readData(final FirebaseCallback firebaseCallback){
        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        for (int i = 0; i < subjects.length; i++) {

            databaseReferenceRandom = FirebaseDatabase.getInstance().getReference()
                    .child("App")
                    .child("Study")
                    .child("Random")
                    .child(professional)
                    .child(subjects[i])
                    .child("Questions");

            databaseReferenceRandom.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String uniqueId = ds.getKey();
                        Log.d("uniqueId", uniqueId);
                        idsArrayList.add(uniqueId);

                        if (ds.getKey().toString().equalsIgnoreCase(uniqueId)) {
                            String subjectName = ds.getValue(String.class);
                            Log.d("subjectName", subjectName);
                            subjectsArrayList.add(subjectName);

                            //Log.d("subjectsArrayListSize", subjectsArrayList.size() + "");
                        }

                        //Log.d("idsArrayListSize", idsArrayList.size() + "");
                    }
                    //firebaseCallback.onCallBack(idsArrayList, subjectsArrayList);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //for (int j = 0; j < subjectsArrayList.size(); j++) {
            //   Log.d("subjectsArrayList", subjectsArrayList.get(j));
            //}

            //for (int k = 0; k < idsArrayList.size(); k++) {
            //    Log.d("idsArrayList", idsArrayList.get(k));
            //}
        }
        firebaseCallback.onCallBack(idsArrayList, subjectsArrayList);
    }*/


    /*private interface FirebaseCallback{
        void onCallBack(ArrayList<String> list, ArrayList<String> list1);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
