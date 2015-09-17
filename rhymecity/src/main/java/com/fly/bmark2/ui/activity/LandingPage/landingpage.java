package com.fly.bmark2.ui.activity.Homepage;

import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.GridView;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.fragment.Homepage.HomeFragment;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
//import android.view.WindowManager;

public class HomeActivity extends MainFragmentActivity implements FragmentContainerActivity {

    //implements ToolbarActivity, ProgressIndicatorActivity, FragmentContainerActivity {
    //@InjectView(R.id.main_activity_toolbar) Toolbar toolbar;
    //@InjectView(R.id.main_activity_progress_indicator) ProgressBar progressIndicator;

    private FragmentManager fragmentManager;
    private static final int INITIAL_DELAY_MILLIS = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        GridView gridView = (GridView) findViewById(R.id.activity_gridview_gv);
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new GridViewAdapter(this));
        swingBottomInAnimationAdapter.setAbsListView(gridView);

        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);

        gridView.setAdapter(swingBottomInAnimationAdapter);

        assert getActionBar() != null;
        getActionBar().setDisplayHomeAsUpEnabled(true);

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
