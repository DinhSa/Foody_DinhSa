package com.example.kaios.foody.Fragment_main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaios.foody.Fragment_ThongBao.Fragment_ThongBao_Tab;
import com.example.kaios.foody.R;

public class Fragment_Bell extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Fragment selectedFragment;
        selectedFragment = new Fragment_ThongBao_Tab();

        //fill Fragment into frameLayout
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_bell,selectedFragment);
        transaction.commit();

        return inflater.inflate(R.layout.fragment__bell, container, false);
    }
}