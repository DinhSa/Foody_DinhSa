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
import android.widget.Toast;

import com.example.kaios.foody.Adapter.Adapter_angi;
import com.example.kaios.foody.Clients.FoodyClient;
import com.example.kaios.foody.MonAn;
import com.example.kaios.foody.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

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

        getAnGi();
        //loadData(Adapter_angi_tab2.nameDanhMuc);
        return v;
    }

//    public void loadData(String danhmucMonAn){
//        DataBaseHandling db = new DataBaseHandling(getContext());
//        db.openDataBase();
//        if(danhmucMonAn!="Danh mục")
//            listMonAn=db.getMonAn(danhmucMonAn);
//        else
//            listMonAn=db.getMonAnAll();
//
//    }


    //load webservice
    public void getAnGi() {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        RequestParams params = new RequestParams();
        params.put("TenDanhMuc", TabActivity_2_angi.TenDanhMuc);
        params.put("KieuDiaDiem", TabActivity_3_angi.KieuDiaDiem);
        params.put("TenDiaDiem", TabActivity_3_angi.TenDiaDiem);
        FoodyClient Angi = new FoodyClient();
        Angi.get(getContext(), "api/AnGi/GetAnGiOptions", headers.toArray(new Header[headers.size()]),
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
                        listMonAn = new ArrayList<MonAn>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                listMonAn.add(new MonAn(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        AdapterRecy = new Adapter_angi(getContext(), imgHeader, imgdanhmuc, tvdanhmuc, listMonAn);
                        recyclerView.setAdapter(AdapterRecy);
                        AdapterRecy.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {
                        progressDialog.dismiss();
                    }
                });

    }

}
