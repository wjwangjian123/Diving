package com.example.edive.fragment.presonfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TobeHarvestedFragment extends Fragment {


    public TobeHarvestedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //待收货
        return inflater.inflate(R.layout.fragment_tobe_harvested, container, false);
    }

}
