package com.example.kaios.foody.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaios.foody.Adapter.Adapter_angi;
import com.example.kaios.foody.Adapter.Adapter_angi_tab2;
import com.example.kaios.foody.MonAn;
import com.example.kaios.foody.R;
import com.example.kaios.foody.SQLite.DataBaseHandling;

import java.util.ArrayList;

public class TabActivity_angi extends Fragment {


    private RecyclerView recyclerView;
    private Adapter_angi AdapterRecy;
    private int imgHeader = R.drawable.qc;
    private ArrayList<MonAn> listMonAn;

    private int[] imgdanhmuc ={R.drawable.gantoi,R.drawable.cou,R.drawable.datcho,
            R.drawable.datgiaohang,R.drawable.ecard,R.drawable.game,
            R.drawable.binhluan,R.drawable.blog,R.drawable.topthanhvien,
            R.drawable.video};
    private String[] tvdanhmuc ={"Gần tôi","Coupon","Đặt chỗ ưu đãi","Đặt giao hàng","E-card",
            "Game & Fun","Bình Luận", "Blogs","Top thành viên","Video"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_activity_angi, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.rc_angi);
        recyclerView.setHasFixedSize(true);

        //chia thành 2 cột
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return position == 0 ? 2 : position <= 10 ? 1 : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);

        loadData(Adapter_angi_tab2.nameDanhMuc);
        AdapterRecy = new Adapter_angi(getContext(), imgHeader, imgdanhmuc, tvdanhmuc, listMonAn);
        recyclerView.setAdapter(AdapterRecy);
        AdapterRecy.notifyDataSetChanged();

        return v;
    }

    public void loadData(String danhmucMonAn){
        DataBaseHandling db = new DataBaseHandling(getContext());
        db.openDataBase();
        listMonAn=db.getMonAn(danhmucMonAn);

    }

}
