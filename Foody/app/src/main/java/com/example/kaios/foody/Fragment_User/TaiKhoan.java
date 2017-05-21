package com.example.kaios.foody.Fragment_User;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.extras.Base64;


public class TaiKhoan {
    private String Email;
    private String Pass;
    private String Name;
    Bitmap HinhDaiDien;


    public TaiKhoan(String Email, String Pass, String Name) {
        this.Email = Email;
        this.Pass = Pass;
        this.Name = Name;
    }

    public TaiKhoan(JSONObject object) {
        try {
            this.Email = object.getString("TaiKhoan1");
            this.Pass = object.getString("MatKhau");
            this.Name = object.getString("TenHienThi");
            //Xử lý hình ảnh
            byte[] byteArray =  Base64.decode(object.getString("HinhDaiDien"), Base64.DEFAULT) ;
            BitmapFactory.Options options = new BitmapFactory.Options();
            //options.inSampleSize = 2;
            this.HinhDaiDien =  BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public Bitmap getHinhDaiDien() {
        return HinhDaiDien;
    }

    public void setHinhDaiDien(Bitmap HinhDaiDien){
        this.HinhDaiDien=HinhDaiDien;
    }
}
