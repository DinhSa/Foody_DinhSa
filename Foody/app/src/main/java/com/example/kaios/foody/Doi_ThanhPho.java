package com.example.kaios.foody;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaios.foody.Adapter.Adapter_DoiTP;
import com.example.kaios.foody.SQLite.DataBaseHandling;

import java.util.ArrayList;

/**
 * Created by kaios on 4/17/2017.
 */

public class Doi_ThanhPho extends Activity {

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
        list=(ListView)findViewById(R.id.listTP);
        list.setAdapter(new Adapter_DoiTP(this, listTinhThanh));

        Back=(TextView)findViewById(R.id.backTP);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
