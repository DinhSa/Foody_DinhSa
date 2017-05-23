package com.example.kaios.foody.ThemDiaDiem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.kaios.foody.R;

import java.util.ArrayList;
import java.util.Arrays;

public class LoaiHinhDiaDiem extends AppCompatActivity {
    ListView lv;
    private ArrayAdapter<String> listAdapter ;
    public static String TenLoaiHinh;

    public static String [] prgmNameList={"Sang trọng","Buffet","Nhà hàng","Ăn vặt/vỉa hè",
            "Ăn chay","Café/Dessert","Quán ăn","Bar/Pub","Quán nhậu","Beer club","Tiệm bánh",
            "Tiệc tận nơi","Shop Online","Giao cơm văn phòng","Khu Ẩm Thực","Việt Nam","Châu Mỹ","Mỹ",
            "Tây Âu","Ý","Pháp","Đức","Tây Ban Nha","Trung Hoa","Ấn Độ","Thái Lan","Nhật bản","Hàn Quốc","Malaysia","Quốc tế"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_hinh_dia_diem);
        lv=(ListView)findViewById(R.id.ListLoaiHinh);
        Button Huy=(Button)findViewById(R.id.btn_huy);
        Button Xong=(Button)findViewById(R.id.btn_Xong);

        //tạo ArrayLisst
        final ArrayList<String> ListLoaiHinh = new ArrayList<String>();
        ListLoaiHinh.addAll( Arrays.asList(prgmNameList) );
        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.row_chon_quan, ListLoaiHinh);
        lv.setAdapter( listAdapter );

        //click button hủy
        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //click button xong
        Xong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //sự kiện cklick listview
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TenLoaiHinh=ListLoaiHinh.get(position);
                ThemDiaDiem.ID_danhmuc=position+1;
                ThemDiaDiem.tvLoaiHinhDiaDiem.setText(TenLoaiHinh);
                ThemDiaDiem.chonLoaiHinhDiaDiem=true;

                finish();
            }
        });


    }
}
