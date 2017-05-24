package com.example.kaios.foody;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.kaios.foody.Fragment_main.Fragment_Bell;
import com.example.kaios.foody.Fragment_main.Fragment_Collection;
import com.example.kaios.foody.Fragment_main.Fragment_Search;
import com.example.kaios.foody.Fragment_main.Fragment_User;
import com.example.kaios.foody.Fragment_main.Fragment_home;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
   public static BottomBar mBottomBar;//bottombar
    public static boolean islogin=false;//kiểm tra đăng nhập hay chưa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomBar=(BottomBar)findViewById(R.id.bottom_bar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            Fragment selectedFragment;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home://chọn home
                        selectedFragment = new Fragment_home();
                        break;
                    case R.id.tab_collection: //chọn bộ sưu tập
                        selectedFragment = new Fragment_Collection();
                        break;
                    case R.id.tab_search://chọn tìm kiếm
                        selectedFragment = new Fragment_Search();
                        break;
                    case R.id.tab_bell://chịn thông báo
                        selectedFragment = new Fragment_Bell();
                        break;
                    case R.id.tab_user://chọn user
                        selectedFragment = new Fragment_User();
                        break;

                }

                //fill Fragment into frameLayout
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_main,selectedFragment);
                transaction.commit();
            }


        });

//        //Manually displaying the first fragment - one time only
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_layout_main, NV_Taikhoan.newInstance());
//        transaction.commit();

    }
}
