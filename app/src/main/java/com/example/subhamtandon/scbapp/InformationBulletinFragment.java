package com.example.subhamtandon.scbapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationBulletinFragment extends Fragment {

    RecyclerView recyclerViewInformationBulletinUser;

    Calendar calendar;
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;


    public InformationBulletinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information_bulletin, container, false);
        recyclerViewInformationBulletinUser = view.findViewById(R.id.recyclerViewInformationBulletinUser);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Information");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String info = ds.child("infoText").getValue(String.class);
                    String infoKey = ds.getKey();
                    String dateOfInfo = ds.child("infoDate").getValue(String.class);
                    String timeOfInfo = ds.child("infoTime").getValue(String.class);

                    ((AdapterForUserInformationBulletin) recyclerViewInformationBulletinUser.getAdapter()).update(info, infoKey, dateOfInfo, timeOfInfo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerViewInformationBulletinUser.setLayoutManager(mLayoutManager);
        AdapterForUserInformationBulletin adapterForUserInformationBulletin = new AdapterForUserInformationBulletin(recyclerViewInformationBulletinUser, getContext(),new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
        recyclerViewInformationBulletinUser.setAdapter(adapterForUserInformationBulletin);

        return view;
    }

}