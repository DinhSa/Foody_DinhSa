package com.example.kaios.foody.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaios.foody.Fragment_angi_odau.fragment_angi;
import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;

public class Adapter_Tab1_angi extends BaseAdapter {
    public static String nameDanhMuc="Mới Nhất";

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public Adapter_Tab1_angi(){}

    public Adapter_Tab1_angi(Context mainActivity, String[] prgmNameList, int[] prgmImages) {
        this.result=prgmNameList;
        this.context=mainActivity;
        this.imageId=prgmImages;
        this.inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Adapter_Tab1_angi.Holder holder=new Adapter_Tab1_angi.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.adapter__tab1, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                nameDanhMuc=result[position]; //lấy tên danh mục
                TextView txt=(TextView) fragment_angi.mTabHost.getTabWidget().getChildAt(1).findViewById(R.id.tabsText);
                txt.setText(nameDanhMuc);//đổi tên tab
                txt.setTextColor(Color.RED); //đổi màu text
                fragment_angi.mTabHost.setCurrentTab(0);//trở về lại tab 0
                fragment_odau.click1=false; //click chưa đc click
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView
            }
        });
        return rowView;
    }

}