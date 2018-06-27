package com.example.subhamtandon.scbapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Level1Fragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_level1, container, false);
                return rootView;
           }

}
