package com.fly.bmark2.ui.activity.LandingPage;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.fragment.landingFragment;
//import android.view.WindowManager;

public class LandingPage extends MainFragmentActivity implements FragmentContainerActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_activity_fragment_container, landingFragment.newInstance()).commit();
        hideTitle();



    }

    @Override
    public int getFragmentContainerId() {
        return R.id.main_activity_fragment_container;
    }

    protected void onResume()
    {
        super.onResume();
        Log.e("RESUME","xx");
    }
}
