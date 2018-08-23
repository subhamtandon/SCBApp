package com.example.subhamtandon.scbapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PracticalsFragment extends Fragment {

    RecyclerView recyclerViewPracticals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_practicals, container, false);

        Bundle bundle = getArguments();
        if(bundle!=null){

            final String professional= bundle.getString("PROFESSIONAL");
            final String subject = bundle.getString("SUBJECT");

            Toast.makeText(getActivity(), professional + ":" +subject, Toast.LENGTH_SHORT).show();

            recyclerViewPracticals= rootView.findViewById(R.id.recyclerViewPracticals);

            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("Practicals");
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if(dataSnapshot!=null) {

                        String practicalName = dataSnapshot.child("mName").getValue(String.class);
                        String url = dataSnapshot.child("mURL").getValue(String.class);
                        String uploadPDFID = dataSnapshot.getKey();

                        ((AdapterForUserPracticals) recyclerViewPracticals.getAdapter()).update(practicalName, url, uploadPDFID);


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

            recyclerViewPracticals.setLayoutManager(new LinearLayoutManager(getContext()));
            AdapterForUserPracticals adapterForUserPracticals = new AdapterForUserPracticals(recyclerViewPracticals, getContext(), professional, subject);
            recyclerViewPracticals.setAdapter(adapterForUserPracticals);
        }

        return rootView;
    }
}
