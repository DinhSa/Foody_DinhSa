package com.example.kaios.foody.Fragment_angi_odau;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;
import com.example.kaios.foody.Tabs.TabActivity_1_odau;
import com.example.kaios.foody.Tabs.TabActivity_2_odau;
import com.example.kaios.foody.Tabs.TabActivity_3_odau;
import com.example.kaios.foody.Tabs.TabActivity_odau;

public class fragment_odau extends Fragment {

    private boolean click1 =false;
    private boolean click2 =false;
    private boolean click3 =false;

    public static FragmentTabHost mTabHost;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.activity_fragment_odau2);

        //add tab
        setupTab(new TextView(getContext()), "Ở Đâu",TabActivity_odau.class);
        setupTab(new TextView(getContext()), "Mới Nhất", TabActivity_1_odau.class);
        setupTab(new TextView(getContext()), "Danh mục",TabActivity_2_odau.class);
        setupTab(new TextView(getContext()), "TP.HCM",TabActivity_3_odau.class);
        //ẩn tab đầu tiên
        mTabHost.getTabWidget().getChildAt(0).setVisibility(View.GONE);


        //click lần 2 trở về tab ở đâu trên tab 1
        mTabHost.getTabWidget().getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (click1 == false) {
                    MainActivity.mBottomBar.setVisibility(View.GONE);//ẩn BottomBar khi hiện listView
                    mTabHost.setCurrentTab(1);
                    click1 = true;
                    click2=false;
                    click3=false;
                } else {
                    MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView
                    mTabHost.setCurrentTab(0);
                    click1 = false;
                }
            }
        });
        //click lần 2 trở về tab ở đâu trên tab 2
        mTabHost.getTabWidget().getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (click2 == false) {
                    MainActivity.mBottomBar.setVisibility(View.GONE);
                    mTabHost.setCurrentTab(2);
                    click2 = true;
                    click1=false;
                    click3=false;
                } else {
                    MainActivity.mBottomBar.setVisibility(View.VISIBLE);
                    mTabHost.setCurrentTab(0);
                    click2 = false;
                }
            }
        });
        //click lần 2 trở về tab ở đâu trên tab 3
        mTabHost.getTabWidget().getChildAt(3).setOnClickListener(new View.OnClickListener() {
            //boolean click2 =false;
            @Override
            public void onClick(View v) {

                if (click3 == false) {
                    MainActivity.mBottomBar.setVisibility(View.GONE);
                    mTabHost.setCurrentTab(3);
                    click3 = true;
                    click2=false;
                    click1=false;
                } else {
                    MainActivity.mBottomBar.setVisibility(View.VISIBLE);
                    mTabHost.setCurrentTab(0);
                    click3 = false;

                }
            }
        });


        //////////////
        return mTabHost;
    }


    //hàm setup tab
    private void setupTab(final View view, final String tag, Class taget) {
        View tabview = createTabView(mTabHost.getContext(), tag);
        TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {return view;}
        });
        Bundle arg = new Bundle();
        mTabHost.addTab(setContent,taget,arg);
    }

    //hàm tạo custom tab
    private static View createTabView(final Context context, final String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;
    }


}