package com.example.kaios.foody.Fragment_SuuTap_DiaDiem;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kaios.foody.R;

public class Frangment_DiaDiem extends Fragment {

    FragmentTabHost mTabHost;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.frangment__dia_diem);

        //add tab
        setupTab(new TextView(getContext()), "Của bạn");
        setupTab(new TextView(getContext()), "Nổi bật");
        setupTab(new TextView(getContext()), "Đã lưu");
        setupTab(new TextView(getContext()), "Xem nhiều");


        //////////////
        return mTabHost;
    }

    //hàm setup tab
    private void setupTab(final View view, final String tag) {
        View tabview = createTabView(mTabHost.getContext(), tag);
        TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {return view;}
        });
        Bundle arg = new Bundle();
        mTabHost.addTab(setContent);

    }

    //hàm tạo custom tab
    private static View createTabView(final Context context, final String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_collect_bell_search_user, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText2);
        tv.setText(text);
        return view;
    }


}