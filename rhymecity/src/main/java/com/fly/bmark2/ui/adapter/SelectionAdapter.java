package com.fly.bmark2.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.fly.bmark2.R;
import com.fly.bmark2.ui.obj.Selection;

import java.util.List;


public class SelectionAdapter extends ArrayAdapter<Selection>
{

    private AQuery aq;
    private final List<Selection> list;

    private class ViewHolder
    {
        TextView selectionName;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public SelectionAdapter(Context _context, List<Selection> departmentList)
    {
        super(_context, R.layout.selection_layout, departmentList);
        aq = new AQuery(_context);
        this.list = departmentList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;

        Selection product = getItem(position);

        if (convertView == null)
        {
            holder = new ViewHolder();

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.selection_layout, parent, false);
            aq.recycle(convertView);

            holder.selectionName = aq.id(R.id.txtSelection).getTextView();

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        if (holder != null)
        {
            aq.id(holder.selectionName).text(product.getName());
        }

        return convertView;
    }
}