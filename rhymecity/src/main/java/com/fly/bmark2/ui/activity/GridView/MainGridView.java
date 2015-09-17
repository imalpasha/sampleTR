package com.fly.bmark2.ui.activity.GridView;

/**
 * Created by metechuser on 17/09/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.fly.bmark2.R;
import com.fly.bmark2.ui.activity.ContactusLocationView;

public class MainGridView extends Activity {

    GridView gridView;

    static final String[] activity = new String[] {
            "cycling", "lunch", "dinner", "breakfast",
            "shopping","medical"};




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);


        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(new GridAdapter(this,activity));


        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch(position)
                {

                    case 0:
                        Intent newActivity1 = new Intent(MainGridView.this,ContactusLocationView.class);
                        startActivity(newActivity1);
                        Toast.makeText(getApplicationContext(),((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        Intent newActivity2 = new Intent(MainGridView.this,ContactusLocationView.class);
                        startActivity(newActivity2);

                        break;

                    case 2:
                        Intent newActivity3 = new Intent(MainGridView.this,ContactusLocationView.class);
                        startActivity(newActivity3);

                        break;

                    case 3:
                        Intent newActivity4 = new Intent(MainGridView.this,ContactusLocationView.class);
                        startActivity(newActivity4);

                        break;

                    case 4:
                        Intent newActivity5 = new Intent(MainGridView.this,ContactusLocationView.class);
                        startActivity(newActivity5);

                        break;

                    case 5:
                        Intent newActivity6 = new Intent(MainGridView.this,ContactusLocationView.class);
                        startActivity(newActivity6);

                        break;


                    default:
                        Toast.makeText(MainGridView.this, "Wrong Input", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                // This activity is NOT part of this app's task, so create a new task
                // when navigating up, with a synthesized back stack.
                TaskStackBuilder.create(this)
                        // Add all of this activity's parents to the back stack
                        .addNextIntentWithParentStack(upIntent)
                        // Navigate up to the closest parent
                        .startActivities();
            } else {
                // This activity is part of this app's task, so simply
                // navigate up to the logical parent activity.
                NavUtils.navigateUpTo(this, upIntent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
