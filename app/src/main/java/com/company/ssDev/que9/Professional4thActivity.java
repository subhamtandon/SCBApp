package com.company.ssDev.que9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class Professional4thActivity extends AppCompatActivity {

    CardView medicineCard, surgeryCard, paediatricsCard, orthopaedicsCard, skinVDCard, anaesthesiologyCard, radiologyCard, ogCard, psychiatryCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional4th);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Third Professional Part 2");
        }

        medicineCard = findViewById(R.id.medicineCard);
        surgeryCard = findViewById(R.id.surgeryCard);
        paediatricsCard = findViewById(R.id.paediatricsCard);
        orthopaedicsCard = findViewById(R.id.orthopaedicsCard);
        skinVDCard = findViewById(R.id.skinVDCard);
        anaesthesiologyCard = findViewById(R.id.anaesthesiologyCard);
        radiologyCard = findViewById(R.id.radiologyCard);
        ogCard = findViewById(R.id.ogCard);
        psychiatryCard = findViewById(R.id.psychiatryCard);

        medicineCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Medicine");
                startActivity(intent);
            }
        });

        surgeryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Surgery");
                startActivity(intent);
            }
        });

        paediatricsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "paediatrics");
                startActivity(intent);
            }
        });

        orthopaedicsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "orthopaedics");
                startActivity(intent);
            }
        });

        skinVDCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Skin & VD");
                startActivity(intent);
            }
        });

        anaesthesiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Anaesthesiology");
                startActivity(intent);
            }
        });

        radiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Radiology");
                startActivity(intent);
            }
        });

        ogCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "O & G");
                startActivity(intent);
            }
        });

        psychiatryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Psychiatry");
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
