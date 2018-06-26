package com.example.subhamtandon.scbapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    CardView scorecardCard, doctorCard, studyCard, questionBankCard;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        scorecardCard =(CardView)view.findViewById(R.id.scorecardCard);
        doctorCard =(CardView)view.findViewById(R.id.doctorCard);
        studyCard =(CardView)view.findViewById(R.id.studyCard);
        questionBankCard =(CardView)view.findViewById(R.id.questionBankCard);

        scorecardCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "No Scorecard", Toast.LENGTH_SHORT).show();
            }
        });

        doctorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.flMain, new DepartmentFragment());
                ft.commit();
            }
        });

        studyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.flMain, new StudyFragment());
                ft.commit();
            }
        });

        questionBankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.flMain, new QuestionBankFragment());
                ft.commit();
            }
        });


        return view;
    }
    public void showHome(View v){



    }

}
