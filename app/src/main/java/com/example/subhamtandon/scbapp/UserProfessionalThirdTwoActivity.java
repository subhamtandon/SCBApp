package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class UserProfessionalThirdTwoActivity extends AppCompatActivity {

    CardView medicineCard, surgeryCard, pediatricsCard, orthopedicsCard, skinVDCard, anaesthesiologyCard, radiologyCard, ogCard;

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
