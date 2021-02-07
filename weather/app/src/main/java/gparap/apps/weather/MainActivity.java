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

import java.util.Random;

import static android.view.View.VISIBLE;

/**
 * Created by gparap on 2020-11-20.
 */
@SuppressWarnings("Convert2Lambda")
@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity {
    TextView labelWeather, labelTemperature, labelTemperatureMax, labelTemperatureMin, labelWindSpeed, labelAirPressure, labelHumidity,
            textViewWeather, textViewTemperature, textViewTemperatureMax, textViewTemperatureMin, textViewWindSpeed, textViewAirPressure,
            textViewHumidity;
    ImageView imageViewWeather;
    Button buttonMetaWeather, buttonOpenWeather, buttonWeatherApi;
    EditText editTextLocation;
    ImageView imageViewSearch;
    WeatherAPI randomAPI;
    String location;
    String locationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomAPI = getRandomAPI();
        location = "";
        locationID = "";
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        buttonMetaWeather = findViewById(R.id.buttonVisitMetaWeather);
        buttonOpenWeather = findViewById(R.id.buttonVisitOpenWeather);
        buttonWeatherApi = findViewById(R.id.buttonVisitWeatherApi);
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

                //randomize weather api to use
                // (less requests for each API)
                randomAPI = getRandomAPI();

                //forecast weather
                switch (randomAPI) {
                    case META_WEATHER_COM:

                        //---------------------------------------------
                        //MetaWeather API logic:
                        // 1st. find the id of the location (city)
                        // 2nd. re-request with the id to get json data
                        //---------------------------------------------

                        getWeatherForecastByMetaWeather();
                        break;

                    case OPEN_WEATHER_ORG:

                        //---------------------------------------------------
                        //WeatherApi API logic:
                        // request with api key and location to get json data
                        //---------------------------------------------------

                        getWeatherForecastByOpenWeather();
                        break;

                    case WEATHER_API_COM:

                        //---------------------------------------------------
                        //WeatherApi API logic:
                        // request with api key and location to get json data
                        //---------------------------------------------------

                        getWeatherForecastByWeatherApi();
                        break;
                }
            }
        });
    }

    /**
     * Goes to the website of a link or licence.
     *
     * @param view button
     */
    public void onClickCredits(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonVisitMetaWeather:
                visitLink(Utils.META_WEATHER);
                break;
            case R.id.buttonVisitOpenWeather:
                visitLink(Utils.OPEN_WEATHER);
                break;
            case R.id.buttonVisitWeatherApi:
                visitLink(Utils.WEATHER_API);
                break;
        }
    }

    /**
     * Creates an intent to visit a website.
     *
     * @param uri website's identifier
     */
    private void visitLink(String uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    /**
     * Don't use the same API for weather forecast (workaround for less single API calls).
     *
     * @return WeatherAPI enum
     */
    private WeatherAPI getRandomAPI() {
        int random = new Random().nextInt(Utils.API_COUNT);
        switch (random) {
            case 0:
                return WeatherAPI.META_WEATHER_COM;
            case 1:
                return WeatherAPI.OPEN_WEATHER_ORG;
            case 2:
                return WeatherAPI.WEATHER_API_COM;
        }
        return null;
    }

    /**
     * Gets location JSON data using an id. This data contain the weather forecast.
     *
     * @param response string containing location id
     */
    private void getWeatherForecastById(String response) {
        //get location (city) id
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(response);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            locationID = jsonObject.getString(Utils.META_WEATHER_LOCATION_ID_FIELD);

            //init RequestQueue
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

            //request response from URL
            String url = Utils.META_WEATHER_URL_FOR_DATA_PREFIX + locationID + Utils.META_WEATHER_URL_FOR_DATA_SUFFIX;
            StringRequest stringRequest = new StringRequest(url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                getWeatherForecast(response, Utils.META_WEATHER);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try {
                                Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            //add request to RequestQueue
            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets and displays weather forecast.
     *
     * @param response string containing weather data
     * @throws JSONException JSONException
     */
    @SuppressLint("SetTextI18n")
    private void getWeatherForecast(String response, String api) throws JSONException {
        JSONObject jsonObjectResponse;
        JSONArray jsonArrayWeather;
        JSONObject jsonObjectWeather;

        switch (api) {
            case Utils.META_WEATHER:
                jsonObjectResponse = new JSONObject(response);
                jsonArrayWeather = jsonObjectResponse.getJSONArray("consolidated_weather");
                jsonObjectWeather = jsonArrayWeather.getJSONObject(0);

                //display the proper icon according to the weather
                Utils.displayWeatherIcon(jsonObjectWeather.get("weather_state_name").toString(), imageViewWeather);

                //fill in weather widgets
                textViewWeather.setText(jsonObjectWeather.get("weather_state_name").toString());
                textViewTemperature.setText(Utils.formatValue(jsonObjectWeather.get("the_temp").toString(), 0) + Utils.SUFFIX_CELCIOUS);
                textViewTemperatureMax.setText(Utils.formatValue(jsonObjectWeather.get("max_temp").toString(), 0) + Utils.SUFFIX_CELCIOUS);
                textViewTemperatureMin.setText(Utils.formatValue(jsonObjectWeather.get("min_temp").toString(), 0) + Utils.SUFFIX_CELCIOUS);
                textViewWindSpeed.setText(Utils.formatValue(jsonObjectWeather.get("wind_speed").toString(), 2) + Utils.SUFFIX_WIND_SPEED);
                textViewAirPressure.setText(Utils.formatValue(jsonObjectWeather.get("air_pressure").toString(), 2) + Utils.SUFFIX_AIR_PRESSURE);
                textViewHumidity.setText(Utils.formatValue(jsonObjectWeather.get("humidity").toString(), 2) + Utils.SUFFIX_HUMIDITY);
                break;

            case Utils.OPEN_WEATHER:
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
                break;

            case Utils.WEATHER_API:
                jsonObjectResponse = new JSONObject(response);
                JSONObject current = (JSONObject) jsonObjectResponse.get("current");

                //display the proper icon according to the weather
                String condition = current.getJSONObject("condition").getString("text");
                Utils.displayWeatherIcon(condition, imageViewWeather);

                //fill in weather widgets
                textViewWeather.setText(condition);
                textViewTemperature.setText(current.getString("temp_c") + Utils.SUFFIX_CELCIOUS);
                textViewTemperatureMax.setText("N/A");
                textViewTemperatureMin.setText("N/A");
                textViewWindSpeed.setText(current.getString("wind_kph") + Utils.SUFFIX_WIND_SPEED);
                textViewAirPressure.setText(current.getString("pressure_mb") + Utils.SUFFIX_AIR_PRESSURE);
                textViewHumidity.setText(current.getString("humidity") + Utils.SUFFIX_HUMIDITY);
                break;
        }
    }

    /**
     * Gets current weather data using the MetaWeather API.
     */
    private void getWeatherForecastByMetaWeather() {
        //init RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //request response from URL
        String url = Utils.META_WEATHER_URL_FOR_ID + location;
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //display the weather
                        showLabelWidgets();
                        getWeatherForecastById(response);
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
                            getWeatherForecast(response, Utils.OPEN_WEATHER);
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
     * Gets current weather data using the WeatherApi API.
     */
    private void getWeatherForecastByWeatherApi() {
        //init RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //request response from URL
        String url = Utils.WEATHER_API_URL_FOR_DATA_PREFIX + Utils.WEATHER_API_KEY + Utils.WEATHER_API_URL_FOR_DATA_SUFFIX + location;
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //display the weather
                            showLabelWidgets();
                            getWeatherForecast(response, Utils.WEATHER_API);
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