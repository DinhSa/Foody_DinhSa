package com.example.kaios.foody.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaios.foody.Adapter.Adapter_angi_odau;
import com.example.kaios.foody.QuanAn;
import com.example.kaios.foody.R;
import com.example.kaios.foody.SQLite.DataBaseHandling;

import java.util.ArrayList;

public class TabActivity_odau extends Fragment {


    private RecyclerView recyclerView;
    private Adapter_angi_odau AdapterRecy;
    private int imgHeader = R.drawable.qc;
    private ArrayList<QuanAn> listQuanAn;

    private int[] imgdanhmuc ={R.drawable.gantoi,R.drawable.cou,R.drawable.datcho,
            R.drawable.datgiaohang,R.drawable.ecard,R.drawable.game,
            R.drawable.binhluan,R.drawable.blog,R.drawable.topthanhvien,
            R.drawable.video};
    private String[] tvdanhmuc ={"Gần tôi","Coupon","Đặt chỗ ưu đãi","Đặt giao hàng","E-card",
            "Game & Fun","Bình Luận", "Blogs","Top thành viên","Video"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_activity_odau, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.rc_odau);
        recyclerView.setHasFixedSize(true);

        //chia thành 2 cột
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return position == 0 ? 2 : position <= 10 ? 1 : 2;
            }
        });
        recyclerView.setLayoutManager(layoutManager);

        loadData();
        AdapterRecy = new Adapter_angi_odau(getContext(), imgHeader, imgdanhmuc, tvdanhmuc, listQuanAn);
        recyclerView.setAdapter(AdapterRecy);
        AdapterRecy.notifyDataSetChanged();

        return v;
    }

    //load DB
    public void loadData(){
        DataBaseHandling db = new DataBaseHandling(getContext());
        db.openDataBase();
        listQuanAn=db.getQuanAn();

    }

}
