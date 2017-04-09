package com.example.kaios.foody.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.kaios.foody.Fragment_angi_odau.fragment_angi;
import com.example.kaios.foody.MainActivity;
import com.example.kaios.foody.R;

import java.util.ArrayList;
import java.util.Arrays;

public class TabActivity_3_angi extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tabs_layout, container, false);
        ListView mainListView = (ListView)v.findViewById( R.id.mainListView );

// Create and populate a List of planet names.
        String[] planets = new String[] { "Tp.HCM", "Quận 1", "Quận 2", "Quận 3",
                "Quận 4", "Quận 5", "Quận 6", "Quận 7"};

        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        // Create ArrayAdapter using the planet list.
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_listview_tab3, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add( "Quân 8" );
        listAdapter.add( "Quân 9" );
        listAdapter.add( "Quân 10" );
        listAdapter.add( "Quân 11" );
        listAdapter.add( "Quân 12" );

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );

        ///Click button Hủy trở về tab chính
        Button btn=(Button) v.findViewById(R.id.btnhuy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_angi.mTabHost.setCurrentTab(0);
                MainActivity.mBottomBar.setVisibility(View.VISIBLE);//hiện BottomBar khi bấm hủy
            }
        });


        return v;
    }
}