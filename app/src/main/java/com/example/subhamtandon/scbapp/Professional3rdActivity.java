package com.example.subhamtandon.scbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Professional3rdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional3rd);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("3rd Professional");

    }
}
