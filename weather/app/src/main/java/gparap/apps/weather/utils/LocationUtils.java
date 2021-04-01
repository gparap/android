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
package gparap.apps.weather.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import gparap.apps.weather.MainActivity;
import gparap.apps.weather.R;

import static android.content.Context.LOCATION_SERVICE;

@SuppressWarnings("FieldCanBeLocal")
public class LocationUtils {
    private static LocationUtils instance;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private final int REQUEST_CODE_ACCESS_FINE_LOCATION = 999;
    private final int MIN_TIME = 1000;
    private final int MIN_DISTANCE = 1000;
    private static String userLatitude, userLongitude;

    public static LocationUtils getInstance() {
        if (instance == null) {
            instance = new LocationUtils();
        }
        return instance;
    }

    public int getRequestCode() {
        return REQUEST_CODE_ACCESS_FINE_LOCATION;
    }

    public String getUserLatitude() {
        return userLatitude;
    }

    public String getUserLongitude() {
        return userLongitude;
    }

    private LocationUtils() {
    }

    public void getUserLocation(Context context) {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        String locationProvider = LocationManager.GPS_PROVIDER;

        //listen for user location changes
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //store user location
                userLatitude = String.valueOf(location.getLatitude());
                userLongitude = String.valueOf(location.getLongitude());
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                //user has disabled location
                Toast.makeText(context, R.string.toast_location_disabled, Toast.LENGTH_SHORT).show();
            }
        };

        //request location permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        (MainActivity) context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ACCESS_FINE_LOCATION);
                return;
            }
        }

        //register for location updates
        locationManager.requestLocationUpdates(locationProvider, MIN_TIME, MIN_DISTANCE, locationListener);
    }

    public void stopLocationUpdates() {
        //unregister for location updates
        if (locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
}
