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
public class PressureConverterInstrumentedTest {
    private final HashMap<String, Integer> unitNamePositionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);

        //update the map with unit names and their positions
        unitNamePositionMap.put("pascal", 0);
        unitNamePositionMap.put("bar", 1);
        unitNamePositionMap.put("atmosphere", 2);
        unitNamePositionMap.put("torr", 3);
        unitNamePositionMap.put("pound_per_square_inch", 4);
        unitNamePositionMap.put("kilopascal", 5);
        unitNamePositionMap.put("millimeter_of_mercury", 6);
    }

    @Test
    public void isCorrect_convertPascalToBar() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pascal"), unitNamePositionMap.get("bar"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("100000.0")));
    }

    @Test
    public void isCorrect_convertPascalToAtmosphere() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pascal"), unitNamePositionMap.get("atmosphere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("101325.0")));
    }

    @Test
    public void isCorrect_convertPascalToTorr() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pascal"), unitNamePositionMap.get("torr"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("133.322")));
    }

    @Test
    public void isCorrect_convertPascalToPoundPerSquareInch() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pascal"), unitNamePositionMap.get("pound_per_square_inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6894.76")));
    }

    @Test
    public void isCorrect_convertPascalToKilopascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pascal"), unitNamePositionMap.get("kilopascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000.0")));
    }

    @Test
    public void isCorrect_convertPascalToMillimeterOfMercury() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pascal"), unitNamePositionMap.get("millimeter_of_mercury"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("133.322")));
    }

    @Test
    public void isCorrect_convertBarToPascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("bar"), unitNamePositionMap.get("pascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E-5")));
    }

    @Test
    public void isCorrect_convertBarToAtmosphere() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("bar"), unitNamePositionMap.get("atmosphere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.01325")));
    }

    @Test
    public void isCorrect_convertBarToTorr() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("bar"), unitNamePositionMap.get("torr"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0013332200000000002")));
    }

    @Test
    public void isCorrect_convertBarToPoundPerSquareInch() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("bar"), unitNamePositionMap.get("pound_per_square_inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0689476")));
    }

    @Test
    public void isCorrect_convertBarToKilopascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("bar"), unitNamePositionMap.get("kilopascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.01")));
    }

    @Test
    public void isCorrect_convertBarToMillimeterOfMercury() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("bar"), unitNamePositionMap.get("millimeter_of_mercury"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0013332200000000002")));
    }

    @Test
    public void isCorrect_convertAtmosphereToPascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("atmosphere"), unitNamePositionMap.get("pascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("9.869229999999999E-6")));
    }

    @Test
    public void isCorrect_convertAtmosphereToBar() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("atmosphere"), unitNamePositionMap.get("bar"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.986923")));
    }

    @Test
    public void isCorrect_convertAtmosphereToTorr() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("atmosphere"), unitNamePositionMap.get("torr"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00131579")));
    }

    @Test
    public void isCorrect_convertAtmosphereToPoundPerSquareInch() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("atmosphere"), unitNamePositionMap.get("pound_per_square_inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.068046")));
    }

    @Test
    public void isCorrect_convertAtmosphereToKilopascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("atmosphere"), unitNamePositionMap.get("kilopascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00986923")));
    }

    @Test
    public void isCorrect_convertAtmosphereToMillimeterOfMercury() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("atmosphere"), unitNamePositionMap.get("millimeter_of_mercury"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.00131579")));
    }

    @Test
    public void isCorrect_convertTorrToPascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("torr"), unitNamePositionMap.get("pascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0075006199999999995")));
    }

    @Test
    public void isCorrect_convertTorrToBar() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("torr"), unitNamePositionMap.get("bar"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("750.062")));
    }

    @Test
    public void isCorrect_convertTorrToAtmosphere() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("torr"), unitNamePositionMap.get("atmosphere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("760.0")));
    }

    @Test
    public void isCorrect_convertTorrToPoundPerSquareInch() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("torr"), unitNamePositionMap.get("pound_per_square_inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("51.7149")));
    }

    @Test
    public void isCorrect_convertTorrToKilopascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("torr"), unitNamePositionMap.get("kilopascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("7.50062")));
    }

    @Test
    public void isCorrect_convertTorrToMillimeterOfMercury() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("torr"), unitNamePositionMap.get("millimeter_of_mercury"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0")));
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToPascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_per_square_inch"), unitNamePositionMap.get("pascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.45038E-4")));
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToBar() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_per_square_inch"), unitNamePositionMap.get("bar"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("14.5038")));
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToAtmosphere() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_per_square_inch"), unitNamePositionMap.get("atmosphere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("14.6959")));
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToTorr() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_per_square_inch"), unitNamePositionMap.get("torr"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0193368")));
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToKilopascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_per_square_inch"), unitNamePositionMap.get("kilopascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.145038")));
    }

    @Test
    public void isCorrect_convertPoundPerSquareInchToMillimeterOfMercury() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("pound_per_square_inch"), unitNamePositionMap.get("millimeter_of_mercury"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0193368")));
    }

    @Test
    public void isCorrect_convertKilopascalToPascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilopascal"), unitNamePositionMap.get("pascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.001")));
    }

    @Test
    public void isCorrect_convertKilopascalToBar() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilopascal"), unitNamePositionMap.get("bar"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("100.0")));
    }

    @Test
    public void isCorrect_convertKilopascalToAtmosphere() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilopascal"), unitNamePositionMap.get("atmosphere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("101.325")));
    }

    @Test
    public void isCorrect_convertKilopascalToTorr() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilopascal"), unitNamePositionMap.get("torr"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.133322")));
    }

    @Test
    public void isCorrect_convertKilopascalToPoundPerSquareInch() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilopascal"), unitNamePositionMap.get("pound_per_square_inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("6.89476")));
    }

    @Test
    public void isCorrect_convertKilopascalToMillimeterOfMercury() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("kilopascal"), unitNamePositionMap.get("millimeter_of_mercury"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.133322")));
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToPascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("millimeter_of_mercury"), unitNamePositionMap.get("pascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0075006199999999995")));
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToBar() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("millimeter_of_mercury"), unitNamePositionMap.get("bar"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("750.062")));
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToAtmosphere() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("millimeter_of_mercury"), unitNamePositionMap.get("atmosphere"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("760.0")));
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToTorr() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("millimeter_of_mercury"), unitNamePositionMap.get("torr"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0")));
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToPoundPerSquareInch() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("millimeter_of_mercury"), unitNamePositionMap.get("pound_per_square_inch"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("51.7149")));
    }

    @Test
    public void isCorrect_convertMillimeterOfMercuryToKilopascal() {
        selectPressureUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("millimeter_of_mercury"), unitNamePositionMap.get("kilopascal"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("7.50062")));
    }

    private void selectPressureUnitCategory() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_pressure)).perform(click());
    }

    private void selectSpinnerValues(int spinnerFromPosition, int spinnerToPosition) {
        onView(withId(R.id.spinner_convertFromUnit)).perform(click());
        onData(anything()).atPosition(spinnerFromPosition).perform(click());
        onView(withId(R.id.spinner_convertToUnit)).perform(click());
        onData(anything()).atPosition(spinnerToPosition).perform(click());
    }
}