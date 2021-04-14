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
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.VolleyError;

import org.json.JSONException;

import gparap.apps.weather.data.WeatherModel;
import gparap.apps.weather.api.VolleyServiceCallback;
import gparap.apps.weather.api.VolleyService;
import gparap.apps.weather.utils.WeatherModelParser;
import gparap.apps.weather.utils.WeatherUtils;
import gparap.apps.weather.viewmodel.WeatherActivityViewModel;

import static android.view.View.VISIBLE;

@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
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
    private final int REQUEST_CODE_ACCESS_FINE_LOCATION = 999;
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

        //observe and display weather data
        viewModel.getWeatherData().observe(this, new Observer<WeatherModel>() {
            @Override
            public void onChanged(@Nullable final WeatherModel model) {
                displayWeather(model);
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

    @SuppressLint("SetTextI18n")
    private void displayWeather(WeatherModel model) {
        showLabelWidgets();

        //display the user's city
        editTextCity.setText(model.getCityName());

        //display the proper icon according to the weather
        WeatherUtils.displayWeatherIcon(model.getWeather(), model.getDescription(), imageViewWeather);

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
            public void onResponse(String response) {//TODO: hide everything weather related
                //parse json response to model
                try {
                    viewModel.getWeatherData().setValue(WeatherModelParser.getInstance().getCurrentWeatherDataModel(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {//TODO: hide everything weather related
                Toast.makeText(WeatherActivity.this, R.string.toast_cannot_fetch_weather, Toast.LENGTH_SHORT).show();
            }
        };

        //get current weather from weather api provider using volley
        VolleyService.getInstance(volleyServiceCallback).getApiResponse(this, url);
    }

    public void getUserLocation() {
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