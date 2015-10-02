package com.fly.bmark2.ui.activity.SplashScreen;

import android.app.FragmentManager;
import android.os.Bundle;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.api.ApiRequestHandler;
import com.fly.bmark2.api.ApiService;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.fragment.SplashScreenFragment;
import com.squareup.otto.Bus;

import javax.inject.Inject;
//import android.view.WindowManager;

public class SplashScreenActivity extends MainFragmentActivity implements FragmentContainerActivity {

    private FragmentManager fragmentManager;
    @Inject
    Bus bus;

    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bus = new Bus();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_activity_fragment_container, SplashScreenFragment.newInstance()).commit();
        hideTitle();
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.main_activity_fragment_container;
    }

    protected void onResume()
    {
        super.onResume();
    }
}
