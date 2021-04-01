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

import gparap.apps.weather.utils.WeatherUtils;

import static org.junit.Assert.*;

public class WeatherUtilsUnitTest {

    @Test
    public void formatWeatherValue() {
        String valueToFormat = "01234.5566778899";
        int offset = 2;
        String expected = "01234.5";
        String actual = WeatherUtils.formatWeatherValue(valueToFormat, offset);
        assertEquals("Error formatting weather value...", actual, expected);
    }

    @Test
    public void convertKelvinToCelcious() {
        Object kelvinDegrees = 10.5;
        String expected = "-262.65";
        String actual = WeatherUtils.convertKelvinToCelcious(kelvinDegrees);
        assertEquals("Error converting Kelvin to Celcious...", actual, expected);
    }
}