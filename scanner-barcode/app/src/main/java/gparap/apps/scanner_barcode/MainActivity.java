package gparap.apps.scanner_barcode;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

/**
 * Created by gparap on 2020-09-26.
 */
public class MainActivity extends AppCompatActivity {
    Button buttonScan;
    TextView textViewScan;
    SurfaceView surfaceViewScan;
    SurfaceHolder surfaceHolder;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addSurfaceHolderCallback();

        //check for camera permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        }
    }

    /**
     * Initialization
     */
    private void init() {
        //get widgets
        buttonScan = findViewById(R.id.buttonScan);
        textViewScan = findViewById(R.id.textViewScan);
        surfaceViewScan = findViewById(R.id.surfaceViewScan);

        //init surface and holder
        surfaceViewScan.setVisibility(View.INVISIBLE);
        surfaceHolder = surfaceViewScan.getHolder();

        //init detector to search for barcodes
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
    }

    /**
     * Starts scanning codes
     * @param view button scan
     */
    @SuppressLint("MissingPermission")
    public void onClickButtonScan(View view) {
        surfaceViewScan.setVisibility(View.VISIBLE);//show surface
        buttonScan.setVisibility(View.INVISIBLE);   //hide button

        //receive preview frames from camera and send them to detector
        if (barcodeDetector.isOperational()) {
            //define a post-processing action to be executed for each detection
            barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
                @Override
                public void release() {
                }

                @Override
                public void receiveDetections(final Detector.Detections<Barcode> detections) {
                    if (detections.getDetectedItems().size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textViewScan.setText(detections.getDetectedItems().valueAt(0).displayValue);
                            }
                        });
                    }
                }
            });

            //receives preview frames from camera
            cameraSource = new CameraSource.Builder(this, barcodeDetector).build();
            try {
                cameraSource.start(surfaceHolder);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds a callback for surface holder
     */
    private void addSurfaceHolderCallback(){
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) { }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //stop and release camera resources
                cameraSource.release();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //close camera
        if (cameraSource != null)
            cameraSource.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //show button
        if (buttonScan != null)
            buttonScan.setVisibility(View.VISIBLE);
    }
}
