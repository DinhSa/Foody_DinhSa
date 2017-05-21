package com.example.kaios.foody.Fragment_User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.kaios.foody.Clients.FoodyClient;
import com.example.kaios.foody.Fragment_main.Fragment_User;
import com.example.kaios.foody.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.extras.Base64;

public class DoiHinhDaiDien extends AppCompatActivity {
    ProgressDialog prgDialog;
    private static int REQUEST_LOAD_IMAGE = 1;
    private static int REQUEST_CAMERA = 2;
    ImageView imgHinhDaiDien;
    private static Boolean Change = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_hinh_dai_dien);
        imgHinhDaiDien = (ImageView) findViewById(R.id.imgHinhDaiDien);
        final LinearLayout DoiDaiDien=(LinearLayout)findViewById(R.id.ln_DoiDaiDien);
        final LinearLayout DoiAnhBia=(LinearLayout)findViewById(R.id.ln_HinhBia);
        Button LuuThayDoiAnh=(Button)findViewById(R.id.btn_luuthaydoi_anh);
        ImageView Back=(ImageView)findViewById(R.id.back_doianhdaidien);

        //hiển thị ảnh đại diện
        if(Fragment_User.HinhBitmap==null){
            imgHinhDaiDien.setImageResource(R.mipmap.ic_daidien);
        }
        else {
            imgHinhDaiDien.setImageBitmap(Fragment_User.HinhBitmap);
        }
        //Click Back
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Click Đổi ảnh dại diện
        DoiDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuImage(DoiDaiDien);
            }
        });
        //Click Dổi ảnh bìa
        DoiAnhBia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuImage(DoiAnhBia);
            }
        });
        //click button lưu thay đổi
        LuuThayDoiAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prgDialog = new ProgressDialog(DoiHinhDaiDien.this);
                // Set Progress Dialog Text
                prgDialog.setMessage("Please wait...");
                // Set Cancelable as False
                prgDialog.setCancelable(false);
                String img = getByteArrayFromImageView(imgHinhDaiDien);
                if(Change){
                    ChangeImg(DoiPassWord.Email,img);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Bạn chưa chọn hình!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_LOAD_IMAGE && null != data) {
                    // Get the Image from data

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();

                    // Set the Image in ImageView after decoding the String
                    imgHinhDaiDien.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    Change = true;
                }
                if (requestCode == REQUEST_CAMERA && null != data) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imgHinhDaiDien.setImageBitmap(photo);
                    Change = true;
                }

            } else {
                Toast.makeText(this, "Bạn hưa chọn hình", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Thất bại", Toast.LENGTH_LONG).show();
        }
    }


    //change image
    public void ChangeImg(String Email, final String img){
        prgDialog.show();
        StringEntity entity = null;
        try {
            JSONObject object = new JSONObject();
            object.put("TaiKhoan", Email);
            object.put("HinhDaiDien", img);
            entity = new StringEntity(object.toString());
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        FoodyClient Doihinh = new FoodyClient();
        Doihinh.changeimage(getApplicationContext(), "api/User/DoiHinhDaiDien", entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Boolean Changed = Boolean.valueOf(new String(responseBody,"UTF-8"));
                    if(Changed){
                        byte[] byteArray =  Base64.decode(img, Base64.DEFAULT) ;
                        Fragment_User.HinhBitmap=BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);//cập nhật ImageView
                        Change=false;
                        Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_LONG).show();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                prgDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Kết Nối Thất Bại", Toast.LENGTH_LONG).show();
                prgDialog.dismiss();
            }
        });
    }
    //load ảnh từ device
    public void loadImagefromGallery() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, REQUEST_LOAD_IMAGE);
    }
    //load ảnh từ camera
    private void loadImagefromCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }
    //menu chọn ảnh
    public void MenuImage(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenu().add(1, 1, 1, "Chọn hình từ thư viện");
        popupMenu.getMenu().add(1, 2, 1, "Chụp ảnh");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == 1) {
                    //Toast.makeText(getApplicationContext(), "Chọn ảnh từ thư viện", Toast.LENGTH_SHORT).show();
                    loadImagefromGallery();
                }
                else {
                    //Toast.makeText(getApplicationContext(), "Chụp ảnh", Toast.LENGTH_SHORT).show();
                    loadImagefromCamera();
                }
                return true;
            }
        });
        popupMenu.show();

    }
    //get bye IMG
    private String getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        String s = Base64.encodeToString(byteArray,Base64.DEFAULT);
        return s;
    }
}
