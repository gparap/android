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

import static android.view.View.VISIBLE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.VolleyError;

import org.json.JSONException;

import gparap.apps.weather.api.VolleyService;
import gparap.apps.weather.api.VolleyServiceCallback;
import gparap.apps.weather.data.WeatherModel;
import gparap.apps.weather.utils.WeatherModelParser;
import gparap.apps.weather.utils.WeatherUtils;
import gparap.apps.weather.viewmodel.WeatherActivityViewModel;

public class WeatherActivity extends AppCompatActivity {
    private TextView textViewWeather,
            labelTemperature, labelTemperatureFeelsLike, labelTemperatureMax, labelTemperatureMin,
            labelWindSpeed, labelAirPressure, labelHumidity, labelVisibility, labelCloudiness,
            textViewTemperature, textViewTemperatureFeelsLike, textViewTemperatureMax, textViewTemperatureMin,
            textViewWindSpeed, textViewAirPressure, textViewHumidity, textViewVisibility, textViewCloudiness;
    private ImageView imageViewWeather;
    private Button buttonWeatherProvider;
    private EditText editTextCity;
    private ImageView iconCitySearch;
    private final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 999;
    private String city = "";
    private Location userLocation;
    private WeatherActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getWidgets();

        //create ViewModel for this activity
        viewModel = new ViewModelProvider(this).get(WeatherActivityViewModel.class);

        //find the weather where the user is currently located
        if (viewModel.getLocationData().getValue() == null) {
            getUserLocation();
        }

        //search a city for weather
        iconCitySearch.setOnClickListener(v -> {
            if (!isCitySearchEmpty()) {
                city = editTextCity.getText().toString();
                hideSoftKeyboard(v);
                getCurrentWeather(false);
            }
        });

        //goto weather provider website
        buttonWeatherProvider.setOnClickListener(v -> WeatherUtils.gotoProviderWebsite(WeatherActivity.this));

        //observe and display weather data
        viewModel.getWeatherData().observe(this, this::displayWeather);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_ACCESS_COARSE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getUserLocation();
            } else {
                Toast.makeText(this, R.string.toast_location_permission_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayWeather(WeatherModel model) {
        showLabelWidgets();

        //display the user's city
        editTextCity.setText(model.getCityName());

        //display the proper icon according to the weather
        WeatherUtils.displayWeatherIcon(model.getWeather(), model.getDescription(), imageViewWeather);

        //convert temperatures
        String temp = String.valueOf(model.getTemperature());
        String temp_feels_like = String.valueOf(model.getFeelsLike());
        String temp_max = String.valueOf(model.getTemperatureMax());
        String temp_min = String.valueOf(model.getTemperatureMin());

        //get unit system for visibility
        String units = WeatherUtils.getMeasureUnit();

        //fill in weather widgets
        textViewWeather.setText(model.getDescription());
        textViewTemperature.setText(WeatherUtils.formatWeatherValue(temp, 0));
        textViewTemperatureFeelsLike.setText(WeatherUtils.formatWeatherValue(temp_feels_like, 0));
        textViewTemperatureMax.setText(WeatherUtils.formatWeatherValue(temp_max, 0));
        textViewTemperatureMin.setText(WeatherUtils.formatWeatherValue(temp_min, 0));
        textViewWindSpeed.setText(model.getWindSpeed() + WeatherUtils.getWindSpeedUnit());
        textViewAirPressure.setText(model.getAirPressure() + WeatherUtils.SUFFIX_AIR_PRESSURE);
        textViewHumidity.setText(model.getHumidity() + WeatherUtils.SUFFIX_HUMIDITY_CLOUDINESS);
        if (isTablet()) {
            if (units.equals(WeatherUtils.UNIT_METRIC)) {
                textViewVisibility.setText(model.getVisibility() + WeatherUtils.SUFFIX_VISIBILITY_METRIC);
            } else {
                textViewVisibility.setText(WeatherUtils.convertMetersToMiles(model.getVisibility())
                        + WeatherUtils.SUFFIX_VISIBILITY_IMPERIAL);
            }
            textViewCloudiness.setText(model.getCloudiness() + WeatherUtils.SUFFIX_HUMIDITY_CLOUDINESS);
        }
    }

    /**
     * Gets current weather data using the provider API.
     *
     * @param isUserLocationBased is search based on user location or city search
     */
    private void getCurrentWeather(boolean isUserLocationBased) {
        //generate request URL
        String url;
        if (isUserLocationBased) {
            url = WeatherUtils.generateURL(userLocation.getLatitude(), userLocation.getLongitude());
        } else {
            url = WeatherUtils.generateURL(city);
        }

        //create callback to communicate with volley service
        VolleyServiceCallback volleyServiceCallback = new VolleyServiceCallback() {
            @Override
            public void onResponse(String response) {
                //parse json response to model
                try {
                    viewModel.getWeatherData().setValue(WeatherModelParser.getInstance().getCurrentWeatherDataModel(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(WeatherActivity.this, R.string.toast_cannot_fetch_weather, Toast.LENGTH_SHORT).show();
            }
        };

        //get current weather from weather api provider using volley
        VolleyService.getInstance(volleyServiceCallback).getApiResponse(this, url);
    }

    public void getUserLocation() {
        //request location permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(WeatherActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        WeatherActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_CODE_ACCESS_COARSE_LOCATION);
                return;
            }
        }

        //observe for user location updates..
        //!!! we are using named observer here,
        //!!! because we want to remove only THIS observer whenever a user location is found
        final Observer<Location> locationObserver = new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                userLocation = location;
                getCurrentWeather(true);
                //..until we have a user location
                if (userLocation != null) {
                    viewModel.getLocationData().removeObserver(this);
                }
            }
        };
        viewModel.getLocationData().observe(this, locationObserver);
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
        labelTemperature.setVisibility(VISIBLE);
        labelTemperatureFeelsLike.setVisibility(VISIBLE);
        labelTemperatureMax.setVisibility(VISIBLE);
        labelTemperatureMin.setVisibility(VISIBLE);
        labelWindSpeed.setVisibility(VISIBLE);
        labelAirPressure.setVisibility(VISIBLE);
        labelHumidity.setVisibility(VISIBLE);
        if (isTablet()) {
            labelVisibility.setVisibility(VISIBLE);
            labelCloudiness.setVisibility(VISIBLE);
        }
    }

    private void getWidgets() {
        buttonWeatherProvider = findViewById(R.id.buttonWeatherProvider);
        editTextCity = findViewById(R.id.editTextSearchCity);
        iconCitySearch = findViewById(R.id.imageViewSearchCityIcon);
        imageViewWeather = findViewById(R.id.imageViewWeatherIcon);
        labelTemperature = findViewById(R.id.labelTemperature);
        labelTemperatureFeelsLike = findViewById(R.id.labelTemperatureFeelsLike);
        labelTemperatureMax = findViewById(R.id.labelTemperatureMax);
        labelTemperatureMin = findViewById(R.id.labelTemperatureMin);
        labelWindSpeed = findViewById(R.id.labelWindSpeed);
        labelAirPressure = findViewById(R.id.labelAirPressure);
        labelHumidity = findViewById(R.id.labelHumidity);
        textViewWeather = findViewById(R.id.textViewWeather);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewTemperatureFeelsLike = findViewById(R.id.textViewTemperatureFeelsLike);
        textViewTemperatureMax = findViewById(R.id.textViewTemperatureMax);
        textViewTemperatureMin = findViewById(R.id.textViewTemperatureMin);
        textViewWindSpeed = findViewById(R.id.textViewWindSpeed);
        textViewAirPressure = findViewById(R.id.textViewAirPressure);
        textViewHumidity = findViewById(R.id.textViewHumidity);
        if (isTablet()) {
            labelVisibility = findViewById(R.id.labelVisibility);
            labelCloudiness = findViewById(R.id.labelCloudness);
            textViewVisibility = findViewById(R.id.textViewVisibility);
            textViewCloudiness = findViewById(R.id.textViewCloudness);
        }
    }

    //Checks if the smallest screen width is equal/bigger than 600dp (tablet)
    private boolean isTablet() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        float width = metrics.widthPixels / metrics.density;
        float height = metrics.heightPixels / metrics.density;
        float smallestScreenWidth = (Math.min(width, height));
        return smallestScreenWidth >= 600.0f;
    }
}