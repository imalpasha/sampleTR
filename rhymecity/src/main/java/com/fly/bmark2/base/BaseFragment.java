package com.fly.bmark2.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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

	public static SQLiteDatabase createDBconnection(Activity activity)
	{
		SQLiteDatabase db = null;

		if(activity != null) {
			db = activity.openOrCreateDatabase("GeoLocation", Context.MODE_PRIVATE, null);
			//db.execSQL("CREATE TABLE IF NOT EXISTS latlong(latlongitude double,refId varchar);");
        }

		return db;
	}


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
