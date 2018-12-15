package com.example.subhamtandon.scbapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewListOfPPTAdmin extends AppCompatActivity {

    FloatingActionButton addNewPPT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list_of_ppt_admin);

        addNewPPT = findViewById(R.id.addNewPPT);
        addNewPPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewListOfPPTAdmin.this, "This feature coming soon ;)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
