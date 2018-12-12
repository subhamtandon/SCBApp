package com.example.subhamtandon.scbapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    CardView scorecardCard, mcqCard, studyCard, questionBankCard, journalCard, paperPresentationCard, quizCard, mnemonicsCard;
    RecyclerView recyclerViewVideoCategories, recyclerViewMedicalRelatedPictures, recyclerViewPPT;
    ArrayList<String> listOfNamesOfVideoCategories, listOfMedicalRelatedPicturesUrls ;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        //scorecardCard =(CardView)view.findViewById(R.id.scorecardCard);
        mcqCard = view.findViewById(R.id.mcqCard);
        journalCard = view.findViewById(R.id.journalCard);
        paperPresentationCard = view.findViewById(R.id.paperPresentationCard);
        quizCard = view.findViewById(R.id.quizCard);
        mnemonicsCard = view.findViewById(R.id.mnemonicsCard);
        //studyCard =(CardView)view.findViewById(R.id.studyCard);
        //questionBankCard =(CardView)view.findViewById(R.id.questionBankCard);
        recyclerViewVideoCategories = view.findViewById(R.id.recyclerViewVideoCategories);
        recyclerViewMedicalRelatedPictures = view.findViewById(R.id.recyclerViewMedicalRelatedPictures);
        recyclerViewPPT = view.findViewById(R.id.recyclerViewPPT);
        listOfNamesOfVideoCategories = new ArrayList<>();
        listOfMedicalRelatedPicturesUrls = new ArrayList<>();
        listOfNamesOfVideoCategories.add("Educational");
        listOfNamesOfVideoCategories.add("Games");
        listOfNamesOfVideoCategories.add("Pshycology");
        listOfNamesOfVideoCategories.add("Nothing");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewVideoCategories.setLayoutManager(linearLayoutManager);
        AdapterForVideoCategories adapterForVideoCategories = new AdapterForVideoCategories(recyclerViewVideoCategories, getContext(),listOfNamesOfVideoCategories );
        recyclerViewVideoCategories.setAdapter(adapterForVideoCategories);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Medical Related Pictures");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null) {


                    String url = dataSnapshot.getValue(String.class);
                    //String uploadPDFID = dataSnapshot.getKey();

                    ((AdapterForMedicalRelatedPictures) recyclerViewMedicalRelatedPictures.getAdapter()).update(url);

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

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewMedicalRelatedPictures.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        AdapterForMedicalRelatedPictures adapterForMedicalRelatedPictures = new AdapterForMedicalRelatedPictures(recyclerViewMedicalRelatedPictures, getContext(),new ArrayList<String>());
        //adapterForRecordsList.notifyDataSetChanged();
        recyclerViewMedicalRelatedPictures.setAdapter(adapterForMedicalRelatedPictures);


        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("App").child("PPTPDFs");
        databaseReference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null) {
                    //for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    UploadPPTPDF retriveUploadPPTPDF = dataSnapshot.getValue(UploadPPTPDF.class);
                    String id = dataSnapshot.getKey();
                    //String pdfUrl = dataSnapshot.child("mPDFURL").getValue(String.class);
                    //Toast.makeText(AdminPPTActivity.this, retriveUploadPPTPDF.mPDFURL, Toast.LENGTH_SHORT).show();
                    //String uploadPDFID = dataSnapshot.getKey();

                    ((AdapterForPPTUser) recyclerViewPPT.getAdapter()).update(retriveUploadPPTPDF.mName,retriveUploadPPTPDF.mThumbnailURL, retriveUploadPPTPDF.mPDFURL, id );
                    //}


                    //String url = dataSnapshot.getValue(String.class);
                    //String uploadPDFID = dataSnapshot.getKey();

                    //((AdapterForListOfPPTsAdmin) listOfPPT.getAdapter()).update(url);

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

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewPPT.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        AdapterForPPTUser adapterForPPTUser = new AdapterForPPTUser(recyclerViewPPT, getContext(),new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
        //adapterForRecordsList.notifyDataSetChanged();
        recyclerViewPPT.setAdapter(adapterForPPTUser);


        mcqCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfessionalsActivity.class);
                startActivity(intent);
            }
        });

        journalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This feature coming soon :)", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), ProfessionalsActivity.class);
//                startActivity(intent);
            }
        });

        paperPresentationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This feature coming soon :)", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), ProfessionalsActivity.class);
//                startActivity(intent);
            }
        });

        quizCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This feature coming soon :)", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), ProfessionalsActivity.class);
//                startActivity(intent);
            }
        });

        mnemonicsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This feature coming soon :)", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), ProfessionalsActivity.class);
//                startActivity(intent);
            }
        });




        return view;
    }
    public void showHome(View v){



    }

}
