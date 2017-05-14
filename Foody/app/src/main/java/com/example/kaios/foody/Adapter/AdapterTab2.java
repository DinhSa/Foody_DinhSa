package com.example.kaios.foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaios.foody.R;

public class AdapterTab2 extends BaseAdapter {
    private static final int TYPE_HEARDER = 0;
    private static final int TYPE_DM = 1;
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public AdapterTab2(){}

    public AdapterTab2(Context mainActivity, String[] prgmNameList, int[] prgmImages) {
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
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AdapterTab2.Holder holder=new AdapterTab2.Holder();
        View rowView;
        int rowType = getItemViewType(position);
        if(rowType==TYPE_HEARDER){
            rowView = inflater.inflate(R.layout.danhmuc_header, null);
            holder.tv=(TextView) rowView.findViewById(R.id.danhmuc);
            holder.tv.setText("Danh mục");
        }
        else {
            rowView = inflater.inflate(R.layout.adapter__tab2, null);
            holder.tv=(TextView) rowView.findViewById(R.id.textView2);
            holder.img=(ImageView) rowView.findViewById(R.id.imageView2);
            holder.tv.setText(result[position-1]);
            holder.img.setImageResource(imageId[position]);
        }
        return rowView;
    }

}