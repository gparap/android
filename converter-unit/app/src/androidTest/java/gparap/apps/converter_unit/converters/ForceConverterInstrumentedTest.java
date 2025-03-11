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
public class ForceConverterInstrumentedTest {
    private final HashMap<String, Integer> unitNamePositionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);

        //update the map with unit names and their positions
        unitNamePositionMap.put("newton", 0);
        unitNamePositionMap.put("pound_force", 1);
        unitNamePositionMap.put("kilogram_force", 2);
        unitNamePositionMap.put("dyne", 3);
        unitNamePositionMap.put("ton_force_metric", 4);
        unitNamePositionMap.put("ton_force_us", 5);
    }

    @Test
    public void isCorrect_convertNewtonToPoundForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("newton"), unitNamePositionMap.get("pound_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertNewtonToKilogramForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("newton"), unitNamePositionMap.get("kilogram_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6.21371E-4")));
    }

    @Test
    public void isCorrect_convertNewtonToDyne() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("newton"), unitNamePositionMap.get("dyne"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.09361")));
    }

    @Test
    public void isCorrect_convertNewtonToMetricTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("newton"), unitNamePositionMap.get("ton_force_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.28084")));
    }

    @Test
    public void isCorrect_convertNewtonToUSTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("newton"), unitNamePositionMap.get("ton_force_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("39.3701")));
    }
    
    @Test
    public void isCorrect_convertPoundForceToNewton() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_force"), unitNamePositionMap.get("newton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertPoundForceToKilogramForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_force"), unitNamePositionMap.get("kilogram_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.621371")));
    }

    @Test
    public void isCorrect_convertPoundForceToDyne() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_force"), unitNamePositionMap.get("dyne"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1093.61")));
    }

    @Test
    public void isCorrect_convertPoundForceToMetricTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_force"), unitNamePositionMap.get("ton_force_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3280.84")));
    }

    @Test
    public void isCorrect_convertPoundForceToUSTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_force"), unitNamePositionMap.get("ton_force_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("39370.1")));
    }

    @Test
    public void isCorrect_convertKilogramForceToNewton() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram_force"), unitNamePositionMap.get("newton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1609.34")));
    }

    @Test
    public void isCorrect_convertKilogramForceToPoundForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram_force"), unitNamePositionMap.get("pound_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.60934")));
    }

    @Test
    public void isCorrect_convertKilogramForceToDyne() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram_force"), unitNamePositionMap.get("dyne"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1760.0")));
    }

    @Test
    public void isCorrect_convertKilogramForceToMetricTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram_force"), unitNamePositionMap.get("ton_force_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5280.0")));
    }

    @Test
    public void isCorrect_convertKilogramForceToUSTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram_force"), unitNamePositionMap.get("ton_force_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("63360.0")));
    }

    @Test
    public void isCorrect_convertDyneToNewton() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("dyne"), unitNamePositionMap.get("newton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.9144")));
    }

    @Test
    public void isCorrect_convertDyneToPoundForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("dyne"), unitNamePositionMap.get("pound_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.144E-4")));
    }

    @Test
    public void isCorrect_convertDyneToKilogramForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("dyne"), unitNamePositionMap.get("kilogram_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5.68182E-4")));
    }

    @Test
    public void isCorrect_convertDyneToMetricTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("dyne"), unitNamePositionMap.get("ton_force_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.0")));
    }

    @Test
    public void isCorrect_convertDyneToUSTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("dyne"), unitNamePositionMap.get("ton_force_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("36.0")));
    }

    @Test
    public void isCorrect_convertMetricTonForceToNewton() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_metric"), unitNamePositionMap.get("newton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.3048")));
    }

    @Test
    public void isCorrect_convertMetricTonForceToPoundForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_metric"), unitNamePositionMap.get("pound_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.048E-4")));
    }

    @Test
    public void isCorrect_convertMetricTonForceToKilogramForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_metric"), unitNamePositionMap.get("kilogram_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.89394E-4")));
    }

    @Test
    public void isCorrect_convertMetricTonForceToDyne() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_metric"), unitNamePositionMap.get("dyne"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.333333")));
    }

    @Test
    public void isCorrect_convertMetricTonForceToUSTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_metric"), unitNamePositionMap.get("ton_force_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("12.0")));
    }

    @Test
    public void isCorrect_convertUSTonForceToNewton() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_us"), unitNamePositionMap.get("newton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0254")));
    }

    @Test
    public void isCorrect_convertUSTonForceToPoundForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_us"), unitNamePositionMap.get("pound_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.54E-5")));
    }

    @Test
    public void isCorrect_convertUSTonForceToKilogramForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_us"), unitNamePositionMap.get("kilogram_force"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.57828E-5")));
    }

    @Test
    public void isCorrect_convertUSTonForceToDyne() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_us"), unitNamePositionMap.get("dyne"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0277778")));
    }

    @Test
    public void isCorrect_convertUSTonForceToMetricTonForce() {
        selectForceUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_force_us"), unitNamePositionMap.get("ton_force_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0833333")));
    }

    private void selectForceUnitCategory() {
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