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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gparap.apps.weather.model.CurrentWeatherDataModel;

public class CurrentWeatherParserJSON {
    private static CurrentWeatherParserJSON instance;
    private CurrentWeatherDataModel model;
    private JSONObject jsonObjectResponse;
    private JSONObject coord;
    private JSONArray weather;
    private JSONObject main;
    private JSONObject wind;
    private JSONObject clouds;
    private JSONObject sys;

    private CurrentWeatherParserJSON() {

    }

    public static CurrentWeatherParserJSON getInstance() {
        if (instance == null) {
            instance = new CurrentWeatherParserJSON();
        }
        return instance;
    }

    public CurrentWeatherDataModel getCurrentWeatherDataModel(String response) throws JSONException {
        model = new CurrentWeatherDataModel();

        getDataFromJsonResponse(response);
        initWeatherModelWithData();

        return model;
    }

    private void getDataFromJsonResponse(String response) throws JSONException {
        jsonObjectResponse = new JSONObject(response);

        coord = (JSONObject) jsonObjectResponse.get("coord");
        weather = jsonObjectResponse.getJSONArray("weather");
        main = (JSONObject) jsonObjectResponse.get("main");
        wind = (JSONObject) jsonObjectResponse.get("wind");
        clouds = (JSONObject) jsonObjectResponse.get("clouds");
        sys = (JSONObject) jsonObjectResponse.get("sys");
    }

    private void initWeatherModelWithData() throws JSONException {
        //coord
        model.setLongitude(coord.getLong("lon"));
        model.setLatitude(coord.getLong("lat"));
        //weather
        model.setId(weather.getJSONObject(0).getInt("id"));
        model.setWeather(weather.getJSONObject(0).getString("main"));
        model.setDescription(weather.getJSONObject(0).getString("description"));
        model.setIcon(weather.getJSONObject(0).getString("icon"));
        //main
        model.setTemperature(main.getDouble("temp"));
        model.setFeelsLike(main.getDouble("feels_like"));
        model.setTemperatureMax(main.getDouble("temp_max"));
        model.setTemperatureMin(main.getDouble("temp_min"));
        model.setAirPressure(main.getDouble("pressure"));
        model.setHumidity(main.getDouble("humidity"));
        //visibility
        model.setVisibility(jsonObjectResponse.getDouble("visibility"));
        //wind
        model.setWindSpeed(wind.getDouble("speed"));
        //clouds
        model.setCloudness(clouds.getInt("all"));
        //sys
        model.setCountryCode(sys.getString("country"));
        model.setSunrise(sys.getLong("sunrise"));
        model.setSunset(sys.getLong("sunset"));
        //name
        model.setCityName(jsonObjectResponse.getString("name"));
    }
}
