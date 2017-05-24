package com.example.kaios.foody.Tabs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.kaios.foody.Adapter.Adapter_Tab3_Odau;
import com.example.kaios.foody.Doi_ThanhPho;
import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;
import com.example.kaios.foody.SQLite.DataBaseHandling;

import java.util.ArrayList;
import java.util.HashMap;

public class TabActivity_3_odau extends Fragment {

    public static String KieuDiaDiem="ThanhPho";
    public static String TenDiaDiem="TP.HCM";
    Adapter_Tab3_Odau elva;
    ExpandableListView expan;
    ArrayList<String> tenquan;
    HashMap<String, ArrayList<String>> tenduong;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.tab3, container, false);

        TextView txt=(TextView)v.findViewById(R.id.tvHeader);
        txt.setText(Doi_ThanhPho.nameTP);
        txt.setTextColor(Color.RED);

        // Tham chiếu ExpandableListView
        expan = (ExpandableListView)v.findViewById(R.id.expan);
        // Đọc dữ liệu từ SQLite
        loadData(Doi_ThanhPho.nameTP);
        elva = new Adapter_Tab3_Odau(getContext(), tenquan, tenduong);
        // Chỉ định Adapter cho ExpandableListView
        expan.setAdapter(elva);



        ///Click button Hủy trở về tab chính
        Button btn=(Button) v.findViewById(R.id.btnhuy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_odau.mTabHost.setCurrentTab(0);
                fragment_odau.click3=false;
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện BottomBar khi bấm hủy
            }
        });

        Button DoiTinhThanh=(Button)v.findViewById(R.id.tp);
        DoiTinhThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Doi_ThanhPho.class);
                startActivity(intent);
            }
        });


        return v;
    }


    //load Data
    private void loadData(String TenThanhPho) {
        DataBaseHandling db = new DataBaseHandling(getContext());
        db.openDataBase();
        tenduong = new HashMap<String, ArrayList<String>>();

        // Dữ liệu cho header được lấy từ bảng tbTenQuan
        tenquan = db.getTenQuan(TenThanhPho);

        for(int i=0;i<tenquan.size();i++){
            // Dữ liệu tương ứng với mỗi header được lấy từ bảng tbTenDuong
            ArrayList<String> itItem = new ArrayList<String>();
            itItem = db.getTenDuong(tenquan.get(i));

            tenduong.put(tenquan.get(i), itItem); // Header, Child data

        }

    }
}