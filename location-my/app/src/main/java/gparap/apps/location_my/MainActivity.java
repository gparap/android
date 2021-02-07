package gparap.apps.location_my;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by gparap on 2020-09-28.
 */
public class MainActivity extends AppCompatActivity {
    private Button buttonShowMyLocation;
    public final static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //Check Google Play services API availability
        int statusCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (statusCode == ConnectionResult.SUCCESS) {
            //goto Map Activity
            buttonShowMyLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(intent);
                }
            });
        } else if (statusCode == ConnectionResult.SERVICE_MISSING
                || statusCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED
                || statusCode == ConnectionResult.SERVICE_DISABLED) {
            //user needs to install an update
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(
                    MainActivity.this, statusCode, REQUEST_CODE,
                    new android.content.DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            finishAndRemoveTask();
                            Toast.makeText(getApplicationContext(),
                                    "Please update Google Play Services", Toast.LENGTH_SHORT).show();
                        }
                    });
            dialog.show();
        } else {
            //API will not work on this device
            Toast.makeText(this, "No Google Play Services on this device", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        buttonShowMyLocation = findViewById(R.id.buttonShowMyLocation);
    }
}