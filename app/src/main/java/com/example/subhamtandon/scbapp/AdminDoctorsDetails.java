package com.example.subhamtandon.scbapp;

import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDoctorsDetails extends AppCompatActivity {

    Button addNewAssistantProfessor, addNewAssociateProfessor, addNewHodName;
    ImageView editHodName;
    DatabaseReference databaseReferenceHod, databaseReferenceAssistantProfessor, databaseReferenceAssociateProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctors_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Doctors Details");
        }

        String departmentName = getIntent().getStringExtra("DEPARTMENT NAME");
        Toast.makeText(this, departmentName, Toast.LENGTH_SHORT).show();

        addNewAssistantProfessor = findViewById(R.id.addNewAssistantProfessor);
        addNewAssociateProfessor = findViewById(R.id.addNewAssociateProfessor);
        addNewHodName = findViewById(R.id.addNewHodName);
        editHodName = findViewById(R.id.editHodName);

        databaseReferenceHod = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("HOD");
        databaseReferenceAssistantProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Assistant Professor");
        databaseReferenceAssociateProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Associate Professor");

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
                            final String id = databaseReferenceHod.push().getKey();
                            databaseReferenceHod.child(id).setValue(textInputEditTextName.getText().toString());
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
                        }else {
                            Toast.makeText(AdminDoctorsDetails.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
