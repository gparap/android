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

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
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
public class VolumeConverterInstrumentedTest {
    private final HashMap<String, Integer> unitNamePositionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);

        //update the map with unit names and their positions
        unitNamePositionMap.put("cubic_meter", 0);
        unitNamePositionMap.put("liter", 1);
        unitNamePositionMap.put("milliliter", 2);
        unitNamePositionMap.put("gallon_us", 3);
        unitNamePositionMap.put("gallon_imperial", 4);
        unitNamePositionMap.put("quart_us", 5);
        unitNamePositionMap.put("quart_imperial", 6);
        unitNamePositionMap.put("pint_us", 7);
        unitNamePositionMap.put("pint_imperial", 8);
        unitNamePositionMap.put("fluid_ounce_us", 9);
        unitNamePositionMap.put("fluid_ounce_imperial", 10);
    }

    @Test
    public void isCorrect_convertCubicMeterToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertCubicMeterToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000000.0")));
    }

    @Test
    public void isCorrect_convertCubicMeterToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("264.172")));
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("219.969")));
    }

    @Test
    public void isCorrect_convertCubicMeterToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1056.688")));
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("879.876")));
    }

    @Test
    public void isCorrect_convertCubicMeterToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2113.376")));
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1759.752")));
    }

    @Test
    public void isCorrect_convertCubicMeterToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("33814.022")));
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("cubic_meter"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("35195.073")));
    }

    @Test
    public void isCorrect_convertLiterToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertLiterToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertLiterToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.264172")));
    }

    @Test
    public void isCorrect_convertLiterToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.219969")));
    }

    @Test
    public void isCorrect_convertLiterToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.056688")));
    }

    @Test
    public void isCorrect_convertLiterToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.879876")));
    }

    @Test
    public void isCorrect_convertLiterToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.113376")));
    }

    @Test
    public void isCorrect_convertLiterToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.759752")));
    }

    @Test
    public void isCorrect_convertLiterToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("33.814022")));
    }

    @Test
    public void isCorrect_convertLiterToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("liter"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("35.195073")));
    }

    @Test
    public void isCorrect_convertMilliliterToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E-6")));
    }

    @Test
    public void isCorrect_convertMilliliterToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertMilliliterToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.64172E-4")));
    }

    @Test
    public void isCorrect_convertMilliliterToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.19969E-4")));
    }

    @Test
    public void isCorrect_convertMilliliterToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001056688")));
    }

    @Test
    public void isCorrect_convertMilliliterToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("8.79876E-4")));
    }

    @Test
    public void isCorrect_convertMilliliterToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.002113376")));
    }

    @Test
    public void isCorrect_convertMilliliterToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001759752")));
    }

    @Test
    public void isCorrect_convertMilliliterToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.033814022")));
    }

    @Test
    public void isCorrect_convertMilliliterToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milliliter"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.035195073")));
    }

    @Test
    public void isCorrect_convertUSGallonToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00378541")));
    }

    @Test
    public void isCorrect_convertUSGallonToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.78541")));
    }

    @Test
    public void isCorrect_convertUSGallonToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3785.41")));
    }

    @Test
    public void isCorrect_convertUSGallonToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.832674")));
    }

    @Test
    public void isCorrect_convertUSGallonToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.0")));
    }

    @Test
    public void isCorrect_convertUSGallonToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.330695")));
    }

    @Test
    public void isCorrect_convertUSGallonToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("8.0")));
    }

    @Test
    public void isCorrect_convertUSGallonToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6.66139")));
    }

    @Test
    public void isCorrect_convertUSGallonToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("128.0")));
    }

    @Test
    public void isCorrect_convertUSGallonToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_us"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("133.227")));
    }

    @Test
    public void isCorrect_convertImperialGallonToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00454609")));
    }

    @Test
    public void isCorrect_convertImperialGallonToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.54609")));
    }

    @Test
    public void isCorrect_convertImperialGallonToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4546.09")));
    }

    @Test
    public void isCorrect_convertImperialGallonToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.20095")));
    }

    @Test
    public void isCorrect_convertImperialGallonToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.80064")));
    }

    @Test
    public void isCorrect_convertImperialGallonToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.0")));
    }

    @Test
    public void isCorrect_convertImperialGallonToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.60127")));
    }

    @Test
    public void isCorrect_convertImperialGallonToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("8.0")));
    }

    @Test
    public void isCorrect_convertImperialGallonToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("153.722")));
    }

    @Test
    public void isCorrect_convertImperialGallonToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gallon_imperial"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("160.0")));
    }

    @Test
    public void isCorrect_convertUSQuartToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.46353E-4")));
    }

    @Test
    public void isCorrect_convertUSQuartToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.946353")));
    }

    @Test
    public void isCorrect_convertUSQuartToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("946.353")));
    }

    @Test
    public void isCorrect_convertUSQuartToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.25")));
    }

    @Test
    public void isCorrect_convertUSQuartToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.208168")));
    }

    @Test
    public void isCorrect_convertUSQuartToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.833348")));
    }

    @Test
    public void isCorrect_convertUSQuartToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.0")));
    }

    @Test
    public void isCorrect_convertUSQuartToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.666696")));
    }

    @Test
    public void isCorrect_convertUSQuartToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("32.0")));
    }

    @Test
    public void isCorrect_convertUSQuartToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_us"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("33.327")));
    }

    @Test
    public void isCorrect_convertImperialQuartToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00113652")));
    }

    @Test
    public void isCorrect_convertImperialQuartToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.13652")));
    }

    @Test
    public void isCorrect_convertImperialQuartToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1136.52")));
    }

    @Test
    public void isCorrect_convertImperialQuartToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.832674")));
    }

    @Test
    public void isCorrect_convertImperialQuartToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.25")));
    }

    @Test
    public void isCorrect_convertImperialQuartToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.20095")));
    }

    @Test
    public void isCorrect_convertImperialQuartToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.4009")));
    }

    @Test
    public void isCorrect_convertImperialQuartToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.0")));
    }

    @Test
    public void isCorrect_convertImperialQuartToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("38.43")));
    }

    @Test
    public void isCorrect_convertImperialQuartToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("quart_imperial"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("40.0")));
    }

    @Test
    public void isCorrect_convertUSPintToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.73176E-4")));
    }

    @Test
    public void isCorrect_convertUSPintToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.473176")));
    }

    @Test
    public void isCorrect_convertUSPintToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("473.176")));
    }

    @Test
    public void isCorrect_convertUSPintToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.125")));
    }

    @Test
    public void isCorrect_convertUSPintToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.104084")));
    }

    @Test
    public void isCorrect_convertUSPintToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.5")));
    }

    @Test
    public void isCorrect_convertUSPintToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.416674")));
    }

    @Test
    public void isCorrect_convertUSPintToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.832674")));
    }

    @Test
    public void isCorrect_convertUSPintToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("16.0")));
    }

    @Test
    public void isCorrect_convertUSPintToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_us"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("16.664")));
    }

    @Test
    public void isCorrect_convertImperialPintToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5.68261E-4")));
    }

    @Test
    public void isCorrect_convertImperialPintToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.568261")));
    }

    @Test
    public void isCorrect_convertImperialPintToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("568.261")));
    }

    @Test
    public void isCorrect_convertImperialPintToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.133419")));
    }

    @Test
    public void isCorrect_convertImperialPintToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.125")));
    }

    @Test
    public void isCorrect_convertImperialPintToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.625")));
    }

    @Test
    public void isCorrect_convertImperialPintToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.5")));
    }

    @Test
    public void isCorrect_convertImperialPintToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.20095")));
    }

    @Test
    public void isCorrect_convertImperialPintToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("19.215")));
    }

    @Test
    public void isCorrect_convertImperialPintToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pint_imperial"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("20.0")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.95735E-5")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0295735")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("29.5735")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0078125")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0074607")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.03125")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.031619")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0625")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.063238")));
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_us"), unitNamePositionMap.get("fluid_ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.96076")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToCubicMeter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("cubic_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.84131E-5")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToLiter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("liter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0284131")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToMilliliter() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("milliliter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("28.4131")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("gallon_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00750594")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToImperialGallon() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("gallon_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0078125")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("quart_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.03")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToImperialQuart() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("quart_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.025")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("pint_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.06")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToImperialPint() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("pint_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.05")));
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSFluidOunce() {
        selectVolumeUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("fluid_ounce_imperial"), unitNamePositionMap.get("fluid_ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.042")));
    }

    private void selectVolumeUnitCategory() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_volume)).perform(click());
    }

    private void selectSpinnerValues(int spinnerFromPosition, int spinnerToPosition) {
        onView(withId(R.id.spinner_convertFromUnit)).perform(click());
        onData(anything()).atPosition(spinnerFromPosition).perform(click());
        onView(withId(R.id.spinner_convertToUnit)).perform(click());
        onData(anything()).atPosition(spinnerToPosition).perform(click());
    }
}