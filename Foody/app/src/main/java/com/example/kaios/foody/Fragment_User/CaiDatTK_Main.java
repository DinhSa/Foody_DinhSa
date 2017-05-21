package com.example.kaios.foody.Fragment_User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaios.foody.R;

public class CaiDatTK_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat_tk__main);
        ImageView imgBack=(ImageView)findViewById(R.id.back_thietlap);
        TextView DoiPass=(TextView)findViewById(R.id.tv_matkhau);
        TextView DoiHinhDaiDien=(TextView)findViewById(R.id.tv_hinhdaidien);

        //click button Back
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Click đổi mật khẩu
        DoiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDatTK_Main.this, DoiPassWord.class);
                startActivity(intent);
            }
        });

        //Click đổi hình đại diện
        DoiHinhDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDatTK_Main.this, DoiHinhDaiDien.class);
                startActivity(intent);
            }
        });

    }
}
