package com.example.aj.stopwatch;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

// implement: button when held down will display current WorkTime (in real time)
// implement: break time
// implement: alarm to go off after set amount of work time
// implement: settings activity
// implement: improve UI.... L?

//FIX:
// mChronoWorkTime is always displayed one iteration behind


public class MainActivity extends Activity {
    /*************************Member Variables******************/
    private static int mCounter;     // used to determine if click is  tart or pause

    /** START LIFECYLE METHODS **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Fragment manager in onCreate method **/
        if (savedInstanceState == null) {
            // Create new fragment and transaction
            Fragment newFragment = new ChronoFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.add(R.id.CHRONOviewgroup, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

        mCounter = 0; // Initialize Counter

    }// end void onCreate(Bundle)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }// end boolean onCreateOptionsMenu(Menu)

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle presses on the action bar items
        /** Switch-Case to handel action bar menue item clicks **/
        switch (item.getItemId()) {
            case R.id.action_graphView:
                getFragmentManager().beginTransaction()
                        .add(R.id.GRAPHVIEWviewgroup, new GraphViewFragment())
                        .commit();
                return true;
            //case R.id.action_settings:
            //openSettings();
            //return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }// end boolean onOptionsItemSelected(MenuItem)
    /** END LIFECYLE METHODS **/


    /********************************* Button Method ****************************/
    public void clickButton(View view){
        /** if counter is even -> clickButton is a StartTimer **/
        if(mCounter%2 == 0){
            // Start the service to do real work
            startService(new Intent(getBaseContext(), StopWatchService.class));

            // Set text in button to Pause!
            Button CLICK_button = (Button)findViewById(R.id.CLICKbotton);
            CLICK_button.setText("Pause!");

            mCounter++;
        }
        /** if counter is odd -> clickButton is PauseTimer -> we have a WorkTime **/
        else{
            // call the stopService method to do real work
            stopService(new Intent(getBaseContext(), StopWatchService.class));

            // Set text in button to Pause!
            Button CLICK_button = (Button)findViewById(R.id.CLICKbotton);
            CLICK_button.setText("Start!");

            // display chronometer worktime
            displayWorkTime();

            mCounter++;
        }

    }


    // CODE NOT CURRENTLY BEING USED


    /** Display worktime to mainactivity screen**/
    public void displayWorkTime(){
        TextView textView = (TextView) findViewById(R.id.DISPLAYworkTime);
        textView.setText("You just worked for " + ChronoFragment.getChronoWorkTime() + " seconds!");
    }

}

/**** OLD CODE THAT MIGHT BE REUSED ****/


/** Create intent to start GraphViewActivity when menu item action_graphView is clicked**/
     /*
    public void startGraphViewActivity(){
        Intent intent = new Intent(this, GraphViewFragment.class);
        startActivity(intent);
    }*/