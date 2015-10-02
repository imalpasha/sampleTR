package com.fly.bmark2.ui.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fly.bmark2.AppApplication;
import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.api.ApiService;
import com.fly.bmark2.api.obj.tryObj;
import com.fly.bmark2.base.BaseFragment;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.activity.LandingPage.LandingPage;
import com.fly.bmark2.ui.module.SplashScreenModule;
import com.fly.bmark2.ui.presenter.HomePresenter;
import com.squareup.otto.Bus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SplashScreenFragment extends BaseFragment implements HomePresenter.HomeView {

    @Inject
    HomePresenter presenter;
    private int fragmentContainerId;
    Bus bus;
    private ApiService apiService;
    @InjectView(R.id.start_button) Button btnGo;
    private SQLiteDatabase db;

    public static SplashScreenFragment newInstance() {

        SplashScreenFragment fragment = new SplashScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

        // new SearchFragment();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppApplication.get(getActivity()).createScopedGraph(new SplashScreenModule(this)).inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.splash_screen, container, false);
        ButterKnife.inject(this, view);
        bus = new Bus();
        db = createDBconnection(getActivity());
        bus.register(this);

        getVersion();


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LandingPage.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    private void getVersion() {
        presenter.onRequestVersion();
    }

    @Override
    public void saveIntoDb(tryObj obj) {

        ArrayList thisList = obj.getResult();
        JSONArray json = new JSONArray(thisList);

        db.execSQL("DELETE FROM latlong3");
        for (int i = 0; i < json.length(); i++) {
            JSONObject row = (JSONObject) json.opt(i);
            MainFragmentActivity.insertRecord(row.optString("Latitude") + "," + row.optString("Longitude"), row.optString("Tag"),row.optString("PlaceName"));

        }

        db.execSQL("DELETE FROM version");
        MainFragmentActivity.insertVersion(obj.getVersion(),null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentContainerId = ((FragmentContainerActivity) getActivity()).getFragmentContainerId();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

}


