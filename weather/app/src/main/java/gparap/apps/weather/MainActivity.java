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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import gparap.apps.weather.model.CurrentWeatherDataModel;
import gparap.apps.weather.utils.CurrentWeatherParser;
import gparap.apps.weather.utils.LocationUtils;
import gparap.apps.weather.utils.WeatherUtils;

import static android.view.View.VISIBLE;

@SuppressWarnings("Convert2Lambda")
@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity {
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
    CurrentWeatherDataModel model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidgets();
        LocationUtils.getInstance().getUserLocation(this);

        //search a city for weather
        iconCitySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCitySearchEmpty()) {
                    city = editTextCity.getText().toString();
                    hideSoftKeyboard(v);
                    getCurrentWeather();
                }
            }
        });

        //goto weather provider website
        buttonWeatherProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoProviderWebsite();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance().stopLocationUpdates();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LocationUtils.getInstance().getRequestCode()) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationUtils.getInstance().getUserLocation(this);
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
     */
    private void getCurrentWeather() {
        //init RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //request response from URL
        String url = WeatherUtils.OPEN_WEATHER__URL_FOR_DATA_PREFIX + city + WeatherUtils.OPEN_WEATHER__URL_FOR_DATA_SUFFIX + WeatherUtils.OPEN_WEATHER_API_KEY;
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //display the weather
                            showLabelWidgets();
                            displayCurrentWeather(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Cannot find city.", Toast.LENGTH_SHORT).show();
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

    private boolean isCitySearchEmpty() {
        if (editTextCity.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, R.string.toast_empty_search, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void gotoProviderWebsite() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(WeatherUtils.OPEN_WEATHER));
        startActivity(intent);
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