package com.example.kaios.foody.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaios.foody.R;

public class TabActivity_angi extends Fragment {
    private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_activity_angi, container, false);
//        BottomNavigationView bottomNavigationView = (BottomNavigationView)v.findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener
//                (new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        //Fragment selectedFragment = null;
//                        switch (item.getItemId()) {
//                            case R.id.action_favorites:
//                                //selectedFragment = NV_trangchu_odau.newInstance();
//                                break;
//                            case R.id.action_schedules:
//                                //selectedFragment = NV_bosuutap.newInstance();
//                                break;
//                            case R.id.action_music:
//                                //selectedFragment = NV_Timkiem   .newInstance();
//                                break;
//
//
//                        }
////                        FragmentTransaction transaction = myContext.getSupportFragmentManager().beginTransaction();
////                        transaction.replace(R.id.myScrollingContent, selectedFragment);
////                        transaction.commit();
//                        return true;
//                    }
//                });
//        //Manually displaying the first fragment - one time only
//        FragmentTransaction transaction = myContext.getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_layout_angi, NV_trangchu_angi.newInstance());
//        transaction.commit();
        return v;
    }


}
