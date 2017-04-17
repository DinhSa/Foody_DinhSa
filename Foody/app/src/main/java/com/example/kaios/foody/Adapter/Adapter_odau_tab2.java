package com.example.kaios.foody.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;

public class Adapter_odau_tab2 extends BaseAdapter {
    public static String nameDanhMuc="Sang trọng";
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public Adapter_odau_tab2(){}

    public Adapter_odau_tab2(Context mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
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
        Adapter_odau_tab2.Holder holder=new Adapter_odau_tab2.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.adapter__tab2, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView2);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView2);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                nameDanhMuc=result[position]; //lấy tên danh mục
                TextView txt=(TextView)fragment_odau.mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.tabsText);
                txt.setText(nameDanhMuc);//đổi tên tab
                txt.setTextColor(Color.RED); //đổi màu text
                fragment_odau.mTabHost.setCurrentTab(0);//trở về lại tab 0
                fragment_odau.click2=false; //click chưa đc click
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView

//                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}