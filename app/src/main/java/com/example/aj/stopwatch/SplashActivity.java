package com.example.aj.stopwatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.aj.stopwatch.R;

import java.util.Timer;
//** This activity will display our splash logo for 3 seconds and then intent mainActivity***//
public class SplashActivity extends Activity {
    private long SplashSleepTime = 3000; // splash screan will display for 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //** Thread class will be used as a timer to start new intent**//
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(SplashSleepTime);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent startMainActivityIntent = new Intent("com.example.aj.stopwatch.MAINACTIVITY");
                    startActivity(startMainActivityIntent);
                }
            }
        };
        // start thread
        timer.start();
    }

    //** Kill splash activity after main activity is launched**//
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
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
