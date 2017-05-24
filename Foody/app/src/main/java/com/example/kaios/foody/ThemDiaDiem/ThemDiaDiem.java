package com.example.kaios.foody.ThemDiaDiem;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kaios.foody.Clients.FoodyClient;
import com.example.kaios.foody.Fragment_User.Utility;
import com.example.kaios.foody.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.extras.Base64;

public class ThemDiaDiem extends AppCompatActivity {

    ProgressDialog prgDialog;
    public static Button btnTP;//button chọn thành phố
    public static Button btnQuan;//button chọn quận
    public static TextView tvLoaiHinhDiaDiem;//textView loại hình địa điểm
    public static TextView tvTenDuong;//texview tên đường
    public static boolean chonquan=false; //đánh dấu dã chọn quận
    public static boolean chonLoaiHinhDiaDiem=false; //đánh dấu đã chọn lại địa hình
    public static boolean chonDuong=false;//đánh dấu đã chọn đường
    public static int ID_MaDuong;//mã đường
    public static int ID_danhmuc; //mã danh mục
    String giobatdau = "09:00:00";//giờ bắt đầu
    String gioketthuc = "21:00:00";//giờ kết thúc
    private static int REQUEST_LOAD_IMAGE = 1;//chọn load ảnh từ device
    private static int REQUEST_CAMERA = 2;//chọn load anh từ camera
    private Boolean Changed = false;//cờ đánh dấu đã chọn hình
    private ImageView hinhThemDD;//hiển thị hình
    EditText edtDiaChi;
    EditText edtSDT;
    EditText GiaThap;
    EditText GiaCao;
    EditText edtMota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dia_diem);

        //trỏ tới ID
        ImageView Back=(ImageView)findViewById(R.id.back_themdiadiem);
        btnTP=(Button)findViewById(R.id.btnTP);
        btnQuan=(Button)findViewById(R.id.btnQuan);
        LinearLayout LoaiHinhDiaDiem=(LinearLayout)findViewById(R.id.ln_loaihinhdiadiem);
        tvLoaiHinhDiaDiem=(TextView)findViewById(R.id.tv_loaihinh);
        tvTenDuong=(TextView)findViewById(R.id.tv_TenDuong);
        final TextView GioMoCua=(TextView)findViewById(R.id.txtChonGioBatDau);
        final TextView GioKetThuc=(TextView)findViewById(R.id.txtChonGioKet);
        ImageView LoadDevice=(ImageView)findViewById(R.id.loadDevice);
        ImageView LoadCamera = (ImageView)findViewById(R.id.loadCamera);
        hinhThemDD=(ImageView)findViewById(R.id.HinhThemDiaDiem);
        TextView tvGui =(TextView)findViewById(R.id.tv_Gui);
        final EditText edtTenDiaDiem =(EditText)findViewById(R.id.edt_tendiadiem);
        edtDiaChi= (EditText)findViewById(R.id.edt_diachi);
        edtSDT = (EditText)findViewById(R.id.edt_sdt);
        GiaThap=(EditText)findViewById(R.id.edt_giathap);
        GiaCao=(EditText)findViewById(R.id.edt_giacao);
        edtMota=(EditText)findViewById(R.id.edt_mota);
        final ImageView HinhThem=(ImageView)findViewById(R.id.HinhThemDiaDiem);
        LinearLayout lnChonDuong =(LinearLayout)findViewById(R.id.ln_TenDuong);




        //click Gửi
        tvGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prgDialog = new ProgressDialog(ThemDiaDiem.this);
                // Set Progress Dialog Text
                prgDialog.setMessage("Please wait...");
                // Set Cancelable as False
                prgDialog.setCancelable(false);

                //khai báo giá trị cần thêm
                double Diem = 10;
                double Lat = 9.6;
                double Long = 6.9;
                String ThanhPho=ChonThanhPho.nameTP_ThemDD;
                String Quan= ChonQuanTP.TenQuan_ThemDD;
                String TenDiaDiem = edtTenDiaDiem.getText().toString();
                String LoaihinhDiaDiem=tvLoaiHinhDiaDiem.getText().toString();
                String DiaChi= edtDiaChi.getText().toString();
                String SDT = edtSDT.getText().toString();
                String GiaCao1= GiaCao.getText().toString();
                String GiaThap1= GiaThap.getText().toString();
                String MoTa= edtMota.getText().toString();
                String TimeStart=giobatdau;
                String TimeEnd=gioketthuc;
                Calendar c = Calendar.getInstance();
                String ThoiGianThem = String.valueOf(c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DATE)+" "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));

                double GiaThapNhat;
                double GiaCaoNhat;
                if(Utility.isNotNull(GiaCao1)&&Utility.isNotNull(GiaThap1)){//giá cao nhất và thấp nhất k rỗng
                    GiaThapNhat = Double.parseDouble(GiaThap1);
                    GiaCaoNhat = Double.parseDouble(GiaCao1);
                }
                else{//ngược lại mặc định =0
                    GiaThapNhat= 0;
                    GiaCaoNhat=0;
                }
               //

                String Img;
                if(Changed){//đã load hình
                    //cover img to string
                    Img = getByteArrayFromImageView(hinhThemDD);
                }
                else {//chưa thêm hình
                    Img=null;
                }

                if(chonquan){//kiểm tra chọn quận

                    if(Utility.isNotNull(TenDiaDiem)){//kiểm tra điền địa điểm

                        if(chonLoaiHinhDiaDiem){//kiểm tra chọn danh mục

                            if(Utility.isNotNull(DiaChi)){//kiểm tra điền địa chỉ

                                if(chonDuong){//kiểm tra chọn đường
                                    ThemDiaDiem_Send(TenDiaDiem, DiaChi, Img, SDT , ID_MaDuong, ID_danhmuc, Diem, Lat, Long,
                                            TimeStart, TimeEnd, GiaThapNhat, GiaCaoNhat, MoTa, ThoiGianThem);
                                }
                                else {//chưa chọn đường
                                    Toast.makeText(getApplicationContext(), "Bạn chưa chọn đường!", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {//chưa điền địa chỉ
                                Toast.makeText(getApplicationContext(), "Bạn chưa điền đại chỉ!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {//chưa chọn danh mục
                            Toast.makeText(getApplicationContext(), "Bạn chưa chọn loại hình địa điểm!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {//chưa điền tên quán
                        Toast.makeText(getApplicationContext(), "Bạn chưa điền tên địa điểm!", Toast.LENGTH_LONG).show();
                    }
                }
                else {//chưa chọn quận
                    Toast.makeText(getApplicationContext(), "Bạn chưa chọn quận!", Toast.LENGTH_LONG).show();
                }
            }
        });



        //click Back
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonThanhPho.nameTP_ThemDD="TP.HCM";//reset tên thành phố
                chonquan=false;//trả về false khi back
                chonLoaiHinhDiaDiem=false;//trả về false khi back
                chonDuong=false;
                finish();
            }
        });
        //click button chọn thành phố
        btnTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemDiaDiem.this, ChonThanhPho.class);
                startActivity(intent);
            }
        });
        //click button chọn quận
        btnQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemDiaDiem.this, ChonQuanTP.class);
                startActivity(intent);
            }
        });
        //Click loại hìnhđịa điểm
        LoaiHinhDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemDiaDiem.this, LoaiHinhDiaDiem.class);
                startActivity(intent);
            }
        });
        //click chọn đường
        lnChonDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemDiaDiem.this, ChonDuong.class);
                startActivity(intent);
            }
        });
        //click chọn giờ bắt đầu
        GioMoCua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog(GioMoCua,9, 0, false);
            }
        });
        //click chọn giờ kết thúc
        GioKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog(GioKetThuc,21, 0, false);
            }
        });
        //click loadImg
        LoadDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImagefromGallery();
            }
        });
        //click loadCamera
        LoadCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImagefromCamera();
            }
        });


    }

    //show clock
    public void TimePickerDialog(final TextView time, int hour, int minute, boolean is24hour) {
        TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.setText(convert(hourOfDay, minute));
                if (time.getId() == R.id.txtChonGioBatDau)
                    giobatdau = pad(hourOfDay)+":"+pad(minute)+":00";
                else
                    gioketthuc = pad(hourOfDay)+":"+pad(minute)+":00";
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timePickerListener, hour, minute, is24hour);
        timePickerDialog.show();
    }
    public String convert(int h, int m) {
        if (h > 12) {
            return pad(h - 12)+":"+pad(m)+" PM";
        }
        else {
            return pad(h)+":"+pad(m)+" AM";
        }
    }
    public String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
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
                    hinhThemDD.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    Changed = true;
                }
                if (requestCode == REQUEST_CAMERA && null != data) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    hinhThemDD.setImageBitmap(photo);
                    Changed = true;
                }

            } else {
                Toast.makeText(this, "Chưa chọn hình", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Yêu cầu thất bại", Toast.LENGTH_LONG).show();
        }
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
    //Xử lý gửi
    public void ThemDiaDiem_Send(String TenDiaDiem, String DiaChi, String img, String SDT, int MaDuong,
                                  int ID_danhmuc, double Diem, double Lat, double Long,
                                   String TimeStart, String TimeEnd, double GiaThapNhat, double GiaCaoNhat, String MoTa, String ThoiGianThem){

        prgDialog.show();
        StringEntity entity = null;
        try {
            JSONObject object = new JSONObject();
            //put param to service
            object.put("TenDiaDiem", TenDiaDiem);
            object.put("DiaChi", DiaChi);
            object.put("Img", img);
            object.put("SDT", SDT);
            object.put("MaDuong",MaDuong);
            object.put("MaDanhMuc", ID_danhmuc);
            object.put("Diem", Diem);
            object.put("Lat", Lat);
            object.put("Long", Long);
            object.put("GioBatDau", TimeStart);
            object.put("GioKetThuc", TimeEnd);
            object.put("GiaThapNhat", GiaThapNhat);
            object.put("GiaCaoNhat", GiaCaoNhat);
            object.put("MoTa", MoTa);
            object.put("ThoiGianThem", ThoiGianThem);
            entity = new StringEntity(object.toString(), "utf-8");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FoodyClient Them = new FoodyClient();
        Them.ThemDiaDiem(getApplicationContext(), "api/ThemDiaDiem/ThemDiaDiemQuanAn", entity, "application/json; charset=utf-8", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Boolean isThem = Boolean.valueOf(new String(responseBody,"UTF-8"));//trả về true or false
                    if(isThem){//true
                        Toast.makeText(getApplicationContext(), "Bạn đã thêm thành công!", Toast.LENGTH_LONG).show();
                        prgDialog.dismiss();//ẩn ProgressDialog
                    }
                    else {//false
                        Toast.makeText(getApplicationContext(), "Thêm thất bại!", Toast.LENGTH_LONG).show();
                        prgDialog.dismiss();//ẩn ProgressDialog
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Lỗi!", Toast.LENGTH_LONG).show();
                prgDialog.dismiss();//ẩn ProgressDialog
            }
        });

    }
}
