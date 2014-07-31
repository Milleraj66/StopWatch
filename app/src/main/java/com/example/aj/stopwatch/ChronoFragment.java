package com.example.aj.stopwatch;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

/**
 * Created by root on 7/26/14.
 */

// mChronoWorkTime is always displayed one iteration behind

public class ChronoFragment extends Fragment{
    /*************************** Member Variables *********************/
    public static Chronometer mChrono;
    public static long mChronoWorkTime;

    /** START LIFECYLE METHODS **/
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mChronoWorkTime = 0; // Initialize mChronoWorkTime at fragment onCreate

    }// end onCreate(Bundle)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup chronoContainer,
                             Bundle savedInstanceState) {
        // inflate ChronoFragment layout as rootview
        View rootView = inflater.inflate(R.layout.fragment_chrono, chronoContainer, false);
        mChrono = (Chronometer) rootView.findViewById(R.id.chronometer);

        return rootView;
    }
    /** END LIFECYLE METHODS **/


    /*************************** startChrono Method ******************************/
    public static void startChrono(){
        // set base time for chronomeater
        mChrono.setBase(SystemClock.elapsedRealtime());
        // start chronometer timer
        mChrono.start();
    }

    /*************************** stopChrono Method ******************************/
    public static void stopChrono(){
        mChrono.stop();
        setChronoWorkTime();


    }

    /*************************** setChronoWorkTime Method ******************************/
    public static void setChronoWorkTime(){
        // set chronoWorkTime in seconds
        mChronoWorkTime = ((SystemClock.elapsedRealtime() - mChrono.getBase())/1000);
    }

    /*************************** getChronoWorkTime Method ******************************/
    public static long getChronoWorkTime(){
        return (mChronoWorkTime);
    }



    // CODE NOT CURRENTLY BEING USED


    /*************************** displayChronoWorkTime Method ******************************/
    public void displayChronoWorkTime(){

        TextView  textView = (TextView) getActivity().findViewById(R.id.display_chrono_worktime);
        textView.setText("You just worked for " + getChronoWorkTime() + " seconds!");
    }



}


/**** OLD CODE THAT MIGHT BE REUSED ****/



    /** Write workTIme to internal storage using SharedPreference Key-Value Pair **/
    /*
    //public static String mFilename = "ChronoWorkTimeData";
    //public static SharedPreferences mSharedChronoWorkTime;
    //private static int mChronoWTcount;
    //mChronoWTcount = 0;
    public static void writeWorkTimeToFileSharedPref(){
        mChronoWTcount++;
        // make editor object so we can edit our SharedWorkTime file
        SharedPreferences.Editor editor = mSharedChronoWorkTime.edit();
        editor.putLong("WT:" + mChronoWTcount, StopWatch.getWorkTime());
        editor.commit();
    }
    //writeWorkTimeToFileSharedPref();
    */