package com.example.aj.stopwatch;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by root on 7/26/14.
 */
public class StopWatch {
    //****************************** member variabls*******************//
    private static long mStartTimer;
    private static long mPauseTimer;
    private static long mWorkTime; // this will be the time displayed and saved in storage


    //******************************* Methods ************************************//

    // sets StartTimer value
    public static void setStartTimer(){
        mStartTimer = System.currentTimeMillis(); // in mSec (no float point)
    }
    //sets PauseTimer value
    public static void setPauseTimer(){
        mPauseTimer = System.currentTimeMillis(); // in mSec (no float point)
    }
    public static void setWorkTime(){
        mWorkTime = ((mPauseTimer-mStartTimer)/1000); // worktime in seconds
    }
    //get WorkTime value long
    public static long getWorkTime(){
        return (mWorkTime);
    }


}
