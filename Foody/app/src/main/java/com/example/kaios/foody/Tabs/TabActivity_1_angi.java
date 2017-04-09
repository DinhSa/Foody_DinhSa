package com.example.kaios.foody.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.kaios.foody.Adapter.Adapter_Tab1;
import com.example.kaios.foody.Fragment_angi_odau.fragment_angi;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;

public class TabActivity_1_angi extends Fragment {

    ListView lv;

    public static int [] prgmImages={R.drawable.ic_1,R.drawable.ic_2,R.drawable.ic_xemnhieu,
            R.drawable.ic_4};
    public static String [] prgmNameList={"Mới nhất","Gần tôi","Xem nhiều","Du khách",};
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tabs_layout, container, false);
        lv=(ListView)v.findViewById(R.id.mainListView);
        lv.setAdapter(new Adapter_Tab1(getContext(), prgmNameList,prgmImages));

        //Click button Hủy trở về tab chính
        Button btn=(Button) v.findViewById(R.id.btnhuy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_angi.mTabHost.setCurrentTab(0);
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện BottomBar khi bấm hủy
            }
        });

        return v;
    }
    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            context1=(MainActivity) context;
        }
    }*/
}