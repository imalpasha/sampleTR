package com.fly.bmark2.ui.activity.SelectPage;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.fragment.SelectFragment;
//import android.view.WindowManager;

public class selectionPage extends MainFragmentActivity implements FragmentContainerActivity {

    private FragmentManager fragmentManager;
    private Bundle getBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBundle = new Bundle();
        Intent intent = getIntent();
        if(intent.hasExtra("LEVEL")){
           getBundle = getIntent().getExtras();
           //getBundle.putString("LEVEL","1");
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_activity_fragment_container, SelectFragment.newInstance(getBundle)).commit();
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
