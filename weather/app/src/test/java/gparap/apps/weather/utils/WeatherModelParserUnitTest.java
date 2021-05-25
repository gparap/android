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
package gparap.apps.weather.utils;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.junit.Test;

import gparap.apps.weather.data.WeatherModel;

public class WeatherModelParserUnitTest {
    @Test
    public void getCurrentWeatherDataModel() throws JSONException {
        String mockJsonResponse = "{\"coord\":{\"lon\":23.7162,\"lat\":37.9795},\"weather\":[" +
                "{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":" +
                "\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":282.97,\"feels_like\":280.46," +
                "\"temp_min\":281.48,\"temp_max\":284.26,\"pressure\":1019,\"humidity\":81}," +
                "\"visibility\":9000,\"wind\":{\"speed\":5.14,\"deg\":320,\"gust\":10.29}," +
                "\"clouds\":{\"all\":75},\"dt\":1617195713,\"sys\":{\"type\":1,\"id\":6613," +
                "\"country\":\"GR\",\"sunrise\":1617163904,\"sunset\":1617209193},\"timezone\"" +
                ":10800,\"id\":264371,\"name\":\"Athens\",\"cod\":200}";


        WeatherModel expected = createExcpectedWeatherDataModel();
        WeatherModel actual = WeatherModelParser.getInstance()
                .getCurrentWeatherDataModel(mockJsonResponse);

        assert areWeatherValuesEqual(actual, expected);
    }

    private WeatherModel createExcpectedWeatherDataModel() {
        WeatherModel model = new WeatherModel();

        model.setId(803);
        model.setWeather("Clouds");
        model.setDescription("broken clouds");
        model.setIcon("04d");
        model.setTemperature(282.97);
        model.setFeelsLike(280.46);
        model.setTemperatureMin(281.48);
        model.setTemperatureMax(284.26);
        model.setAirPressure(1019);
        model.setHumidity(81);
        model.setVisibility(9000);
        model.setWindSpeed(5.14);
        model.setCloudness(75);
        model.setCityName("Athens");

        return model;
    }

    public boolean areWeatherValuesEqual(@Nullable WeatherModel actual,
                                           WeatherModel expected) {
        if (actual == null) {
            return false;
        }

        //compare weather data objects value by value
        return actual.getId() == expected.getId() &&
                actual.getWeather().equals(expected.getWeather()) &&
                actual.getDescription().equals(expected.getDescription()) &&
                actual.getIcon().equals(expected.getIcon()) &&
                actual.getTemperature() == expected.getTemperature() &&
                actual.getFeelsLike() == expected.getFeelsLike() &&
                actual.getTemperatureMin() == expected.getTemperatureMin() &&
                actual.getTemperatureMax() == expected.getTemperatureMax() &&
                actual.getAirPressure() == expected.getAirPressure() &&
                actual.getHumidity() == expected.getHumidity() &&
                actual.getVisibility() == expected.getVisibility() &&
                actual.getWindSpeed() == expected.getWindSpeed() &&
                actual.getCloudness() == expected.getCloudness() &&
                actual.getCityName().equals(expected.getCityName());
    }
}