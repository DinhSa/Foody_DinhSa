package com.example.kaios.foody.Fragment_main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaios.foody.Fragment_angi_odau.fragment_angi;
import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.R;

public class Fragment_home extends Fragment {


    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    TextView tvODau, tvAnGi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tvODau = (TextView)v.findViewById(R.id.ODau);
        tvAnGi = (TextView)v.findViewById(R.id.AnGi);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager)v.findViewById(R.id.view_home);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //Hoán đổi hiệu ứng chọn khi kéo
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        changeSwipe(tvODau, tvAnGi,true);
                        break;
                    case 1:
                        changeSwipe(tvAnGi, tvODau,false);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvODau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });

        tvAnGi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });


        //sự kiện click Plus
        ImageView btnPlus=(ImageView)v.findViewById(R.id.imgPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                View bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_plus,null);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });


        return v;
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if(context instanceof MainActivity){
//            context1=(MainActivity) context;
//        }
//    }

    private void changeSwipe(TextView tvTurnOn, TextView tvTurnOff, boolean onLeft)
    {
        tvTurnOn.setBackgroundResource(android.R.color.transparent);
        tvTurnOn.setTextColor(Color.parseColor("#000000"));
        if (onLeft) {
            tvTurnOff.setBackgroundResource(R.drawable.round_left_2);
        }
        else{
            tvTurnOff.setBackgroundResource(R.drawable.round_right_2);
        }
        tvTurnOff.setTextColor(Color.parseColor("#FFFFFF"));
    }



    public class SectionsPagerAdapter extends FragmentStatePagerAdapter
    {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return new fragment_angi();
                case 0:
                    return new fragment_odau();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }



}
