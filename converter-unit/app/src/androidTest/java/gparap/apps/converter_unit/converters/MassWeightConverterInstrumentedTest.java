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
public class MassWeightConverterInstrumentedTest {
    private final HashMap<String, Integer> unitNamePositionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);

        //update the map with unit names and their positions
        unitNamePositionMap.put("kilogram", 0);
        unitNamePositionMap.put("gram", 1);
        unitNamePositionMap.put("milligram", 2);
        unitNamePositionMap.put("metric_ton", 3);
        unitNamePositionMap.put("ton_us", 4);
        unitNamePositionMap.put("ton_imperial", 5);
        unitNamePositionMap.put("pound", 6);
        unitNamePositionMap.put("ounce_us", 7);
        unitNamePositionMap.put("ounce_imperial", 8);
        unitNamePositionMap.put("stone", 9);
        unitNamePositionMap.put("carat", 10);
    }

    @Test
    public void isCorrect_convertKilogramToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertKilogramToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000000.0")));
    }

    @Test
    public void isCorrect_convertKilogramToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertKilogramToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00110231")));
    }

    @Test
    public void isCorrect_convertKilogramToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.84207E-4")));
    }

    @Test
    public void isCorrect_convertKilogramToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.20462")));
    }

    @Test
    public void isCorrect_convertKilogramToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("35.274")));
    }

    @Test
    public void isCorrect_convertKilogramToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("35.195")));
    }

    @Test
    public void isCorrect_convertKilogramToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.157473")));
    }

    @Test
    public void isCorrect_convertKilogramToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilogram"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5000.0")));
    }

    @Test
    public void isCorrect_convertGramToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertGramToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertGramToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E-6")));
    }

    @Test
    public void isCorrect_convertGramToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.10231E-6")));
    }

    @Test
    public void isCorrect_convertGramToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.84207E-7")));
    }

    @Test
    public void isCorrect_convertGramToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00220462")));
    }

    @Test
    public void isCorrect_convertGramToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.035274")));
    }

    @Test
    public void isCorrect_convertGramToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.035195")));
    }

    @Test
    public void isCorrect_convertGramToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.57473E-4")));
    }

    @Test
    public void isCorrect_convertGramToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("gram"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5.0")));
    }

    @Test
    public void isCorrect_convertMilligramToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E-6")));
    }

    @Test
    public void isCorrect_convertMilligramToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertMilligramToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E-9")));
    }

    @Test
    public void isCorrect_convertMilligramToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.10231E-9")));
    }

    @Test
    public void isCorrect_convertMilligramToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.84207E-10")));
    }

    @Test
    public void isCorrect_convertMilligramToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.20462E-6")));
    }

    @Test
    public void isCorrect_convertMilligramToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.5274E-5")));
    }

    @Test
    public void isCorrect_convertMilligramToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.5195E-5")));
    }

    @Test
    public void isCorrect_convertMilligramToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.57473E-7")));
    }

    @Test
    public void isCorrect_convertMilligramToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("milligram"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.005")));
    }

    @Test
    public void isCorrect_convertMetricTonToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertMetricTonToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000000.0")));
    }

    @Test
    public void isCorrect_convertMetricTonToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E9")));
    }

    @Test
    public void isCorrect_convertMetricTonToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.10231")));
    }

    @Test
    public void isCorrect_convertMetricTonToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.984207")));
    }

    @Test
    public void isCorrect_convertMetricTonToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2204.62")));
    }

    @Test
    public void isCorrect_convertMetricTonToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("35274.0")));
    }

    @Test
    public void isCorrect_convertMetricTonToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("35195.0")));
    }

    @Test
    public void isCorrect_convertMetricTonToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("157.473")));
    }

    @Test
    public void isCorrect_convertMetricTonToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("metric_ton"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5000000.0")));
    }

    @Test
    public void isCorrect_convertUSTonToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("907.18474")));
    }

    @Test
    public void isCorrect_convertUSTonToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("907184.74")));
    }

    @Test
    public void isCorrect_convertUSTonToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.0718474E8")));
    }

    @Test
    public void isCorrect_convertUSTonToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.90718474")));
    }

    @Test
    public void isCorrect_convertUSTonToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.892857")));
    }

    @Test
    public void isCorrect_convertUSTonToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2000.0")));
    }

    @Test
    public void isCorrect_convertUSTonToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("32000.0")));
    }

    @Test
    public void isCorrect_convertUSTonToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("31999.93")));
    }

    @Test
    public void isCorrect_convertUSTonToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("142.857")));
    }

    @Test
    public void isCorrect_convertUSTonToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_us"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4535923.7")));
    }

    @Test
    public void isCorrect_convertImperialTonToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1016.04691")));
    }

    @Test
    public void isCorrect_convertImperialTonToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1016046.91")));
    }

    @Test
    public void isCorrect_convertImperialTonToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.01604691E9")));
    }

    @Test
    public void isCorrect_convertImperialTonToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.01605")));
    }

    @Test
    public void isCorrect_convertImperialTonToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.12")));
    }

    @Test
    public void isCorrect_convertImperialTonToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2240.0")));
    }

    @Test
    public void isCorrect_convertImperialTonToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("35840.0")));
    }

    @Test
    public void isCorrect_convertImperialTonToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("35839.92")));
    }

    @Test
    public void isCorrect_convertImperialTonToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("160.0")));
    }

    @Test
    public void isCorrect_convertImperialTonToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ton_imperial"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5080234.55")));
    }

    @Test
    public void isCorrect_convertPoundToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.453592")));
    }

    @Test
    public void isCorrect_convertPoundToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("453.592")));
    }

    @Test
    public void isCorrect_convertPoundToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("453592.0")));
    }

    @Test
    public void isCorrect_convertPoundToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.53592E-4")));
    }

    @Test
    public void isCorrect_convertPoundToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("5.0E-4")));
    }

    @Test
    public void isCorrect_convertPoundToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.46429E-4")));
    }

    @Test
    public void isCorrect_convertPoundToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("16.0")));
    }

    @Test
    public void isCorrect_convertPoundToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("15.999")));
    }

    @Test
    public void isCorrect_convertPoundToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0714286")));
    }

    @Test
    public void isCorrect_convertPoundToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2267.96")));
    }

    @Test
    public void isCorrect_convertUSOunceToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0283495")));
    }

    @Test
    public void isCorrect_convertUSOunceToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("28.3495")));
    }

    @Test
    public void isCorrect_convertUSOunceToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("28349.5")));
    }

    @Test
    public void isCorrect_convertUSOunceToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.83495E-5")));
    }

    @Test
    public void isCorrect_convertUSOunceToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.125E-5")));
    }

    @Test
    public void isCorrect_convertUSOunceToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.79018E-5")));
    }

    @Test
    public void isCorrect_convertUSOunceToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0625")));
    }

    @Test
    public void isCorrect_convertUSOunceToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.911458")));
    }

    @Test
    public void isCorrect_convertUSOunceToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00446429")));
    }

    @Test
    public void isCorrect_convertUSOunceToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_us"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("141.748")));
    }

    @Test
    public void isCorrect_convertImperialOunceToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0283495")));
    }

    @Test
    public void isCorrect_convertImperialOunceToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("28.3495")));
    }

    @Test
    public void isCorrect_convertImperialOunceToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("28349.5")));
    }

    @Test
    public void isCorrect_convertImperialOunceToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.83495E-5")));
    }

    @Test
    public void isCorrect_convertImperialOunceToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.125E-5")));
    }

    @Test
    public void isCorrect_convertImperialOunceToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.7902E-5")));
    }

    @Test
    public void isCorrect_convertImperialOunceToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0625")));
    }

    @Test
    public void isCorrect_convertImperialOunceToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.04084")));
    }

    @Test
    public void isCorrect_convertImperialOunceToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00446429")));
    }

    @Test
    public void isCorrect_convertImperialOunceToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("ounce_imperial"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("141.748")));
    }

    @Test
    public void isCorrect_convertStoneToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6.35029")));
    }

    @Test
    public void isCorrect_convertStoneToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6350.29")));
    }

    @Test
    public void isCorrect_convertStoneToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6350290.0")));
    }

    @Test
    public void isCorrect_convertStoneToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00635029")));
    }

    @Test
    public void isCorrect_convertStoneToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.007")));
    }

    @Test
    public void isCorrect_convertStoneToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00625")));
    }

    @Test
    public void isCorrect_convertStoneToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("14.0")));
    }

    @Test
    public void isCorrect_convertStoneToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("224.0")));
    }

    @Test
    public void isCorrect_convertStoneToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("224.0")));
    }

    @Test
    public void isCorrect_convertStoneToCarat() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("stone"), unitNamePositionMap.get("carat"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("31751.5")));
    }

    @Test
    public void isCorrect_convertCaratToKilogram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("kilogram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.0E-4")));
    }

    @Test
    public void isCorrect_convertCaratToGram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("gram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.2")));
    }

    @Test
    public void isCorrect_convertCaratToMilligram() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("milligram"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("200.0")));
    }

    @Test
    public void isCorrect_convertCaratToMetricTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("metric_ton"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.0E-7")));
    }

    @Test
    public void isCorrect_convertCaratToUSTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("ton_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.20462E-7")));
    }

    @Test
    public void isCorrect_convertCaratToImperialTon() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("ton_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.96841E-7")));
    }

    @Test
    public void isCorrect_convertCaratToPound() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("pound"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4.40925E-4")));
    }

    @Test
    public void isCorrect_convertCaratToUSOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("ounce_us"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00705479")));
    }

    @Test
    public void isCorrect_convertCaratToImperialOunce() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("ounce_imperial"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0070557")));
    }

    @Test
    public void isCorrect_convertCaratToStone() {
        selectMassWeightUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("carat"), unitNamePositionMap.get("stone"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.14961E-5")));
    }

    private void selectMassWeightUnitCategory() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_mass_weight)).perform(click());
    }

    private void selectSpinnerValues(int spinnerFromPosition, int spinnerToPosition) {
        onView(withId(R.id.spinner_convertFromUnit)).perform(click());
        onData(anything()).atPosition(spinnerFromPosition).perform(click());
        onView(withId(R.id.spinner_convertToUnit)).perform(click());
        onData(anything()).atPosition(spinnerToPosition).perform(click());
    }
}