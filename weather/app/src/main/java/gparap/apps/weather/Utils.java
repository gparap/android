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

import android.widget.ImageView;

import static android.view.View.VISIBLE;

public class Utils {
    final static String OPEN_WEATHER = "https://openweathermap.org";
    final static String OPEN_WEATHER_API_KEY = "API_KEY_HERE";
    final static String OPEN_WEATHER__URL_FOR_DATA_PREFIX = "https://api.openweathermap.org/data/2.5/weather?q=";
    final static String OPEN_WEATHER__URL_FOR_DATA_SUFFIX = "&appid=";

    //helper suffixes
    final static String SUFFIX_CELCIOUS = " \u2103";
    final static String SUFFIX_WIND_SPEED = " km/h";
    final static String SUFFIX_AIR_PRESSURE = " mb";
    final static String SUFFIX_HUMIDITY = " %";

    /**
     * Displays the appropriate weather icon based on the text describing the weather condition.
     *
     * @param weather condition
     */
    public static void displayWeatherIcon(String weather, ImageView imageViewWeather) {
        switch (weather) {
            case "Thunderstorm":
                imageViewWeather.setImageResource((R.drawable.thunderstorm));
                break;
            case "Drizzle":
                imageViewWeather.setImageResource((R.drawable.shower_rain));
                break;
            case "Rain":
                imageViewWeather.setImageResource((R.drawable.rain_day));
                break;
            case "Snow":
                imageViewWeather.setImageResource((R.drawable.snow));
                break;
            case "Atmosphere":
                imageViewWeather.setImageResource((R.drawable.mist));
                break;
            case "Clear":
                imageViewWeather.setImageResource((R.drawable.clear_sky_day));
                break;
            case "Clouds":          //TODO: use id 'cause there are variations
                imageViewWeather.setImageResource((R.drawable.few_clouds_day));
                break;
        }
        //show icon
        imageViewWeather.setVisibility(VISIBLE);
    }

    /**
     * Format string values got from API response.
     *
     * @param value  original value from API response
     * @param offset how many decimals after dot(.)
     * @return formatted string value
     */
    public static String formatValue(String value, int offset) {
        String temp = value;
        try {
            temp = temp.substring(0, temp.indexOf('.') + offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Converts kelvin temperature to celcious temperature using formula:
     * Celsius = Kelvin - 273.15
     *
     * @param temp temperature in kelvin
     * @return temperature in celcious to string
     */
    public static String convertKelvinToCelcious(Object temp) {
        return String.valueOf(Double.parseDouble(temp.toString()) - 273.15);
    }
}
