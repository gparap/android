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
package gparap.apps.weather.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import java.util.Locale;

import gparap.apps.weather.R;

import static android.view.View.VISIBLE;

public class WeatherUtils {
    public final static String OPEN_WEATHER = "https://openweathermap.org";
    public final static String APP_ID = "API_KEY_HERE";
    public final static String CITY_SEARCH_API_URL_PREFIX = "https://api.openweathermap.org/data/2.5/weather?q=";
    public final static String USER_SEARCH_API_URL_PREFIX = "https://api.openweathermap.org/data/2.5/weather?";
    public final static String API_URL_SUFFIX = "&appid=";

    //helper suffixes
    public final static String SUFFIX_CELSIUS = " \u2103";
    public final static String SUFFIX_FAHRENHEIT = " \u2109";
    public final static String SUFFIX_WIND_SPEED_METRIC = " m/s";
    public final static String SUFFIX_WIND_SPEED_IMPERIAL = " mph";
    public final static String SUFFIX_AIR_PRESSURE = " hPa";
    public final static String SUFFIX_HUMIDITY_CLOUDNESS = " %";
    public final static String SUFFIX_VISIBILITY_METRIC = " meters";
    public final static String SUFFIX_VISIBILITY_IMPERIAL = " miles";

    //units of measurement
    public static final String UNIT_METRIC = "metric";
    public static final String UNIT_IMPERIAL = "imperial";

    /**
     * Displays the appropriate weather icon based on the text describing the weather condition.
     *
     * @param weather          main weather condition
     * @param description      secondary weather condition description
     * @param imageViewWeather weather icon placeholder
     */
    public static void displayWeatherIcon(String weather, String description, ImageView imageViewWeather) {
        switch (weather) {
            case "Thunderstorm":
                imageViewWeather.setImageResource(R.drawable.thunderstorm);
                break;
            case "Drizzle":
                imageViewWeather.setImageResource(R.drawable.shower_rain);
                break;
            case "Rain":
                if (description.equals("freezing rain")) {
                    imageViewWeather.setImageResource(R.drawable.snow);
                } else if (description.contains("shower")) {
                    imageViewWeather.setImageResource(R.drawable.shower_rain);
                } else {
                    imageViewWeather.setImageResource(R.drawable.rain);
                }
                break;
            case "Snow":
                imageViewWeather.setImageResource(R.drawable.snow);
                break;
            case "Atmosphere":
                imageViewWeather.setImageResource(R.drawable.mist);
                break;
            case "Clear":
                imageViewWeather.setImageResource(R.drawable.clear_sky);
                break;
            case "Clouds":
                if (description.equals("few clouds")) {
                    imageViewWeather.setImageResource(R.drawable.few_clouds);
                } else if (description.equals("scattered clouds")) {
                    imageViewWeather.setImageResource(R.drawable.scattered_clouds);
                } else {
                    imageViewWeather.setImageResource(R.drawable.broken_clouds);
                }
                break;
        }
        //show icon
        imageViewWeather.setVisibility(VISIBLE);
    }

    /**
     * Returns the appropriate unit of measurement based on user's locale
     *
     * @return metric or imperial
     */
    public static String getMeasureUnit() {
        //check if locale is "US" (officially uses imperial units)
        if (Locale.getDefault().equals(Locale.US)) {
            return UNIT_IMPERIAL;
        }

        return UNIT_METRIC;
    }

    /**
     * Format string values got from API response.
     *
     * @param value  original value from API response
     * @param offset how many decimals after dot(.)
     * @return formatted string value
     */
    public static String formatWeatherValue(String value, int offset) {
        String temp = value;
        try {
            temp = temp.substring(0, temp.indexOf('.') + offset);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //pick the right symbol
        if (getMeasureUnit().equals(UNIT_METRIC)) {
            return temp + WeatherUtils.SUFFIX_CELSIUS;
        } else {
            return temp + WeatherUtils.SUFFIX_FAHRENHEIT;
        }
    }

    /**
     * Returns the wind speed suffix according to the unit of measurement.
     *
     * @return meter/second or miles/hour
     */
    public static String getWindSpeedUnit() {
        if (getMeasureUnit().equals(UNIT_METRIC)) {
            return WeatherUtils.SUFFIX_WIND_SPEED_METRIC;
        } else {
            return WeatherUtils.SUFFIX_WIND_SPEED_IMPERIAL;
        }
    }

    /**
     * Converts meters to international miles. (1meter ~= 0.000621miles)
     * Used for visibility display, because weather provider's service doesn't make the conversion
     * when we have imperial units.
     *
     * @param meters to convert
     * @return visibility in international miles
     */
    public static double convertMetersToMiles(double meters) {
        return meters * 0.000621;
    }

    /**
     * Generates a url to be used for weather api call.
     *
     * @param city city name given by user
     * @return url
     */
    public static String generateURL(String city) {
        return WeatherUtils.CITY_SEARCH_API_URL_PREFIX
                + city
                + WeatherUtils.API_URL_SUFFIX
                + WeatherUtils.APP_ID
                + "&units=" + getMeasureUnit()
                + "&lang=" + LocaleUtils.getInstance().getSupportedLanguage();
    }

    /**
     * Generates a url to be used for weather api call.
     *
     * @param latitude   user location's latitude
     * @param longtitude user location's longtitude
     * @return url
     */
    public static String generateURL(double latitude, double longtitude) {
        return WeatherUtils.USER_SEARCH_API_URL_PREFIX
                + "lat=" + latitude
                + "&lon=" + longtitude
                + WeatherUtils.API_URL_SUFFIX
                + WeatherUtils.APP_ID
                + "&units=" + getMeasureUnit()
                + "&lang=" + LocaleUtils.getInstance().getSupportedLanguage();
    }

    /**
     * Opens the weather api provider's website.
     *
     * @param context current activity
     */
    public static void gotoProviderWebsite(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(WeatherUtils.OPEN_WEATHER));
        context.startActivity(intent);
    }
}
