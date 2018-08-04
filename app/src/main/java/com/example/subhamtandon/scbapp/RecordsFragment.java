package com.example.subhamtandon.scbapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class RecordsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_records, container, false);

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
