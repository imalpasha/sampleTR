package com.fly.bmark2.ui.activity.Homepage;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.fragment.Homepage.HomeFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import butterknife.ButterKnife;

//import android.view.WindowManager;

public class HomeActivity extends MainFragmentActivity implements FragmentContainerActivity {

    //implements ToolbarActivity, ProgressIndicatorActivity, FragmentContainerActivity {
    //@InjectView(R.id.main_activity_toolbar) Toolbar toolbar;
    //@InjectView(R.id.main_activity_progress_indicator) ProgressBar progressIndicator;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_activity_fragment_container, HomeFragment.newInstance()).commit();

        hideTitle();

        int checkGooglePlayServices =    GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (checkGooglePlayServices != ConnectionResult.SUCCESS) {

                    /* Returns status code indicating whether there was an error.
                    Can be one of following in ConnectionResult: SUCCESS, SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED, SERVICE_DISABLED, SERVICE_INVALID.
                    */
            GooglePlayServicesUtil.getErrorDialog(checkGooglePlayServices,this, 1122).show();
        }
        else {
            Log.e("CONNECTION",Integer.toString(checkGooglePlayServices));
        }
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.main_activity_fragment_container;
    }
}
