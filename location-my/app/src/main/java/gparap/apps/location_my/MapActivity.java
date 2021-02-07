package gparap.apps.location_my;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by gparap on 2020-09-28.
 */
public class MapActivity extends AppCompatActivity {
    private SupportMapFragment supportMapFragment;
    private LocationListener locationListener;
    private GoogleMap map;
    private final int MIN_TIME = 1000;  //1000 = 1 second
    private final int MIN_DISTANCE = 1; //meters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init();

        //request permission to access location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            showLocation();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MainActivity.REQUEST_CODE);
            }
        }
    }

    private void init() {
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    }

    private void showLocation() {
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                map = googleMap;

                //receiving notifications from the LocationManager when the location has changed
                locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        //get global positioning
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                        //update map
                        try {
                            map.clear();
                            map.setMyLocationEnabled(true);
                            map.addMarker(new MarkerOptions().position(latLng).title("My Location"));
                            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        } catch (SecurityException ignored) {
                        }
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                    }
                };

                //register for location updates
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                try {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE,
                            locationListener);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showLocation();
        }
    }
}