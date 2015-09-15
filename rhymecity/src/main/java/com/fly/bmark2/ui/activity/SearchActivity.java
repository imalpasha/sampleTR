package com.fly.bmark2.ui.activity;

import android.app.FragmentManager;
import android.os.Bundle;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.ui.fragment.SearchFragment;

import butterknife.ButterKnife;

//import android.view.WindowManager;

public class SearchActivity extends MainFragmentActivity implements FragmentContainerActivity {

    //implements ToolbarActivity, ProgressIndicatorActivity, FragmentContainerActivity {
    //@InjectView(R.id.main_activity_toolbar) Toolbar toolbar;
    //@InjectView(R.id.main_activity_progress_indicator) ProgressBar progressIndicator;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, SearchFragment.newInstance()).commit();

    }

    private void goToSearchFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.main_activity_fragment_container, SearchFragment.newInstance())
                .commit();
    }

   /* @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }*/

    /*@Override
    public ProgressBar getProgressIndicator() {
        return progressIndicator;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }*/

    @Override
    public int getFragmentContainerId() {
        return R.id.main_activity_fragment_container;
    }
}
