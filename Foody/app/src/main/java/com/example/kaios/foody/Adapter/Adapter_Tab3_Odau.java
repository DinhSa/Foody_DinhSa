package com.example.kaios.foody.Adapter;

/**
 * Created by kaios on 4/9/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.kaios.foody.Fragment_angi_odau.fragment_odau;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;
import com.example.kaios.foody.Tabs.TabActivity_3_odau;

import java.util.ArrayList;
import java.util.HashMap;


public class Adapter_Tab3_Odau extends BaseExpandableListAdapter {
    private Context _context;
    private ArrayList<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<String>> _listDataChild;

    public Adapter_Tab3_Odau(Context context, ArrayList<String> listDataHeader,
                                     HashMap<String, ArrayList<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return _listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosititon) {
        return childPosititon;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean b, View view, final ViewGroup viewGroup) {
        String data = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater li = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.listview_header, null);
        }

        TextView tvHeader = (TextView) view.findViewById(R.id.tvHeader);
        //tvHeader.setTypeface(null, Typeface.BOLD);
        tvHeader.setText(data);

        Button btnSoDuong = (Button)view.findViewById(R.id.btnSoDuong);
        btnSoDuong.setText(getChildrenCount(groupPosition) + " đường");
        btnSoDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b)
                    ((ExpandableListView) viewGroup).collapseGroup(groupPosition);
                else
                    ((ExpandableListView) viewGroup).expandGroup(groupPosition);
            }
        });

        tvHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = (String) getGroup(groupPosition);
                TextView txt=(TextView) fragment_odau.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.tabsText);
                TabActivity_3_odau.KieuDiaDiem="QuanHuyen";
                TabActivity_3_odau.TenDiaDiem=ten;
                txt.setText(ten);
                txt.setTextColor(Color.RED);
                fragment_odau.mTabHost.setCurrentTab(0);
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);
                fragment_odau.click3=false;
            }
        });
        return view;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean b, View view, ViewGroup viewGroup) {
        final String data = (String) getChild(groupPosition, childPosition);

        if (view == null) {
            LayoutInflater li = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.listview_item, null);
        }

        TextView tvItem = (TextView) view.findViewById(R.id.tvItem);

        tvItem.setText(data);

        tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String ten1 = (String) getChild(groupPosition, childPosition);
                TextView txt=(TextView) fragment_odau.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.tabsText);
                TabActivity_3_odau.KieuDiaDiem="DuongPho";
                TabActivity_3_odau.TenDiaDiem=data;
                txt.setText(data);
                txt.setTextColor(Color.RED);
                fragment_odau.mTabHost.setCurrentTab(0);
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);
                fragment_odau.click3=false;
            }
        });
        return view;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
