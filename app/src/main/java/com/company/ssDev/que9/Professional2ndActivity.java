package com.company.ssDev.que9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class Professional2ndActivity extends AppCompatActivity {

    CardView pathologyCard, pharmacologyCard, microbiologyCard, fmtCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional2nd);

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

        pathologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Pathology");
                startActivity(intent);
            }
        });

        pharmacologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Pharmacology");
                startActivity(intent);
            }
        });

        microbiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "Microbiology");
                startActivity(intent);
            }
        });

        fmtCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DifferentMaterialsActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", "FMT");
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
