package com.gui.garethpower.rugbyapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag3 extends Fragment {

    //newInstance() method return the referenc to fragment
    public static Frag3 newInstance(){
        Frag3 fragment3 = new Frag3();
        return fragment3;
    }

    public Frag3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frag3, container, false);

        String[] tableItems = {"1. England (8)",
                               "2. Ireland (6)",
                               "3. Wales (5)",
                               "4. France (5)",
                               "5. Scotland (5)",
                               "6. Italy (0)"};

        //finds the listview in the xml
        ListView listView = (ListView) rootView.findViewById(R.id.sixNationsTable);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                tableItems
        );

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
                }else if (position == 1){

                }else if (position == 2){

                }else if (position == 3){

                }else if (position == 4){

                }else if (position == 5){

                }
            }
        });

        //returns the view
        return rootView;
    }

}
