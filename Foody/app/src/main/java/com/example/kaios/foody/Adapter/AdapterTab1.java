package com.example.kaios.foody.Adapter;

/**
 * Created by kaios on 5/14/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaios.foody.R;

public class AdapterTab1 extends BaseAdapter {

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public AdapterTab1(){}

    public AdapterTab1(Context mainActivity, String[] prgmNameList, int[] prgmImages) {
        this.result=prgmNameList;
        this.context=mainActivity;
        this.imageId=prgmImages;
        this.inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        AdapterTab1.Holder holder=new AdapterTab1.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.adapter__tab1, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        return rowView;
    }

}