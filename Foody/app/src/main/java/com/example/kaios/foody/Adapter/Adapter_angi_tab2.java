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
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;

public class Adapter_angi_tab2 extends BaseAdapter {
    private static final int TYPE_HEARDER = 0;
    private static final int TYPE_DM = 1;
    boolean IsPressFirstItem_DanhMuc=false;
    public static String TenDanhMuc="Danh mục";
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public Adapter_angi_tab2(){}

    public Adapter_angi_tab2(Context mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        this.result=prgmNameList;
        this.context=mainActivity;
        this.imageId=prgmImages;
        this.inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return TYPE_HEARDER;
        return TYPE_DM;
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

//    public class Holder
//    {
//        TextView tv;
//        ImageView img;
//    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //Adapter_angi_tab2.Holder holder=new Adapter_angi_tab2.Holder();
        View rowView;
        int rowType = getItemViewType(position);
        if(rowType==TYPE_HEARDER){
            rowView = inflater.inflate(R.layout.danhmuc_header, null);
            TextView tv=(TextView) rowView.findViewById(R.id.danhmuc);
            tv.setText("Danh mục");

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TenDanhMuc="Danh mục"; //lấy tên danh mục
                    TextView txt=(TextView)fragment_angi.mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.tabsText);
                    txt.setText(TenDanhMuc);//đổi tên tab
                    txt.setTextColor(Color.RED); //đổi màu text
                    fragment_angi.mTabHost.setCurrentTab(0);//trở về lại tab 0
                    fragment_angi.click2=false; //click chưa đc click
                    MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView

                }
            });
        }
        else {
            rowView = inflater.inflate(R.layout.adapter__tab2, null);
            TextView tv=(TextView) rowView.findViewById(R.id.textView2);
            ImageView img=(ImageView) rowView.findViewById(R.id.imageView2);
            tv.setText(result[position-1]);
            img.setImageResource(imageId[position-1]);
            //
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TenDanhMuc=result[position-1]; //lấy tên danh mục
                    TextView txt=(TextView) fragment_angi.mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.tabsText);
                    txt.setText(TenDanhMuc);//đổi tên tab
                    txt.setTextColor(Color.RED); //đổi màu text
                    fragment_angi.mTabHost.setCurrentTab(0);//trở về lại tab 0
                    fragment_angi.click2=false; //click chưa đc click
                    MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView

//              Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
                }
            });
        }
        //

        return rowView;
    }

}