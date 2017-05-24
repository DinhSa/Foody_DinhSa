package com.example.kaios.foody.Fragment_main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaios.foody.Fragment_Search.Fragment_Search_tab;
import com.example.kaios.foody.R;

public class Fragment_Search extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Fragment selectedFragment;
        selectedFragment = new Fragment_Search_tab();

        //fill Fragment into frameLayout
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_search,selectedFragment);
        transaction.commit();

        return inflater.inflate(R.layout.fragment__search, container, false);
    }
}