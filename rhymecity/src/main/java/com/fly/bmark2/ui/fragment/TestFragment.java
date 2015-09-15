package com.fly.bmark2.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fly.bmark2.R;
import com.trnql.smart.base.SmartFragment;
import com.trnql.smart.location.AddressEntry;
import com.trnql.smart.location.LocationEntry;

/**
 * Created by Dell on 9/14/2015.
 */
public class TestFragment extends SmartFragment {

    public static TestFragment newInstance()
    {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TestFragment()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.map_layout, container, false);
        return v;
    }

    @Override
    protected void smartLatLngChange(LocationEntry location) {
        String lat_string = String.valueOf(location.getLatitude());
        String long_string = String.valueOf(location.getLongitude());
        String acc_string = String.valueOf(location.getAccuracy());
        // aq.id(R.id.txtAddress).text(long_string);

        Log.e("long_string", long_string);
    }

    @Override
    protected void smartAddressChange(AddressEntry address) {
        String addr_string = address.toString();
        Log.e("addr_string", addr_string);
        //aq.id(R.id.txtAddress).text(addr_string);

    }
}
