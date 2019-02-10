package com.company.ssDev.que9;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {

    EditText editTextEmailReset;
    Button buttonReset;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        editTextEmailReset = findViewById(R.id.editTextEmailReset);
        buttonReset = findViewById(R.id.buttonReset);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ready = "true";
                String email = editTextEmailReset.getText().toString();
                if (TextUtils.isEmpty(email)){
                    editTextEmailReset.setError(getString(R.string.error_field_required));
                    ready = "false";
                }else if (!isEmailValid(email)) {
                    editTextEmailReset.setError(getString(R.string.error_invalid_email));
                    ready = "false";
                }

                if (ready.equals("true")){
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(PasswordResetActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordResetActivity.this, MainActivity.class));
                            }else {
                                Toast.makeText(PasswordResetActivity.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }
}
