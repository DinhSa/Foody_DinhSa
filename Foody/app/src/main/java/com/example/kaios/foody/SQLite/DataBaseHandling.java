package com.example.kaios.foody.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.kaios.foody.MonAn;
import com.example.kaios.foody.QuanAn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by kaios on 4/10/2017.
 */

public class DataBaseHandling extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "QuanLyFoodyDB.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context ctx;
    public DataBaseHandling(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }


    //copy DB from assets
    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = getDatabasePath();

        // if the path doesn't exist first, create it
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    //get path
    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    //Open DB
    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    // Getting All Department
    public ArrayList<String> getTenQuan() {
        // Select All Query
        ArrayList<String> alDept = new ArrayList<>();
        alDept.clear();
        String sql= "SELECT TenQuan FROM QuanHuyen WHERE MaTP= 0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                // Adding Department to list
                alDept.add(c.getString(0));
            } while (c.moveToNext());
        }

        db.close();
        c.close();
        return alDept;
    }

    // Getting All Department
    public ArrayList<String> getTenDuong(String tenquan) {
        // Select All Query
        ArrayList<String> alEmp = new ArrayList<>();
        alEmp.clear();
        String sql = "SELECT TenDuong\n" +
                "FROM QuanHuyen inner join DuongQuan on QuanHuyen.MaQuan=DuongQuan.MaQuan\n" +
                "WHERE TenQuan='" + tenquan + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                // Adding employee to list
                alEmp.add( c.getString(0));
            } while (c.moveToNext());
        }

        db.close();
        c.close();
        return alEmp;
    }

    //get quán ăn
    public ArrayList<QuanAn> getQuanAn(){
        ArrayList<QuanAn> listQuanAn = new ArrayList<>();
        listQuanAn.clear();
        String sql= "SELECT TenQuanAn, DiaChi, TenDuong, DiemQuan, HinhAnh\n" +
                "FROM DuongQuan inner join QuanAn on DuongQuan.MaDuong=QuanAn.MaDuong\n" +
                "WHERE QuanAn.MaDanhMuc=1";

        //thực thi lệnh sql
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String tenQuan = c.getString(0);
                String diachiQuan = c.getString(1);
                String tenThanhPho = c.getString(2);
                String diemQuan = c.getString(3);
                byte[] hinhQuan = c.getBlob(4);
                Bitmap bmp_hinhQuan = BitmapFactory.decodeByteArray(hinhQuan, 0, hinhQuan.length);
                listQuanAn.add(new QuanAn(diemQuan, tenQuan, diachiQuan, tenThanhPho, bmp_hinhQuan));
            } while (c.moveToNext());
        }

        db.close();
        c.close();
        return listQuanAn;
    }

    public ArrayList<MonAn> getMonAn(){
        ArrayList<MonAn> listMonAn = new ArrayList<>();
        listMonAn.clear();
        String sql= "SELECT TenMonAn, TenQuanAn, DiaChi, TenDuong, MonAn.HinhAnh\n" +
                "FROM MonAn, QuanAn, DuongQuan\n" +
                "WHERE  MonAn.MaQuanAn=QuanAn.MaQuanAn\n" +
                "and DuongQuan.MaDuong=QuanAn.MaDuong\n" +
                "and QuanAn.MaDanhMuc=1";

        //thực thi lệnh sql
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String tenMonAn = c.getString(0);
                String tenQuan = c.getString(1);
                String diachi = c.getString(2);
                String tenduong = c.getString(3);
                byte[] hinhMonAn = c.getBlob(4);
                Bitmap bmp_hinhMonAn = BitmapFactory.decodeByteArray(hinhMonAn, 0, hinhMonAn.length);

                listMonAn.add(new MonAn(bmp_hinhMonAn, tenMonAn, tenQuan, diachi, tenduong));
            } while (c.moveToNext());
        }

        db.close();
        c.close();
        return listMonAn;
    }

}
