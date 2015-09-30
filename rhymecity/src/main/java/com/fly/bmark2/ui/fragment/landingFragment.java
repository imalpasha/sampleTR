package com.fly.bmark2.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.augmented3.MainActivity;
import com.fly.bmark2.base.BaseFragment;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.activity.LocationPage.ContactusLocationView;
import com.fly.bmark2.ui.adapter.GridViewAdapter;
import com.fly.bmark2.ui.presenter.HomePresenter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.trnql.smart.activity.ActivityEntry;
import com.trnql.smart.location.LocationEntry;

import butterknife.ButterKnife;

public class landingFragment extends BaseFragment implements HomePresenter.HomeView {

    private int fragmentContainerId;
    private SQLiteDatabase db;
    private static final int INITIAL_DELAY_MILLIS = 300;
    private String movement;
    private String whatToUse;
    AlertDialog alert;
    private GridView gridView;
    private ListView listView;
    private TextView txt1,txt2,txt3,txt4,txt5,txt6;
    String latitude;
    String longitude;
    Boolean firstTriggered = true;


    public static landingFragment newInstance() {

        landingFragment fragment = new landingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

        // new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // FireFlyApplication.get(getActivity()).createScopedGraph(new HomeModule(this)).inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_gridview, container, false);
        ButterKnife.inject(this, view);

        db = createDBconnection(getActivity());


        gridView = (GridView) view.findViewById(R.id.activity_gridview_gv);
        listView = (ListView) view.findViewById(R.id.activity_listview);

        txt1 = (TextView) view.findViewById(R.id.txt1);
        txt2 = (TextView) view.findViewById(R.id.txt2);
        txt3 = (TextView) view.findViewById(R.id.txt3);
        txt4 = (TextView) view.findViewById(R.id.txt4);
        txt5 = (TextView) view.findViewById(R.id.txt5);
        txt6 = (TextView) view.findViewById(R.id.txt6);


        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new GridViewAdapter(getActivity(), "grid"));
        SwingBottomInAnimationAdapter listviewAdapter = new SwingBottomInAnimationAdapter(new GridViewAdapter(getActivity(), "listview"));

        swingBottomInAnimationAdapter.setAbsListView(gridView);
        listviewAdapter.setAbsListView(listView);

        gridView.setAdapter(swingBottomInAnimationAdapter);

        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setAdapter(listviewAdapter);



        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);

        assert listviewAdapter.getViewAnimator() != null;
        listviewAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String tag = v.findViewById(R.id.txtAct).getTag().toString();
                selectViewMethod(tag);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String tag = v.findViewById(R.id.txtAct).getTag().toString();
                selectViewMethod(tag);
            }
        });


        return view;
    }

    public void createDataListFromLatLong(String lat,String longi)
    {
        String[] myStrings = { "CYCLING", "DESERT", "DINNER","LUNCH","BREAKFAST"};

        /*Insert Activity*/
        int k = 0;
        for(int x = 0 ; x< 55 ; x++)
        {

            String passLatitude = lat;
            String passLongitude = longi;
            String tag = myStrings[k];
            Double randLatitude = Double.parseDouble(passLatitude) + Math.random()/3-0.1;
            Double randLongitude =  Double.parseDouble(passLongitude) + Math.random()/3-0.1;

            if(k == 4)
            {
                k = 0;
            }
            else
            {
                k++;
            }

            MainFragmentActivity.insertRecord(Double.toString(randLatitude) + "," + Double.toString(randLongitude), tag);
        }
    }

    @Override
    protected void smartLatLngChange(LocationEntry location) {

        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());
        double math = Math.random();
        txt6.setText("loc : "+latitude+"-"+longitude+"-"+Double.toString(math));

        if( firstTriggered == true) {
            db.execSQL("DELETE FROM latlong2");
            createDataListFromLatLong(latitude, longitude);
            firstTriggered = false;
        }
    }

    @Override
    protected void smartActivityChange(ActivityEntry userActivity) {
        movement = userActivity.getActivityString();
        Boolean movement2 = userActivity.isWalking();
        Boolean movement3 = userActivity.isTilting();
        Boolean movement4 = userActivity.isInVehicle();
        Boolean movement5 = userActivity.isOnFoot();
        Boolean movement6 = userActivity.isStill();

        //Log.e("movement",Boolean.toString(movement2));
        txt1.setText("isWalking : "+Boolean.toString(movement2));
        txt2.setText("isTilting : "+Boolean.toString(movement3));
        txt3.setText("isInVehicle : "+Boolean.toString(movement4));
        txt4.setText("isOnFoot : "+Boolean.toString(movement5));
        txt5.setText("isStill : "+Boolean.toString(movement6));

        if(gridView != null) {
            if (!userActivity.isStill()) {
                listView.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
            }else {
                gridView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }

        }

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentContainerId = ((FragmentContainerActivity) getActivity()).getFragmentContainerId();
    }


    public void selectViewMethod(final String tag)
    {
        final CharSequence colors[] = new CharSequence[] {"Google Map", "Augmented Reality"};

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.custom_alert, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customLayout);
        builder.setTitle("View on");
        alert = builder.create();
        alert.show();

        aq.recycle(customLayout);
        aq.id(R.id.gmapID).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ContactusLocationView.class);
                intent.putExtra("tag", tag);
                getActivity().startActivityForResult(intent, 1);
                aq.id(R.id.actChoice).visibility(View.GONE);
                aq.id(R.id.progressbar).visibility(View.VISIBLE);

            }
        });

        aq.id(R.id.arID).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("tag", tag);
                getActivity().startActivity(intent);
                aq.id(R.id.actChoice).visibility(View.GONE);
                aq.id(R.id.progressbar).visibility(View.VISIBLE);
            }
        });

    }

    public void onResume()
    {
        super.onResume();
        if( alert != null) {
            if (alert.isShowing()) {
                alert.dismiss();
            }
        }
    }
}


