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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private SignInButton buttonGoogle;

    private FirebaseAuth firebaseAuth;

    private static final int RC_SIGN_IN = 1;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
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
                            finish();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
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

        String email = editTextEmail .getText().toString().trim();

        String password = editTextPassword.getText().toString().trim();

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

                                Log.d("pass", "signInWithEmail:success");
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

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
