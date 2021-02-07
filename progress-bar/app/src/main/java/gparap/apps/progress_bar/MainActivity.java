package gparap.apps.progress_bar;

import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by gparap on 2020-08-26.
 */
public class MainActivity extends AppCompatActivity {

    int progressCounter = 0;    //helper
    int progressFactor = 1;     //how fast is the project

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get widget
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar_horizontal);

        //update progress bar based on fixed timer
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                progressCounter += progressFactor;
                progressBar.setProgress(progressCounter);
            }
        };

        //schedule update if progress is not complete
        if (progressCounter < 100) {
            timer.schedule(timerTask, 0, 200);
        }
    }
}