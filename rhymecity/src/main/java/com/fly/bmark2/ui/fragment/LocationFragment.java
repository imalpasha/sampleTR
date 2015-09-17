package com.fly.bmark2.ui.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fly.bmark2.R;
import com.fly.bmark2.augmented3.MainActivity;
import com.fly.bmark2.base.AQuery;
import com.fly.bmark2.base.BaseFragment;
import com.fly.bmark2.utils.Utils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.trnql.smart.location.AddressEntry;
import com.trnql.smart.location.LocationEntry;

import butterknife.InjectView;


/**
 * Created by Dell on 9/9/2015.
 */
public class LocationFragment extends BaseFragment
{
    @InjectView(R.id.txtAddress)
    TextView txtAddress;
    @InjectView(R.id.storeAddressLayout) TextView storeAddressLayout;
    @InjectView(R.id.mapview) MapView mapView;
    @InjectView(R.id.navigationIcon)ImageView navigationIcon;

    //static MapView mapView;
    static GoogleMap map;
    static String fullAddress;
    String latitude;
    String longitude;
    Boolean firstTriggered = true;
    Double randLatitude,randLongitude;
    ClusterManager<MyItem> mClusterManager;
    SQLiteDatabase db;
    //static com.google.android.maps.GeoPoint geo;


    public static LocationFragment newInstance(Bundle getData)
    {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle(getData);
        fragment.setArguments(args);
        return fragment;
    }

    public LocationFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.map_layout, container, false);
        aq.recycle(v);

        aq = new AQuery(getActivity(), v);
        // Gets the MapView from the XML layout and creates it


        Bundle actID = getArguments();
        String id = actID.getString("id");
        Log.e("id",id);

        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(false);
        MapsInitializer.initialize(getActivity());

        aq.id(R.id.storeAddressLayout).visible();
        aq.id(R.id.navigationIcon).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://maps.google.com/maps?saddr=&daddr=" + latitude + "," + longitude + "&z=1";
                Utils.launchNavigator(getActivity(), URL);
            }
        });

        aq.id(R.id.imgAR).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent loginPage = new Intent(getActivity(), SeatSelectionActivity.class);
                Intent AR = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(AR);
                getActivity().finish();
            }
        });

        mClusterManager = new ClusterManager<MyItem>(getActivity(), map);
        mClusterManager.setRenderer(new RenderCluster(getActivity(), map, mClusterManager));

        db = createDBconnection(getActivity());
        Cursor c=db.rawQuery("SELECT * FROM latlong",null);


        if (c .moveToFirst()) {

            while (c.isAfterLast() == false) {
                String latLong = c.getString(c.getColumnIndex("latlongitude"));
                String refID = c.getString(c.getColumnIndex("refId"));

                String foo = latLong;
                String[] split = foo.split(",");
                randLatitude = Double.parseDouble(split[0]);
                randLongitude  = Double.parseDouble(split[1]);

                MyItem offsetItem = new MyItem(randLatitude, randLongitude);
                offsetItem.setLatitude(randLatitude);
                offsetItem.setLongitude(randLongitude);
                offsetItem.setRefID(refID);
                mClusterManager.addItem(offsetItem);

                c.moveToNext();
            }
        }

        return v;
    }

    @Override
    protected void smartLatLngChange(LocationEntry location) {

        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());
        //String acc_string = String.valueOf(location.getAccuracy());
        triggerMap(latitude, longitude);
    }

    public void triggerMap(String passLatitude,String passLongitude)
    {

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.


        if( firstTriggered == true) {



            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(passLatitude), Double.parseDouble(passLongitude)), 12);
            map.animateCamera(cameraUpdate);

            //Current Location
            Marker marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(passLatitude), Double.parseDouble(passLongitude)))
                    .title("Current Location")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            marker.showInfoWindow();


            mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
                @Override
                public boolean onClusterItemClick(MyItem item) {

                    aq.id(R.id.txtAddress).text(item.getRefID());

                    latitude = Double.toString(item.getLatitude());
                    longitude = Double.toString(item.getLongitude());

                    return false;

                }

                ;
            });

           // insertData();
            map.setOnCameraChangeListener(mClusterManager);
            map.setOnMarkerClickListener(mClusterManager);



            firstTriggered = false;
        }
        else
        {
            Log.e("Task Triggered", "True");
        }
    }

    public void insertData()
    {
        //Nearest Location (Whatever that suitable) Sample
        Log.e("randLongitude","xxxxxxxxxx");

            Cursor c=db.rawQuery("SELECT * FROM latlong",null);
            if (c .moveToFirst()) {

                while (c.isAfterLast() == false) {
                    randLatitude = Double.parseDouble(c.getString(c.getColumnIndex("latlongitude")));
                    randLongitude = Double.parseDouble(c.getString(c.getColumnIndex("refId")));

                    MyItem offsetItem = new MyItem(randLatitude, randLongitude);
                    offsetItem.setLatitude(randLatitude);
                    offsetItem .setLongitude(randLongitude);
                    mClusterManager.addItem(offsetItem);

                    Log.e("randLatitude",Double.toString(randLatitude));
                    Log.e("randLongitude",Double.toString(randLongitude));

                    c.moveToNext();
                }
            }

    }


    @Override
    protected void smartAddressChange(AddressEntry address) {
        String addr_string = address.toString();
        aq.id(R.id.txtAddress).text(addr_string);
    }

    @Override
    public void onResume()
    {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
