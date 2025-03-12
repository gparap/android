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

import java.util.HashMap;

import gparap.apps.converter_unit.MainActivity;
import gparap.apps.converter_unit.R;

@SuppressWarnings("ConstantConditions") /* Unboxing of 'unitNamePositionMap.get(key)' may produce 'NullPointerException' (!!! keys always safe !!!). */
@RunWith(AndroidJUnit4.class)
public class LengthDistanceConverterInstrumentedTest {
    private final HashMap<String, Integer> unitNamePositionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);

        //update the map with unit names and their positions
        unitNamePositionMap.put("meter", 0);
        unitNamePositionMap.put("kilometer", 1);
        unitNamePositionMap.put("mile", 2);
        unitNamePositionMap.put("yard", 3);
        unitNamePositionMap.put("foot", 4);
        unitNamePositionMap.put("inch", 5);
    }

    @Test
    public void isCorrect_convertMeterToKilometer() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("meter"), unitNamePositionMap.get("kilometer"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertMeterToMile() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("meter"), unitNamePositionMap.get("mile"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6.21371E-4")));
    }

    @Test
    public void isCorrect_convertMeterToYard() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("meter"), unitNamePositionMap.get("yard"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.09361")));
    }

    @Test
    public void isCorrect_convertMeterToFoot() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("meter"), unitNamePositionMap.get("foot"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.28084")));
    }

    @Test
    public void isCorrect_convertMeterToInch() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("meter"), unitNamePositionMap.get("inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("39.3701")));
    }
    @Test
    public void isCorrect_convertKilometerToMeter() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilometer"), unitNamePositionMap.get("meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertKilometerToMile() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilometer"), unitNamePositionMap.get("mile"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.621371")));
    }

    @Test
    public void isCorrect_convertKilometerToYard() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilometer"), unitNamePositionMap.get("yard"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1093.61")));
    }

    @Test
    public void isCorrect_convertKilometerToFoot() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilometer"), unitNamePositionMap.get("foot"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3280.84")));
    }

    @Test
    public void isCorrect_convertKilometerToInch() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilometer"), unitNamePositionMap.get("inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("39370.1")));
    }

    @Test
    public void isCorrect_convertMileToMeter() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("mile"), unitNamePositionMap.get("meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1609.34")));
    }

    @Test
    public void isCorrect_convertMileToKilometer() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("mile"), unitNamePositionMap.get("kilometer"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.60934")));
    }

    @Test
    public void isCorrect_convertMileToYard() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("mile"), unitNamePositionMap.get("yard"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1760.0")));
    }

    @Test
    public void isCorrect_convertMileToFoot() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("mile"), unitNamePositionMap.get("foot"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5280.0")));
    }

    @Test
    public void isCorrect_convertMileToInch() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("mile"), unitNamePositionMap.get("inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("63360.0")));
    }

    @Test
    public void isCorrect_convertYardToMeter() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("yard"), unitNamePositionMap.get("meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.9144")));
    }

    @Test
    public void isCorrect_convertYardToKilometer() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("yard"), unitNamePositionMap.get("kilometer"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.144E-4")));
    }

    @Test
    public void isCorrect_convertYardToMile() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("yard"), unitNamePositionMap.get("mile"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5.68182E-4")));
    }

    @Test
    public void isCorrect_convertYardToFoot() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("yard"), unitNamePositionMap.get("foot"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.0")));
    }

    @Test
    public void isCorrect_convertYardToInch() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("yard"), unitNamePositionMap.get("inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("36.0")));
    }

    @Test
    public void isCorrect_convertFootToMeter() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("foot"), unitNamePositionMap.get("meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.3048")));
    }

    @Test
    public void isCorrect_convertFootToKilometer() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("foot"), unitNamePositionMap.get("kilometer"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.048E-4")));
    }

    @Test
    public void isCorrect_convertFootToMile() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("foot"), unitNamePositionMap.get("mile"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.89394E-4")));
    }

    @Test
    public void isCorrect_convertFootToYard() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("foot"), unitNamePositionMap.get("yard"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.333333")));
    }

    @Test
    public void isCorrect_convertFootToInch() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("foot"), unitNamePositionMap.get("inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("12.0")));
    }

    @Test
    public void isCorrect_convertInchToMeter() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("inch"), unitNamePositionMap.get("meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0254")));
    }

    @Test
    public void isCorrect_convertInchToKilometer() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("inch"), unitNamePositionMap.get("kilometer"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.54E-5")));
    }

    @Test
    public void isCorrect_convertInchToMile() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("inch"), unitNamePositionMap.get("mile"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.57828E-5")));
    }

    @Test
    public void isCorrect_convertInchToYard() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("inch"), unitNamePositionMap.get("yard"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0277778")));
    }

    @Test
    public void isCorrect_convertInchToFoot() {
        selectLengthDistanceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("inch"), unitNamePositionMap.get("foot"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0833333")));
    }

    private void selectLengthDistanceUnitCategory() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_length_distance)).perform(click());
    }

    private void selectSpinnerValues(int spinnerFromPosition, int spinnerToPosition) {
        onView(withId(R.id.spinner_convertFromUnit)).perform(click());
        onData(anything()).atPosition(spinnerFromPosition).perform(click());
        onView(withId(R.id.spinner_convertToUnit)).perform(click());
        onData(anything()).atPosition(spinnerToPosition).perform(click());
    }
}