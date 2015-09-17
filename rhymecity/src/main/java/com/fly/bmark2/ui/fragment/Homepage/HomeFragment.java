package com.fly.bmark2.ui.fragment.Homepage;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fly.bmark2.R;
import com.fly.bmark2.augmented3.MainActivity;
import com.fly.bmark2.base.BaseFragment;
import com.fly.bmark2.ui.activity.ContactusLocationView;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.presenter.HomePresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeFragment extends BaseFragment implements HomePresenter.HomeView {

   // @Inject
   /// HomePresenter presenter;

    @InjectView(R.id.homeBookFlight) LinearLayout bookFlight;
    @InjectView(R.id.manageFlight) LinearLayout manageFlight;

    private int fragmentContainerId;
    private SQLiteDatabase db;

    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();
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

        View view = inflater.inflate(R.layout.home, container, false);
        ButterKnife.inject(this, view);

        bookFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMap();
            }
        });
        manageFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAR();
            }
        });

        /*db = createDBconnection(getActivity());
        Cursor c=db.rawQuery("SELECT * FROM latlong",null);

        Log.e("COUNNNNNNNT", Integer.toString(c.getCount()));

        if (c .moveToFirst()) {

            while (c.isAfterLast() == false) {
                String xx = c.getString(c.getColumnIndex("latlongitude"));
                String yy = c.getString(c.getColumnIndex("refId"));

                Log.e("xxxx", xx);
                Log.e("yyyy", yy);

                c.moveToNext();
            }
        }*/
        return view;
    }

    /*Public-Inner Func*/
    public void goToMap()
    {
       //Intent loginPage = new Intent(getActivity(), SeatSelectionActivity.class);
       Intent loginPage = new Intent(getActivity(), ContactusLocationView.class);
        loginPage.putExtra("id","1");
        getActivity().startActivity(loginPage);
    }

    /*Public-Inner Func*/
    public void goToAR()
    {
        //Intent loginPage = new Intent(getActivity(), AugmentedStart.class);
        Intent loginPage = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(loginPage);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //progressIndicator = ((ProgressIndicatorActivity) getActivity()).getProgressIndicator();
        fragmentContainerId = ((FragmentContainerActivity) getActivity()).getFragmentContainerId();
        //((ToolbarActivity) getActivity()).getToolbar().setTitle(getString(R.string.app_name));
    }

   /* @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }*/
}


