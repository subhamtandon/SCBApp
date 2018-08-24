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

public class UserProfessionalSecondActivity extends AppCompatActivity {

    CardView pathologyCard, pharmacologyCard, microbiologyCard, fmtCard, secondProfessionalMockTestCard;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_professional_second);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Second Professional");
        }

        pathologyCard = findViewById(R.id.pathologyCard);
        pharmacologyCard = findViewById(R.id.pharmacologyCard);
        microbiologyCard = findViewById(R.id.microbiologyCard);
        fmtCard = findViewById(R.id.fmtCard);
        secondProfessionalMockTestCard = findViewById(R.id.secondProfessionalMockTestCard);

        pathologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Pathology");
                startActivity(intent);
            }
        });

        pharmacologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Pharmacology");
                startActivity(intent);
            }
        });

        microbiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Microbiology");
                startActivity(intent);
            }
        });

        fmtCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChaptersActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "FMT");
                startActivity(intent);
            }
        });

        secondProfessionalMockTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfessionalSecondActivity.this);
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
                            Intent intent = new Intent(UserProfessionalSecondActivity.this, MockTestActivity.class);
                            intent.putExtra("PROFESSIONAL", professional);
                            intent.putExtra("NUMBER OF QUESTIONS", mSpinner.getSelectedItem().toString());
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(UserProfessionalSecondActivity.this, "Select number of Questions", Toast.LENGTH_SHORT).show();
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
