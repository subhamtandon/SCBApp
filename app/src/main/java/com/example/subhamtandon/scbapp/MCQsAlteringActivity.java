package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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
    Switch switchOption1, switchOption2, switchOption3, switchOption4;

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
    Boolean option1Value;
    String option2Text;
    String option2ImageUrl;
    Boolean option2Value;
    String option3Text;
    String option3ImageUrl;
    Boolean option3Value;
    String option4Text;
    String option4ImageUrl;
    Boolean option4Value;


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

        switchOption1 = (Switch) findViewById(R.id.switch1);
        switchOption2 = (Switch) findViewById(R.id.switch2);
        switchOption3 = (Switch) findViewById(R.id.switch3);
        switchOption4 = (Switch) findViewById(R.id.switch4);

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

        switchOption1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    option1Value = true;
                else
                    option1Value = false;

            }
        });

        switchOption2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    option2Value = true;
                else
                    option2Value = false;

            }
        });

        switchOption3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    option3Value = true;
                else
                    option3Value = false;

            }
        });

        switchOption4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    option4Value = true;
                else
                    option4Value = false;

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);

                questionText = editTextQuestion.getText().toString();
                explanationText = editTextExplanation.getText().toString();
                option1Text = editTextOption1.getText().toString();
                option2Text = editTextOption2.getText().toString();
                option3Text = editTextOption3.getText().toString();
                option4Text = editTextOption4.getText().toString();

                questionImageUrl = " ";
                explanationImageUrl = " ";
                option1ImageUrl = " ";
                option2ImageUrl = " ";
                option3ImageUrl = " ";
                option4ImageUrl = " ";

                option1Value = false;
                option2Value = false;
                option3Value = false;
                option4Value = false;

                QuestionDetails questionDetails = new QuestionDetails(
                        questionText,
                        questionImageUrl,
                        explanationText,
                        explanationImageUrl,
                        option1Text,
                        option1ImageUrl,
                        option1Value,
                        option2Text,
                        option2ImageUrl,
                        option2Value,
                        option3Text,
                        option3ImageUrl,
                        option3Value,
                        option4Text,
                        option4ImageUrl,
                        option4Value,
                        chapter,
                        mode,
                        set
                );

                DatabaseReference reference = database.getReference();

                reference
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

                if (imagePathQuestion == null){
                    questionImageUrl = null;
                }else {
                    uploadImageQuestion(imagePathQuestion);
                }

                if (imagePathExplanation == null){
                    explanationImageUrl = null;
                }else {
                    uploadImageExplanation(imagePathExplanation);
                }


                if (imagePathOption1 == null){
                    option1ImageUrl = null;
                }else {
                    uploadImageOption1(imagePathOption1);
                }


                if (imagePathOption2 == null){
                    option2ImageUrl = null;
                }else {
                    uploadImageOption2(imagePathOption2);
                }


                if (imagePathOption3 == null){
                    option3ImageUrl = null;
                }else {
                    uploadImageOption3(imagePathOption3);
                }


                if (imagePathOption4 == null){
                    option4ImageUrl = null;
                }else {
                    uploadImageOption4(imagePathOption4);
                }
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

    private void uploadImageExplanation(Uri imagePathQuestion) {

        StorageReference storageReference = storage.getReference();

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);
        final String fileName =  System.currentTimeMillis()+"";

        storageReference.child("Uploads").child(professional).child(subject).child("MCQs").child(questionCode).child("Explanation").child(fileName).putFile(imagePathExplanation)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();

                        DatabaseReference reference = database.getReference();

                        reference.child("App").child("Study").child(professional).child(subject).child("MCQs").child(questionCode).child(questionImageUrl).setValue(url)
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

    private void uploadImageOption4(Uri imagePathQuestion) {

        StorageReference storageReference = storage.getReference();

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);
        final String fileName =  System.currentTimeMillis()+"";

        storageReference.child("Uploads").child(professional).child(subject).child("MCQs").child(questionCode).child("Option 4").child(fileName).putFile(imagePathOption4)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();

                        DatabaseReference reference = database.getReference();

                        reference.child("App").child("Study").child(professional).child(subject).child("MCQs").child(questionCode).child(questionImageUrl).setValue(url)
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

    private void uploadImageOption3(Uri imagePathQuestion) {

        StorageReference storageReference = storage.getReference();

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);
        final String fileName =  System.currentTimeMillis()+"";

        storageReference.child("Uploads").child(professional).child(subject).child("MCQs").child(questionCode).child("Option 3").child(fileName).putFile(imagePathOption3)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();

                        DatabaseReference reference = database.getReference();

                        reference.child("App").child("Study").child(professional).child(subject).child("MCQs").child(questionCode).child(option3ImageUrl).setValue(url)
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

    private void uploadImageOption2(Uri imagePathQuestion) {

        StorageReference storageReference = storage.getReference();

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);
        final String fileName =  System.currentTimeMillis()+"";

        storageReference.child("Uploads").child(professional).child(subject).child("MCQs").child(questionCode).child("Option 2").child(fileName).putFile(imagePathOption2)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();

                        DatabaseReference reference = database.getReference();

                        reference.child("App").child("Study").child(professional).child(subject).child("MCQs").child(questionCode).child(option2ImageUrl).setValue(url)
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

    private void uploadImageOption1(Uri imagePathQuestion) {

        StorageReference storageReference = storage.getReference();

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);
        final String fileName =  System.currentTimeMillis()+"";

        storageReference.child("Uploads").child(professional).child(subject).child("MCQs").child(questionCode).child("Option 1").child(fileName).putFile(imagePathOption1)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();

                        DatabaseReference reference = database.getReference();

                        reference.child("App").child("Study").child(professional).child(subject).child("MCQs").child(questionCode).child(option1ImageUrl).setValue(url)
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

    private void uploadImageQuestion(Uri imagePathQuestion) {

        StorageReference storageReference = storage.getReference();

        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");
        final String chapter = getIntent().getStringExtra("CHAPTER");
        final String mode = getIntent().getStringExtra("MODE");
        final String set = getIntent().getStringExtra("SET");
        final String questionCode = professional.substring(0,2) + subject.substring(0,2) + chapter.substring(chapter.length() - 2) + mode.substring(0,1) + set.substring(set.length() - 2);
        final String fileName =  System.currentTimeMillis()+"";

        storageReference.child("Uploads").child(professional).child(subject).child("MCQs").child(questionCode).child("Question").child(fileName).putFile(imagePathQuestion)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();

                        DatabaseReference reference = database.getReference();

                        reference.child("App").child("Study").child(professional).child(subject).child("MCQs").child(questionCode).child(questionImageUrl).setValue(url)
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
