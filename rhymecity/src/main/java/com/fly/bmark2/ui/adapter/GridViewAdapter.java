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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.fly.bmark2.R;
import com.fly.bmark2.util.BitmapCache;
import com.nhaarman.listviewanimations.ArrayAdapter;

public class GridViewAdapter extends ArrayAdapter<Integer> {

    private final Context mContext;
    private final BitmapCache mMemoryCache;

    public GridViewAdapter(final Context context) {
        mContext = context;
        mMemoryCache = new BitmapCache();

        for (int i = 0; i < 100; i++) {
            add(i);
        }

    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;


        if (imageView == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }

        int imageResId;
       // String t;
        switch (getItem(position) % 5) {
            case 0:
                imageResId = R.drawable.breakfast;
                break;
            case 1:
                imageResId = R.drawable.lunch;
                break;
            case 2:
                imageResId = R.drawable.dinner;
                break;
            case 3:
                imageResId = R.drawable.dessert;
                break;
            default:
                imageResId = R.drawable.cycling;
        }

        Bitmap bitmap = getBitmapFromMemCache(imageResId);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(mContext.getResources(), imageResId);
            addBitmapToMemoryCache(imageResId, bitmap);
        }
        imageView.setImageBitmap(bitmap);

        return imageView;
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


