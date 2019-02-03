package com.company.ssDev.que9;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudyFragment extends Fragment {

    CardView Professional1stCard, Professional2ndCard, Professional3rdCard, Professional4thCard;

    public StudyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View mView = getLayoutInflater().inflate(R.layout.activity_professionals_spinner, null);

                builder.setTitle("Choose your Professional");

                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.professionalsList));

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                mSpinner.setAdapter(adapter);

                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final Intent intent;
                        switch (position){
                            case 1:
                                intent = new Intent(getContext(), Professional1stActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(getContext(), Professional2ndActivity.class);
                                startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(getContext(), Professional3rdActivity.class);
                                startActivity(intent);
                                break;
                            case 4:
                                intent = new Intent(getContext(), Professional4thActivity.class);
                                startActivity(intent);
                                break;
                            default:
                                Toast.makeText(getContext(), "Choose Your Professional", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                        Toast.makeText(getContext(), "Choose Your Professional", Toast.LENGTH_SHORT).show();

                        }
                });

                builder.setView(mView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Study Section");
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Professional1stCard =(CardView)view.findViewById(R.id.Professional1stCard);
        //Professional2ndCard=(CardView)view.findViewById(R.id.Professional2ndCard);
        //Professional3rdCard=(CardView)view.findViewById(R.id.Professional3rdCard);
        //Professional4thCard=(CardView)view.findViewById(R.id.Professional4thCard);

        /*Professional1stCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                startActivity(new Intent(getContext(),Professional1stActivity.class));
                //Toast.makeText(getActivity(), "Will be available soon", Toast.LENGTH_SHORT).show();
            }
        });
        Professional2ndCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                startActivity(new Intent(getContext(),Professional2ndActivity.class));
                //Toast.makeText(getActivity(), "Will be available soon", Toast.LENGTH_SHORT).show();
            }
        });
        Professional3rdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                startActivity(new Intent(getContext(),Professional3rdActivity.class));
                //Toast.makeText(getActivity(), "Will be available soon", Toast.LENGTH_SHORT).show();
            }
        });
        Professional4thCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                startActivity(new Intent(getContext(),Professional4thActivity.class));
                //Toast.makeText(getActivity(), "Will be available soon", Toast.LENGTH_SHORT).show();
            }
        });*/

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
