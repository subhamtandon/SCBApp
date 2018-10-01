package com.example.subhamtandon.scbapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SCBSectionFragment extends Fragment {

    CardView firstProfessionalCard, secondProfessionalCard, thirdProfessionalPartOneCard, thirdProfessionalPartTwoCard;

    Intent intent;


    public SCBSectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scbsection, container, false);

        firstProfessionalCard = view.findViewById(R.id.firstProfessionalCard);
        secondProfessionalCard = view.findViewById(R.id.secondProfessionalCard);
        thirdProfessionalPartOneCard = view.findViewById(R.id.thirdProfessionalPartOneCard);
        thirdProfessionalPartTwoCard = view.findViewById(R.id.thirdProfessionalPartTwoCard);

        firstProfessionalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getContext(), Professional1stActivity.class);
                intent.putExtra("PROFESSIONAL", "First Professional");
                startActivity(intent);
            }
        });

        secondProfessionalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getContext(), Professional2ndActivity.class);
                intent.putExtra("PROFESSIONAL", "Second Professional");
                startActivity(intent);
            }
        });

        thirdProfessionalPartOneCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getContext(), Professional3rdActivity.class);
                intent.putExtra("PROFESSIONAL", "Third Professional Part-1");
                startActivity(intent);
            }
        });

        thirdProfessionalPartTwoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getContext(), Professional4thActivity.class);
                intent.putExtra("PROFESSIONAL", "Third Professional Part-2");
                startActivity(intent);
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button's click listener
                    FragmentTransaction ft= getFragmentManager().beginTransaction();
                    ft.replace(R.id.flMain, new HomeFragment());
                    ft.commit();
                    return true;
                }
                return false;
            }
        });
    }

}
