package com.example.kaios.foody.ThemDiaDiem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.kaios.foody.Adapter.Adapter_DoiTP;
import com.example.kaios.foody.R;
import com.example.kaios.foody.SQLite.DataBaseHandling;

import java.util.ArrayList;

import static com.example.kaios.foody.R.id.listTP;

public class ChonThanhPho extends AppCompatActivity {
    public static String nameTP_ThemDD="TP.HCM";
    ListView list;
    Context context;
    ArrayList<String> listTinhThanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_thanh_pho);
        Button BoQua=(Button)findViewById(R.id.btn_boqua);
        context=this;
        loadDB();
        list=(ListView)findViewById(listTP);
        list.setAdapter(new Adapter_DoiTP(this, listTinhThanh));

        //click button bỏ qua
        BoQua.setOnClickListener(new View.OnClickListener() {
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
                nameTP_ThemDD=text;
                ThemDiaDiem.btnTP.setText(nameTP_ThemDD);
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
