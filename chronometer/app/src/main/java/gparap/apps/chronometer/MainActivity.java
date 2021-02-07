package gparap.apps.chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

/**
 * Created by gparap on 2020-09-15.
 */
public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    long stoppedTime;
    boolean isRunning;  //chronometer is running on foreground
    boolean isStarted;  //chronometer has been started running

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //initialization
    private void init(){
        chronometer = findViewById(R.id.chronometer);
        stoppedTime = 0L;
        isRunning = false;
        isStarted = false;
    }

    //start chronometer
    private void start(){
        if (!isRunning) {
            //start clock from now on (not app boot) minus any stopped time
            chronometer.setBase(SystemClock.elapsedRealtime() - stoppedTime);
            chronometer.start();
            isRunning = true;
        }
    }

    //stop chronometer
    private void stop(){
        if (isRunning) {
            //hold time that passed since the start of the counting and stop
            stoppedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            isRunning = false;
        }
    }

    //Start the chronometer with START button
    public void OnClickStart(View view){
        start();
    }

    //Stop the chronometer with STOP button
    public void OnClickStop(View view){
        stop();
    }

    //Clear the chronometer with CLEAR button
    public void OnClickClear(View view){
        //clear time and reset variables
        chronometer.setBase(SystemClock.elapsedRealtime());
        stoppedTime = 0L;
        chronometer.stop();
        isRunning = false;
        isStarted = false;
    }

    @Override
    protected void onPause() {
        super.onPause();

        //hold the chronometer state before losing focus
        isStarted = isRunning;
        stop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //resume only if chronometer has been started running before losing focus
        if (isStarted){
            start();
        }
    }
}
