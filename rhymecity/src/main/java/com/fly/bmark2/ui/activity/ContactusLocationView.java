package com.fly.bmark2.ui.activity;

import android.app.FragmentManager;
import android.os.Bundle;

import com.fly.bmark2.MainFragmentActivity;
import com.fly.bmark2.R;
import com.fly.bmark2.ui.fragment.LocationFragment;

import butterknife.ButterKnife;

//import android.view.WindowManager;

public class ContactusLocationView extends MainFragmentActivity implements FragmentContainerActivity {

	private FragmentManager fragmentManager;
	private String departmentRefId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ButterKnife.inject(this);


		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().add(R.id.main_activity_fragment_container, LocationFragment.newInstance(getIntent().getExtras())).commit();

	}

	@Override
	public int getFragmentContainerId() {
		return R.id.main_activity_fragment_container;
	}
}
