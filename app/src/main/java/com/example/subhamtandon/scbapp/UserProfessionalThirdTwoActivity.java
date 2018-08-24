package com.example.subhamtandon.scbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class UserProfessionalThirdTwoActivity extends AppCompatActivity {

    CardView medicineCard, surgeryCard, pediatricsCard, orthopedicsCard, skinVDCard, anaesthesiologyCard, radiologyCard, ogCard, thirdProfessionalPartTwoMockTestCard;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_professional_third_two);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Third Professional Part 2");
        }

        medicineCard = findViewById(R.id.medicineCard);
        surgeryCard = findViewById(R.id.surgeryCard);
        pediatricsCard = findViewById(R.id.pediatricsCard);
        orthopedicsCard = findViewById(R.id.orthopedicsCard);
        skinVDCard = findViewById(R.id.skinVDCard);
        anaesthesiologyCard = findViewById(R.id.anaesthesiologyCard);
        radiologyCard = findViewById(R.id.radiologyCard);
        ogCard = findViewById(R.id.ogCard);
        thirdProfessionalPartTwoMockTestCard = findViewById(R.id.thirdProfessionalPartTwoMockTestCard);

        medicineCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Medicine");
                startActivity(intent);
            }
        });

        surgeryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Surgery");
                startActivity(intent);
            }
        });

        pediatricsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Pediatrics");
                startActivity(intent);
            }
        });

        orthopedicsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Orthopedics");
                startActivity(intent);
            }
        });

        skinVDCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Skin & VD");
                startActivity(intent);
            }
        });

        anaesthesiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Anaesthesiology");
                startActivity(intent);
            }
        });

        radiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Radiology");
                startActivity(intent);
            }
        });

        ogCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "O & G");
                startActivity(intent);
            }
        });

        thirdProfessionalPartTwoMockTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfessionalThirdTwoActivity.this);
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
                            Intent intent = new Intent(UserProfessionalThirdTwoActivity.this, MockTestActivity.class);
                            intent.putExtra("PROFESSIONAL", professional);
                            intent.putExtra("NUMBER OF QUESTIONS", mSpinner.getSelectedItem().toString());
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(UserProfessionalThirdTwoActivity.this, "Select number of Questions", Toast.LENGTH_SHORT).show();
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
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
