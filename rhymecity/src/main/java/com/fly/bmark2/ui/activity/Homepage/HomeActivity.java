package com.fly.bmark2.ui.activity.Homepage;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.fragment.Homepage.HomeFragment;

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

        MainFragmentActivity.db = openOrCreateDatabase("GeoLocation", Context.MODE_PRIVATE, null);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_activity_fragment_container, HomeFragment.newInstance()).commit();
        hideTitle();

       // FlowManager.init(this);
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.main_activity_fragment_container;
    }
}
