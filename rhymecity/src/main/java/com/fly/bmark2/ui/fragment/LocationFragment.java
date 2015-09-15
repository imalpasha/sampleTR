package com.fly.bmark2.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fly.bmark2.R;
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

    //static com.google.android.maps.GeoPoint geo;


    public static LocationFragment newInstance()
    {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
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

                Log.e("Clicked", "TRUE");
                String URL = "http://maps.google.com/maps?saddr=&daddr=" + latitude + "," + longitude + "&z=1";
                Utils.launchNavigator(getActivity(), URL);

            }
        });

        aq.id(R.id.imgWeather).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aq.id(R.id.detailBlock).visible();
            }
        });

        aq.id(R.id.clsBtn).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aq.id(R.id.detailBlock).gone();
            }
        });


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

          Log.e("firstTriggered","SEKALI");
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(passLatitude), Double.parseDouble(passLongitude)), 12);
            map.animateCamera(cameraUpdate);

            //Current Location
            Marker marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(passLatitude), Double.parseDouble(passLongitude)))
                    .title("Current Location")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            marker.showInfoWindow();

            mClusterManager = new ClusterManager<MyItem>(getActivity(),map);
            mClusterManager.setRenderer(new RenderCluster(getActivity(),map, mClusterManager));


            mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
                        @Override
                        public boolean onClusterItemClick(MyItem item) {

                            String latitude = Double.toString(item.getLatitude());
                            String longitude = Double.toString(item.getLongitude());

                            Log.e("latitude",latitude);
                            Log.e("longitude",longitude);

                            aq.id(R.id.txtAddress).text(item.getTitle());

                            return false;

                        };
                    });

            map.setOnCameraChangeListener(mClusterManager);
            map.setOnMarkerClickListener(mClusterManager);

            //Nearest Location (Whatever that suitable) Sample
            for(int x = 0 ; x < 5 ; x++)
            {
                randLatitude = Double.parseDouble(passLatitude) + Math.random()/5-0.1;
                randLongitude =  Double.parseDouble(passLongitude) + Math.random()/5-0.1;

                MyItem offsetItem = new MyItem(randLatitude, randLongitude);
                offsetItem.setLatitude(randLatitude);
                offsetItem .setLongitude(randLongitude);
                mClusterManager.addItem(offsetItem);
            }

            firstTriggered = false;
        }
        else
        {
            Log.e("Task Triggered", "True");
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
