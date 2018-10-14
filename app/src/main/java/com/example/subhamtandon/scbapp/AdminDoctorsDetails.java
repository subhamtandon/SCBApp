package com.example.subhamtandon.scbapp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

    Button addNewAssistantProfessor, addNewAssociateProfessor, addNewHodName, addNewProfessor;
    ImageView editHodName;
    DatabaseReference databaseReferenceHod, databaseReferenceAssistantProfessor, databaseReferenceAssociateProfessor, databaseReferenceProfessor;
    RecyclerView recyclerViewAssistantProfessors, recyclerViewAssociateProfessors, recyclerViewProfessors;
    TextView hodNameTextView, hodDescription;
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
        addNewProfessor = findViewById(R.id.addNewProfessor);
        editHodName = findViewById(R.id.editHodName);
        hodNameTextView = findViewById(R.id.hodNameTextView);
        hodDescription = findViewById(R.id.hodDescription);
        recyclerViewAssistantProfessors = findViewById(R.id.recyclerViewAssistantProfessors);
        recyclerViewAssociateProfessors = findViewById(R.id.recyclerViewAssociateProfessors);
        recyclerViewProfessors = findViewById(R.id.recyclerViewProfessors);

        databaseReferenceHod = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("HOD");
        databaseReferenceAssistantProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Assistant Professor");
        databaseReferenceAssociateProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Associate Professor");
        databaseReferenceProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Professor");

        databaseReferenceHod.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DoctorDetails doctorDetails = dataSnapshot.getValue(DoctorDetails.class);
                    //String hodName = dataSnapshot.getValue(String.class);
                    //Log.d("hodName", hodName);
                    hodNameTextView.setText(doctorDetails.name);
                    if (doctorDetails.getDescription().equalsIgnoreCase("Empty")) {
                        hodDescription.setVisibility(View.GONE);
                    } else {
                        hodDescription.setText(doctorDetails.description);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminDoctorsDetails.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        databaseReferenceProfessor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String professorId = ds.getKey();
                    DoctorDetails doctorDetails1 = ds.getValue(DoctorDetails.class);
                    ((AdapterForAdminProfessors) recyclerViewProfessors.getAdapter()).update(doctorDetails1, professorId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminDoctorsDetails.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewProfessors.setLayoutManager(new LinearLayoutManager(this));
        AdapterForAdminProfessors adapterForAdminProfessors = new AdapterForAdminProfessors(recyclerViewProfessors, AdminDoctorsDetails.this, new ArrayList<DoctorDetails>(), new ArrayList<String>(), departmentName);
        recyclerViewProfessors.setAdapter(adapterForAdminProfessors);

        databaseReferenceAssistantProfessor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String assistantProfessorId = ds.getKey();
                    DoctorDetails doctorDetails2 = ds.getValue(DoctorDetails.class);
                    ((AdapterForAdminAssistantProfessors) recyclerViewAssistantProfessors.getAdapter()).update(doctorDetails2, assistantProfessorId);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminDoctorsDetails.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewAssistantProfessors.setLayoutManager(new LinearLayoutManager(this));
        AdapterForAdminAssistantProfessors adapterForAdminAssistantProfessors = new AdapterForAdminAssistantProfessors(recyclerViewAssistantProfessors, AdminDoctorsDetails.this, new ArrayList<DoctorDetails>(), new ArrayList<String>(), departmentName);
        recyclerViewAssistantProfessors.setAdapter(adapterForAdminAssistantProfessors);

        databaseReferenceAssociateProfessor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String associateProfessorId = ds.getKey();
                    DoctorDetails doctorDetails3 = ds.getValue(DoctorDetails.class);
                    ((AdapterForAdminAssociateProfessors) recyclerViewAssociateProfessors.getAdapter()).update(doctorDetails3, associateProfessorId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminDoctorsDetails.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewAssociateProfessors.setLayoutManager(new LinearLayoutManager(this));
        AdapterForAdminAssociateProfessors adapterForAdminAssociateProfessors = new AdapterForAdminAssociateProfessors(recyclerViewAssociateProfessors, AdminDoctorsDetails.this, new ArrayList<DoctorDetails>(), new ArrayList<String>(), departmentName);
        recyclerViewAssociateProfessors.setAdapter(adapterForAdminAssociateProfessors);

        addNewHodName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminDoctorsDetails.this);
                View mView = getLayoutInflater().inflate(R.layout.enter_doctors_name, null);

                final TextInputEditText textInputEditTextName = mView.findViewById(R.id.textInputEditTextName);
                final TextInputEditText textInputEditTextDescription = mView.findViewById(R.id.textInputEditTextDescription);
                Button enterNameButton = mView.findViewById(R.id.enterNameButton);
                Button cancelButton = mView.findViewById(R.id.cancelButton);

                mBuilder.setCancelable(false);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                enterNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ready = "true";
                        String name = textInputEditTextName.getText().toString();
                        String description = textInputEditTextDescription.getText().toString();
                        if (TextUtils.isEmpty(name)){
                            textInputEditTextName.setError("error_field_required");
                            ready = "false";
                        }
                        if (TextUtils.isEmpty(description)){
                            description = "Empty";
                        }
                        if (ready.equalsIgnoreCase("true")){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();

                            DoctorDetails doctorDetails = new DoctorDetails(
                                    name,
                                    description
                            );
                            databaseReferenceHod.setValue(doctorDetails);
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        addNewProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminDoctorsDetails.this);
                View mView = getLayoutInflater().inflate(R.layout.enter_doctors_name, null);

                final TextInputEditText textInputEditTextName = mView.findViewById(R.id.textInputEditTextName);
                final TextInputEditText textInputEditTextDescription = mView.findViewById(R.id.textInputEditTextDescription);
                Button enterNameButton = mView.findViewById(R.id.enterNameButton);
                Button cancelButton = mView.findViewById(R.id.cancelButton);

                mBuilder.setCancelable(false);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                enterNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ready = "true";
                        String name = textInputEditTextName.getText().toString();
                        String description = textInputEditTextDescription.getText().toString();
                        if (TextUtils.isEmpty(name)){
                            textInputEditTextName.setError("error_field_required");
                            ready = "false";
                        }
                        if (TextUtils.isEmpty(description)){
                            description = "Empty";
                        }
                        if (ready.equalsIgnoreCase("true")){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();
                            final String id = databaseReferenceProfessor.push().getKey();
                            DoctorDetails doctorDetails = new DoctorDetails(
                                    name,
                                    description
                            );
                            databaseReferenceProfessor.child(id).setValue(doctorDetails);
                            dialog.dismiss();
                            reloadActivity();
                        }
                        /*if (!textInputEditTextName.getText().toString().isEmpty()){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();
                            final String id = databaseReferenceProfessor.push().getKey();
                            databaseReferenceProfessor.child(id).setValue(textInputEditTextName.getText().toString());
                            dialog.dismiss();
                            reloadActivity();
                        }else {
                            Toast.makeText(AdminDoctorsDetails.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                        }*/
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
                final TextInputEditText textInputEditTextDescription = mView.findViewById(R.id.textInputEditTextDescription);
                Button enterNameButton = mView.findViewById(R.id.enterNameButton);
                Button cancelButton = mView.findViewById(R.id.cancelButton);

                mBuilder.setCancelable(false);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                enterNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ready = "true";
                        String name = textInputEditTextName.getText().toString();
                        String description = textInputEditTextDescription.getText().toString();
                        if (TextUtils.isEmpty(name)){
                            textInputEditTextName.setError("error_field_required");
                            ready = "false";
                        }
                        if (TextUtils.isEmpty(description)){
                            description = "Empty";
                        }
                        if (ready.equalsIgnoreCase("true")){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();
                            final String id = databaseReferenceAssistantProfessor.push().getKey();
                            DoctorDetails doctorDetails = new DoctorDetails(
                                    name,
                                    description
                            );
                            databaseReferenceAssistantProfessor.child(id).setValue(doctorDetails);
                            dialog.dismiss();
                            reloadActivity();
                        }
                        /*if (!textInputEditTextName.getText().toString().isEmpty()){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();
                            final String id = databaseReferenceAssistantProfessor.push().getKey();
                            databaseReferenceAssistantProfessor.child(id).setValue(textInputEditTextName.getText().toString());
                            dialog.dismiss();
                            reloadActivity();
                        }else {
                            Toast.makeText(AdminDoctorsDetails.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                        }*/
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
                final TextInputEditText textInputEditTextDescription = mView.findViewById(R.id.textInputEditTextDescription);
                Button enterNameButton = mView.findViewById(R.id.enterNameButton);
                Button cancelButton = mView.findViewById(R.id.cancelButton);

                mBuilder.setCancelable(false);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                enterNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ready = "true";
                        String name = textInputEditTextName.getText().toString();
                        String description = textInputEditTextDescription.getText().toString();
                        if (TextUtils.isEmpty(name)){
                            textInputEditTextName.setError("error_field_required");
                            ready = "false";
                        }
                        if (TextUtils.isEmpty(description)){
                            description = "Empty";
                        }
                        if (ready.equalsIgnoreCase("true")){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();
                            final String id = databaseReferenceAssociateProfessor.push().getKey();
                            DoctorDetails doctorDetails = new DoctorDetails(
                                    name,
                                    description
                            );
                            databaseReferenceAssociateProfessor.child(id).setValue(doctorDetails);
                            dialog.dismiss();
                            reloadActivity();
                        }
                        /*if (!textInputEditTextName.getText().toString().isEmpty()){
                            Toast.makeText(AdminDoctorsDetails.this, "Name added successfully", Toast.LENGTH_SHORT).show();
                            final String id = databaseReferenceAssociateProfessor.push().getKey();
                            databaseReferenceAssociateProfessor.child(id).setValue(textInputEditTextName.getText().toString());
                            dialog.dismiss();
                            reloadActivity();
                        }else {
                            Toast.makeText(AdminDoctorsDetails.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                        }*/
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
