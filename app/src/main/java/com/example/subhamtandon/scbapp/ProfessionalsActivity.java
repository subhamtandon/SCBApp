package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class ProfessionalsActivity extends AppCompatActivity {

    CardView firstProfessionalCard, secondProfessionalCard, thirdProfessionalPartOneCard, thirdProfessionalPartTwoCard;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professionals);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Professionals");
        }

        firstProfessionalCard = findViewById(R.id.firstProfessionalCard);
        secondProfessionalCard = findViewById(R.id.secondProfessionalCard);
        thirdProfessionalPartOneCard = findViewById(R.id.thirdProfessionalPartOneCard);
        thirdProfessionalPartTwoCard = findViewById(R.id.thirdProfessionalPartTwoCard);

        firstProfessionalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), UserProfessionalFirstActivity.class);
                intent.putExtra("PROFESSIONAL", "First Professional");
                startActivity(intent);
            }
        });

        secondProfessionalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), UserProfessionalSecondActivity.class);
                intent.putExtra("PROFESSIONAL", "Second Professional");
                startActivity(intent);
            }
        });

        thirdProfessionalPartOneCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), UserProfessionalThirdOneActivity.class);
                intent.putExtra("PROFESSIONAL", "Third Professional Part-1");
                startActivity(intent);
            }
        });

        thirdProfessionalPartTwoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), UserProfessionalThirdTwoActivity.class);
                intent.putExtra("PROFESSIONAL", "Third Professional Part-2");
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