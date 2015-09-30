package com.fly.bmark2.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fly.bmark2.R;
import com.fly.bmark2.augmented3.MainActivity;
import com.fly.bmark2.base.BaseFragment;
import com.fly.bmark2.ui.activity.LocationPage.ContactusLocationView;
import com.fly.bmark2.ui.activity.FragmentContainerActivity;
import com.fly.bmark2.ui.activity.SelectPage.selectionPage;
import com.fly.bmark2.ui.adapter.SelectionAdapter;
import com.fly.bmark2.ui.obj.Selection;
import com.fly.bmark2.ui.presenter.HomePresenter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class selectFragment extends BaseFragment implements HomePresenter.HomeView {

    private int fragmentContainerId;
    private SQLiteDatabase db;
    private static final int INITIAL_DELAY_MILLIS = 300;
    AlertDialog alert;
    private ListView listView;
    private List<Selection> selectionList;
    private Boolean offPage = false;



    public static selectFragment newInstance(Bundle getData) {

        selectFragment fragment = new selectFragment();
        Bundle args = new Bundle(getData);
        fragment.setArguments(args);
        return fragment;

        // new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // FireFlyApplication.get(getActivity()).createScopedGraph(new HomeModule(this)).inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

       // View view = inflater.inflate(R.layout.selection, container, false);
       View view = inflater.inflate(R.layout.selection_layout, container, false);

        ButterKnife.inject(this, view);
        db = createDBconnection(getActivity());
        Cursor c = null;

        selectionList = new ArrayList<Selection>();
        Bundle actID = getArguments();
        String level = null;
        String id = null;
        offPage = false;

        if(actID.getString("LEVEL") != null)
        {
            level = actID.getString("LEVEL");
            id = actID.getString("id");
            if(level.equals("2"))
            {
                offPage = true;
            }
        }

        //level = "1";

        String[] args={id};

        if(level == "" || level == null)
        {
            c = db.rawQuery("SELECT * FROM state", null);
            if (c.moveToFirst()) {

                while (c.isAfterLast() == false) {

                    String thisName = c.getString(c.getColumnIndex("stateName"));
                    String thisId = c.getString(c.getColumnIndex("stateID"));
                    String thisLevel = c.getString(c.getColumnIndex("level"));

                    selectionList.add(new Selection(thisName, thisId, thisLevel));

                    c.moveToNext();
                }
            }
        }
        else if(level.equals("1"))
        {
           //c = db.rawQuery("SELECT * FROM daerah WHERE stateID = ?", null);
           c = db.rawQuery("SELECT * FROM daerah", null);
            if (c.moveToFirst()) {

                while (c.isAfterLast() == false) {

                    String thisName = c.getString(c.getColumnIndex("daerahName"));
                    String thisId = c.getString(c.getColumnIndex("daerahID"));
                    String thisLevel = c.getString(c.getColumnIndex("level"));

                    selectionList.add(new Selection(thisName, thisId, thisLevel));

                    c.moveToNext();
                }
            }

        }
        else if(level.equals("2"))
        {
            c = db.rawQuery("SELECT * FROM bml WHERE daerahID = ?", args);
            if (c.moveToFirst()) {

                while (c.isAfterLast() == false) {

                    String thisName = c.getString(c.getColumnIndex("bmlName"));
                    String thisId = c.getString(c.getColumnIndex("bmlID"));
                    String thisLevel = c.getString(c.getColumnIndex("level"));

                    selectionList.add(new Selection(thisName, thisId, thisLevel));

                    c.moveToNext();
                }
            }

        }
        else
        {
            //selectViewMethod("BREAKFAST");
            Log.e("DEADCODE", "TRUE");

        }
        ///////




        listView = (ListView) view.findViewById(R.id.listView1);

        SwingBottomInAnimationAdapter listviewAdapter = new SwingBottomInAnimationAdapter(new SelectionAdapter(getActivity(), selectionList));

        listviewAdapter.setAbsListView(listView);

        listView.setAdapter(listviewAdapter);

        assert listviewAdapter.getViewAnimator() != null;
        listviewAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Selection clickedItem = (Selection) parent.getItemAtPosition(position);

                if (offPage) {
                    //String tag = v.findViewById(R.id.txtAct).getTag().toString();
                    selectViewMethod("BREAKFAST");
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), selectionPage.class);
                    intent.putExtra("id", clickedItem.getId());
                    intent.putExtra("LEVEL", clickedItem.getLevel());
                    intent.putExtra("name", clickedItem.getName());
                    getActivity().startActivity(intent);
                }
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentContainerId = ((FragmentContainerActivity) getActivity()).getFragmentContainerId();
    }


    public void selectViewMethod(final String tag)
    {

        Log.e("HERE","2");
        final CharSequence colors[] = new CharSequence[] {"Google Map", "Augmented Reality"};

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.custom_alert, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customLayout);
        builder.setTitle("View on");
        alert = builder.create();
        alert.show();

        aq.recycle(customLayout);
        aq.id(R.id.gmapID).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ContactusLocationView.class);
                intent.putExtra("tag", tag);
                getActivity().startActivityForResult(intent, 1);
                aq.id(R.id.actChoice).visibility(View.GONE);
                aq.id(R.id.progressbar).visibility(View.VISIBLE);

            }
        });

        aq.id(R.id.arID).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("tag", tag);
                getActivity().startActivity(intent);
                aq.id(R.id.actChoice).visibility(View.GONE);
                aq.id(R.id.progressbar).visibility(View.VISIBLE);
            }
        });

    }

    public void onResume()
    {
        super.onResume();
        if( alert != null) {
            if (alert.isShowing()) {
                alert.dismiss();
            }
        }
    }
}


