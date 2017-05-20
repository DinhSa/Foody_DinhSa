package com.example.kaios.foody.Fragment_User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;

public class Login_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_main);
        ImageView BackLogin=(ImageView)findViewById(R.id.back_login);
        Button btnDangKyTaiKhoan = (Button) findViewById(R.id.btnDangKyTaiKhoan);
        Button btnLoginEmail=(Button)findViewById(R.id.btnLoginEmail);
        BackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDangKyTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_main.this, Register.class);
                startActivity(intent);
            }
        });

        btnLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_main.this, Login_Request.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MainActivity.islogin)
            finish();
    }
}
