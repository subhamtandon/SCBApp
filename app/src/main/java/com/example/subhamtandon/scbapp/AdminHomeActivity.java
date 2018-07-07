package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class AdminHomeActivity extends AppCompatActivity {

    CardView StudyCard,DepartmentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        StudyCard = (CardView) findViewById(R.id.StudyCard);
        DepartmentCard = (CardView) findViewById(R.id.DepartmentCard);

        StudyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AdminStudyActivity.class));

            }
        });

        DepartmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AdminDepartmentActivity.class));

            }
        });

    }
}
