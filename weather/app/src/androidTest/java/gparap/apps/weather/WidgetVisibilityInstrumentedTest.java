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

import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

public class WidgetVisibilityInstrumentedTest {

    @Before
    public void setUp() {
        ActivityScenario.launch(WeatherActivity.class);
    }

    @Test
    public void isWidgetVisible_editTextSearchCity() {
        onView(withId(R.id.editTextSearchCity)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_imageViewSearchCityIcon() {
        onView(withId(R.id.imageViewSearchCityIcon)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_buttonWeatherProvider() {
        onView(withId(R.id.buttonWeatherProvider)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetInvisible_imageViewWeatherIcon() {
        onView(withId(R.id.imageViewWeatherIcon)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_labelWeather() {
        onView(withId(R.id.labelWeather)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_labelTemperature() {
        onView(withId(R.id.labelTemperature)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_labelTemperatureMax() {
        onView(withId(R.id.labelTemperatureMax)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_labelTemperatureMin() {
        onView(withId(R.id.labelTemperatureMin)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_labelWindSpeed() {
        onView(withId(R.id.labelWindSpeed)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_labelAirPressure() {
        onView(withId(R.id.labelAirPressure)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_labelHumidity() {
        onView(withId(R.id.labelHumidity)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_textViewWeather() {
        onView(withId(R.id.textViewWeather)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_textViewTemperature() {
        onView(withId(R.id.textViewTemperature)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_textViewTemperatureMax() {
        onView(withId(R.id.textViewTemperatureMax)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_textViewTemperatureMin() {
        onView(withId(R.id.textViewTemperatureMin)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_textViewWindSpeed() {
        onView(withId(R.id.textViewWindSpeed)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_textViewAirPressure() {
        onView(withId(R.id.textViewAirPressure)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetInvisible_textViewHumidity() {
        onView(withId(R.id.textViewHumidity)).check(matches(not(isDisplayed())));
    }
}