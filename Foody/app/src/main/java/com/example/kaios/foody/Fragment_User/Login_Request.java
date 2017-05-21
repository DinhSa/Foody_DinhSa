package com.example.kaios.foody.Fragment_User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kaios.foody.Clients.FoodyClient;
import com.example.kaios.foody.Fragment_main.Fragment_User;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login_Request extends AppCompatActivity {

    ProgressDialog prgDialog;
    ImageView Back;
    EditText EdtEmail;
    EditText EdtPass;
    Button btnLogin;
    Button btnDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__request);
        Back=(ImageView)findViewById(R.id.BackDN);
        EdtEmail=(EditText)findViewById(R.id.EdtEmail);
        EdtPass=(EditText)findViewById(R.id.EdtPass);
        btnLogin=(Button)findViewById(R.id.btnDN);
        btnDK=(Button)findViewById(R.id.btnDK);
        //Click button Back
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //CLick Button Đăng Nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email= EdtEmail.getText().toString();
                String Pass=EdtPass.getText().toString();
                // Instantiate Progress Dialog object
                prgDialog = new ProgressDialog(Login_Request.this);
                // Set Progress Dialog Text
                prgDialog.setMessage("Please wait...");
                // Set Cancelable as False
                prgDialog.setCancelable(false);
                //
                Login(Email,Pass);

            }
        });
        //Click button Đăng Ký
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToRegister();
                finish();
            }
        });
    }
    public void Login(String Email, String Pass){
        if(Utility.isNotNull(Email) && Utility.isNotNull(Pass)){
            RequestParams params = new RequestParams();
            prgDialog.show();
            params.put("email", Email);
            params.put("pass", Pass);
            FoodyClient client = new FoodyClient();
            client.register("api/User/Login", params, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    MainActivity.islogin=true; //đánh dấu đã đăng nhập
                    TaiKhoan taikhoan =new TaiKhoan(response);
                    Fragment_User.HinhBitmap=taikhoan.getHinhDaiDien();//get anh dại diện
                    Fragment_User.NameUser= taikhoan.getName();//get Tên hiển thị
                    DoiPassWord.Email=taikhoan.getEmail();//get Email
                    // hide Progress Dialog
                    prgDialog.dismiss();
                    ToMain();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_LONG).show();
                    prgDialog.dismiss();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
        }


    }

    public void ToMain(){
        Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

    public void ToRegister(){
        Intent loginIntent = new Intent(getApplicationContext(),Register.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

}
