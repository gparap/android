/*
 * Copyright 2020-present gparap
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

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.VISIBLE;

@SuppressWarnings("Convert2Lambda")
@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity {
    TextView labelWeather, labelTemperature, labelTemperatureMax, labelTemperatureMin, labelWindSpeed, labelAirPressure, labelHumidity,
            textViewWeather, textViewTemperature, textViewTemperatureMax, textViewTemperatureMin, textViewWindSpeed, textViewAirPressure,
            textViewHumidity;
    ImageView imageViewWeather;
    Button buttonOpenWeather;
    EditText editTextLocation;
    ImageView imageViewSearch;
    String location;
    String locationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location = "";
        locationID = "";
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        buttonOpenWeather = findViewById(R.id.buttonVisitOpenWeather);
        editTextLocation = findViewById(R.id.editTextSearch);
        imageViewSearch = findViewById(R.id.imageViewSearch);
        imageViewWeather = findViewById(R.id.imageViewWeather);
        getLabelWidgets();
        getWeatherWidgets();

        //search a location for weather
        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //location cannot be empty
                if (editTextLocation.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Location cannot be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                location = editTextLocation.getText().toString();

                //hide soft keyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                getWeatherForecastByOpenWeather();
            }
        });
    }

    /**
     * Goes to the website of a link or licence.
     *
     * @param view button
     */
    public void onClickCredits(View view) {
        visitLink();
    }

    /**
     * Creates an intent to visit a website.
     */
    private void visitLink() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(Utils.OPEN_WEATHER));
        startActivity(intent);
    }

    /**
     * Gets and displays weather forecast.
     *
     * @param response string containing weather data
     * @throws JSONException JSONException
     */
    @SuppressLint("SetTextI18n")
    private void getWeatherForecast(String response) throws JSONException {
        JSONObject jsonObjectResponse;
        JSONArray jsonArrayWeather;
        JSONObject jsonObjectWeather;

        jsonObjectResponse = new JSONObject(response);

        //get json objects
        jsonArrayWeather = jsonObjectResponse.getJSONArray("weather");
        jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
        JSONObject main = (JSONObject) jsonObjectResponse.get("main");
        JSONObject wind = (JSONObject) jsonObjectResponse.get("wind");

        //display the proper icon according to the weather
        Utils.displayWeatherIcon(jsonObjectWeather.getString("main"), imageViewWeather);

        //convert temperatures
        String temp = Utils.convertKelvinToCelcious(main.get("temp"));
        String temp_max = Utils.convertKelvinToCelcious(main.get("temp_max"));
        String temp_min = Utils.convertKelvinToCelcious(main.get("temp_min"));

        //fill in weather widgets
        textViewWeather.setText(jsonObjectWeather.getString("main"));
        textViewTemperature.setText(Utils.formatValue(temp, 0) + Utils.SUFFIX_CELCIOUS);
        textViewTemperatureMax.setText(Utils.formatValue(temp_max, 0) + Utils.SUFFIX_CELCIOUS);
        textViewTemperatureMin.setText(Utils.formatValue(temp_min, 0) + Utils.SUFFIX_CELCIOUS);
        textViewWindSpeed.setText(wind.get("speed").toString() + Utils.SUFFIX_WIND_SPEED);
        textViewAirPressure.setText(main.get("pressure").toString() + Utils.SUFFIX_AIR_PRESSURE);
        textViewHumidity.setText(main.get("humidity").toString() + Utils.SUFFIX_HUMIDITY);
    }

    /**
     * Gets current weather data using the OpenWeather API.
     */
    private void getWeatherForecastByOpenWeather() {
        //init RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //request response from URL
        String url = Utils.OPEN_WEATHER__URL_FOR_DATA_PREFIX + location + Utils.OPEN_WEATHER__URL_FOR_DATA_SUFFIX + Utils.OPEN_WEATHER_API_KEY;
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //display the weather
                            showLabelWidgets();
                            getWeatherForecast(response);
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

    /**
     * Gets all views used as weather labels.
     */
    private void getLabelWidgets() {
        labelWeather = findViewById(R.id.labelWeather);
        labelTemperature = findViewById(R.id.labelTemperature);
        labelTemperatureMax = findViewById(R.id.labelTemperatureMax);
        labelTemperatureMin = findViewById(R.id.labelTemperatureMin);
        labelWindSpeed = findViewById(R.id.labelWindSpeed);
        labelAirPressure = findViewById(R.id.labelAirPressure);
        labelHumidity = findViewById(R.id.labelHumidity);
    }

    /**
     * Gets all views used as response weather data.
     */
    private void getWeatherWidgets() {
        textViewWeather = findViewById(R.id.textViewWeather);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewTemperatureMax = findViewById(R.id.textViewTemperatureMax);
        textViewTemperatureMin = findViewById(R.id.textViewTemperatureMin);
        textViewWindSpeed = findViewById(R.id.textViewWindSpeed);
        textViewAirPressure = findViewById(R.id.textViewAirPressure);
        textViewHumidity = findViewById(R.id.textViewHumidity);
    }

    /**
     * Shows all views used as weather labels.
     */
    private void showLabelWidgets() {
        labelWeather.setVisibility(VISIBLE);
        labelTemperature.setVisibility(VISIBLE);
        labelTemperatureMax.setVisibility(VISIBLE);
        labelTemperatureMin.setVisibility(VISIBLE);
        labelWindSpeed.setVisibility(VISIBLE);
        labelAirPressure.setVisibility(VISIBLE);
        labelHumidity.setVisibility(VISIBLE);
    }
}