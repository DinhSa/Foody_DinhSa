package com.example.kaios.foody.ThemDiaDiem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.kaios.foody.R;
import com.example.kaios.foody.SQLite.DataBaseHandling;

import java.util.ArrayList;

public class ChonQuanTP extends AppCompatActivity {

    public static String TenQuan_ThemDD = null;
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    ArrayList<String> ListQuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_quan_tp);
        Button BoQua=(Button)findViewById(R.id.btn_boquachonQuan);
        //click button bỏ qua
        BoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.listChonquan );
        loadData(ChonThanhPho.nameTP_ThemDD);

        // Create ArrayAdapter using the ListQuan.
        listAdapter = new ArrayAdapter<String>(this, R.layout.row_chon_quan, ListQuan);

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TenQuan_ThemDD=ListQuan.get(position);//lấy Item tên quận
                ThemDiaDiem.btnQuan.setText(TenQuan_ThemDD);//set name
                ThemDiaDiem.chonquan=true;//đánh dấu đã chọn
                finish();
            }
        });
    }

    //load Data
    private void loadData(String TenThanhPho) {
        DataBaseHandling db = new DataBaseHandling(this);
        db.openDataBase();
        // Dữ liệu cho LisQuan được lấy từ bảng tbTenQuan
        ListQuan = db.getTenQuan(TenThanhPho);
    }
}
