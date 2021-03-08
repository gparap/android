package com.example.step_counter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gparap on 2020-09-12.
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor sensorStepCounter;
    TextView textViewSteps;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void init() {
        sensorManager = (SensorManager) getApplicationContext().getSystemService(SENSOR_SERVICE);
        sensorStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        //sensor unsupported by device
        if (sensorStepCounter != null){
            Toast.makeText(getApplicationContext(),"Device doesn't support Step Counter", Toast.LENGTH_SHORT).show();
        }
        textViewSteps = findViewById(R.id.textViewSteps);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //update steps field from sensor input
        if (sensorStepCounter != null){
            textViewSteps.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //register listener for sensor
        if (sensorStepCounter != null)
            sensorManager.registerListener(this, sensorStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister listener for sensor
        sensorManager.unregisterListener(this);
    }
}