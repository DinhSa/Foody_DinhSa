package com.example.kaios.foody.Tabs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaios.foody.Adapter.Adapter_odau;
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
    private ArrayList<QuanAn> listQuanAn=new ArrayList<QuanAn>();//khởi tạo list quán ăn

    private int[] imgdanhmuc = {R.drawable.tc01, R.drawable.tc02, R.drawable.tc03,
            R.drawable.tc04, R.drawable.tc05, R.drawable.tc06,
            R.drawable.tc07, R.drawable.tc08, R.drawable.tc09,
            R.drawable.tc10};
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

        //loadData(Adapter_odau_tab2.nameDanhMuc);
        AdapterRecy = new Adapter_odau(getContext(), imgHeader, imgdanhmuc, tvdanhmuc, listQuanAn);
        recyclerView.setAdapter(AdapterRecy);
        AdapterRecy.notifyDataSetChanged();

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getODau();
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
        params.put("TenDanhMuc", TabActivity_2_odau.TenDanhMuc);
        params.put("KieuDiaDiem", TabActivity_3_odau.KieuDiaDiem);
        params.put("TenDiaDiem", TabActivity_3_odau.TenDiaDiem);
        FoodyClient odau = new FoodyClient();
        odau.get(getContext(), "api/ODau/GetODauOptions", headers.toArray(new Header[headers.size()]),
                params, new JsonHttpResponseHandler() {

                    ProgressDialog progressDialog;

                    @Override
                    public void onStart() {
                        progressDialog = new ProgressDialog(getContext(), R.style.DialogTheme);
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        listQuanAn = new ArrayList<QuanAn>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                listQuanAn.add(new QuanAn(response.getJSONObject(i)));//lấy dữ liệu từ server
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        AdapterRecy = new Adapter_odau(getContext(), imgHeader, imgdanhmuc, tvdanhmuc, listQuanAn);//tạo adapter
                        recyclerView.setAdapter(AdapterRecy);//set adpater
                        AdapterRecy.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                        Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                        progressDialog = ProgressDialog.show(getContext(),"Lỗi","kiểm tra kết nối", true);
                    }

                    @Override
                    public void onFinish() {
                        progressDialog.dismiss();
                    }
                });
    }

}