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
package gparap.apps.weather;

import org.junit.Test;

import java.util.Locale;

import gparap.apps.weather.utils.WeatherUtils;

import static org.junit.Assert.*;

public class WeatherUtilsUnitTest {
    // !!! do not use official appid
    private final String APP_ID = WeatherUtils.APP_ID;

    @Test
    public void formatWeatherValue_Celsius() {
        //set unit to metric
        Locale.setDefault(Locale.ENGLISH);

        String valueToFormat = "01234.5566778899";
        int offset = 2;
        String expected = "01234.5" + WeatherUtils.SUFFIX_CELSIUS;
        String actual = WeatherUtils.formatWeatherValue(valueToFormat, offset);
        assertEquals("Error formatting weather value...", actual, expected);
    }

    @Test
    public void formatWeatherValue_Fahrenheit() {
        //set unit to imperial
        Locale.setDefault(Locale.US);

        String valueToFormat = "01234.5566778899";
        int offset = 2;
        String expected = "01234.5" + WeatherUtils.SUFFIX_FAHRENHEIT;
        String actual = WeatherUtils.formatWeatherValue(valueToFormat, offset);
        assertEquals("Error formatting weather value...", actual, expected);
    }

    @Test
    public void generateURL_userLocationBased() {
        String expected = "https://api.openweathermap.org/data/2.5/weather?q=Athens&appid="
                + APP_ID
                + "&units=" + WeatherUtils.getMeasureUnit();
        String actual = WeatherUtils.generateURL("Athens");
        assertEquals(expected, actual);
    }

    @Test
    public void generateURL_citySearchBased() {
        String expected = "https://api.openweathermap.org/data/2.5/weather?lat=35.0&lon=139.0&appid="
                + APP_ID
                + "&units=" + WeatherUtils.getMeasureUnit();
        String actual = WeatherUtils.generateURL(35, 139);
        assertEquals(expected, actual);
    }

    @Test
    public void getWindSpeedUnit_Metric() {
        //set unit to metric
        Locale.setDefault(Locale.ENGLISH);

        String actual = WeatherUtils.SUFFIX_WIND_SPEED_METRIC;
        String expected = WeatherUtils.getWindSpeedUnit();
        assertEquals(actual, expected);
    }

    @Test
    public void getWindSpeedUnit_Imperial() {
        //set unit to imperial
        Locale.setDefault(Locale.US);

        String actual = WeatherUtils.SUFFIX_WIND_SPEED_IMPERIAL;
        String expected = WeatherUtils.getWindSpeedUnit();
        assertEquals(actual, expected);
    }

    @Test
    public void getMeasureUnit_Metric() {
        //set unit to metric
        Locale.setDefault(Locale.ENGLISH);

        String expected = WeatherUtils.UNIT_METRIC;
        String actual = WeatherUtils.getMeasureUnit();
        assertEquals(expected, actual);
    }

    @Test
    public void getMeasureUnit_Imperial() {
        //set unit to imperial
        Locale.setDefault(Locale.US);

        String expected = WeatherUtils.UNIT_IMPERIAL;
        String actual = WeatherUtils.getMeasureUnit();
        assertEquals(expected, actual);
    }

    @Test
    public void convertMetersToMiles() {
        double meters = 1000;
        double expected_miles = 0.621;
        double actual_miles = WeatherUtils.convertMetersToMiles(meters);
        assertEquals("Error converting meters to miles...", expected_miles, actual_miles, 0.0);
    }
}