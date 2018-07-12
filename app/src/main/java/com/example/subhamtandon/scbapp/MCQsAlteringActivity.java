package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");

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

                showFileChooserQuestion();

            }
        });

        selectImageOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooserOption1();

            }
        });

        selectImageOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooserOption2();

            }
        });

        selectImageOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooserOption3();

            }
        });

        selectImageOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooserOption4();

            }
        });

        selectImageExplanation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooserExplanation();

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //uploadImageQuestion(filePathQuestion);
                //uploadImageOption1(filePathOption1);
                //uploadImageOption2(filePathOption2);
                //uploadImageOption3(filePathOption3);
                //uploadImageOption4(filePathOption4);
                //uploadImageExplanation(filePathExplanation);

                String question = editTextQuestion.getText().toString();
                String option1 = editTextOption1.getText().toString();
                String option2 = editTextOption2.getText().toString();
                String option3 = editTextOption3.getText().toString();
                String option4 = editTextOption4.getText().toString();
                String explanation = editTextExplanation.getText().toString();

                //database.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("MCQs").child(chapter).child(mode).child(set).setValue(question)
            }
        });
    }

    private void showFileChooserExplanation() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),6);
    }

    private void showFileChooserOption4() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),5);
    }

    private void showFileChooserOption3() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),4);
    }

    private void showFileChooserOption2() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),3);
    }

    private void showFileChooserOption1() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select An Image"),2);
    }

    private void showFileChooserQuestion() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an Image"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathQuestion = data.getData();
            notificationQuestion.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }else if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathOption1 = data.getData();
            notificationOption1.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else if (requestCode == 3 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathOption2 = data.getData();
            notificationOption2.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else if (requestCode == 4 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathOption3 = data.getData();
            notificationOption3.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else if (requestCode == 5 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathOption4 = data.getData();
            notificationOption4.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else if (requestCode == 6 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePathExplanation = data.getData();
            notificationExplanation.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else
            Toast.makeText(MCQsAlteringActivity.this, "please select an image", Toast.LENGTH_SHORT).show();
    }

    private void uploadImageExplanation(Uri filePathExplanation) {

        StorageReference storageReference = storage.getReference();
    }

    private void uploadImageOption4(Uri filePathOption4) {

        StorageReference storageReference = storage.getReference();
    }

    private void uploadImageOption3(Uri filePathOption3) {

        StorageReference storageReference = storage.getReference();
    }

    private void uploadImageOption2(Uri filePathOption2) {

        StorageReference storageReference = storage.getReference();
    }

    private void uploadImageOption1(Uri filePathOption1) {

        StorageReference storageReference = storage.getReference();
    }

    private void uploadImageQuestion(Uri filePathQuestion) {

        StorageReference storageReference = storage.getReference();

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String fileName =  System.currentTimeMillis()+"";

        storageReference.child("Uploads").child(professional).child(subject).child(chapter).child(mode).child(set).child(fileName).putFile(filePathQuestion)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();

                        DatabaseReference reference = database.getReference();

                        reference.child("App").child("Study").child(professional).child(subject).child(chapter).child(mode).child(set).child(fileName).setValue(url)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful())
                                        Toast.makeText(MCQsAlteringActivity.this, "File successfully uploaded", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(MCQsAlteringActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MCQsAlteringActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
