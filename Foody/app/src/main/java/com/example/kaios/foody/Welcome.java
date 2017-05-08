package com.example.kaios.foody;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {
    public ProgressDialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {

            public void run() {
                myDialog = ProgressDialog.show(Welcome.this,"", "Loading", true);

                Intent intent=new Intent(Welcome.this,MainActivity.class);
                Welcome.this.startActivity(intent);
                myDialog.dismiss();
                Welcome.this.finish();
            }

        }, 3000);// 3 Seconds
    }
}
