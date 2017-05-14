package com.example.kaios.foody;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.extras.Base64;

public class QuanAn {
    private int ID;
    private double tvDiem;
    private String tvTen;
    private String tvDiaDiem;
    private String tvTenDuong;
    private String tvQuanHuyen;
    private String tvThanhPho;
    private Bitmap imgHinh;


    public QuanAn(JSONObject object) {
        try {
            this.ID=object.getInt("id");
            this.tvDiem=object.getDouble("Diem");
            this.tvTen = object.getString("TenQuan");
            this.tvDiaDiem = object.getString("DiaChi");
            this.tvTenDuong = object.getString("TenDuong");
            this.tvQuanHuyen = object.getString("TenQuanHuyen");
            this.tvThanhPho = object.getString("TenThanhPho");
            //Xử lý hình ảnh
            byte[] byteArray =  Base64.decode(object.getString("HinhAnh"), Base64.DEFAULT) ;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            this.imgHinh =  BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public QuanAn(double tvDiem, String tvTen, String tvDiaDiem, String tvTenDuong, String tvQuanHuyen, String tvThanhPho) {
        this.tvDiem = tvDiem;
        this.tvTen = tvTen;
        this.tvDiaDiem = tvDiaDiem;
        this.tvTenDuong= tvTenDuong;
        this.tvQuanHuyen=tvQuanHuyen;
        this.tvThanhPho = tvThanhPho;
    }

    //Get
    public int getID(){
        return this.ID;
    }

    public double getTvDiem() {
        return this.tvDiem;
    }

    public String getTvTen() {
        return this.tvTen;
    }

    public String getTvDiaDiem() {
        return this.tvDiaDiem;
    }

    public String getTvTenDuong(){
        return this.tvTenDuong;
    }

    public String getTvQuanHuyen() {
        return this.tvQuanHuyen;
    }

    public String getTvThanhPho() {
        return this.tvThanhPho;
    }

    public Bitmap getImgHinh() {
        return this.imgHinh;
    }

    //Set
    public void setTvDiem(double tvDiem) {
        this.tvDiem = tvDiem;
    }

    public void setTvTen(String tvTen) {
        this.tvTen = tvTen;
    }

    public void setTvDiaDiem(String tvDiaDiem) {
        this.tvDiaDiem = tvDiaDiem;
    }

    public void setTvTenDuong(String tvTenDuong){
        this.tvTenDuong=tvTenDuong;
    }

    public void setTvQuanHuyen(String tvQuanHuyen){
        this.tvQuanHuyen=tvQuanHuyen;
    }

    public void setTvThanhPho(String tvThanhPho) {
        this.tvThanhPho = tvThanhPho;
    }

    public void setImgHinh(Bitmap imgHinh) {
        this.imgHinh = imgHinh;
    }
}
