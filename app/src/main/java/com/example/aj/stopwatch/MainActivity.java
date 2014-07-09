package com.example.aj.stopwatch;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Vector;

// implement: button when held down will display current WorkTime (in real time)
// implement: another activity to use graphView to display stored worktimes
// implement: break time
// implement: alarm to go off after set amount of work time
// implement: settings activity
// implement: improve UI.... L?

public class MainActivity extends Activity {
    // private class variables
    private long StartTimer;
    private long PauseTimer;
    private float WorkTime; // this will be the time displayed and saved in storage
    private static int Counter;     // used to determine if click is start or pause
    private static int WTcount;  // show which WT the value is associated with in sharePref
    public static String filename = "WorkTimeData"; // filename for shared data file
    SharedPreferences SharedWorkTime; //SharedPreference variable for WorkTime data file
    /********************************************************************************/


    /** When button is clicked this method is called
     *  Will be used to start and stop timer **/
    public void clickButton(View view){
        // if counter is even -> clickButton is a StartTimer
        if(Counter%2 == 0){
            StartTimer = System.currentTimeMillis(); // in mSec (no float point)
            // Set text in button to Pause!
            Button CLICK_button = (Button)findViewById(R.id.CLICKbotton);
            CLICK_button.setText("Pause!");
            Counter++;
        }
        // if counter is odd -> clickButton is PauseTimer -> we have a WorkTime
        else{
            PauseTimer = System.currentTimeMillis(); // in mSec (no float point)
            // Set text in button to Pause!
            Button CLICK_button = (Button)findViewById(R.id.CLICKbotton);
            CLICK_button.setText("Start!");
            // set WorkTime as minute with float point (double)
            setWorkTime();
            writeWorkTimeToFileSharedPref();
            displayWorkTime();
            if(Counter==11){
                displaySharedWorkTime();
            }
            Counter++;
        }

    }
    /** Sets the current WorkTime value and converts it to minutes**/
    public void setWorkTime(){
        float tempWT = 0;
        tempWT = (PauseTimer-StartTimer); //msecs
        tempWT = (tempWT/1000); //secs
        tempWT = (tempWT/60);   //minutes

        WorkTime = tempWT; // set WorkTime and convert to minutes
    }
    /** Get WorkTime value if needed by other class**/
    public float getWorkTime(){
        return (WorkTime);
    }

    /** Display worktime to mainactivity screen**/
    public void displayWorkTime(){
        TextView textView = (TextView) findViewById(R.id.DISPLAYworkTime);
        textView.setText("You just worked for " + getWorkTime() + " minutes!");
    }

    /** Display the shared preference file for WorkTime values**/
    public void displaySharedWorkTime(){
        float tempWTsharedPref = 0;
        TextView myTextView = (TextView) findViewById(R.id.DISPLAYsharedWorkTime);
        int tmpCount = 0;
        SharedWorkTime = getSharedPreferences(filename, MODE_PRIVATE);

        do {
            tmpCount++;
            tempWTsharedPref = SharedWorkTime.getFloat("WT:"+tmpCount, -1);
            myTextView.append(""+tempWTsharedPref);
            myTextView.append("\n");
        }while(tempWTsharedPref != -1); //

    }

    /** Write workTIme to internal storage using SharedPreference Key-Value Pair**/
    public void writeWorkTimeToFileSharedPref(){
        WTcount++;
        // make editor object so we can edit our SharedWorkTime file
        SharedPreferences.Editor editor = SharedWorkTime.edit();
        editor.putFloat("WT:" + WTcount, getWorkTime());
        editor.commit();

    }

    ///******************************************************************************// Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializaions:
        SharedWorkTime = getSharedPreferences(filename, MODE_PRIVATE);
        Counter = 0;
        WTcount = 0;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    }
}
