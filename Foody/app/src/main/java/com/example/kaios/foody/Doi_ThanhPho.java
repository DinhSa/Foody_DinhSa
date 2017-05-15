package com.example.kaios.foody;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaios.foody.Adapter.Adapter_DoiTP;
import com.example.kaios.foody.Fragment_angi_odau.fragment_angi;
import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.SQLite.DataBaseHandling;
import com.example.kaios.foody.Tabs.TabActivity_2_angi;
import com.example.kaios.foody.Tabs.TabActivity_2_odau;
import com.example.kaios.foody.Tabs.TabActivity_3_angi;
import com.example.kaios.foody.Tabs.TabActivity_3_odau;

import java.util.ArrayList;

import static com.example.kaios.foody.R.id.listTP;

/**
 * Created by kaios on 4/17/2017.
 */

public class Doi_ThanhPho extends Activity {
    public static String nameTP="TP.HCM";
    TextView Back;
    ListView list;
    Context context;
    ArrayList<String> listTinhThanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doi_thanhpho);

        context=this;
        loadDB();
        list=(ListView)findViewById(listTP);
        list.setAdapter(new Adapter_DoiTP(this, listTinhThanh));

        Back=(TextView)findViewById(R.id.backTP);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Sự kiện Click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = listTinhThanh.get(position-1);
//                TextView txt=(TextView) fragment_odau.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.tabsText);
//                TextView txt1=(TextView) fragment_angi.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.tabsText);
                nameTP=text;
                TabActivity_3_odau.TenDiaDiem=text;
                TabActivity_3_odau.KieuDiaDiem="ThanhPho";
                TabActivity_3_angi.TenDiaDiem=text;
                TabActivity_3_angi.KieuDiaDiem="ThanhPho";
                //reset params
                TabActivity_2_angi.TenDanhMuc = "Danh mục";
                TabActivity_2_odau.TenDanhMuc= "Danh mục";
                fragment_odau.click3=false;
                fragment_angi.click3=false;
                Intent intent = new Intent(Doi_ThanhPho.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //load DB
    public void loadDB(){
        DataBaseHandling db = new DataBaseHandling(this);
        db.openDataBase();
        listTinhThanh=db.getTinhThanh();

    }

}
