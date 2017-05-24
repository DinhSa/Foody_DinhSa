package com.example.kaios.foody.Fragment_User;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaios.foody.Clients.FoodyClient;
import com.example.kaios.foody.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class DoiPassWord extends AppCompatActivity {
    public static String Email;
    private String nameEmailDangKy="Email đăng ký: " +Email;
    public ProgressDialog prgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_pass_word);

        final EditText Oldpass=(EditText)findViewById(R.id.edt_oldpass);
        final EditText newpass=(EditText)findViewById(R.id.edit_passnew);
        final EditText confirm=(EditText)findViewById(R.id.edit_confirm);
        Button btnSave=(Button)findViewById(R.id.btn_luuthaydoi);

        ImageView BackDoiPass=(ImageView)findViewById(R.id.back_doipass);
        TextView EmailDangKy=(TextView)findViewById(R.id.tv_emailDangKy);
        EmailDangKy.setText(nameEmailDangKy);

        //click button Back
        BackDoiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Click button lưu thay đổi
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Old=Oldpass.getText().toString();
                String New=newpass.getText().toString();
                String Confirm=confirm.getText().toString();
                prgDialog = new ProgressDialog(DoiPassWord.this);
                // Set Progress Dialog Text
                prgDialog.setMessage("Vui lòng chờ...");
                // Set Cancelable as False
                prgDialog.setCancelable(false);
                DoiPass(Old,New,Confirm);
            }
        });
    }

    private void DoiPass(String old, String NewPass, String Confirm){
        if(Utility.isNotNull(old)&&Utility.isNotNull(NewPass)&&Utility.isNotNull(Confirm)){
            if(Utility.validatePasslenght(NewPass)){
                if(Utility.validatePass(NewPass,Confirm)){
                    prgDialog.show();//show dialog
                    RequestParams params = new RequestParams();
                    // Put Http parameter username with value of Pass Edit View control
                    params.put("Email", Email);
                    params.put("NewPass", NewPass);
                    params.put("OldPass", old);
                    // Invoke RESTful Web Service with Http parameters
                    FoodyClient client = new FoodyClient();
                    client.ChangePass("api/User/ChangePass", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            prgDialog.dismiss();//ẩn pdialog
                            try {
                                Boolean changed = Boolean.valueOf(new String(responseBody,"UTF-8"));
                                if (changed)//trả về true
                                    Toast.makeText(getApplicationContext(), "PassWord đã được đổi!", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(), "Pass cũ không đúng!", Toast.LENGTH_LONG).show();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            prgDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Lỗi!", Toast.LENGTH_LONG).show();
                        }

                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "PassWord không khớp!", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "PassWord ít nhất 4 ký tự!", Toast.LENGTH_LONG).show();
            }

        }
        else {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
        }
    }
}
