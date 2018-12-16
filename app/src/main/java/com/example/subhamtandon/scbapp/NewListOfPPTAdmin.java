package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewListOfPPTAdmin extends AppCompatActivity {

    FloatingActionButton addNewPPT;
    RecyclerView recyclerViewNewPDF;
    String typeOfCard, typeOfPaperPresentation= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list_of_ppt_admin);

        addNewPPT = findViewById(R.id.addNewPPT);
        addNewPPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NewListOfPPTAdmin.this, "This feature coming soon ;)", Toast.LENGTH_SHORT).show();
                Intent next = new Intent(NewListOfPPTAdmin.this, NewPPTPDFAddingActivity.class);
                next.putExtra("TypeOfCard",typeOfCard);
                next.putExtra("TypeOfPaperPresentation",typeOfPaperPresentation);
                startActivity(next);
            }
        });

        recyclerViewNewPDF = findViewById(R.id.recyclerViewListOfNewPPTsPDFs);

        typeOfCard = getIntent().getStringExtra("TypeOfCard");
        if(typeOfCard.equals("PaperPresentation")){
            typeOfPaperPresentation = getIntent().getStringExtra("TypeOfPaperPresentation");

            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child("NewPPTPDFs").child(typeOfCard).child(typeOfPaperPresentation);
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if(dataSnapshot!=null) {

                        String pdfName = dataSnapshot.child("mName").getValue(String.class);
                        String url = dataSnapshot.child("mURL").getValue(String.class);
                        String uploadPDFID = dataSnapshot.getKey();

                        ((AdapterForPPTPDFListAdmin) recyclerViewNewPDF.getAdapter()).update(pdfName, url, uploadPDFID);

                    }
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

                    Toast.makeText(NewListOfPPTAdmin.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

            recyclerViewNewPDF.setLayoutManager(new LinearLayoutManager(NewListOfPPTAdmin.this));
            AdapterForPPTPDFListAdmin adapterForPPTPDFListAdmin = new AdapterForPPTPDFListAdmin(recyclerViewNewPDF, NewListOfPPTAdmin.this, new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), typeOfCard, typeOfPaperPresentation);
            //adapterForRecordsList.notifyDataSetChanged();
            recyclerViewNewPDF.setAdapter(adapterForPPTPDFListAdmin);
        }
        else {

            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child("NewPPTPDFs").child(typeOfCard);
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if(dataSnapshot!=null) {

                        String pdfName = dataSnapshot.child("mName").getValue(String.class);
                        String url = dataSnapshot.child("mURL").getValue(String.class);
                        String uploadPDFID = dataSnapshot.getKey();

                        ((AdapterForPPTPDFListAdmin) recyclerViewNewPDF.getAdapter()).update(pdfName, url, uploadPDFID);

                    }
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


                    Toast.makeText(NewListOfPPTAdmin.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

            recyclerViewNewPDF.setLayoutManager(new LinearLayoutManager(NewListOfPPTAdmin.this));
            AdapterForPPTPDFListAdmin adapterForPPTPDFListAdmin = new AdapterForPPTPDFListAdmin(recyclerViewNewPDF, NewListOfPPTAdmin.this, new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), typeOfCard, typeOfPaperPresentation);
            //adapterForRecordsList.notifyDataSetChanged();
            recyclerViewNewPDF.setAdapter(adapterForPPTPDFListAdmin);
        }
    }
}
