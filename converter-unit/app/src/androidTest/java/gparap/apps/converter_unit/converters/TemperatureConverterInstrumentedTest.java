/*
 * Copyright 2025 gparap
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
package gparap.apps.converter_unit.converters;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsAnything.anything;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import gparap.apps.converter_unit.MainActivity;
import gparap.apps.converter_unit.R;

@RunWith(AndroidJUnit4.class)
public class TemperatureConverterInstrumentedTest {

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void isCorrect_convertCelsiusToFahrenheit() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(0, 1);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("33.8")));
    }

    @Test
    public void isCorrect_convertCelsiusToKelvin() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(0, 2);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("274.15")));
    }

    @Test
    public void isCorrect_convertCelsiusToRankine() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(0, 3);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("493.46999999999997")));
    }

    @Test
    public void isCorrect_convertFahrenheitToCelsius() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(1, 0);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("-17.22222222222222")));
    }

    @Test
    public void isCorrect_convertFahrenheitToKelvin() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(1, 2);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("255.92777777777778")));
    }

    @Test
    public void isCorrect_convertFahrenheitToRankine() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(1, 3);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("460.67")));
    }

    @Test
    public void isCorrect_convertKelvinToCelsius() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(2, 0);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("-272.15")));
    }

    @Test
    public void isCorrect_convertKelvinToFahrenheit() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(2, 1);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("-457.87")));
    }

    @Test
    public void isCorrect_convertKelvinToRankine() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(2, 3);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.8")));
    }

    @Test
    public void isCorrect_convertRankineToCelsius() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(3, 0);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("-272.59444444444443")));
    }

    @Test
    public void isCorrect_convertRankineToFahrenheit() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(3, 1);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("-458.67")));
    }

    @Test
    public void isCorrect_convertRankineToKelvin() {
        selectTemperatureUnitCategory();
        selectSpinnerValues(3, 2);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.5555555555555556")));
    }

    private void selectTemperatureUnitCategory() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_temperature)).perform(click());
    }

    private void selectSpinnerValues(int spinnerFromPosition, int spinnerToPosition) {
        onView(withId(R.id.spinner_convertFromUnit)).perform(click());
        onData(anything()).atPosition(spinnerFromPosition).perform(click());
        onView(withId(R.id.spinner_convertToUnit)).perform(click());
        onData(anything()).atPosition(spinnerToPosition).perform(click());
    }
}