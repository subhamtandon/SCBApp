package com.example.subhamtandon.scbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import java.util.zip.Inflater;

public class NewPPTsAndPDFsTypes extends AppCompatActivity {

    CardView journalCardAdmin, paperPresentationCardAdmin, quizCardAdmin, mnemonicsCardAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ppts_and_pdfs_types);

        journalCardAdmin = findViewById(R.id.journalCardAdmin);
        paperPresentationCardAdmin = findViewById(R.id.paperPresentationCardAdmin);
        quizCardAdmin = findViewById(R.id.quizCardAdmin);
        mnemonicsCardAdmin = findViewById(R.id.mnemonicsCardAdmin);

        journalCardAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewPPTsAndPDFsTypes.this, NewListOfPPTAdmin.class);
                startActivity(i);
            }
        });

        paperPresentationCardAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence options[] = new CharSequence[] {"Clinical", "NonClinical"};

                AlertDialog.Builder builder = new AlertDialog.Builder(NewPPTsAndPDFsTypes.this);
                builder.setCancelable(false);
                builder.setTitle("Select your option:");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on options[which]
                        if(which == 0){
                            Intent i = new Intent(NewPPTsAndPDFsTypes.this, NewListOfPPTAdmin.class);
                            i.putExtra("TypeOfPaperPresentation", "Clinical");
                            startActivity(i);
                        }
                        else if(which == 1){
                            Intent i = new Intent(NewPPTsAndPDFsTypes.this, NewListOfPPTAdmin.class);
                            i.putExtra("TypeOfPaperPresentation", "NonClinical");
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(NewPPTsAndPDFsTypes.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();

            }
        });

        quizCardAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewPPTsAndPDFsTypes.this, NewListOfPPTAdmin.class);
                startActivity(i);
            }
        });

        mnemonicsCardAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewPPTsAndPDFsTypes.this, NewListOfPPTAdmin.class);
                startActivity(i);
            }
        });

    }
}
