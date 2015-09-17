package com.fly.bmark2.ui.activity.GridView;

/**
 * Created by metechuser on 17/09/2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fly.bmark2.R;


public class GridAdapter extends BaseAdapter {
    private Context context;
    private final String[] mobileValues;



    public GridAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;

    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View v;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(     Context.LAYOUT_INFLATER_SERVICE );
            v = inflater.inflate(R.layout.item_grid, parent, false);
        } else {
            v = (View) convertView;
        }
        TextView text = (TextView)v.findViewById(R.id.grid_item_label);
        text.setText(mobileValues[position]);
        ImageView imageView = (ImageView)v.findViewById(R.id.grid_item_image1);



        String mobile = mobileValues[position];

        if (mobile.equals("cycling")) {
            imageView.setImageResource(R.drawable.cycling);
        } else if (mobile.equals("lunch")) {
            imageView.setImageResource(R.drawable.lunch);
        } else if (mobile.equals("dinner")) {
            imageView.setImageResource(R.drawable.dinner);
        } else if (mobile.equals("breakfast")) {
            imageView.setImageResource(R.drawable.breakfast);
        } else if (mobile.equals("shopping")) {
            imageView.setImageResource(R.drawable.shopping);
        } else if (mobile.equals("medical")) {
            imageView.setImageResource(R.drawable.medical);

        }

        return v;

    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}





