package com.example.kaios.foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kaios.foody.Doi_ThanhPho;
import com.example.kaios.foody.R;

import java.util.ArrayList;

/**
 * Created by kaios on 4/17/2017.
 */

public class Adapter_DoiTP extends BaseAdapter {

    private static final int TYPE_HEARDER = 0;
    private static final int TYPE_TP = 1;


    ArrayList<String> result;
    Context context;
    private static LayoutInflater inflater=null;

    public Adapter_DoiTP(){}

    public Adapter_DoiTP(Doi_ThanhPho mainActivity, ArrayList<String> prgmNameList) {
        this.result=prgmNameList;
        this.context=mainActivity;
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
        return TYPE_TP;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
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
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Adapter_DoiTP.Holder holder=new Adapter_DoiTP.Holder();
        final View rowView;
        int rowType = getItemViewType(position);
        if(rowType == TYPE_HEARDER){
            rowView = inflater.inflate(R.layout.adapter_doi_thanhpho2, null);
            holder.tv=(TextView)rowView.findViewById(R.id.search);
            holder.tv=(TextView)rowView.findViewById(R.id.local);
            holder.tv=(TextView)rowView.findViewById(R.id.vn);
        }
        else {
            rowView = inflater.inflate(R.layout.adapter_doi_thanhpho, null);
            holder.tv=(TextView) rowView.findViewById(R.id.DoiTP);
            holder.tv.setText(result.get(position-1));
//            rowView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // TODO Auto-generated method stub
////                    nameTP=result.get(position-1);//lấy tên tỉnh thành
////                    TextView txt=(TextView) fragment_odau.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.tabsText);
////                    txt.setText(nameTP);//đổi tên tab
////                    fragment_odau.mTabHost.setCurrentTab(0);
////                    txt.setTextColor(Color.RED); //đổi màu text
////                    fragment_odau.mTabHost.setCurrentTab(0);//trở về lại tab 0
////                    fragment_odau.click3=false; //click chưa đc click
////                    MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView
////                    TextView txt=(TextView)rowView.findViewById(R.id.tvnoti);
////                    txt.setText("");
////                    txt.setText("Mặc định");//đổi tên tab
////                    txt.setTextColor(Color.BLUE); //đổi màu text
////                fragment_odau.mTabHost.setCurrentTab(3);//trở về lại tab 0
////                fragment_odau.click1=false; //click chưa đc click
////                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện lại BottomBar khi tắt listView
//                }
//            });

        }

        return rowView;
    }

}