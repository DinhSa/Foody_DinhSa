package com.example.kaios.foody;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaios.foody.Adapter.Adapter_DoiTP;
import com.example.kaios.foody.Fragment_angi_odau.fragment_angi;
import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.SQLite.DataBaseHandling;

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
                fragment_odau.click3=false;
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện BottomBar khi bấm hủy
                finish();
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = listTinhThanh.get(position-1);
                TextView txt=(TextView) fragment_odau.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.tabsText);
                TextView txt1=(TextView) fragment_angi.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.tabsText);
                txt.setText(text);//đổi tên tab
                txt1.setText(text);//đổi tên tab
                nameTP=text;
                txt.setTextColor(Color.RED);
                txt1.setTextColor(Color.RED);
                fragment_odau.click3=false;
                fragment_angi.click3=false;
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện BottomBar khi bấm hủy
                finish();
            }
        });
    }

    public void loadDB(){
        DataBaseHandling db = new DataBaseHandling(this);
        db.openDataBase();
        listTinhThanh=db.getTinhThanh();

    }

}
