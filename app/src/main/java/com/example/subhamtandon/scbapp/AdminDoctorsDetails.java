package com.example.subhamtandon.scbapp;

import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminDoctorsDetails extends AppCompatActivity {

    Button addNewAssistantProfessor, addNewAssociateProfessor, addNewHodName;
    ImageView editHodName;
    DatabaseReference databaseReferenceHod, databaseReferenceAssistantProfessor, databaseReferenceAssociateProfessor;
    RecyclerView recyclerViewAssistantProfessors, recyclerViewAssociateProfessors;
    TextView hodNameTextView;
    String departmentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctors_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Doctors Details");
        }

        departmentName = getIntent().getStringExtra("DEPARTMENT NAME");
        Toast.makeText(this, departmentName, Toast.LENGTH_SHORT).show();

        addNewAssistantProfessor = findViewById(R.id.addNewAssistantProfessor);
        addNewAssociateProfessor = findViewById(R.id.addNewAssociateProfessor);
        addNewHodName = findViewById(R.id.addNewHodName);
        editHodName = findViewById(R.id.editHodName);
        hodNameTextView = findViewById(R.id.hodNameTextView);
        recyclerViewAssistantProfessors = findViewById(R.id.recyclerViewAssistantProfessors);
        recyclerViewAssociateProfessors = findViewById(R.id.recyclerViewAssociateProfessors);

        databaseReferenceHod = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("HOD");
        databaseReferenceAssistantProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Assistant Professor");
        databaseReferenceAssociateProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Associate Professor");

        databaseReferenceHod.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    String hodName = dataSnapshot.getValue(String.class);
                    //Log.d("hodName", hodName);
                    hodNameTextView.setText(hodName);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReferenceAssistantProfessor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String assistantProfessorName = ds.getValue(String.class);
                    String assistantProfessorId = ds.getKey();
                    ((AdapterForAdminAssistantProfessors) recyclerViewAssistantProfessors.getAdapter()).update(assistantProfessorName, assistantProfessorId);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerViewAssistantProfessors.setLayoutManager(new LinearLayoutManager(this));
        AdapterForAdminAssistantProfessors adapterForAdminAssistantProfessors = new AdapterForAdminAssistantProfessors(recyclerViewAssistantProfessors, AdminDoctorsDetails.this, new ArrayList<String>(), new ArrayList<String>(), departmentName);
        recyclerViewAssistantProfessors.setAdapter(adapterForAdminAssistantProfessors);

        databaseReferenceAssociateProfessor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String associateProfessorName = ds.getValue(String.class);
                    String associateProfessorId = ds.getKey();
                    ((AdapterForAdminAssociateProfessors) recyclerViewAssociateProfessors.getAdapter()).update(associateProfessorName, associateProfessorId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerViewAssociateProfessors.setLayoutManager(new LinearLayoutManager(this));
        AdapterForAdminAssociateProfessors adapterForAdminAssociateProfessors = new AdapterForAdminAssociateProfessors(recyclerViewAssociateProfessors, AdminDoctorsDetails.this, new ArrayList<String>(), new ArrayList<String>(), departmentName);
        recyclerViewAssociateProfessors.setAdapter(adapterForAdminAssociateProfessors);

        addNewHodName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminDoctorsDetails.this);
                View mView = getLayoutInflater().inflate(R.layout.enter_doctors_name, null);

                final TextInputEditText textInputEditTextName = mView.findViewById(R.id.textInputEditTextName);
                Button enterNameButton = mView.findViewById(R.id.enterNameButton);

                mBuilder.setCancelable(false);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                enterNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!textInputEditTextName.getText().toString().isEmpty()){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();
                            //final String id = databaseReferenceHod.push().getKey();
                            databaseReferenceHod.setValue(textInputEditTextName.getText().toString());
                            dialog.dismiss();

                        }else {
                            Toast.makeText(AdminDoctorsDetails.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        addNewAssistantProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminDoctorsDetails.this);
                View mView = getLayoutInflater().inflate(R.layout.enter_doctors_name, null);

                final TextInputEditText textInputEditTextName = mView.findViewById(R.id.textInputEditTextName);
                Button enterNameButton = mView.findViewById(R.id.enterNameButton);

                mBuilder.setCancelable(false);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                enterNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!textInputEditTextName.getText().toString().isEmpty()){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();
                            final String id = databaseReferenceAssistantProfessor.push().getKey();
                            databaseReferenceAssistantProfessor.child(id).setValue(textInputEditTextName.getText().toString());
                            dialog.dismiss();
                            reloadActivity();
                        }else {
                            Toast.makeText(AdminDoctorsDetails.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        addNewAssociateProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminDoctorsDetails.this);
                View mView = getLayoutInflater().inflate(R.layout.enter_doctors_name, null);

                final TextInputEditText textInputEditTextName = mView.findViewById(R.id.textInputEditTextName);
                Button enterNameButton = mView.findViewById(R.id.enterNameButton);

                mBuilder.setCancelable(false);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                enterNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!textInputEditTextName.getText().toString().isEmpty()){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();
                            final String id = databaseReferenceAssociateProfessor.push().getKey();
                            databaseReferenceAssociateProfessor.child(id).setValue(textInputEditTextName.getText().toString());
                            dialog.dismiss();
                            reloadActivity();
                        }else {
                            Toast.makeText(AdminDoctorsDetails.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void reloadActivity(){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
