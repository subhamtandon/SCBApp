package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class UserProfessionalFirstActivity extends AppCompatActivity {

    FloatingActionButton changeProfession;

    CardView AnatomyCard, PhysiologyCard, BiochemistryCard, MockTestCard;

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
        MockTestCard=(CardView) findViewById(R.id.MockTestCard);

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
        MockTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                //startActivity(new Intent(getApplicationContext(),Professional4thActivity.class));
                //Toast.makeText(getActivity(), "Will be available soon", Toast.LENGTH_SHORT).show();
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
