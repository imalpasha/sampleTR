package com.fly.bmark2.augmented3;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fly.bmark2.R;

import java.io.File;

public class MainSamplesListActivity extends ListActivity {

	public static final String EXTRAS_KEY_ACTIVITY_TITLE_STRING = "activityTitle";
	public static final String EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL = "activityArchitectWorldUrl";

	public static final String EXTRAS_KEY_ACTIVITY_IR = "activityIr";
	public static final String EXTRAS_KEY_ACTIVITY_GEO = "activityGeo";

	public static final String EXTRAS_KEY_ACTIVITIES_ARCHITECT_WORLD_URLS_ARRAY = "activitiesArchitectWorldUrls";
	public static final String EXTRAS_KEY_ACTIVITIES_TILES_ARRAY = "activitiesTitles";
	public static final String EXTRAS_KEY_ACTIVITIES_CLASSNAMES_ARRAY = "activitiesClassnames";

	public static final String EXTRAS_KEY_ACTIVITIES_IR_ARRAY = "activitiesIr";
	public static final String EXTRAS_KEY_ACTIVITIES_GEO_ARRAY = "activitiesGeo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(this.getContentViewId());

		this.setTitle(this.getActivityTitle());

		/* extract names of samples from res/arrays */
		final String[] values = this.getListLabels();

		/* use default list-ArrayAdapter */
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		/* get className of activity to call when clicking item at position x */
		//final String className = getListActivities()[position];

		final Intent intent = new Intent(this,SampleCamActivity.class );
		intent.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING,
				this.getListLabels()[position]);
		intent.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples"
				+ File.separator + this.getArchitectWorldUrls()[position]
				+ File.separator + "index.html");
		intent.putExtra(EXTRAS_KEY_ACTIVITY_IR,
				this.getActivitiesIr()[position]);
		intent.putExtra(EXTRAS_KEY_ACTIVITY_GEO,
				this.getActivitiesGeo()[position]);

		Log.e("EXTRAS_KEY_ACTIVITY_IR",Boolean.toString(this.getActivitiesIr()[position]));
		Log.e("EXTRAS_KEY_ACTIVITY_GEO",Boolean.toString(this.getActivitiesGeo()[position]) );
			/* launch activity */
		this.startActivity(intent);

		/*try {
			Log.e("className", className);
			//Class.forName(className)


		} catch (Exception e) {

			Toast.makeText(this, className + "\nnot defined/accessible",
					Toast.LENGTH_SHORT).show();
		}*/
	}

	protected final String[] getListLabels() {
		return getIntent().getExtras().getStringArray(
				EXTRAS_KEY_ACTIVITIES_TILES_ARRAY);
	}

	protected String getActivityTitle() {
		return getIntent().getExtras().getString(
				EXTRAS_KEY_ACTIVITY_TITLE_STRING);
	}

	protected String[] getListActivities() {
		return getIntent().getExtras().getStringArray(
				EXTRAS_KEY_ACTIVITIES_CLASSNAMES_ARRAY);
	}

	protected String[]

	getArchitectWorldUrls() {
		return getIntent().getExtras().getStringArray(
				EXTRAS_KEY_ACTIVITIES_ARCHITECT_WORLD_URLS_ARRAY);
	}

	protected boolean[] getActivitiesIr() {
		return getIntent().getExtras().getBooleanArray(
				EXTRAS_KEY_ACTIVITIES_IR_ARRAY);
	}
	
	protected boolean[] getActivitiesGeo() {
		return getIntent().getExtras().getBooleanArray(
				EXTRAS_KEY_ACTIVITIES_GEO_ARRAY);
	}
	
	protected int getContentViewId() {
		return R.layout.list_sample;
	}

}
