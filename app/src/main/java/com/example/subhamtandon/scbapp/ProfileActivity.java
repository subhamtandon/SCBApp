package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserName;
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {

            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserName = (TextView) findViewById(R.id.textViewUserName);

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);

        textViewUserName.setText(user.getDisplayName());

        textViewUserEmail.setText(user.getEmail());

        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == buttonLogout){

            firebaseAuth.signOut();
            firebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

    }
}
