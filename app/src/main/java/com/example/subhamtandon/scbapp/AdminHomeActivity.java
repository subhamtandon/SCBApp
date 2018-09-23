package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class AdminHomeActivity extends AppCompatActivity {

    CardView StudyCard,DepartmentCard,InformationBulletinCard, MedicalRelatedPicsCard, PPTCard;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        StudyCard = (CardView) findViewById(R.id.StudyCard);
        DepartmentCard = (CardView) findViewById(R.id.DepartmentCard);
        InformationBulletinCard = findViewById(R.id.InformationBulletinCard);
        MedicalRelatedPicsCard = findViewById(R.id.MedicalRelatedPicturesCard);
        PPTCard = findViewById(R.id.PPTCard);

        firebaseAuth = FirebaseAuth.getInstance();

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

        InformationBulletinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), InformationBulletinActivity.class));
            }
        });

        MedicalRelatedPicsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminMedicalRelatedPicsActivity.class));
            }
        });
        PPTCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminPPTActivity.class));
            }
        });

    }

    public void logout(View view){

        firebaseAuth.signOut();
        firebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));

    }

}
