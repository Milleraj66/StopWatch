package com.example.aj.stopwatch;

import android.provider.BaseColumns;

/**
 * Created by root on 7/30/14.
 */

/** A contract class is a container for constants that define names for URIs, tables, and columns.
 * The contract class allows you to use the same constants across all the other classes in the same package.
 * This lets you change a column name in one place and have it propagate throughout your code. **/
public final class WorkTimeContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public WorkTimeContract() {}

    /**************** Inner class that defines the table contents*****/
    public static abstract class WorkTimeEntry implements BaseColumns {
        public static final String TABLE_NAME = "WorkTime";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_WORKTIME = "worktime";
        //public static final String COLUMN_NAME_SUBTITLE = "subtitle";

    } // end WorkTimeEntry class

}// end WorkTimeContract Class
