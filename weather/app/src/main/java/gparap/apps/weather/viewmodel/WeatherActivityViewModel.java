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
package gparap.apps.weather.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import gparap.apps.weather.data.LocationLiveData;
import gparap.apps.weather.data.WeatherModel;

public class WeatherActivityViewModel extends AndroidViewModel {
    private final LocationLiveData locationLiveData;
    private MutableLiveData<WeatherModel> weatherLiveData;

    public WeatherActivityViewModel(@NonNull Application application) {
        super(application);
        locationLiveData = new LocationLiveData(application);
    }

    public LocationLiveData getLocationData() {
        return locationLiveData;
    }

    public MutableLiveData<WeatherModel> getWeatherData() {
        if (weatherLiveData == null) {
            weatherLiveData = new MutableLiveData<>();
        }
        return weatherLiveData;
    }
}
