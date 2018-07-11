package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetsAlteringActivity extends AppCompatActivity {

    EditText setEditText;
    Button submitButton;
    TextView showSetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets_altering);

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");

        Toast.makeText(this, professional + " : " + subject + " : " + chapter + " "+ mode, Toast.LENGTH_SHORT).show();

        setEditText = (EditText) findViewById(R.id.setEditText);
        submitButton = (Button) findViewById(R.id.submitButton);
        showSetTextView = (TextView) findViewById(R.id.showSetTextView);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whichSet = setEditText.getText().toString();

                String ready = "true";

                if (TextUtils.isEmpty(whichSet)){
                    setEditText.setError(getString(R.string.error_field_required));
                    ready = "false";
                }

                if(ready.equalsIgnoreCase("true")){
                    Intent next = new Intent(SetsAlteringActivity.this, MCQsAlteringActivity.class);
                    next.putExtra("PROFESSIONAL", professional);
                    next.putExtra("SUBJECT", subject);
                    next.putExtra("CHAPTER", chapter);
                    next.putExtra("MODE",mode);
                    next.putExtra("SET",whichSet);
                    startActivity(next);
                }

            }
        });
    }

}
