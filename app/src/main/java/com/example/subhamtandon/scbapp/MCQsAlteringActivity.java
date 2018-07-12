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

    Uri imagePathQuestion;
    Uri imagePathExplanation;
    Uri imagePathOption1;
    Uri imagePathOption2;
    Uri imagePathOption3;
    Uri imagePathOption4;

    String questionCode;
    String questionText;
    String questionImageUrl;
    String explanationText;
    String explanationImageUrl;
    String option1Text;
    String option1ImageUrl;
    String option2Text;
    String option2ImageUrl;
    String option3Text;
    String option3ImageUrl;
    String option4Text;
    String option4ImageUrl;


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

                //
                //uploadImageOption1(filePathOption1);
                //uploadImageOption2(filePathOption2);
                //uploadImageOption3(filePathOption3);
                //uploadImageOption4(filePathOption4);
                //uploadImageExplanation(filePathExplanation);

                questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);

                questionText = editTextQuestion.getText().toString();
                if (imagePathQuestion == null){
                    questionImageUrl = null;
                }else {
                    questionImageUrl = uploadImageQuestion(imagePathQuestion);
                }

                /*explanationText = editTextExplanation.getText().toString();
                if (imagePathExplanation == null){
                    explanationImageUrl = null;
                }else {
                    explanationImageUrl = uploadImageExplanation(imagePathExplanation);
                }

                option1Text = editTextOption1.getText().toString();
                if (imagePathOption1 == null){
                    option1ImageUrl = null;
                }else {
                   option1ImageUrl = uploadImageOption1(imagePathOption1);
                }

                option2Text = editTextOption2.getText().toString();
                if (imagePathOption2 == null){
                    option2ImageUrl = null;
                }else {
                    option2ImageUrl = uploadImageOption2(imagePathOption2);
                }

                option3Text = editTextOption3.getText().toString();
                if (imagePathOption3 == null){
                    option3ImageUrl = null;
                }else {
                    option3ImageUrl = uploadImageOption3(imagePathOption3);
                }

                option4Text = editTextOption4.getText().toString();
                if (imagePathOption4 == null){
                    option4ImageUrl = null;
                }else {
                    option4ImageUrl = uploadImageOption4(imagePathOption4);
                }*/


                QuestionDetails questionDetails = new QuestionDetails(
                        questionText,
                        questionImageUrl,
                        explanationText,
                        explanationImageUrl,
                        option1Text,
                        option1ImageUrl,
                        option2Text,
                        option2ImageUrl,
                        option3Text,
                        option3ImageUrl,
                        option4Text,
                        option4ImageUrl,
                        chapter,
                        mode,
                        set
                );

                database.getInstance().getReference()
                        .child("App")
                        .child("Study")
                        .child(professional)
                        .child(subject)
                        .child("MCQs")
                        .child(questionCode)
                        .setValue(questionDetails)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){

                                    Toast.makeText(MCQsAlteringActivity.this, "Question Added successfully", Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(MCQsAlteringActivity.this, "failed to add question", Toast.LENGTH_SHORT).show();
                            }
                        });
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

            imagePathQuestion = data.getData();
            notificationQuestion.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }else if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null){

            imagePathOption1 = data.getData();
            notificationOption1.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else if (requestCode == 3 && resultCode == RESULT_OK && data != null && data.getData() != null){

            imagePathOption2 = data.getData();
            notificationOption2.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else if (requestCode == 4 && resultCode == RESULT_OK && data != null && data.getData() != null){

            imagePathOption3 = data.getData();
            notificationOption3.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else if (requestCode == 5 && resultCode == RESULT_OK && data != null && data.getData() != null){

            imagePathOption4 = data.getData();
            notificationOption4.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else if (requestCode == 6 && resultCode == RESULT_OK && data != null && data.getData() != null){

            imagePathExplanation = data.getData();
            notificationExplanation.setText("A file is selected : " + data.getData().getLastPathSegment().toString());

        }
        else
            Toast.makeText(MCQsAlteringActivity.this, "please select an image", Toast.LENGTH_SHORT).show();
    }

    /*private String uploadImageExplanation(Uri filePathExplanation) {

        StorageReference storageReference = storage.getReference();
        Uri url;
        return url;
    }*/

    /*private String uploadImageOption4(Uri filePathOption4) {

        StorageReference storageReference = storage.getReference();
        Uri url;
        return url;
    }*/

    /*private String uploadImageOption3(Uri filePathOption3) {

        StorageReference storageReference = storage.getReference();
        Uri url;
        return url;
    }*/

    /*private String uploadImageOption2(Uri filePathOption2) {

        StorageReference storageReference = storage.getReference();
        Uri url;
        return url;
    }*/

    /*private String uploadImageOption1(Uri filePathOption1) {

        StorageReference storageReference = storage.getReference();
        String url = null;
        return url;
    }*/

    private String uploadImageQuestion(Uri imagePathQuestion) {

        StorageReference storageReference = storage.getReference();

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);
        //final String fileName =  System.currentTimeMillis()+"";

        final String url = null;

        storageReference.child("Uploads").child(professional).child(subject).child("MCQs").child(questionCode).child("Question").putFile(imagePathQuestion)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                         //url = taskSnapshot.getDownloadUrl().toString();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MCQsAlteringActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();

            }
        });

        return url;
    }

    /*private void uploadImageQuestion(Uri filePathQuestion) {

        StorageReference storageReference = storage.getReference();


        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);
        final String fileName =  System.currentTimeMillis()+"";

        storageReference.child("Uploads").child(professional).child(subject).child(chapter).child(mode).child(set).child(fileName).putFile(filePathQuestion)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();

                        DatabaseReference reference = database.getReference();

                        reference.child("App").child("Study").child(professional).child(subject).child("MCQs").child(questionCode).child(mode).child(set).child(fileName).setValue(url)
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

    }*/

}
