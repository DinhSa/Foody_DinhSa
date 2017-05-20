package com.example.kaios.foody;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CaiDatTK_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat_tk__main);
        ImageView imgBack=(ImageView)findViewById(R.id.back_thietlap);
        TextView DoiPass=(TextView)findViewById(R.id.tv_matkhau);

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

    }
}
