package com.example.subhamtandon.scbapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
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

    CardView scorecardCard, mcqCard, studyCard, questionBankCard;
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
        mcqCard =(CardView)view.findViewById(R.id.mcqCard);
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



        /*

        scorecardCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "No Scorecard", Toast.LENGTH_SHORT).show();
            }
        });
        */

        mcqCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfessionalsActivity.class);
                startActivity(intent);
            }
        });
        /*

        studyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentTransaction ft= getFragmentManager().beginTransaction();
                //ft.replace(R.id.flMain, new StudyFragment());
                //ft.commit();
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View mView = getLayoutInflater().inflate(R.layout.activity_professionals_spinner, null);

                builder.setTitle("Choose your Professional");

                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.professionalsList));

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                mSpinner.setAdapter(adapter);

                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final Intent intent;
                        switch (position){
                            case 1:
                                intent = new Intent(getContext(), Professional1stActivity.class);
                                intent.putExtra("PROFESSIONAL","1st Professional");
                                startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(getContext(), Professional2ndActivity.class);
                                intent.putExtra("PROFESSIONAL","2nd Professional");
                                startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(getContext(), Professional3rdActivity.class);
                                intent.putExtra("PROFESSIONAL","3rd Professional part 1");
                                startActivity(intent);
                                break;
                            case 4:
                                intent = new Intent(getContext(), Professional4thActivity.class);
                                intent.putExtra("PROFESSIONAL","3rd Professional part 2");
                                startActivity(intent);
                                break;
                            default:
                                Toast.makeText(getContext(), "Choose Your Professional", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                        Toast.makeText(getContext(), "Choose Your Professional", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setView(mView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        /*

        questionBankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.flMain, new QuestionBankFragment());
                ft.commit();
            }
        });
        */




        return view;
    }
    public void showHome(View v){



    }

}
