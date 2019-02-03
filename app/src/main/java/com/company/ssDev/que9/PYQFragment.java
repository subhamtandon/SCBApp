package com.company.ssDev.que9;

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

public class PYQFragment extends Fragment {

    RecyclerView recyclerViewPYQs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pyq, container, false);

        Bundle bundle = getArguments();
        if(bundle!=null){

            final String professional= bundle.getString("PROFESSIONAL");
            final String subject = bundle.getString("SUBJECT");

            Toast.makeText(getActivity(), professional + ":" + subject, Toast.LENGTH_SHORT).show();

            recyclerViewPYQs= rootView.findViewById(R.id.recyclerViewPYQs);

            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("PYQs");
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if(dataSnapshot!=null) {

                        String PYQName = dataSnapshot.child("mName").getValue(String.class);
                        String url = dataSnapshot.child("mURL").getValue(String.class);
                        String uploadPDFID = dataSnapshot.getKey();

                        ((AdapterForUserPYQs) recyclerViewPYQs.getAdapter()).update(PYQName, url, uploadPDFID);

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

            recyclerViewPYQs.setLayoutManager(new LinearLayoutManager(getContext()));
            AdapterForUserPYQs adapterForUserPYQs = new AdapterForUserPYQs(recyclerViewPYQs, getContext(), professional, subject);
            recyclerViewPYQs.setAdapter(adapterForUserPYQs);
        }

        return rootView;
    }
}
