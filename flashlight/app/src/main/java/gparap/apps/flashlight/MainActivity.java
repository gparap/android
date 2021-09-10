/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.flashlight;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private SwitchCompat switcher;          //an on/off switch
    private boolean isSwitchOn;
    private boolean hasFlashlight;
    private CameraManager cameraManager;
    private String cameraId;                //which camera to use
    private boolean isPermissionGranted;    //permission to use camera feature

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        switcher = findViewById(R.id.switcher);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        isSwitchOn = false;             //by default
        hasFlashlight = false;          //by default
        isPermissionGranted = false;    //by default

        //check if device can use camera features (flashlight)
        hasFlashlight = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        //choose which camera to use
        try {
            cameraId = cameraManager.getCameraIdList()[0];  //back camera
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        //check for permission to use camera
        isPermissionGranted = checkForPermission();

        //ask for permission to use camera by using a dialog
        if (!isPermissionGranted) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Camera Permission");
            builder.setMessage("Application requests permission to use camera.");
            builder.setPositiveButton("OK", null);
            builder.setOnDismissListener(dialog -> requestPermissions(new String[]{Manifest.permission.CAMERA}, 1));
            //show dialog
            builder.show();
        }
    }

    public void OnClickSwitcher(View view) {
        if (hasFlashlight) {
            //turn switch off
            if (isSwitchOn) {
                switcher.setText(R.string.turn_light_on);
                try {
                    cameraManager.setTorchMode(cameraId, false);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                isSwitchOn = false;
            }
            //turn switch on
            else {
                switcher.setText(R.string.turn_light_off);
                try {
                    cameraManager.setTorchMode(cameraId, true);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                isSwitchOn = true;
            }
        }
        //device don't support feature
        else {
            Toast.makeText(this, getString(R.string.toast_unsupported_device), Toast.LENGTH_SHORT).show();
            switcher.setChecked(false);
        }
    }

    //Checks if the app has permission to use the camera
    private boolean checkForPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED;
    }

    //Callback for the result from requesting camera permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //the user grants permission to use camera
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
        }
        //user rejects permission
        else {
            //close activity
            finish();
        }
    }
}