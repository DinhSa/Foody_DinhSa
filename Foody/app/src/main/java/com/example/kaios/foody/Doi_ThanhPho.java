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
    TextView Back;//nút quay về
    ListView list;//list Thành Phố
    Context context;
    ArrayList<String> listTinhThanh; //mảng list thành phố
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doi_thanhpho);

        context=this;
        loadDB(); //load dữ liệu
        list=(ListView)findViewById(listTP); //trỏ tới ID ListTp
        list.setAdapter(new Adapter_DoiTP(this, listTinhThanh)); //set Adapter cho list thành phố

        Back=(TextView)findViewById(R.id.backTP); //trỏ tới ID nút quay về
        Back.setOnClickListener(new View.OnClickListener() { //Sự kiện click
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Sự kiện Click mỗi địa điểm
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = listTinhThanh.get(position-1);
//                TextView txt=(TextView) fragment_odau.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.tabsText);
//                TextView txt1=(TextView) fragment_angi.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.tabsText);
                nameTP=text;
                TabActivity_3_odau.TenDiaDiem=text; //gán dữ liệu truyền vào webservice
                TabActivity_3_odau.KieuDiaDiem="ThanhPho";//gán dữ liệu truyền vào webservice
                TabActivity_3_angi.TenDiaDiem=text;//gán dữ liệu truyền vào webservice
                TabActivity_3_angi.KieuDiaDiem="ThanhPho";//gán dữ liệu truyền vào webservice
                //reset params
                TabActivity_2_angi.TenDanhMuc = "Danh mục";
                TabActivity_2_odau.TenDanhMuc= "Danh mục";
                fragment_odau.click3=false;
                fragment_angi.click3=false;
                ToMain();//trở về trang home
            }
        });
    }

    //load DB
    public void loadDB(){
        DataBaseHandling db = new DataBaseHandling(this);//khởi tạo
        db.openDataBase();//mở database
        listTinhThanh=db.getTinhThanh();//get list

    }

    public void ToMain(){
        Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//xóa các activity trc đó
        startActivity(loginIntent);
    }

}
