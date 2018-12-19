package com.example.subhamtandon.scbapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentFragment extends Fragment {

    Spinner spinnerDepartmentsUser;
    DatabaseReference databaseReferenceHod, databaseReferenceAssistantProfessor, databaseReferenceAssociateProfessor, databaseReferenceProfessor;
    RecyclerView recyclerViewAssistantProfessors, recyclerViewAssociateProfessors, recyclerViewProfessors;
    TextView hodNameTextView, hodDescription;

    public DepartmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_department, container, false);

        spinnerDepartmentsUser = view.findViewById(R.id.spinnerDepartmentsUser);
        recyclerViewProfessors = view.findViewById(R.id.recyclerViewProfessors);
        recyclerViewAssistantProfessors = view.findViewById(R.id.recyclerViewAssistantProfessors);
        recyclerViewAssociateProfessors = view.findViewById(R.id.recyclerViewAssociateProfessors);
        hodNameTextView = view.findViewById(R.id.hodNameTextView);
        hodDescription = view.findViewById(R.id.hodDescription);

        ((UserProfile) getActivity())
                .setActionBarTitle("Doctors' Details");

        //databaseReferenceHod = FirebaseDatabase.getInstance().getReference("App").child("Departments").child("Anatomy").child("HOD");
        //databaseReferenceAssistantProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child("Anatomy").child("Assistant Professor");
        //databaseReferenceAssociateProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child("Anatomy").child("Associate Professor");

        /*databaseReferenceHod.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    String hodName = dataSnapshot.getValue(String.class);
                    //
                    // +Log.d("hodName", hodName);
                    hodNameTextView.setText(hodName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReferenceAssistantProfessor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String assistantProfessorName = ds.getValue(String.class);
                    String assistantProfessorId = ds.getKey();
                    ((AdapterForUserAssistantProfessors) recyclerViewAssistantProfessors.getAdapter()).update(assistantProfessorName, assistantProfessorId);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerViewAssistantProfessors.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterForUserAssistantProfessors adapterForUserAssistantProfessors = new AdapterForUserAssistantProfessors(recyclerViewAssistantProfessors, getContext(), new ArrayList<String>(), new ArrayList<String>(), spinnerDepartmentsUser.getSelectedItem().toString());
        recyclerViewAssistantProfessors.setAdapter(adapterForUserAssistantProfessors);

        databaseReferenceAssociateProfessor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String associateProfessorName = ds.getValue(String.class);
                    String associateProfessorId = ds.getKey();
                    ((AdapterForUserAssociateProfessors) recyclerViewAssociateProfessors.getAdapter()).update(associateProfessorName, associateProfessorId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerViewAssociateProfessors.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterForUserAssociateProfessors adapterForUserAssociateProfessors = new AdapterForUserAssociateProfessors(recyclerViewAssociateProfessors, getContext(), new ArrayList<String>(), new ArrayList<String>(), spinnerDepartmentsUser.getSelectedItem().toString());
        recyclerViewAssociateProfessors.setAdapter(adapterForUserAssociateProfessors);*/

        spinnerDepartmentsUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String department  = spinnerDepartmentsUser.getSelectedItem().toString();
                Log.d("spinneritem", parent.getItemAtPosition(position).toString());
                Log.d("getSelectedItem", department);
                hodNameTextView.setText("HOD");
                hodDescription.setVisibility(View.GONE);

                databaseReferenceHod = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(department).child("HOD");
                databaseReferenceAssistantProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(department).child("Assistant Professor");
                databaseReferenceAssociateProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(department).child("Associate Professor");
                databaseReferenceProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(department).child("Professor");

                Log.d("databasereference", databaseReferenceHod+"");
                databaseReferenceHod.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("datasnapshot", dataSnapshot+"");
                        if (dataSnapshot.getValue() != null){
                            //Log.d("datasnapshot", department+dataSnapshot);
                            DoctorDetails doctorDetails = dataSnapshot.getValue(DoctorDetails.class);
                            hodNameTextView.setText(doctorDetails.name);
                            if (doctorDetails.getDescription().equalsIgnoreCase("Empty")) {
                                hodDescription.setVisibility(View.GONE);
                            } else {
                                hodDescription.setVisibility(View.VISIBLE);
                                hodDescription.setText(doctorDetails.description);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                databaseReferenceProfessor.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            String professorId = ds.getKey();
                            DoctorDetails doctorDetails1 = ds.getValue(DoctorDetails.class);
                            ((AdapterForUserProfessors) recyclerViewProfessors.getAdapter()).update(doctorDetails1, professorId);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                recyclerViewProfessors.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterForUserProfessors adapterForUserProfessors = new AdapterForUserProfessors(recyclerViewProfessors, getContext(), new ArrayList<DoctorDetails>(), new ArrayList<String>(), parent.getItemAtPosition(position).toString());
                recyclerViewProfessors.setAdapter(adapterForUserProfessors);

                databaseReferenceAssistantProfessor.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            String assistantProfessorId = ds.getKey();
                            DoctorDetails doctorDetails2 = ds.getValue(DoctorDetails.class);
                            ((AdapterForUserAssistantProfessors) recyclerViewAssistantProfessors.getAdapter()).update(doctorDetails2, assistantProfessorId);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                recyclerViewAssistantProfessors.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterForUserAssistantProfessors adapterForUserAssistantProfessors1 = new AdapterForUserAssistantProfessors(recyclerViewAssistantProfessors, getContext(), new ArrayList<DoctorDetails>(), new ArrayList<String>(), parent.getItemAtPosition(position).toString());
                recyclerViewAssistantProfessors.setAdapter(adapterForUserAssistantProfessors1);

                databaseReferenceAssociateProfessor.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            String associateProfessorId = ds.getKey();
                            DoctorDetails doctorDetails3 = ds.getValue(DoctorDetails.class);
                            ((AdapterForUserAssociateProfessors) recyclerViewAssociateProfessors.getAdapter()).update(doctorDetails3, associateProfessorId);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                recyclerViewAssociateProfessors.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterForUserAssociateProfessors adapterForUserAssociateProfessors1 = new AdapterForUserAssociateProfessors(recyclerViewAssociateProfessors, getContext(), new ArrayList<DoctorDetails>(), new ArrayList<String>(), parent.getItemAtPosition(position).toString());
                recyclerViewAssociateProfessors.setAdapter(adapterForUserAssociateProfessors1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
    public void reloadActivity() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

}
