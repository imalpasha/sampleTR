package com.fly.bmark2.augmented3;

import android.os.Bundle;

public class SampleCamContentFromNativeActivity extends SampleCamActivity {
	
	@Override
	protected void onPostCreate( final Bundle savedInstanceState ) {
		super.onPostCreate( savedInstanceState );
		this.injectData();
	}
}