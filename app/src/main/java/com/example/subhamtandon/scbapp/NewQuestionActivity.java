package com.example.subhamtandon.scbapp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewQuestionActivity extends AppCompatActivity {

    EditText addQuestionEditText, addOptionAEditText, addOptionBEditText, addOptionCEditText, addOptionDEditText, addExplanationEditText;
    Spinner correctOptionSpinner;
    Button submitQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);

        addQuestionEditText = findViewById(R.id.addQuestionEditText);
        addOptionAEditText = findViewById(R.id.addOptionAEditText);
        addOptionBEditText = findViewById(R.id.addOptionBEditText);
        addOptionCEditText = findViewById(R.id.addOptionCEditText);
        addOptionDEditText = findViewById(R.id.addOptionDEditText);
        addExplanationEditText = findViewById(R.id.addExplanationEditText);
        correctOptionSpinner = findViewById(R.id.correctOptionSpinner);
        submitQuestionButton = findViewById(R.id.submitQuestionButton);

        submitQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = addQuestionEditText.getText().toString();
                String optionA = addOptionAEditText.getText().toString();
                String optionB = addOptionBEditText.getText().toString();
                String optionC = addOptionCEditText.getText().toString();
                String optionD = addOptionDEditText.getText().toString();
                String correctAnswer = correctOptionSpinner.getSelectedItem().toString();
                String explanation = addExplanationEditText.getText().toString();

                String ready = "true";
                if (TextUtils.isEmpty(question)){
                    addQuestionEditText.setError(getString(R.string.error_field_required));
                    ready = "false";
                }
                if (TextUtils.isEmpty(optionA)){
                    addOptionAEditText.setError(getString(R.string.error_field_required));
                    ready = "false";
                }
                if (TextUtils.isEmpty(optionB)){
                    addOptionBEditText.setError(getString(R.string.error_field_required));
                    ready = "false";
                }
                if (TextUtils.isEmpty(optionC)){
                    addOptionCEditText.setError(getString(R.string.error_field_required));
                    ready = "false";
                }
                if (TextUtils.isEmpty(optionD)){
                    addOptionDEditText.setError(getString(R.string.error_field_required));
                    ready = "false";
                }
                if (correctAnswer.equalsIgnoreCase("Choose Correct Option")){
                    Toast.makeText(NewQuestionActivity.this, "Choose correct option from the Dropdown", Toast.LENGTH_SHORT).show();
                    ready = "false";
                }
                if (TextUtils.isEmpty(explanation)){
                    addExplanationEditText.setError(getString(R.string.error_field_required));
                    ready = "false";
                }

                if (ready.equalsIgnoreCase("true")){
                    Toast.makeText(NewQuestionActivity.this, question+optionA+optionB+optionC+optionD+correctAnswer+explanation, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
