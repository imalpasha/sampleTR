package com.fly.bmark2.ui.activity.LandingPage;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.activity.SplashScreen.SplashScreenActivity;
import com.fly.bmark2.ui.fragment.LandingFragment;
//import android.view.WindowManager;

public class LandingPage extends MainFragmentActivity implements FragmentContainerActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_activity_fragment_container, LandingFragment.newInstance()).commit();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent back = new Intent(this, SplashScreenActivity.class);
        this.startActivity(back);
        this.finish();
    }
}
