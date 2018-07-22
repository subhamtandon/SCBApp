package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetsAlteringActivity extends AppCompatActivity {

    TextInputEditText setEditText;
    Button submitSetButton;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets_altering);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");

        Toast.makeText(this, professional + " : " + subject + " : " + chapter + " "+ mode, Toast.LENGTH_SHORT).show();

        setEditText = (TextInputEditText) findViewById(R.id.setEditText);
        submitSetButton = (Button) findViewById(R.id.submitSetButton);
        databaseReference = FirebaseDatabase.getInstance().getReference("App").child("Study").child(professional).child(subject).child("MCQs");

        submitSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whichSet = setEditText.getText().toString();

                String ready = "true";

                if (TextUtils.isEmpty(whichSet)){
                    setEditText.setError(getString(R.string.error_field_required));
                    ready = "false";
                }

                if(ready.equalsIgnoreCase("true")){

                    databaseReference.child(chapter)
                            .child(mode)
                            .child(whichSet)
                            .setValue(null);

                    Intent done = new Intent(SetsAlteringActivity.this, AddingQuestionActivity.class);
                    //done.putExtra("TYPE","MCQs");
                    done.putExtra("PROFESSIONAL",professional);
                    done.putExtra("SUBJECT",subject);
                    done.putExtra("CHAPTER", chapter);
                    done.putExtra("MODE",mode);
                    done.putExtra("SET",whichSet);
                    startActivity(done);
                }

            }
        });
    }

}
