package com.example.subhamtandon.scbapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;

public class AdminPPTActivity extends AppCompatActivity {

    RecyclerView listOfPPT;
    FloatingActionButton addPPT;
    Uri pdfUri;

    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    EditText addPPTPDFEditText;
    TextView addPPTPDFTextView;
    Button selectPPTPDF, addPPTPDF;

    StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ppt);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        addPPT = findViewById(R.id.addPPT);
        listOfPPT = findViewById(R.id.recyclerViewListOfPPTs);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("PPTs").child("Thumbnail");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null) {


                    String url = dataSnapshot.getValue(String.class);
                    //String uploadPDFID = dataSnapshot.getKey();

                    ((AdapterForListOfPPTsAdmin) listOfPPT.getAdapter()).update(url);

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

                Toast.makeText(AdminPPTActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        listOfPPT.setLayoutManager(new LinearLayoutManager(AdminPPTActivity.this));
        AdapterForListOfPPTsAdmin adapterForListOfPPTsAdmin = new AdapterForListOfPPTsAdmin(listOfPPT, AdminPPTActivity.this,new ArrayList<String>());
        //adapterForRecordsList.notifyDataSetChanged();
        listOfPPT.setAdapter(adapterForListOfPPTsAdmin);

        addPPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminPPTActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.adding_pptpdf_dailog, null);
                addPPTPDFEditText = mView.findViewById(R.id.addPPTPDFEditText);
                addPPTPDFTextView = mView.findViewById(R.id.addPPTPDFTextView);
                selectPPTPDF = mView.findViewById(R.id.selectPPTPDF);
                addPPTPDF = mView.findViewById(R.id.addPPTPDF);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                selectPPTPDF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ContextCompat.checkSelfPermission(AdminPPTActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                            selectPdf();

                        }else {

                            ActivityCompat.requestPermissions(AdminPPTActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

                        }
                    }
                });

                addPPTPDF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (pdfUri != null){
                            String ready = "true";
                            if(addPPTPDFEditText.getText().toString().trim().equals("")){
                                addPPTPDFEditText.setError(getString(R.string.error_field_required));
                                ready="false";
                            }
                            if(ready.equals("true")) {
                                if(uploadTask != null && uploadTask.isInProgress()){
                                    Toast.makeText(AdminPPTActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    progressDialog = new ProgressDialog(AdminPPTActivity.this);
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                    progressDialog.setTitle("Uploading File...");
                                    progressDialog.setProgress(0);
                                    progressDialog.show();

                                    StorageReference storageReference = storage.getReference();
                                    final String fileName =  System.currentTimeMillis()+"." + getFileExtension(pdfUri);
                                    //final String fileName1 = System.currentTimeMillis()+"";

                                    //final String fileName =  practicalFileName.getText().toString()+".pdf";
                                    //final String fileName1 = practicalFileName.getText().toString()+"";

                                    uploadTask = storageReference.child("Uploads").child("PPTPDFs").child(fileName).putFile(pdfUri)
                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                    String url = taskSnapshot.getDownloadUrl().toString();


                                                    DatabaseReference reference = database.getReference();
                                                    UploadPDF uploadPDF = new UploadPDF(addPPTPDFEditText.getText().toString().trim(),taskSnapshot.getDownloadUrl().toString());
                                                    String uploadPDFID = reference.push().getKey();

                                                    reference.child("App").child("PPTPDFs").child(uploadPDFID).setValue(uploadPDF)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                    if (task.isSuccessful())
                                                                        Toast.makeText(AdminPPTActivity.this, "File successfully uploaded", Toast.LENGTH_SHORT).show();
                                                                    else
                                                                        Toast.makeText(AdminPPTActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();

                                                                }
                                                            });
                                                    progressDialog.dismiss();

                                                    onBackPressed();
                                                    dialog.dismiss();


                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                    Toast.makeText(AdminPPTActivity.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();


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
                        }else
                            Toast.makeText(AdminPPTActivity.this, "Select a file", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }


    private void selectPdf() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
        //addPPTPDFTextView.setText();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 86 && resultCode == RESULT_OK && data != null){

            pdfUri = data.getData();
            addPPTPDFTextView.setText("A file is selected : " + data.getData().getLastPathSegment());
            Toast.makeText(this, "A file is selected", Toast.LENGTH_SHORT).show();

        }else
            Toast.makeText(AdminPPTActivity.this, "please select a file", Toast.LENGTH_SHORT).show();

    }
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
