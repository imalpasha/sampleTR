package com.fly.bmark2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.androidquery.util.AQUtility;
import com.fly.bmark2.base.AQuery;
import com.fly.bmark2.base.BaseFragmentActivity;
import com.fly.bmark2.drawer.DrawerItem;
import com.fly.bmark2.drawer.NavigationDrawerFragment;

import butterknife.ButterKnife;


public class MainFragmentActivity extends BaseFragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks
{

    /**
     * Fragment managing the behaviors, interactions and presentation of the
     * navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aq = new AQuery(this);
        setMenuButton();
        ButterKnife.inject(this);

        hideTitle();

        // Set up the drawer.
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        /*Create Table - Generate dummy data - nearest location*/
        db = openOrCreateDatabase("GeoLocation", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS latlong(latlongitude double,refId varchar);");

        db.execSQL("DELETE FROM latlong");

        for(int x = 0 ; x< 15 ; x++)
        {
            String passLatitude = "2.9243503";
            String passLongitude = "101.6559618";

            Double randLatitude = Double.parseDouble(passLatitude) + Math.random()/5-0.1;
            Double randLongitude =  Double.parseDouble(passLongitude) + Math.random()/5-0.1;

            insertRecord(Double.toString(randLatitude)+","+Double.toString(randLongitude),Integer.toString(x+1));
        }

    }

    public void insertRecord(String latlong,String refId) {

        ContentValues values = new ContentValues();

        values.put("latlongitude", latlong);
        values.put("refId", refId);
        db.insert("latlong", null, values);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (isTaskRoot())
        {
            // clean the file cache with advance option
            long triggerSize = 3000000; // starts cleaning when cache size is
            // larger than 3M
            long targetSize = 2000000; // remove the least recently used files
            // until cache size is less than 2M
            AQUtility.cleanCacheAsync(this, triggerSize, targetSize);
        }
    }

    public void setMenuButton()
    {
        View actionBarView = getSupportActionBar().getCustomView();
        aq.recycle(actionBarView);
        aq.id(R.id.menubutton).visible();
        aq.id(R.id.menubutton).clicked(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mNavigationDrawerFragment.openDrawer();
            }
        });
    }

    @Override
    public void onNavigationDrawerItemSelected(int position, DrawerItem item) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }


}
