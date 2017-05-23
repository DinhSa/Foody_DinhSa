package com.example.kaios.foody.ThemDiaDiem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaios.foody.R;
import com.example.kaios.foody.SQLite.DataBaseHandling;

import java.util.ArrayList;

/**
 * Created by kaios on 5/23/2017.
 */

public class ChonDuong extends AppCompatActivity {

    private String TenDuong_ThemDD = null;
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    ArrayList<String> ListDuong;//List đường theo quận

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_quan_tp);
        Button BoQua=(Button)findViewById(R.id.btn_boquachonQuan);
        TextView head = (TextView)findViewById(R.id.tvHeader);
        head.setText("Chọn đường");//đặt header cho listview
        //click button bỏ qua
        BoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.listChonquan );
        loadData(ChonQuanTP.TenQuan_ThemDD);

        // Create ArrayAdapter using the ListDuong
        listAdapter = new ArrayAdapter<String>(this, R.layout.row_chon_quan, ListDuong);

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TenDuong_ThemDD=ListDuong.get(position);//lấy Item tên đường
                ThemDiaDiem.tvTenDuong.setText(TenDuong_ThemDD); //set name
                ThemDiaDiem.ID_MaDuong= GetID(TenDuong_ThemDD);//lấy ID tên dường
                ThemDiaDiem.chonDuong=true;//true
                finish();
            }
        });
    }

    //load Data
    private void loadData(String TenQuan) {
        DataBaseHandling db = new DataBaseHandling(this);
        db.openDataBase();
        // Dữ liệu cho ListDuong được lấy từ bảng tbTenDuong
        ListDuong = db.getTenDuong(TenQuan);
    }
    //get ID
    private int GetID(String TenDuong){
        DataBaseHandling db = new DataBaseHandling(this);
        db.openDataBase();
        return db.GetID(TenDuong);
    }
}
