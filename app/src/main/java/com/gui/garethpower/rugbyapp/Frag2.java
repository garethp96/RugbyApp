package com.gui.garethpower.rugbyapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag2 extends Fragment {

    //newInstance() method return the referenc to fragment
    public static Frag2 newInstance(){
        Frag2 fragment2 = new Frag2();
        return fragment2;
    }

    public Frag2() {
        // Required empty public constructor
    }

    //variables
    TextView textView14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frag2, container, false);
        textView14 = (TextView) rootView.findViewById(R.id.textView14);

        // return rootview
        return rootView;
    }
}
