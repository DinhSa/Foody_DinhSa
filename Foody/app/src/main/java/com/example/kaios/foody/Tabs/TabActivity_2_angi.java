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

import com.example.kaios.foody.Adapter.AdapterTab2;
import com.example.kaios.foody.Fragment_angi_odau.fragment_angi;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;

public class TabActivity_2_angi extends Fragment {

    public static String TenDanhMuc="Danh mục";
    ListView lv;

    public static int [] prgmImages={R.drawable.h16,R.drawable.h17,R.drawable.h18,R.drawable.h19,R.drawable.h20,R.drawable.h21,
            R.drawable.h22,R.drawable.h23,R.drawable.h24,R.drawable.h25,R.drawable.h26,R.drawable.h27,
            R.drawable.h28,R.drawable.h29,R.drawable.h30,R.drawable.h1,R.drawable.h2,R.drawable.h3,
            R.drawable.h4,R.drawable.h5,R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,
            R.drawable.h10,R.drawable.h11,R.drawable.h12,R.drawable.h13,R.drawable.h14,R.drawable.h15,
            };
    public static String [] prgmNameList={"Việt Nam","Châu Mỹ","Mỹ",
            "Tây Âu","Ý","Pháp","Đức","Tây Ban Nha","Trung Hoa","Ấn Độ","Thái Lan","Nhật bản","Hàn Quốc","Malaysia","Quốc tế",
            "Sang trọng","Buffet","Nhà hàng","Ăn vặt/vỉa hè",
            "Ăn chay","Café/Dessert","Quán ăn","Bar/Pub","Quán nhậu","Beer club","Tiệm bánh",
            "Tiệc tận nơi","Shop Online","Giao cơm văn phòng","Khu Ẩm Thực"};
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tabs_layout, container, false);
        lv=(ListView)v.findViewById(R.id.mainListView);
        lv.setAdapter(new AdapterTab2(getContext(), prgmNameList,prgmImages));

        //Sự kiện Click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    TenDanhMuc="Danh mục"; //lấy tên danh mục
                    TextView txt=(TextView) fragment_angi.mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.tabsText);
                    txt.setText(TenDanhMuc);//đổi tên tab
                    txt.setTextColor(Color.RED); //đổi màu text
                    fragment_angi.mTabHost.setCurrentTab(0);//trở về lại tab 0
                    fragment_angi.click2=false; //click chưa đc click
                    MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView
                }
                else {
                    TenDanhMuc= prgmNameList[position-1]; //lấy tên danh mục
                    TextView txt=(TextView) fragment_angi.mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.tabsText);
                    txt.setText(TenDanhMuc);//đổi tên tab
                    txt.setTextColor(Color.RED); //đổi màu text
                    fragment_angi.mTabHost.setCurrentTab(0);//trở về lại tab 0
                    fragment_angi.click2=false; //click chưa đc click
                    MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView
                }

            }
        });

        //Click button Hủy trở về tab chính
        Button btn=(Button) v.findViewById(R.id.btnhuy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_angi.mTabHost.setCurrentTab(0);
                fragment_angi.click2=false;
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện BottomBar khi bấm hủy
            }
        });

        return v;
    }
}