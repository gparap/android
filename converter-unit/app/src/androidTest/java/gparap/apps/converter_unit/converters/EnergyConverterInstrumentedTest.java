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
public class EnergyConverterInstrumentedTest {
    private final HashMap<String, Integer> unitNamePositionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);

        //update the map with unit names and their positions
        unitNamePositionMap.put("joule", 0);
        unitNamePositionMap.put("calorie", 1);
        unitNamePositionMap.put("kilocalorie", 2);
        unitNamePositionMap.put("electronvolt", 3);
        unitNamePositionMap.put("watt_hour", 4);
        unitNamePositionMap.put("british_thermal_unit", 5);
        unitNamePositionMap.put("kilowatt_hour", 6);
    }

    @Test
    public void isCorrect_convertJouleToCalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("joule"), unitNamePositionMap.get("calorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.2390057361376673")));
    }

    @Test
    public void isCorrect_convertJouleToKilocalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("joule"), unitNamePositionMap.get("kilocalorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.390057361376673E-4")));
    }

    @Test
    public void isCorrect_convertJouleToElectronvolt() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("joule"), unitNamePositionMap.get("electronvolt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6.241495961752112E-20")));
    }

    @Test
    public void isCorrect_convertJouleToWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("joule"), unitNamePositionMap.get("watt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.777777777777778E-4")));
    }

    @Test
    public void isCorrect_convertJouleToBritishThermalUnit() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("joule"), unitNamePositionMap.get("british_thermal_unit"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.478133944988911E-4")));
    }

    @Test
    public void isCorrect_convertJouleToKiloWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("joule"), unitNamePositionMap.get("kilowatt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3600000.0")));
    }

    @Test
    public void isCorrect_convertCalorieToJoule() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("calorie"), unitNamePositionMap.get("joule"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.184")));
    }

    @Test
    public void isCorrect_convertCalorieToKilocalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("calorie"), unitNamePositionMap.get("kilocalorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertCalorieToElectronvolt() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("calorie"), unitNamePositionMap.get("electronvolt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.611441910397084E-19")));
    }

    @Test
    public void isCorrect_convertCalorieToWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("calorie"), unitNamePositionMap.get("watt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0011622217495853772")));
    }

    @Test
    public void isCorrect_convertCalorieToBritishThermalUnit() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("calorie"), unitNamePositionMap.get("british_thermal_unit"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.003965673133357656")));
    }

    @Test
    public void isCorrect_convertCalorieToKiloWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("calorie"), unitNamePositionMap.get("kilowatt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.1622217495853774E-6")));
    }

    @Test
    public void isCorrect_convertKilocalorieToJoule() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilocalorie"), unitNamePositionMap.get("joule"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4184.0")));
    }

    @Test
    public void isCorrect_convertKilocalorieToCalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilocalorie"), unitNamePositionMap.get("calorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertKilocalorieToElectronvolt() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilocalorie"), unitNamePositionMap.get("electronvolt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.611441910397084E-16")));
    }

    @Test
    public void isCorrect_convertKilocalorieToWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilocalorie"), unitNamePositionMap.get("watt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0011622217495853772")));
    }

    @Test
    public void isCorrect_convertKilocalorieToBritishThermalUnit() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilocalorie"), unitNamePositionMap.get("british_thermal_unit"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.947813394498891")));
    }

    @Test
    public void isCorrect_convertKilocalorieToKiloWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilocalorie"), unitNamePositionMap.get("kilowatt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.1622217495853774E-6")));
    }

    @Test
    public void isCorrect_convertElectronvoltToJoule() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("electronvolt"), unitNamePositionMap.get("joule"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.60218E-19")));
    }

    @Test
    public void isCorrect_convertElectronvoltToCalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("electronvolt"), unitNamePositionMap.get("calorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6.135231243785849E-20")));
    }

    @Test
    public void isCorrect_convertElectronvoltToKilocalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("electronvolt"), unitNamePositionMap.get("kilocalorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.829302103250478E-23")));
    }

    @Test
    public void isCorrect_convertElectronvoltToWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("electronvolt"), unitNamePositionMap.get("watt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.4505E-23")));
    }

    @Test
    public void isCorrect_convertElectronvoltToBritishThermalUnit() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("electronvolt"), unitNamePositionMap.get("british_thermal_unit"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.5185676643982333E-22")));
    }

    @Test
    public void isCorrect_convertElectronvoltToKiloWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("electronvolt"), unitNamePositionMap.get("kilowatt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.4504999999999997E-26")));
    }

    @Test
    public void isCorrect_convertWattHourToJoule() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt_hour"), unitNamePositionMap.get("joule"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3600.0")));
    }

    @Test
    public void isCorrect_convertWattHourToCalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt_hour"), unitNamePositionMap.get("calorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("860.421")));
    }

    @Test
    public void isCorrect_convertWattHourToKilocalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt_hour"), unitNamePositionMap.get("kilocalorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0011622217495853772")));
    }

    @Test
    public void isCorrect_convertWattHourToElectronvolt() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt_hour"), unitNamePositionMap.get("electronvolt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.2469385462307605E-16")));
    }

    @Test
    public void isCorrect_convertWattHourToBritishThermalUnit() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt_hour"), unitNamePositionMap.get("british_thermal_unit"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.947813394498891")));
    }

    @Test
    public void isCorrect_convertWattHourToKiloWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("watt_hour"), unitNamePositionMap.get("kilowatt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToJoule() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("british_thermal_unit"), unitNamePositionMap.get("joule"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1055.06")));
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToCalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("british_thermal_unit"), unitNamePositionMap.get("calorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("252.164")));
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToKilocalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("british_thermal_unit"), unitNamePositionMap.get("kilocalorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.947813394498891")));
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToElectronvolt() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("british_thermal_unit"), unitNamePositionMap.get("electronvolt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6.585152729406184E-17")));
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("british_thermal_unit"), unitNamePositionMap.get("watt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.2930712104427134")));
    }

    @Test
    public void isCorrect_convertBritishThermalUnitToKiloWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("british_thermal_unit"), unitNamePositionMap.get("kilowatt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.930712104427134E-4")));
    }

    @Test
    public void isCorrect_convertKiloWattHourToJoule() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt_hour"), unitNamePositionMap.get("joule"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3600000.0")));
    }

    @Test
    public void isCorrect_convertKiloWattHourToCalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt_hour"), unitNamePositionMap.get("calorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("860421.0")));
    }

    @Test
    public void isCorrect_convertKiloWattHourToKilocalorie() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt_hour"), unitNamePositionMap.get("kilocalorie"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("860.421")));
    }

    @Test
    public void isCorrect_convertKiloWattHourToElectronvolt() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt_hour"), unitNamePositionMap.get("electronvolt"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.246938546230761E-13")));
    }

    @Test
    public void isCorrect_convertKiloWattHourToWattHour() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt_hour"), unitNamePositionMap.get("watt_hour"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertKiloWattHourToBritishThermalUnit() {
        selectEnergyUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilowatt_hour"), unitNamePositionMap.get("british_thermal_unit"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3412.14")));
    }

    private void selectEnergyUnitCategory() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_energy)).perform(click());
    }

    private void selectSpinnerValues(int spinnerFromPosition, int spinnerToPosition) {
        onView(withId(R.id.spinner_convertFromUnit)).perform(click());
        onData(anything()).atPosition(spinnerFromPosition).perform(click());
        onView(withId(R.id.spinner_convertToUnit)).perform(click());
        onData(anything()).atPosition(spinnerToPosition).perform(click());
    }
}