package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminMedicalRelatedPicsActivity extends AppCompatActivity {

    FloatingActionButton addFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_medical_related_pics);
        addFloatingActionButton = findViewById(R.id.addMedicalRelatedPic);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(AdminMedicalRelatedPicsActivity.this,AddingMedicalRelatedPicAdminActivity.class ));
            }
        });
    }
}
