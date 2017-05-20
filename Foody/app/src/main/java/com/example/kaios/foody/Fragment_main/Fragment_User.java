package com.example.kaios.foody.Fragment_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaios.foody.CaiDatTK_Main;
import com.example.kaios.foody.Fragment_User.Login_main;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;

public class Fragment_User extends Fragment {
    public static String NameUser="Đăng Nhập";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment__user, container, false);

        TextView tvlogin= (TextView)v.findViewById(R.id.tv_login);
        ImageView btnlogin= (ImageView) v.findViewById(R.id.btn_login_user);
        LinearLayout longout=(LinearLayout) v.findViewById(R.id.tv_DangXuat);
        TextView EditUser=(TextView)v.findViewById(R.id.tv_3_1) ;
        tvlogin.setText(NameUser);

        if(!MainActivity.islogin){
            //ẩn textview Longout khi chưa đăng nhập
            longout.setVisibility(View.GONE);
        }
        else {
            longout.setVisibility(View.VISIBLE);
            longout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.islogin=false;
                    Fragment_User.NameUser= "Đăng Nhập";
                    ToMain();
                }
            });
        }

        //click longin
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login_main.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login_main.class);
                startActivity(intent);
            }
        });

        //Click Thiết lập tài khoản
        EditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.islogin){
                    Intent intent = new Intent(getActivity(), CaiDatTK_Main.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "Vui lòng đăng nhập!", Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }


    public void ToMain(){
        Intent loginIntent = new Intent(getActivity(),MainActivity.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }
}