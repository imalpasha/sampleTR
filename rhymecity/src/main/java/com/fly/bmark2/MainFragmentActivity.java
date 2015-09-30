package com.fly.bmark2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.androidquery.util.AQUtility;
import com.fly.bmark2.base.AQuery;
import com.fly.bmark2.base.BaseFragmentActivity;

import butterknife.ButterKnife;


public class MainFragmentActivity extends BaseFragmentActivity
{

    /**
     * Fragment managing the behaviors, interactions and presentation of the
     * navigation drawer.
     */
   // private NavigationDrawerFragment mNavigationDrawerFragment;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aq = new AQuery(this);

        ButterKnife.inject(this);

        hideTitle();

        // Set up the drawer.


        /*Create Table - Generate dummy data - nearest location*/
        db = openOrCreateDatabase("GeoLocation", Context.MODE_PRIVATE, null);

        //db.execSQL("DROP TABLE latlong2");
        //db.execSQL("DROP TABLE state");
        ///db.execSQL("DROP TABLE daerah");
        //db.execSQL("DROP TABLE bml");

        db.execSQL("CREATE TABLE IF NOT EXISTS latlong2(latlongitude double,tag varchar)");
        db.execSQL("CREATE TABLE IF NOT EXISTS state(stateName varchar,stateID varchar,level varchar)");
        db.execSQL("CREATE TABLE IF NOT EXISTS daerah(daerahName varchar,daerahID varchar,stateID varchar,level varchar)");
        db.execSQL("CREATE TABLE IF NOT EXISTS bml(bmlName varchar,daerahID varchar,bmlID varchar,level varchar)");

        db.execSQL("DELETE FROM state");
        db.execSQL("DELETE FROM daerah");
        db.execSQL("DELETE FROM bml");

        String[] myStrings = { "CYCLING", "DESERT", "DINNER","LUNCH","BREAKFAST"};
        String[] myState = { "SELANGOR", "KELANTAN", "JOHOR","KEDAI","PAHANG"};
        String[] myDaerah = { "KLANG", "KAPAR", "KULIM"};
        String[] myBM = { "PA1", "PA2", "PA3"};


        //Insert State
        /*int c = 0;
        for(int v = 0 ; v< myState.length ; v++)
        {
            insertState(myState[c], Integer.toString(v + 1));
            c++;
        }

        //Insert Daerah
        int b = 0;
        for(int v = 0 ; v< myDaerah.length ; v++)
        {
            insertDaerah(myDaerah[b], Integer.toString(1), Integer.toString(v + 1));
            b++;
        }

        //Insert BML
        int f = 0;
        for(int v = 0 ; v< myDaerah.length ; v++)
        {
            insertBML(myBM[f], Integer.toString(1), Integer.toString(v + 1));
            f++;
        }*/

    }

    public static void insertRecord(String latlong,String refId) {

        ContentValues values = new ContentValues();

        values.put("latlongitude", latlong);
        values.put("tag", refId);
        db.insert("latlong2", null, values);
    }

    public void insertState(String state,String id) {

        ContentValues values = new ContentValues();

        values.put("stateName", state);
        values.put("stateID", id);
        values.put("level", "1");

        db.insert("state", null, values);
    }

    public void insertDaerah(String daerah,String stateId,String daerahId) {

        ContentValues values = new ContentValues();

        values.put("daerahName", daerah);
        values.put("daerahID", daerahId);
        values.put("stateID", stateId);
        values.put("level", "2");

        db.insert("daerah", null, values);
    }

    public void insertBML(String bmlName,String daerahId,String bmlId) {

        ContentValues values = new ContentValues();

        values.put("bmlName", bmlName);
        values.put("daerahID", daerahId);
        values.put("bmlID", bmlId);
        values.put("level", "3");
        db.insert("bml", null, values);
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
