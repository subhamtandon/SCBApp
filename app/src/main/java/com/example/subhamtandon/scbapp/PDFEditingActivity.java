package com.example.subhamtandon.scbapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class PDFEditingActivity extends AppCompatActivity {

    EditText pdfName;
    TextView selectedPDFName;
    Button nameChange, fileChange, submitEditing, selectFile;
    PDFView pdfView;
    Uri pdfUri;
    StorageTask uploadTask;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfediting);

        pdfName = findViewById(R.id.pdfName);
        nameChange = findViewById(R.id.nameChange);
        fileChange = findViewById(R.id.fileChange);
        submitEditing = findViewById(R.id.submitEditing);
        pdfView = findViewById(R.id.pdfView);

        selectedPDFName = findViewById(R.id.selectedFileName);
        selectFile = findViewById(R.id.selectFile);



        final String professional = getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("Records");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null) {

                    String recordName = dataSnapshot.child("mName").getValue(String.class);
                    String url = dataSnapshot.child("mURL").getValue(String.class);

                    Uri myUri = Uri.parse(url);

                    pdfName.setText(recordName);
                    pdfView.fromUri(myUri).load();

                    nameChange.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("Records");
                            databaseReference.child(dataSnapshot.getKey().toString()).child("mName").setValue(pdfName.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(PDFEditingActivity.this, "Name Changed Successfully", Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    }
                                    else
                                        Toast.makeText(PDFEditingActivity.this, "Fail to change name", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PDFEditingActivity.this, "Fail to change name", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });

                    selectFile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ContextCompat.checkSelfPermission(PDFEditingActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                                selectPdf();

                            }else {

                                ActivityCompat.requestPermissions(PDFEditingActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

                            }
                        }
                    });

                    fileChange.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (pdfUri != null){
                                if(uploadTask != null && uploadTask.isInProgress()){
                                    Toast.makeText(PDFEditingActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    //uploadFile(pdfUri);
                                    progressDialog = new ProgressDialog(PDFEditingActivity.this);
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                    progressDialog.setTitle("Uploading File...");
                                    progressDialog.setProgress(0);
                                    progressDialog.show();

                                    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                                    final String fileName =  System.currentTimeMillis()+"." + getFileExtension(pdfUri);

                                    uploadTask = storageReference.child("Uploads").child(professional).child(subject).child("Records").child(fileName).putFile(pdfUri)
                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                    String url = taskSnapshot.getDownloadUrl().toString();

                                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

                                                    //UploadPDF uploadPDF = new UploadPDF(recordFileName.getText().toString().trim(),taskSnapshot.getDownloadUrl().toString());
                                                    //String uploadPDFID = reference.push().getKey();

                                                    reference.child("App").child("Study").child(professional).child(subject).child("Records").child(dataSnapshot.getKey().toString()).child("mURL").setValue(url)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                    if (task.isSuccessful())
                                                                        Toast.makeText(PDFEditingActivity.this, "File successfully uploaded", Toast.LENGTH_SHORT).show();
                                                                    else
                                                                        Toast.makeText(PDFEditingActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();

                                                                }
                                                            });

                                                    progressDialog.dismiss();

                                                    onBackPressed();

                                                    /*

                                                    Intent done = new Intent(PDFEditingActivity.this, UploadDoneActivity.class);
                                                    done.putExtra("TYPE","Records");
                                                    done.putExtra("PROFESSIONAL",professional);
                                                    done.putExtra("SUBJECT",subject);
                                                    startActivity(done);
                                                    */

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                    Toast.makeText(PDFEditingActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();

                                                }
                                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                                    int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                                    progressDialog.setProgress(currentProgress);

                                                }
                                            });
                                }
                            }
                            else
                                Toast.makeText(PDFEditingActivity.this, "Select a file", Toast.LENGTH_SHORT).show();
                        }
                    });


                    //((AdapterForRecordsList) PDFEditingActivity.getAdapter()).update(recordName, url);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            selectPdf();

        }else
            Toast.makeText(PDFEditingActivity.this, "please provide permission", Toast.LENGTH_SHORT).show();



    }
    private void selectPdf() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 86 && resultCode == RESULT_OK && data != null){

            pdfUri = data.getData();
            selectedPDFName.setText("A file is selected : " + data.getData().getLastPathSegment());

        }else
            Toast.makeText(PDFEditingActivity.this, "please select a file", Toast.LENGTH_SHORT).show();

    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
