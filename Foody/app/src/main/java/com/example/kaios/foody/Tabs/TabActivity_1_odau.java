package com.example.kaios.foody.Tabs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaios.foody.Adapter.AdapterTab1;
import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;
//import com.example.kaios.foody.R;

public class TabActivity_1_odau extends Fragment {
    public static String nameDanhMuc="Mới Nhất";
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

        lv.setAdapter(new AdapterTab1(getContext(), prgmNameList,prgmImages));

        //Click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameDanhMuc=prgmNameList[position]; //lấy tên danh mục
                TextView txt=(TextView) fragment_odau.mTabHost.getTabWidget().getChildAt(1).findViewById(R.id.tabsText);
                txt.setText(nameDanhMuc);//đổi tên tab
                txt.setTextColor(Color.RED); //đổi màu text
                fragment_odau.mTabHost.setCurrentTab(0);//trở về lại tab 0
                fragment_odau.click1=false; //click chưa đc click
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView
            }
        });

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

}
