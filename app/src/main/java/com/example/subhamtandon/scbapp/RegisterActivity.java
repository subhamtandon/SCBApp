package com.example.subhamtandon.scbapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private Button buttonRegister;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), UserProfile.class));
        }

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);

        textViewSignin.setOnClickListener(this);

    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    public void registerUser(){

        final String firstName = editTextFirstName.getText().toString();

        final String lastName = editTextLastName.getText().toString();

        final String email = editTextEmail.getText().toString();

        String password = editTextPassword.getText().toString();

        String ready= "true";
        if (TextUtils.isEmpty(email)){

            //email is empty
            editTextEmail.setError(getString(R.string.error_field_required));
            ready = "false";

        }else if (!isEmailValid(email)) {
            editTextPassword.setError(getString(R.string.error_invalid_email));
            ready = "false";
        }

        if (TextUtils.isEmpty(password)){

            //password is empty
            editTextPassword.setError(getString(R.string.error_field_required));
            ready = "false";

        }

        if (TextUtils.isEmpty(firstName)){

            //firstName is empty
            editTextFirstName.setError("This field is required");
            ready = "false";

        }

        if (TextUtils.isEmpty(lastName)){

            //lastName is empty
            editTextLastName.setError("This field is required");
            ready = "false";

        }
        //if validations are ok
        if (ready.equals("true")) {
            progressDialog.setMessage("Registering User...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.dismiss();
                            if (task.isSuccessful()) {

                                UserInformation user = new UserInformation(
                                        firstName,
                                        lastName,
                                        email
                                );

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            Log.d("pass", "createUserWithEmail:success");
                                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                            startActivity(new Intent(getApplicationContext(),UserProfile.class));

                                        }else {

                                            Log.w("fail", "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(RegisterActivity.this, "Could not register. Please try again", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(firstName + " " + lastName)
                                        .build();

                                firebaseUser.updateProfile(profileUpdates);

                            } else {

                                if (task.getException() instanceof FirebaseAuthUserCollisionException){

                                    Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                }else {

                                    Log.w("fail", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Could not register. Please try again", Toast.LENGTH_SHORT).show();

                                }
                            }
                            progressDialog.dismiss();

                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {

        if(v == textViewSignin) {
            //will open login activity
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        if(v == buttonRegister){
            registerUser();
        }

    }
}
