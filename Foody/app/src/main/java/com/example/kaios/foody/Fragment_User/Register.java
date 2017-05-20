package com.example.kaios.foody.Fragment_User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kaios.foody.Clients.FoodyClient;
import com.example.kaios.foody.Fragment_main.Fragment_User;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kaios on 5/16/2017.
 */

public class Register extends Activity {
    ProgressDialog prgDialog;
    EditText etxtTaiKhoanMailDangKy;
    EditText etxtMatKhauDangKy;
    EditText etxtXacNhanMatKhau;
    EditText etxtTenHienThi;
    Button btnDangKyTaiKhoan;
    Button btnDangNhap;
    ImageView BackDK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        etxtTaiKhoanMailDangKy = (EditText) findViewById(R.id.etxtTaiKhoanMailDangKy);
        etxtMatKhauDangKy = (EditText) findViewById(R.id.etxtMatKhauDangKy);
        etxtXacNhanMatKhau = (EditText) findViewById(R.id.etxtXacNhanMatKhau);
        etxtTenHienThi = (EditText) findViewById(R.id.etxtTenHienThi);
        btnDangKyTaiKhoan = (Button) findViewById(R.id.btnDangKyTaiKhoan);
        btnDangNhap=(Button)findViewById(R.id.btnDangNhap);
        BackDK = (ImageView) findViewById(R.id.imgThoatManHinhDangKy);

        //click back
        BackDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //click register
        btnDangKyTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = etxtTaiKhoanMailDangKy.getText().toString();
                String Pass = etxtMatKhauDangKy.getText().toString();
                String ConfirmPass= etxtXacNhanMatKhau.getText().toString();
                String Name = etxtTenHienThi.getText().toString();
                // Instantiate Progress Dialog object
                prgDialog = new ProgressDialog(Register.this);
                // Set Progress Dialog Text
                prgDialog.setMessage("Vui lòng chờ...");
                // Set Cancelable as False
                prgDialog.setCancelable(false);
                //call Register
                registerUser(Email,Pass, ConfirmPass, Name);
            }
        });

        //Click button Đăng Nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToLogin();
            }
        });
    }

    //Rgister
    public void registerUser(String Email, String Pass, String ConfirmPass, final String Name){

        RequestParams params = new RequestParams();
        // When Name Edit View, Email Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(Email) && Utility.isNotNull(Pass) && Utility.isNotNull(Name)&& Utility.isNotNull(ConfirmPass)){
            // When Email entered is Valid
            if(Utility.validate(Email)) {
                // When Pass entered is Valid
                if(Utility.validatePasslenght(Pass)){
                        // When Pass entered is Valid
                    if(Utility.validatePass(Pass,ConfirmPass)){

                        // Show Progress Dialog
                        prgDialog.show();
                        // Put Http parameter name with value of Email Edit View control
                        params.put("Email", Email);
                        // Put Http parameter username with value of Pass Edit View control
                        params.put("pass", Pass);
                        // Put Http parameter password with value of name Edit View control
                        params.put("name", Name);
                        // Invoke RESTful Web Service with Http parameters
                        FoodyClient client = new FoodyClient();
                        client.register("api/User/Register", params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                MainActivity.islogin=true;
                                Fragment_User.NameUser= Name;
                                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                ToMain();
                                // hide Progress Dialog
                                prgDialog.dismiss();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            }
                        });
                    }

                    // When Email is invalid
                    else{
                        Toast.makeText(getApplicationContext(), "PassWord doesn't match!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "PassWord ít nhất 4 ký tự!", Toast.LENGTH_LONG).show();
                }
            }
            else {

                Toast.makeText(getApplicationContext(), "Email không hợp lệ!", Toast.LENGTH_LONG).show();
            }
        }
        // When any of the Edit View control left blank
        else{
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
        }

    }

    public void ToLogin(){
        Intent loginIntent = new Intent(getApplicationContext(),Login_main.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

    public void ToMain(){
        Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }
}
