package com.example.kaios.foody.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaios.foody.Adapter.Adapter_odau;
import com.example.kaios.foody.Adapter.Adapter_odau_tab2;
import com.example.kaios.foody.Clients.FoodyClient;
import com.example.kaios.foody.QuanAn;
import com.example.kaios.foody.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class TabActivity_odau extends Fragment {

    private RecyclerView recyclerView;
    private Adapter_odau AdapterRecy;
    private int imgHeader = R.drawable.qc;
    private ArrayList<QuanAn> listQuanAn;

    private int[] imgdanhmuc = {R.drawable.gantoi, R.drawable.cou, R.drawable.datcho,
            R.drawable.datgiaohang, R.drawable.ecard, R.drawable.game,
            R.drawable.binhluan, R.drawable.blog, R.drawable.topthanhvien,
            R.drawable.video};
    private String[] tvdanhmuc = {"Gần tôi", "Coupon", "Đặt chỗ ưu đãi", "Đặt giao hàng", "E-card",
            "Game & Fun", "Bình Luận", "Blogs", "Top thành viên", "Video"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_activity_odau, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.rc_odau);
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
        getODau();
        //loadData(Adapter_odau_tab2.nameDanhMuc);
//        AdapterRecy = new Adapter_odau(getContext(), imgHeader, imgdanhmuc, tvdanhmuc, listQuanAn);
//        recyclerView.setAdapter(AdapterRecy);
//        AdapterRecy.notifyDataSetChanged();


        return v;
    }

    //load DB
//    public void loadData(String danhmuc){
//        DataBaseHandling db = new DataBaseHandling(getContext());
//        db.openDataBase();
//        if(danhmuc!="Danh mục")
//            listQuanAn=db.getQuanAn(danhmuc);
//        else
//            listQuanAn=db.getQuanAnAll();
//
//    }


    //load webservice
    private void getODau() {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        RequestParams params = new RequestParams();
        params.put("TenDanhMuc", Adapter_odau_tab2.TenDanhMuc);
        params.put("KieuDiaDiem", TabActivity_3_odau.KieuDiaDiem);
        params.put("TenDiaDiem", TabActivity_3_odau.TenDiaDiem);
        FoodyClient odau = new FoodyClient();
        odau.get(getContext(), "api/ODau/GetODauOptions", headers.toArray(new Header[headers.size()]),
                params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        listQuanAn = new ArrayList<QuanAn>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                listQuanAn.add(new QuanAn(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        AdapterRecy = new Adapter_odau(getContext(), imgHeader, imgdanhmuc, tvdanhmuc, listQuanAn);
                        recyclerView.setAdapter(AdapterRecy);
                        AdapterRecy.notifyDataSetChanged();
                    }
                });
    }
}