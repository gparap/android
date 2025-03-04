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
public class AreaConverterInstrumentedTest {
    private final HashMap<String, Integer> unitNamePositionMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);

        //update the map with unit names and their positions
        unitNamePositionMap.put("square_meter", 0);
        unitNamePositionMap.put("square_kilometer", 1);
        unitNamePositionMap.put("square_mile", 2);
        unitNamePositionMap.put("hectare", 3);
        unitNamePositionMap.put("acre", 4);
    }

    @Test
    public void isCorrect_convertSquareMeterToSquareKilometer() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_meter"), unitNamePositionMap.get("square_kilometer"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E-6")));
    }

    @Test
    public void isCorrect_convertSquareMeterToSquareMile() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_meter"), unitNamePositionMap.get("square_mile"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("3.86102158592535E-7")));
    }

    @Test
    public void isCorrect_convertSquareMeterToHectare() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_meter"), unitNamePositionMap.get("hectare"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0E-4")));
    }

    @Test
    public void isCorrect_convertSquareMeterToAcre() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_meter"), unitNamePositionMap.get("acre"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.471053814671653E-4")));
    }

    @Test
    public void isCorrect_convertSquareKilometerToSquareMeter() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_kilometer"), unitNamePositionMap.get("square_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1000000.0")));
    }

    @Test
    public void isCorrect_convertSquareKilometerToSquareMile() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_kilometer"), unitNamePositionMap.get("square_mile"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.386102158592535")));
    }

    @Test
    public void isCorrect_convertSquareKilometerToHectare() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_kilometer"), unitNamePositionMap.get("hectare"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("100.0")));
    }

    @Test
    public void isCorrect_convertSquareKilometerToAcre() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_kilometer"), unitNamePositionMap.get("acre"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("247.1053814672")));
    }

    @Test
    public void isCorrect_convertSquareMileToSquareMeter() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_mile"), unitNamePositionMap.get("square_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2589988.11")));
    }

    @Test
    public void isCorrect_convertSquareMileToSquareKilometer() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_mile"), unitNamePositionMap.get("square_kilometer"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.58998811")));
    }

    @Test
    public void isCorrect_convertSquareMileToHectare() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_mile"), unitNamePositionMap.get("hectare"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("258.998811")));
    }

    @Test
    public void isCorrect_convertSquareMileToAcre() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("square_mile"), unitNamePositionMap.get("acre"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("640.0")));
    }

    @Test
    public void isCorrect_convertHectareToSquareMeter() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("hectare"), unitNamePositionMap.get("square_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("10000.0")));
    }

    @Test
    public void isCorrect_convertHectareToSquareKilometer() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("hectare"), unitNamePositionMap.get("square_kilometer"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.01")));
    }

    @Test
    public void isCorrect_convertHectareToSquareMile() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("hectare"), unitNamePositionMap.get("square_mile"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0038610215859253504")));
    }

    @Test
    public void isCorrect_convertHectareToAcre() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("hectare"), unitNamePositionMap.get("acre"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("2.471053814672")));
    }

    @Test
    public void isCorrect_convertAcreToSquareMeter() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("acre"), unitNamePositionMap.get("square_meter"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("4046.8564224")));
    }

    @Test
    public void isCorrect_convertAcreToSquareKilometer() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("acre"), unitNamePositionMap.get("square_kilometer"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.004046856422399432")));
    }

    @Test
    public void isCorrect_convertAcreToSquareMile() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("acre"), unitNamePositionMap.get("square_mile"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0015625")));
    }

    @Test
    public void isCorrect_convertAcreToHectare() {
        selectAreaUnitCategory();
        selectSpinnerValues(unitNamePositionMap.get("acre"), unitNamePositionMap.get("hectare"));
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.40468564224")));
    }

    private void selectAreaUnitCategory() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_area)).perform(click());
    }

    private void selectSpinnerValues(int spinnerFromPosition, int spinnerToPosition) {
        onView(withId(R.id.spinner_convertFromUnit)).perform(click());
        onData(anything()).atPosition(spinnerFromPosition).perform(click());
        onView(withId(R.id.spinner_convertToUnit)).perform(click());
        onData(anything()).atPosition(spinnerToPosition).perform(click());
    }
}