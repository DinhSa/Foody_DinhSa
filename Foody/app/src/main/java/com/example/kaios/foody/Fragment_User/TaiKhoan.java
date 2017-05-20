package com.example.kaios.foody.Fragment_User;

import org.json.JSONException;
import org.json.JSONObject;


public class TaiKhoan {
    private String Email;
    private String Pass;
    private String Name;

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
}
