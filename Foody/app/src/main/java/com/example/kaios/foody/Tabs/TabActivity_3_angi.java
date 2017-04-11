package com.example.kaios.foody.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.kaios.foody.Adapter.ExpandableListViewAdapter;
import com.example.kaios.foody.Fragment_angi_odau.fragment_angi;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;
import com.example.kaios.foody.SQLite.DataBaseHandling;

import java.util.ArrayList;
import java.util.HashMap;

public class TabActivity_3_angi extends Fragment {

    ExpandableListViewAdapter elva;
    ExpandableListView expan;
    ArrayList<String> tenquan;
    HashMap<String, ArrayList<String>> tenduong;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.tab3, container, false);


        // Tham chiếu ExpandableListView
        expan = (ExpandableListView)v.findViewById(R.id.expan);
        // Đọc dữ liệu từ SQLite
        loadData();
        elva = new ExpandableListViewAdapter(getContext(), tenquan, tenduong);
        // Chỉ định Adapter cho ExpandableListView
        expan.setAdapter(elva);



        ///Click button Hủy trở về tab chính
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


    //load Data
    private void loadData() {
        DataBaseHandling db = new DataBaseHandling(getContext());
        db.openDataBase();
        tenduong = new HashMap<String, ArrayList<String>>();

        // Dữ liệu cho header được lấy từ bảng tbTenQuan
        tenquan = db.getTenQuan();

        for(int i=0;i<tenquan.size();i++){
            // Dữ liệu tương ứng với mỗi header được lấy từ bảng tbTenDuong
            ArrayList<String> itItem = new ArrayList<String>();
            itItem = db.getTenDuong(tenquan.get(i));

            tenduong.put(tenquan.get(i), itItem); // Header, Child data

        }

    }
}