package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MCQsAlteringActivity extends AppCompatActivity {

    EditText editTextQuestion, editTextOption1, editTextOption2, editTextOption3, editTextOption4, editTextExplanation;
    Button selectImageQuestion, selectImageOption1,selectImageOption2, selectImageOption3, selectImageOption4, selectImageExplanation, submitButton;
    TextView notificationQuestion, notificationOption1, notificationOption2, notificationOption3, notificationOption4, notificationExplanation;
    Switch switch1, switch2, switch3, switch4;
    Uri filePathQuestion;
    Uri filePathOption1;
    Uri filePathOption2;
    Uri filePathOption3;
    Uri filePathOption4;
    Uri filePathExplanation;


    FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcqs_altering);

        String professional = getIntent().getStringExtra("PROFESSIONAL");
        String subject = getIntent().getStringExtra("SUBJECT");
        String chapter = getIntent().getStringExtra("CHAPTER");
        String mode = getIntent().getStringExtra("MODE");
        String set = getIntent().getStringExtra("SET");

        Toast.makeText(this, professional + " : " + subject + " : " + chapter + " : "+ mode + " : " + set, Toast.LENGTH_SHORT).show();

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        editTextOption1 = (EditText) findViewById(R.id.editTextOption1);
        editTextOption2 = (EditText) findViewById(R.id.editTextOption2);
        editTextOption3 = (EditText) findViewById(R.id.editTextOption3);
        editTextOption4 = (EditText) findViewById(R.id.editTextOption4);
        editTextExplanation = (EditText) findViewById(R.id.editTextExplanation);

        selectImageQuestion = (Button) findViewById(R.id.selectImageQuestion);
        selectImageOption1 = (Button) findViewById(R.id.selectImageOption1);
        selectImageOption2 = (Button) findViewById(R.id.selectImageOption2);
        selectImageOption3 = (Button) findViewById(R.id.selectImageOption3);
        selectImageOption4 = (Button) findViewById(R.id.selectImageOption4);
        selectImageExplanation = (Button) findViewById(R.id.selectImageExplanation);
        submitButton = (Button) findViewById(R.id.submitButton);

        notificationQuestion = (TextView) findViewById(R.id.notificationQuestion);
        notificationOption1 = (TextView) findViewById(R.id.notificationOption1);
        notificationOption2 = (TextView) findViewById(R.id.notificationOption2);
        notificationOption3 = (TextView) findViewById(R.id.notificationOption3);
        notificationOption4 = (TextView) findViewById(R.id.notificationOption4);
        notificationExplanation = (TextView) findViewById(R.id.notificationExplanation);

        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
        switch4 = (Switch) findViewById(R.id.switch4);

        selectImageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();

            }
        });

        selectImageOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();

            }
        });

        selectImageOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();

            }
        });

        selectImageOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();

            }
        });

        selectImageOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();

            }
        });

        selectImageExplanation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();

            }
        });
    }

    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an Image"),1);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),2);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),3);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),4);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),5);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),6);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathQuestion = data.getData();
            notificationQuestion.setText("A file is selected : " + data.getData().getLastPathSegment());

        }else if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathOption1 = data.getData();
            notificationOption1.setText("A file is selected : " + data.getData().getLastPathSegment());

        }
        else if (requestCode == 3 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathOption2 = data.getData();
            notificationOption2.setText("A file is selected : " + data.getData().getLastPathSegment());

        }
        else if (requestCode == 4 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathOption3 = data.getData();
            notificationOption3.setText("A file is selected : " + data.getData().getLastPathSegment());

        }
        else if (requestCode == 5 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathOption4 = data.getData();
            notificationOption4.setText("A file is selected : " + data.getData().getLastPathSegment());

        }
        else if (requestCode == 6 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathExplanation = data.getData();
            notificationExplanation.setText("A file is selected : " + data.getData().getLastPathSegment());

        }
        else
            Toast.makeText(MCQsAlteringActivity.this, "please select an image", Toast.LENGTH_SHORT).show();
    }

}
