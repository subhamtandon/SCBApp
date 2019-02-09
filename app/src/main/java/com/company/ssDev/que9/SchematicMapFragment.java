package com.company.ssDev.que9;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchematicMapFragment extends Fragment {

    PhotoView photoView;

    public SchematicMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schematic_map, container, false);
        photoView = view.findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.schematicmapimage);
        return view;
    }

}
