package gparap.apps.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by gparap on 2020-09-10.
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    ImageView imageViewCompass;
    SensorManager sensorManager;
    Sensor sensorAccelerometer,
           sensorMagneticField;
    float[] gravityData,        //accelerometer reading data
            geomagneticData,    //magnetic field reading data
            rotationMatrixData; //data based on accelerometer and magnetic field
    float  azimuth;             //rotation around the -Z axis
    int fromDegrees = 0;        //starting degrees of rotation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        imageViewCompass = findViewById(R.id.imageViewCompass);

        //sensor manager and sensors (acceleration, magnetic field)
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        //array that holds rotation matrix data
        rotationMatrixData = new float[9];
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //get accelerometer data
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            gravityData = event.values;
        }
        //get magnetic field data
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            geomagneticData = event.values;
        }
        //compute the rotation matrix based on previous data
        if (gravityData != null && geomagneticData != null){
            SensorManager.getRotationMatrix(rotationMatrixData, null, gravityData, geomagneticData);
        }

        //get azimuth from orientation values
        float[] orientationValues = new float[3];
        if (rotationMatrixData != null){
            SensorManager.getOrientation(rotationMatrixData, orientationValues);
            azimuth = orientationValues[0];
        }

        //convert azimuth from rand to degrees
        int degrees = (int) (azimuth * 180 / Math.PI);

        //rotate image that many degrees
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, -degrees,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);
        imageViewCompass.startAnimation(rotateAnimation);

        //continue animation from -Z axis previous degrees
        fromDegrees = -degrees;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        //register listeners for sensors only when app is in the foreground
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorMagneticField, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //unregister listeners for sensors when app is in the background
        sensorManager.unregisterListener(this);
    }
}