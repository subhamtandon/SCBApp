package com.example.subhamtandon.scbapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MCQsFragment extends Fragment{

    RecyclerView recyclerViewChapters;
    RecyclerView.Adapter adapterChapters;
    List<ListItemChapters> listItemChapters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mcqs, container, false);

        recyclerViewChapters =(RecyclerView)rootView.findViewById(R.id.recyclerViewChapters);
        recyclerViewChapters.setHasFixedSize(true);
        recyclerViewChapters.setLayoutManager(new LinearLayoutManager(getContext()));

        listItemChapters = new ArrayList<>();

        for(int i =0; i<=10; i++){
            ListItemChapters listItemChapter = new ListItemChapters("Chapter "+ (i+1), "Description is coming soon");
            listItemChapters.add(listItemChapter);
        }

        adapterChapters = new AdapterForBinding(listItemChapters, getContext());

        recyclerViewChapters.setAdapter(adapterChapters);


        Bundle bundle = getArguments();
        if(bundle!=null){

            final String professional= bundle.getString("PROFESSIONAL");
            final String subject = bundle.getString("SUBJECT");

            Toast.makeText(getActivity(), professional, Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), subject , Toast.LENGTH_SHORT).show();
        }


        return rootView;
    }

}
