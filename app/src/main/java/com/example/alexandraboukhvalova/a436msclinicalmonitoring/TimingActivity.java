package com.example.alexandraboukhvalova.a436msclinicalmonitoring;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TimingActivity extends AppCompatActivity {

    private TextView tempTextView;
    private TextView currEvent;
    private Button tempBtn;
    private Chronometer tapTestChronometer;
    private Handler mHandler = new Handler();
    private long startTime;
    private long elapsedTime;
    private final int REFRESH_RATE = 100;
    private String minutes,seconds;
    private long secs,mins,hrs,msecs;
    private boolean stopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);

        tempTextView = (TextView)findViewById(R.id.timerTextView);
        currEvent = (TextView)findViewById(R.id.eventTextView);
        tempBtn = (Button)findViewById(R.id.starTimerButton);

        tapTestChronometer = (Chronometer)findViewById(R.id.TapTestChronometer);
        tapTestChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                String currentTime = tapTestChronometer.getText().toString();
                if (currentTime.equals("00:10")) {
                    tapTestChronometer.stop();
                    tapTestChronometer.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void startClick (View view){
        if(stopped){
            startTime = System.currentTimeMillis() - elapsedTime;
        }
        else{
            startTime = System.currentTimeMillis();
        }
        mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer, 0);
    }

    private void updateTimer (float time){
        secs = (long)(time/1000);
        mins = (long)((time/1000)/60);
        hrs = (long)(((time/1000)/60)/60);

		/* Convert the seconds to String
		 * and format to ensure it has
		 * a leading zero when required
		 */
        secs = secs % 60;
        seconds=String.valueOf(secs);
        if(secs == 0){
            seconds = "00";
        }
        if(secs <10 && secs > 0){
            seconds = "0"+seconds;
        }

		/* Convert the minutes to String and format the String */

        mins = mins % 60;
        minutes=String.valueOf(mins);
        if(mins == 0){
            minutes = "00";
        }
        if(mins <10 && mins > 0){
            minutes = "0"+minutes;
        }

		/* Setting the timer text to the elapsed time */
        ((TextView)findViewById(R.id.timerTextView)).setText(minutes + ":" + seconds);
    }

    private void performTrialTiming() {
        tapTestChronometer.setBase(SystemClock.elapsedRealtime());
        tapTestChronometer.start();
        tapTestChronometer.setVisibility(View.VISIBLE);
    }

    private Runnable startTimer = new Runnable() {
        public void run() {

            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);

            if(secs == 2) {
                /* TODO: Display indication to the user to use their left hand*/
                currEvent.setText("LEFT HAND");
            } else if (secs == 4) {
                /* TODO: Rishabh display a 3 second countdown in the middle of the screen in big numbers*/
                currEvent.setText("3 SECOND COUNTDOWN");
            } else if (secs == 7) {
                /* TODO: Matt count the number of taps during this time period, but make a method outside of this Runnable*/
                /* TODO: Brian display first 10 second timer*/
                performTrialTiming();
                currEvent.setText("LEFT HAND TRIAL 1");
            } else if (secs == 17) {
                /* TODO: Matt display tap count from left hand trial 1*/
                currEvent.setText("DISPLAY TAP COUNT FOR 2 seconds");
            } else if (secs == 19) {
                /* TODO: Brian display 10 second timer*/
                performTrialTiming();
                currEvent.setText("LEFT HAND TRIAL 2");
            } else if (secs == 29) {
                /* TODO: Matt display tap count from left hand trial 1*/
                currEvent.setText("DISPLAY TAP COUNT FOR 2 seconds");
            } else if (secs > 31) {
                currEvent.setText("Press Start Test to begin again.");
                ((TextView)findViewById(R.id.timerTextView)).setText("00:00");
            }

            mHandler.postDelayed(this,REFRESH_RATE);
        }
    };
}
