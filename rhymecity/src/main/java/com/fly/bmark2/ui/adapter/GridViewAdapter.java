/*
 * Copyright 2014 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fly.bmark2.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.fly.bmark2.R;
import com.fly.bmark2.utils.BitmapCache;
import com.nhaarman.listviewanimations.ArrayAdapter;

public class GridViewAdapter extends ArrayAdapter<Integer> {

    private final Context mContext;
    private final BitmapCache mMemoryCache;
    private AQuery aq;
    private String act;
    public GridViewAdapter(final Context context,String act) {
        mContext = context;
        mMemoryCache = new BitmapCache();
        aq = new AQuery(context);
        this.act = act;

        for (int i = 0; i < 100; i++) {
            add(i);
        }

    }

    private class ViewHolder
    {
        TextView selectionName;
        ImageView img;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        final ViewHolder holder;


        if (convertView == null)
        {
            holder = new ViewHolder();

            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           if(act == "grid"){
               convertView = vi.inflate(R.layout.grid_in, parent, false);
            }else{
                convertView = vi.inflate(R.layout.listview_in, parent, false);
            }

            aq.recycle(convertView);

            holder.selectionName = aq.id(R.id.txtImg).getTextView();
            holder.img = aq.id(R.id.txtAct).getImageView();

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        if (holder != null)
        {

            int imageResId;
            // String t;
            switch (getItem(position) % 5) {
                case 0:
                    imageResId = R.drawable.breakfast;
                    aq.id(holder.selectionName).text("BreakFast");
                    break;
                case 1:
                    imageResId = R.drawable.lunch;
                    aq.id(holder.selectionName).text("Lunch");

                    break;
                case 2:
                    imageResId = R.drawable.dinner;
                    aq.id(holder.selectionName).text("Dinner");

                    break;
                case 3:
                    imageResId = R.drawable.dessert;
                    aq.id(holder.selectionName).text("Dessert");

                    break;
                default:
                    imageResId = R.drawable.cycling;
                    aq.id(holder.selectionName).text("Cycling");
            }

            Bitmap bitmap = getBitmapFromMemCache(imageResId);
            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), imageResId);
                addBitmapToMemoryCache(imageResId, bitmap);
            }
            aq.id(holder.selectionName).image(bitmap);
        }

        return convertView;
    }

    private void addBitmapToMemoryCache(final int key, final Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromMemCache(final int key) {
        return mMemoryCache.get(key);
    }
}


