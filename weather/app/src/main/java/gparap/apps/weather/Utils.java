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
    final static int API_COUNT = 3; //increment for every new a new weather provider

    //credits and licences
    final static String META_WEATHER = "https://www.metaweather.com";   //api no.0
    final static String OPEN_WEATHER = "https://openweathermap.org";    //api no.1
    final static String WEATHER_API = "https://www.weatherapi.com";     //api no.2 needs update every month

    //META_WEATHER
    final static String META_WEATHER_LOCATION_ID_FIELD = "woeid";
    final static String META_WEATHER_URL_FOR_ID = "https://www.metaweather.com/api/location/search/?query=";
    final static String META_WEATHER_URL_FOR_DATA_PREFIX = "https://www.metaweather.com/api/location/";
    final static String META_WEATHER_URL_FOR_DATA_SUFFIX = "/";

    //OPEN_WEATHER
    final static String OPEN_WEATHER_API_KEY = "MY_API_CODE_HERE";
    final static String OPEN_WEATHER__URL_FOR_DATA_PREFIX = "https://api.openweathermap.org/data/2.5/weather?q=";
    final static String OPEN_WEATHER__URL_FOR_DATA_SUFFIX = "&appid=";

    //WEATHER_API
    final static String WEATHER_API_KEY = "MY_API_CODE_HERE";    //needs update every month
    final static String WEATHER_API_URL_FOR_DATA_PREFIX = "http://api.weatherapi.com/v1/current.json?key=";
    final static String WEATHER_API_URL_FOR_DATA_SUFFIX = "&q=";

    //helper suffixes
    final static String SUFFIX_CELCIOUS = " \u2103";
    final static String SUFFIX_WIND_SPEED = " km/h";
    final static String SUFFIX_AIR_PRESSURE = " mb";
    final static String SUFFIX_HUMIDITY = " %";

    /**
     * Displays the appropriate weather icon based on the text (from all apis) describing the weather condition.
     *
     * @param weather condition
     */
    public static void displayWeatherIcon(String weather, ImageView imageViewWeather) {
        switch (weather) {
            case "Snow":
            case "Sleet":
            case "Patchy sleet possible":
            case "Patchy freezing drizzle possible":
            case "Blowing snow":
            case "Light sleet":
            case "Moderate or heavy sleet":
            case "Light snow":
            case "Moderate snow":
            case "Heavy snow":
            case "Ice pellets":
            case "Light snow showers":
            case "Moderate or heavy snow showers":
            case "Light showers of ice pellets":
            case "Moderate or heavy showers of ice pellets":
            case "Moderate or heavy snow with thunder":
                imageViewWeather.setImageResource((R.drawable.snowy));
                break;
            case "Hail":
            case "Thunderstorm":
            case "Thundery outbreaks possible":
            case "Blizzard":
            case "Patchy light rain with thunder":
            case "Moderate or heavy rain with thunder":
                imageViewWeather.setImageResource((R.drawable.stormy));
                break;
            case "Heavy Rain":
            case "Light Rain":
            case "Light rain":
            case "Moderate rain at times":
            case "Moderate rain":
            case "Heavy rain at times":
            case "Heavy rain":
            case "Light freezing rain":
            case "Moderate or heavy freezing rain":
            case "Rain":
                imageViewWeather.setImageResource((R.drawable.rainy));
                break;
            case "Showers":
            case "Patchy rain possible":
            case "Patchy light drizzle":
            case "Light drizzle":
            case "Freezing drizzle":
            case "Heavy freezing drizzle":
            case "Patchy light rain":
            case "Light rain shower":
            case "Moderate or heavy rain shower":
            case "Torrential rain shower":
            case "Light sleet showers":
            case "Moderate or heavy sleet showers":
            case "Drizzle":
                imageViewWeather.setImageResource((R.drawable.sunny_rainy));
                break;
            case "Heavy Cloud":
            case "Light Cloud":
            case "Overcast":
            case "Partly cloudy":
            case "Cloudy":
            case "Mist":
            case "Fog":
            case "Freezing fog":
            case "Clouds":
                imageViewWeather.setImageResource((R.drawable.cloudy));
                break;
            case "Clear":
            case "Sunny":
                imageViewWeather.setImageResource((R.drawable.sunny));
                break;
            case "Patchy snow possible":
            case "Patchy light snow":
            case "Patchy moderate snow":
            case "Patchy heavy snow":
            case "Patchy light snow with thunder":
                imageViewWeather.setImageResource((R.drawable.snowy_sunny));
                break;
            case "Smoke":
            case "Haze":
            case "Dust":
            case "Sand":
            case "Ash":
            case "Squall":
            case "Tornado":
                imageViewWeather.setImageResource((R.drawable.variable));
                break;
        }
        //show icon
        imageViewWeather.setVisibility(VISIBLE);
    }

    /**
     * Format string values gotten from different API (with different formats) string responses to a more general display.
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
