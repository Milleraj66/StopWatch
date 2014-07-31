package com.example.aj.stopwatch;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by root on 7/26/14.
 */
public class StopWatchService extends Service{
    /*************************** Member Variables *********************/

    /** START LIFECYLE METHODS **/
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        ChronoFragment.startChrono();

        return START_STICKY; // service go forever
    }

    @Override
    public void onDestroy(){
        ChronoFragment.stopChrono();

        /** Get current date and reformat it **/
        Calendar c = Calendar.getInstance();
        //System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        /** Insert worktime data into database **/
        // Create WorkTimeDbHelper object
        WorkTimeDbHelper mDbHelper = new WorkTimeDbHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(WorkTimeContract.WorkTimeEntry.COLUMN_NAME_ENTRY_ID, id);
        values.put(WorkTimeContract.WorkTimeEntry.COLUMN_NAME_DATE, formattedDate);
        values.put(WorkTimeContract.WorkTimeEntry.COLUMN_NAME_WORKTIME, ChronoFragment.getChronoWorkTime());
        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(WorkTimeContract.WorkTimeEntry.TABLE_NAME, null, values);


        super.onDestroy();
    }
    /** END LIFECYLE METHODS **/

    /******************* getContext Method *********************/


}


/**** OLD CODE THAT MIGHT BE REUSED ****/




    /*
        Context context = (Context) getContext();

    public Object getContext() {
        return context;
    }*/

