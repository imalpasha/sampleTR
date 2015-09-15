package com.fly.bmark2.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trnql.smart.base.SmartFragment;

public class BaseFragment extends SmartFragment {

	protected com.fly.bmark2.base.AQuery aq;
	protected SharedPreferences pref;

	/*public static void showConnectionError(String test, Activity activity)
	{
        if(activity != null) {
            try {
                TextView txtUTC = (TextView) activity.findViewById(R.id.txtUTC);
                txtUTC.setText(test);

                FrameLayout mainFrame = (FrameLayout) activity.findViewById(R.id.utc_container);
                mainFrame.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	public static void baseInitiateLoading(Activity activity)
	{
        Log.e("Initiate Loading","TRUE");
		try
		{
			final FrameLayout mainFrame = (FrameLayout) activity.findViewById(R.id.container);
			mainFrame.setVisibility(View.VISIBLE);

			RelativeLayout mainFrameRelative = (RelativeLayout) activity.findViewById(R.id.mainFrameRelative);
			mainFrameRelative.setVisibility(View.VISIBLE);
			mainFrame.bringChildToFront(mainFrameRelative);
			mainFrame.invalidate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void baseRemoveLoading(Activity activity)
	{
		try
		{
			RelativeLayout mainFrameRelative = (RelativeLayout) activity.findViewById(R.id.mainFrameRelative);
			mainFrameRelative.setVisibility(View.GONE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		aq = new com.fly.bmark2.base.AQuery(getActivity());
		pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
	}

	/*public void showUTCError(String msg)
	{
		Activity activity = getActivity();
		if (activity instanceof MainFragmentActivity)
		{
			MainFragmentActivity myactivity = (MainFragmentActivity) activity;
			myactivity.unableToConnectToServer(msg);
		}
	}*/

	public boolean isNetworkAvailable(Activity activity)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

}
