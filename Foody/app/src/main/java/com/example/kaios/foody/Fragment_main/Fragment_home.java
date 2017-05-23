package com.example.kaios.foody.Fragment_main;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kaios.foody.Fragment_User.Login_main;
import com.example.kaios.foody.Fragment_angi_odau.fragment_angi;
import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;
import com.example.kaios.foody.ThemDiaDiem.ThemDiaDiem;

import static com.example.kaios.foody.Fragment_angi_odau.fragment_angi.click1;
import static com.example.kaios.foody.Fragment_angi_odau.fragment_angi.click2;
import static com.example.kaios.foody.Fragment_angi_odau.fragment_angi.click3;

public class Fragment_home extends Fragment {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    TextView tvODau, tvAnGi;
    ImageView btnPlus;

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
                        fragment_angi.mTabHost.setCurrentTab(0);
                        click1 = false;
                        click2=false;
                        click3=false;
                        break;
                    case 1:
                        changeSwipe(tvAnGi, tvODau,false);
                        fragment_odau.mTabHost.setCurrentTab(0);
                        fragment_odau.click1 = false;
                        fragment_odau.click2=false;
                        fragment_odau.click3=false;
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

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //sự kiện click Plus
        btnPlus=(ImageView)v.findViewById(R.id.imgPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtomSheetDialog();
            }
        });

        return v;
    }

    //show BottomDialog
    private void ButtomSheetDialog(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_plus,null);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
        final LinearLayout ln_ThemDiaDiem=(LinearLayout)bottomSheetView.findViewById(R.id.ln_ThemDiaDiem);
        ln_ThemDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.islogin){//đã đăng nhập
                    Intent intent = new Intent(getActivity(), ThemDiaDiem.class);
                    startActivity(intent);//start thêm địa điểm
                    bottomSheetDialog.dismiss();
                }
                else {//chưa dăng nhập
                    Intent intent = new Intent(getActivity(), Login_main.class);
                    startActivity(intent);//đi đến trang đăng nhập
                    bottomSheetDialog.dismiss();
                }
            }
        });
        //bottomSheetDialog.show();
    }

    private void changeSwipe(TextView tvTurnOn, TextView tvTurnOff, boolean onLeft) {
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

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

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
