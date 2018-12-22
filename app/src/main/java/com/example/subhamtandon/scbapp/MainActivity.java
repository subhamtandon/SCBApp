package com.example.subhamtandon.scbapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private SignInButton buttonGoogle;

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;

    private static final int RC_SIGN_IN = 1;

    private GoogleApiClient mGoogleApiClient;

    ArrayAdapter<String> adapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();

        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {
            Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
            //setContentView(R.layout.activity_main);
        }

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            if (firebaseAuth.getCurrentUser().getEmail().equalsIgnoreCase("scbmch2018@gmail.com")) {
                finish();
                startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
            } else {

                /*databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("College Name");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null){
                            String collegeName = dataSnapshot.getValue(String.class);

                            if (collegeName.equalsIgnoreCase("SCB Medical College, Cuttack")){
                                //profile activity here
                                finish();
                                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                            }else {
                                finish();
                                startActivity(new Intent(getApplicationContext(), UserProfileNonSCB.class));
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, databaseError + "", Toast.LENGTH_SHORT).show();
                    }
                });*/

                finish();
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
            }
        }

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        textViewSignup = (TextView) findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        buttonGoogle = (SignInButton) findViewById(R.id.buttonGoogle);

        buttonGoogle.setOnClickListener(this);


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Toast.makeText(MainActivity.this, "You got an error", Toast.LENGTH_SHORT).show();

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

        return builder;
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

        progressDialog.setMessage("Signing in...");
        progressDialog.show();

    }
    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("info", "Google sign in failed", e);
                finish();
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        Log.d("info", "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("info", "signInWithCredential:success");
                            boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();

                            if (isNew){
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                View mView = getLayoutInflater().inflate(R.layout.college_name_autocompletetextview, null);

                                final AutoCompleteTextView collegeNameAutoCompleteTextView = mView.findViewById(R.id.collegeNameAutoCompleteTextView);

                                TextView otherCollegeTextview = mView.findViewById(R.id.otherCollegeTextview);

                                Button buttonCollegeName = (Button) mView.findViewById(R.id.buttonCollegeName);

                                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.collegeList));

                                collegeNameAutoCompleteTextView.setAdapter(adapter);

                                builder.setTitle("Enter College Name")
                                        .setCancelable(false);
                                builder.setView(mView);
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                                otherCollegeTextview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        collegeNameAutoCompleteTextView.setText("Other");
                                    }
                                });

                                buttonCollegeName.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (!collegeNameAutoCompleteTextView.getText().toString().isEmpty()) {
                                            FirebaseDatabase.getInstance().getReference("Users")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("collegeName")
                                                    .setValue(collegeNameAutoCompleteTextView.getText().toString());

                                            alertDialog.dismiss();
                                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                                            startActivity(intent);

                                            /*if (collegeNameAutoCompleteTextView.getText().toString().equalsIgnoreCase("SCB Medical College, Cuttack")){
                                                Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                                                startActivity(intent);
                                            }else {
                                                Intent intent = new Intent(getApplicationContext(), UserProfileNonSCB.class);
                                                startActivity(intent);
                                            }*/


                                        }else {
                                            Toast.makeText(MainActivity.this, "Please enter college Name", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else {
                                finish();
                                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("info", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    public void userLogin(View view){

        final String email = editTextEmail .getText().toString().trim();

        final String password = editTextPassword.getText().toString().trim();

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
        //if validations are ok
        if(ready.equals("true")) {
            progressDialog.setMessage("Logging in...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.dismiss();
                            if (task.isSuccessful()) {

                                if (email.equalsIgnoreCase("scbmch2018@gmail.com")  && password.equalsIgnoreCase("scb2018") ){

                                    finish();
                                    startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));

                                }else {

                                    Log.d("pass", "signInWithEmail:success");
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), UserProfile.class));
                                }

                            } else {

                                Log.w("fail", "signInWithEmail:failure", task.getException());

                            }

                        }
                    });
        }

    }
    public void signUpClicked(View view){
        Log.i("info","clicked");
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

    }

    @Override
    public void onClick(View v) {

        signIn();

    }
}
