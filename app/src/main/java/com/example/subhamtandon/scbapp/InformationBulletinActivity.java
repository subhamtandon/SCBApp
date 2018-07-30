package com.example.subhamtandon.scbapp;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class InformationBulletinActivity extends AppCompatActivity {

    RecyclerView recyclerViewInfos;
    EditText addInfoEditText;
    Button addInfo;
    FloatingActionButton addInfoFloating;

    Calendar calendar;
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;


    String newInfo;
    String date, time;

    ProgressBar progressBar;

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

                        String info = dataSnapshot2.child("InfoText").getValue(String.class);
                        String infoKey = dataSnapshot2.getKey();
                        String dateOfInfo = dataSnapshot2.child("Date").getValue(String.class);
                        String timeOfInfo = dataSnapshot2.child("Time").getValue(String.class);
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

        recyclerViewInfos.setLayoutManager(new LinearLayoutManager(InformationBulletinActivity.this));
        AdapterForInfoList adapterForInfoList = new AdapterForInfoList(recyclerViewInfos, InformationBulletinActivity.this,new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
        recyclerViewInfos.setAdapter(adapterForInfoList);



        addInfoFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(InformationBulletinActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.alert_dialog_box_add_info, null);
                addInfoEditText = mView.findViewById(R.id.addInfoEditText);
                addInfo = mView.findViewById(R.id.addInfo);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

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

                            date = dateFormat.format(calendar.getTime());
                            time = timeFormat.format(calendar.getTime());

                            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Information");
                            final String infoKey = databaseReference.push().getKey();

                            Toast.makeText(InformationBulletinActivity.this, date + " " +time ,Toast.LENGTH_SHORT).show();


                            databaseReference.child(infoKey).child("InfoText").setValue(newInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(InformationBulletinActivity.this, "New Information Added", Toast.LENGTH_SHORT).show();
                                        databaseReference.child(infoKey).child("Date").setValue(date);
                                        databaseReference.child(infoKey).child("Time").setValue(time);
                                    }
                                    else
                                        Toast.makeText(InformationBulletinActivity.this, "New Information not added", Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();

                                }
                            });
                        }

                    }

                });



            }
        });


    }
}
