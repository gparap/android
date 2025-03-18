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
public class PowerConverterInstrumentedTest {
    private final HashMap<String, Integer> unitNamePositionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);

        //update the map with unit names and their positions
        unitNamePositionMap.put("watt", 0);
        unitNamePositionMap.put("kilowatt", 1);
        unitNamePositionMap.put("megawatt", 2);
        unitNamePositionMap.put("horsepower_metric", 3);
        unitNamePositionMap.put("horsepower_imperial", 4);
        unitNamePositionMap.put("volt_ampere", 5);
        unitNamePositionMap.put("kilovolt_ampere", 6);
    }

    @Test
    public void isCorrect_convertWattToKilowatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt"), unitNamePositionMap.get("kilowatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertWattToMegawatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt"), unitNamePositionMap.get("megawatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000000.0")));
    }

    @Test
    public void isCorrect_convertWattToMetricHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt"), unitNamePositionMap.get("horsepower_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("735.49875")));
    }

    @Test
    public void isCorrect_convertWattToImperialHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt"), unitNamePositionMap.get("horsepower_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("745.69987")));
    }

    @Test
    public void isCorrect_convertWattToVoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt"), unitNamePositionMap.get("volt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0")));
    }

    @Test
    public void isCorrect_convertWattToKilovoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt"), unitNamePositionMap.get("kilovolt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertKilowattToWatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt"), unitNamePositionMap.get("watt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertKilowattToMegawatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt"), unitNamePositionMap.get("megawatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertKilowattToMetricHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt"), unitNamePositionMap.get("horsepower_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.73549875")));
    }

    @Test
    public void isCorrect_convertKilowattToImperialHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt"), unitNamePositionMap.get("horsepower_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.74569987")));
    }

    @Test
    public void isCorrect_convertKilowattToVoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt"), unitNamePositionMap.get("volt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertKilowattToKilovoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt"), unitNamePositionMap.get("kilovolt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0")));
    }

    @Test
    public void isCorrect_convertMegawattToWatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("megawatt"), unitNamePositionMap.get("watt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E-6")));
    }

    @Test
    public void isCorrect_convertMegawattToKilowatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("megawatt"), unitNamePositionMap.get("kilowatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertMegawattToMetricHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("megawatt"), unitNamePositionMap.get("horsepower_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("7.3549875E-4")));
    }

    @Test
    public void isCorrect_convertMegawattToImperialHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("megawatt"), unitNamePositionMap.get("horsepower_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("7.4569987E-4")));
    }

    @Test
    public void isCorrect_convertMegawattToVoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("megawatt"), unitNamePositionMap.get("volt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E-6")));
    }

    @Test
    public void isCorrect_convertMegawattToKilovoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("megawatt"), unitNamePositionMap.get("kilovolt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToWatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_metric"), unitNamePositionMap.get("watt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0013596216")));
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToKilowatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_metric"), unitNamePositionMap.get("kilowatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.3596216")));
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToMegawatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_metric"), unitNamePositionMap.get("megawatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1359.6216")));
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToImperialHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_metric"), unitNamePositionMap.get("horsepower_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0138697")));
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToVoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_metric"), unitNamePositionMap.get("volt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0013596216")));
    }

    @Test
    public void isCorrect_convertMetricHorsepowerToKilovoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_metric"), unitNamePositionMap.get("kilovolt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.3596216")));
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToWatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_imperial"), unitNamePositionMap.get("watt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0013410221")));
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToKilowatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_imperial"), unitNamePositionMap.get("kilowatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.3410221")));
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToMegawatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_imperial"), unitNamePositionMap.get("megawatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1341.0221")));
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToMetricHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_imperial"), unitNamePositionMap.get("horsepower_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.9863201")));
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToVoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_imperial"), unitNamePositionMap.get("volt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0013410221")));
    }

    @Test
    public void isCorrect_convertImperialHorsepowerToKilovoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("horsepower_imperial"), unitNamePositionMap.get("kilovolt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.3410221")));
    }

    @Test
    public void isCorrect_convertVoltAmpereToWatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("volt_ampere"), unitNamePositionMap.get("watt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0")));
    }

    @Test
    public void isCorrect_convertVoltAmpereToKilowatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("volt_ampere"), unitNamePositionMap.get("kilowatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertVoltAmpereToMegawatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("volt_ampere"), unitNamePositionMap.get("megawatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000000.0")));
    }

    @Test
    public void isCorrect_convertVoltAmpereToMetricHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("volt_ampere"), unitNamePositionMap.get("horsepower_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("735.49875")));
    }

    @Test
    public void isCorrect_convertVoltAmpereToImperialHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("volt_ampere"), unitNamePositionMap.get("horsepower_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("745.69987")));
    }

    @Test
    public void isCorrect_convertVoltAmpereToKilovoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("volt_ampere"), unitNamePositionMap.get("kilovolt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("745.69987")));
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToWatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilovolt_ampere"), unitNamePositionMap.get("watt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToKilowatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilovolt_ampere"), unitNamePositionMap.get("kilowatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0")));
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToMegawatt() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilovolt_ampere"), unitNamePositionMap.get("megawatt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToMetricHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilovolt_ampere"), unitNamePositionMap.get("horsepower_metric"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.73549875")));
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToImperialHorsepower() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilovolt_ampere"), unitNamePositionMap.get("horsepower_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.74569987")));
    }

    @Test
    public void isCorrect_convertKilovoltAmpereToVoltAmpere() {
        selectPowerUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilovolt_ampere"), unitNamePositionMap.get("volt_ampere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    private void selectPowerUnitCategory() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_power)).perform(click());
    }

    private void selectSpinnerValues(int spinnerFromPosition, int spinnerToPosition) {
        onView(withId(R.id.spinner_convertFromUnit)).perform(click());
        onData(anything()).atPosition(spinnerFromPosition).perform(click());
        onView(withId(R.id.spinner_convertToUnit)).perform(click());
        onData(anything()).atPosition(spinnerToPosition).perform(click());
    }
}