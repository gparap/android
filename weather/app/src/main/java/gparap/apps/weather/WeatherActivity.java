/*
 * Copyright 2020 gparap
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
package gparap.apps.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;

import gparap.apps.weather.model.CurrentWeatherDataModel;
import gparap.apps.weather.utils.CurrentWeatherParser;
import gparap.apps.weather.utils.WeatherUtils;

import static android.view.View.VISIBLE;

@SuppressWarnings("Convert2Lambda")
@SuppressLint("NonConstantResourceId")
public class WeatherActivity extends AppCompatActivity {
    private TextView labelWeather,
            labelTemperature, labelTemperatureMax, labelTemperatureMin,
            labelWindSpeed, labelAirPressure, labelHumidity,
            textViewWeather,
            textViewTemperature, textViewTemperatureMax, textViewTemperatureMin,
            textViewWindSpeed, textViewAirPressure, textViewHumidity;
    private ImageView imageViewWeather;
    private Button buttonWeatherProvider;
    private EditText editTextCity;
    private ImageView iconCitySearch;
    private String city = "";
    private CurrentWeatherDataModel model;
    final int REQUEST_CODE_ACCESS_FINE_LOCATION = 999;
    private Location userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getWidgets();

        //find the weather where the user is currently located
        getUserLocation();

        //search a city for weather
        iconCitySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCitySearchEmpty()) {
                    city = editTextCity.getText().toString();
                    hideSoftKeyboard(v);
                    getCurrentWeather(false);
                }
            }
        });

        //goto weather provider website
        buttonWeatherProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherUtils.gotoProviderWebsite(WeatherActivity.this);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getUserLocation();
            } else {
                Toast.makeText(this, R.string.toast_location_permission_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Gets and displays weather forecast.
     *
     * @param response string containing weather data
     * @throws JSONException JSONException
     */
    @SuppressLint("SetTextI18n")
    private void displayCurrentWeather(String response) throws JSONException {
        model = CurrentWeatherParser.getInstance().getCurrentWeatherDataModel(response);

        //display the proper icon according to the weather
        WeatherUtils.displayWeatherIcon(model.getWeather(), imageViewWeather);

        //convert temperatures
        String temp = WeatherUtils.convertKelvinToCelcious(model.getTemperature());
        String temp_max = WeatherUtils.convertKelvinToCelcious(model.getTemperatureMax());
        String temp_min = WeatherUtils.convertKelvinToCelcious(model.getTemperatureMin());

        //fill in weather widgets
        textViewWeather.setText(model.getWeather());
        textViewTemperature.setText(WeatherUtils.formatWeatherValue(temp, 0) + WeatherUtils.SUFFIX_CELCIOUS);
        textViewTemperatureMax.setText(WeatherUtils.formatWeatherValue(temp_max, 0) + WeatherUtils.SUFFIX_CELCIOUS);
        textViewTemperatureMin.setText(WeatherUtils.formatWeatherValue(temp_min, 0) + WeatherUtils.SUFFIX_CELCIOUS);
        textViewWindSpeed.setText(model.getWindSpeed() + WeatherUtils.SUFFIX_WIND_SPEED);
        textViewAirPressure.setText(model.getAirPressure() + WeatherUtils.SUFFIX_AIR_PRESSURE);
        textViewHumidity.setText(model.getHumidity() + WeatherUtils.SUFFIX_HUMIDITY);
    }

    /**
     * Gets current weather data using the provider API.
     *
     * @param isUserLocationBased is search based on user location or city search
     */
    private void getCurrentWeather(boolean isUserLocationBased) {
        //init RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherActivity.this);

        //request response from URL
        String url;
        if (isUserLocationBased) {
            url = WeatherUtils.generateURL(userLocation.getLatitude(), userLocation.getLongitude());
        } else {
            url = WeatherUtils.generateURL(city);
        }

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //display the weather
                            showLabelWidgets();
                            displayCurrentWeather(response);

                            //display the user's city
                            editTextCity.setText(model.getCityName());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WeatherActivity.this, R.string.toast_cannot_fetch_weather, Toast.LENGTH_SHORT).show();
                        //TODO: hide everything weather related
                        try {
                            Log.e("VOLLEY_ERROR", "onErrorResponse: " + error);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        //add request to RequestQueue
        requestQueue.add(stringRequest);
    }

    public void getUserLocation() {
        FusedLocationProviderClient fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);

        //Used for receiving notifications from the FusedLocationProviderApi
        // when the device location has changed or can no longer be determined.
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    System.out.println(location.toString());
                }
            }
        };

        //request location permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(WeatherActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        WeatherActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ACCESS_FINE_LOCATION);
                return;
            }
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                task.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location == null) {
                            //request location permissions
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (ActivityCompat.checkSelfPermission(WeatherActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(
                                            WeatherActivity.this,
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            REQUEST_CODE_ACCESS_FINE_LOCATION);
                                    return;
                                }
                            }
                            fusedLocationProviderClient.requestLocationUpdates(createLocationRequest(),
                                    locationCallback,
                                    Looper.getMainLooper());
                        } else {
                            userLocation = location;
                            getCurrentWeather(true);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e.toString());
                        //user has disabled location
                        Toast.makeText(WeatherActivity.this, R.string.toast_location_disabled, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private boolean isCitySearchEmpty() {
        if (editTextCity.getText().toString().isEmpty()) {
            Toast.makeText(WeatherActivity.this, R.string.toast_empty_search, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void showLabelWidgets() {
        labelWeather.setVisibility(VISIBLE);
        labelTemperature.setVisibility(VISIBLE);
        labelTemperatureMax.setVisibility(VISIBLE);
        labelTemperatureMin.setVisibility(VISIBLE);
        labelWindSpeed.setVisibility(VISIBLE);
        labelAirPressure.setVisibility(VISIBLE);
        labelHumidity.setVisibility(VISIBLE);
    }

    private void getWidgets() {
        buttonWeatherProvider = findViewById(R.id.buttonWeatherProvider);
        editTextCity = findViewById(R.id.editTextSearchCity);
        iconCitySearch = findViewById(R.id.imageViewSearchCityIcon);
        imageViewWeather = findViewById(R.id.imageViewWeatherIcon);
        labelWeather = findViewById(R.id.labelWeather);
        labelTemperature = findViewById(R.id.labelTemperature);
        labelTemperatureMax = findViewById(R.id.labelTemperatureMax);
        labelTemperatureMin = findViewById(R.id.labelTemperatureMin);
        labelWindSpeed = findViewById(R.id.labelWindSpeed);
        labelAirPressure = findViewById(R.id.labelAirPressure);
        labelHumidity = findViewById(R.id.labelHumidity);
        textViewWeather = findViewById(R.id.textViewWeather);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewTemperatureMax = findViewById(R.id.textViewTemperatureMax);
        textViewTemperatureMin = findViewById(R.id.textViewTemperatureMin);
        textViewWindSpeed = findViewById(R.id.textViewWindSpeed);
        textViewAirPressure = findViewById(R.id.textViewAirPressure);
        textViewHumidity = findViewById(R.id.textViewHumidity);
    }
}