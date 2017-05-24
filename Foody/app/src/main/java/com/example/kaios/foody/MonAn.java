package com.example.kaios.foody;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.extras.Base64;


public class MonAn {
    private int ID;
    private String tvTenMonAn;
    private String tvTenQuanMonAn;
    private String tvDiaDiemQuanMonAn;
    private String tvTenDuong;
    private String tvQuanHuyen;
    private String tvThanhPho;
    private Bitmap imgHinh;

    public MonAn(String tvTenMonAn, String tvTenQuanMonAn, String tvDiaDiemQuanMonAn,
                 String tvTenDuong, String tvQuanHuyen, String tvThanhPho ) {
        this.tvTenMonAn = tvTenMonAn;
        this.tvTenQuanMonAn = tvTenQuanMonAn;
        this.tvDiaDiemQuanMonAn = tvDiaDiemQuanMonAn;
        this.tvTenDuong = tvTenDuong;
        this.tvQuanHuyen = tvQuanHuyen;
        this.tvThanhPho = tvThanhPho;
    }

    public MonAn(JSONObject object) {
        try {
            this.ID=object.getInt("id");
            this.tvTenMonAn = object.getString("TenMonAn");//lấy tên món ăn
            this.tvTenQuanMonAn = object.getString("TenQuan");//
            this.tvDiaDiemQuanMonAn = object.getString("DiaChi");
            this.tvTenDuong = object.getString("TenDuong");
            this.tvQuanHuyen = object.getString("TenQuanHuyen");
            this.tvThanhPho = object.getString("TenThanhPho");
            //Xử lý hình ảnh
            byte[] byteArray =  Base64.decode(object.getString("HinhAnh"), Base64.DEFAULT) ;
            BitmapFactory.Options options = new BitmapFactory.Options();
            this.imgHinh =  BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getID(){
        return this.ID;
    }

    public String getTvTenMonAn() {
        return tvTenMonAn;
    }

    public String getTvTenQuanMonAn() {
        return tvTenQuanMonAn;
    }

    public String getTvDiaDiemQuanMonAn() {
        return tvDiaDiemQuanMonAn;
    }

    public String getTvTenDuong() {
        return tvTenDuong;
    }

    public String getTvQuanHuyen(){
        return this.tvQuanHuyen;
    }

    public String getTvThanhPho(){
        return this.tvThanhPho;
    }

    public Bitmap getImgHinh() {
        return imgHinh;
    }


//Set
    public void setImgHinh(Bitmap imgHinh) {
        this.imgHinh = imgHinh;
    }

    public void setTvTenMonAn(String tvTenMonAn) {
        this.tvTenMonAn = tvTenMonAn;
    }

    public void setTvTenQuanMonAn(String tvTenQuanMonAn) {
        this.tvTenQuanMonAn = tvTenQuanMonAn;
    }

    public void setTvDiaDiemQuanMonAn(String tvDiaDiemQuanMonAn) {
        this.tvDiaDiemQuanMonAn = tvDiaDiemQuanMonAn;
    }

    public void setTvTenDuong(String tvTenDuong) {
        this.tvTenDuong = tvTenDuong;
    }

    public void setTvQuanHuyen(String tvQuanHuyen) {
       this.tvQuanHuyen = tvQuanHuyen;
    }

    public void setTvThanhPho(String tvThanhPho) {
        this.tvThanhPho = tvThanhPho;
    }
}
