package com.example.aj.stopwatch;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import java.util.zip.Inflater;

public class GraphViewFragment extends Fragment{

    // ERROR

    // Now im just working on getting data from our database
    // and printing to the screen

    /**********************************************************************/ //lifecycle methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup graphviewContainer,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_graphview, graphviewContainer, false);
        //rootView.findViewById(R.id.layout);

        // New object of WorkTImeDbHelper type with MainActivity context
        WorkTimeDbHelper mDbHelper = new WorkTimeDbHelper(getActivity());
        // Real database stuff now
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                WorkTimeContract.WorkTimeEntry._ID,
                WorkTimeContract.WorkTimeEntry.COLUMN_NAME_DATE,
                WorkTimeContract.WorkTimeEntry.COLUMN_NAME_WORKTIME
        };
        // How you want the results sorted in the resulting Cursor
        String sortOrder = WorkTimeContract.WorkTimeEntry._ID + " DESC";
        // Set what you want your curser like, the curser will give you
        // a row at a time of your query
        Cursor cursor = db.query(
                WorkTimeContract.WorkTimeEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        //
        cursor.moveToFirst();
        long itemId = cursor.getLong(
                cursor.getColumnIndexOrThrow(WorkTimeContract.WorkTimeEntry._ID));

        cursor.moveToFirst();
        String itemDate = cursor.getString(
                cursor.getColumnIndexOrThrow(WorkTimeContract.WorkTimeEntry.COLUMN_NAME_DATE));

        cursor.moveToFirst();
        long itemWorkTime = cursor.getLong(
                cursor.getColumnIndexOrThrow(WorkTimeContract.WorkTimeEntry.COLUMN_NAME_WORKTIME));

        /*
        // init example series data
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
                new GraphViewData(1, 2.0d)
                , new GraphViewData(2, 1.5d)
                , new GraphViewData(3, 2.5d)
                , new GraphViewData(4, 1.0d)
        });

        GraphView graphView = new LineGraphView(
                getActivity() // context
                , "GraphViewDemo" // heading
        );
        graphView.addSeries(exampleSeries); // data

        FrameLayout layout = (FrameLayout) rootView.findViewById(R.id.layout);
        layout.addView(graphView);
        */
        return rootView;

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.graph_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
