package com.example.kaios.foody.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.kaios.foody.Adapter.Adapter_Tab1_odau;
import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;
//import com.example.kaios.foody.R;

public class TabActivity_1_odau extends Fragment {
    ListView lv;

    public static int [] prgmImages={R.drawable.ic_1,R.drawable.ic_2,R.drawable.ic_3,
            R.drawable.ic_4,R.drawable.ic_5,R.drawable.ic_6,R.drawable.ic_7,R.drawable.ic8};
    public static String [] prgmNameList={"Mới nhất","Gần tôi","Phổ biến","Du khách",
            "Ưu đãi E-card","Đặt chỗ","Ưu đãi thẻ","Đặt giao hàng"};
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tabs_layout, container, false);
        lv=(ListView)v.findViewById(R.id.mainListView);

        lv.setAdapter(new Adapter_Tab1_odau(getContext(), prgmNameList,prgmImages));

        //Click button Hủy trở về tab chính
        Button btn=(Button) v.findViewById(R.id.btnhuy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_odau.mTabHost.setCurrentTab(0);
                fragment_odau.click1=false;
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
