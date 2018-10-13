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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class InformationBulletinActivity extends AppCompatActivity {

    RecyclerView recyclerViewInfos;
    EditText addInfoEditText;
    ImageView addInfoImageView;
    Button addInfo, selectInfoImage;
    FloatingActionButton addInfoFloating;

    Uri mImageUri;

    Calendar calendar;
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;

    ProgressDialog progressDialog;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    StorageTask mUploadTask;


    String newInfo;
    String date, time;

    ProgressBar progressBar;

    private static int PICK_IMAGE_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_bulletin);

        recyclerViewInfos = findViewById(R.id.recyclerViewInformationBulletinAdmin);

        progressBar = findViewById(R.id.progressBarForInfoList);

        addInfoFloating = findViewById(R.id.addInfoFloating);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");

        storageReference = FirebaseStorage.getInstance().getReference().child("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Information Bulletin");

        //addInfoEditText = findViewById(R.id.addInfoEditText);


        //addInfo = findViewById(R.id.addInfo);

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Information");
        Log.e("see",databaseReference1+"");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null) {
                    for(DataSnapshot dataSnapshot2:dataSnapshot.getChildren()) {

                        Log.e("toget",dataSnapshot2.getValue().toString());
                        Log.e("toget2",dataSnapshot2.getKey().toString());

                        String info = dataSnapshot2.child("infoText").getValue(String.class);
                        String infoKey = dataSnapshot2.getKey();
                        String dateOfInfo = dataSnapshot2.child("infoDate").getValue(String.class);
                        String timeOfInfo = dataSnapshot2.child("infoTime").getValue(String.class);
                        Log.d("getting", info);

                        ((AdapterForInfoList) recyclerViewInfos.getAdapter()).update(info, infoKey, dateOfInfo, timeOfInfo);
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                }
                //else
                //    progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(InformationBulletinActivity.this, "No access to database", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        /*
        databaseReference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot!=null) {
                    for(DataSnapshot dataSnapshot2:dataSnapshot.getChildren()) {
                        String info = dataSnapshot2.child("InfoText").getValue(String.class);
                        Log.d("getting", info);

                        ((AdapterForInfoList) recyclerViewInfos.getAdapter()).update(info);
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else
                    progressBar.setVisibility(View.INVISIBLE);
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
                Toast.makeText(InformationBulletinActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);

            }
        });
        */

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(InformationBulletinActivity.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerViewInfos.setLayoutManager(mLayoutManager);
        AdapterForInfoList adapterForInfoList = new AdapterForInfoList(recyclerViewInfos, InformationBulletinActivity.this,new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
        recyclerViewInfos.setAdapter(adapterForInfoList);



        addInfoFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(InformationBulletinActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.alert_dialog_box_add_info, null);
                addInfoEditText = mView.findViewById(R.id.addInfoEditText);
                addInfoImageView = mView.findViewById(R.id.addInfoImageView);
                selectInfoImage = mView.findViewById(R.id.selectInfoImage);
                addInfo = mView.findViewById(R.id.addInfo);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                selectInfoImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ContextCompat.checkSelfPermission(InformationBulletinActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                            openFileChooser();

                        }else {

                            ActivityCompat.requestPermissions(InformationBulletinActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

                        }
                    }
                });

                addInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newInfo = addInfoEditText.getText().toString();
                        Log.d("Info",newInfo);
                        String ready = "true";
                        if(TextUtils.isEmpty(newInfo)){
                            addInfoEditText.setError(getString(R.string.error_field_required));
                            ready = "false";

                        }
                        if(ready.equals("true")){

                            if(mUploadTask != null && mUploadTask.isInProgress()){
                                Toast.makeText(InformationBulletinActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if(mImageUri !=null){
                                    Log.d("going",mImageUri.toString());
                                    progressDialog = new ProgressDialog(InformationBulletinActivity.this);
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                    progressDialog.setTitle("Uploading File...");
                                    progressDialog.setProgress(0);
                                    progressDialog.show();progressDialog = new ProgressDialog(InformationBulletinActivity.this);


                                    StorageReference storageReference1 = storageReference.child("Info Images").child(System.currentTimeMillis()+"."+getFileExtention(mImageUri));
                                    mUploadTask = storageReference1.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            String url = taskSnapshot.getDownloadUrl().toString();
                                            //String id = databaseReference.push().getKey();

                                            date = dateFormat.format(calendar.getTime());
                                            time = timeFormat.format(calendar.getTime());

                                            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Information");
                                            final String infoKey = databaseReference.push().getKey();

                                            Toast.makeText(InformationBulletinActivity.this, date + " " +time ,Toast.LENGTH_SHORT).show();

                                            UploadInfo uploadInfo = new UploadInfo(newInfo, date, time, url);

                                            databaseReference.child(infoKey).setValue(uploadInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(InformationBulletinActivity.this, "New Information Added", Toast.LENGTH_SHORT).show();
                                                        //databaseReference.child(infoKey).child("Date").setValue(date);
                                                        //databaseReference.child(infoKey).child("Time").setValue(time);
                                                        Toast.makeText(InformationBulletinActivity.this, "File successfully uploaded", Toast.LENGTH_SHORT).show();

                                                        reloadActivity();
                                                    }
                                                    else
                                                        Toast.makeText(InformationBulletinActivity.this, "New Information not added", Toast.LENGTH_SHORT).show();

                                                    dialog.dismiss();

                                                }
                                            });

                                            progressDialog.dismiss();
                                            onBackPressed();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(InformationBulletinActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            onBackPressed();
                                        }
                                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                            int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                            progressDialog.setProgress(currentProgress);

                                        }
                                    });

                                }
                                else {
                                    Toast.makeText(InformationBulletinActivity.this, "No Image selected", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
            }
        });
    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData() != null){
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(addInfoImageView);
        }
    }
    private String getFileExtention(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            openFileChooser();

        }else
            Toast.makeText(InformationBulletinActivity.this, "please provide permission", Toast.LENGTH_SHORT).show();



    }


    public void reloadActivity(){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

}
