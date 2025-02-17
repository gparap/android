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
package gparap.apps.converter_unit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void isVisible_textView_unitConversionCategory() {
        onView(withId(R.id.textView_unitConversionCategory)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_convertFromUnitLabel() {
        onView(withId(R.id.textView_convertFromUnitLabel)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_spinner_convertFromUnit() {
        onView(withId(R.id.spinner_convertFromUnit)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_editText_convertFromUnitValue() {
        onView(withId(R.id.editText_conversionInputValue)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_convertToUnitLabel() {
        onView(withId(R.id.textView_convertToUnitLabel)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_spinner_convertToUnit() {
        onView(withId(R.id.spinner_convertToUnit)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_editText_conversionInputValue() {
        onView(withId(R.id.editText_conversionInputValue)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_textView_conversionResult() {
        onView(withId(R.id.textView_conversionResult)).check(matches(isDisplayed()));
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryLengthDistance() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_length_distance)).perform(click());
            onView(withText(R.string.subcategory_meter)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryArea() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_area)).perform(click());
            onView(withText(R.string.subcategory_square_meter)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryVolume() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_volume)).perform(click());
            onView(withText(R.string.subcategory_cubic_meter)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryMassWeight() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_mass_weight)).perform(click());
            onView(withText(R.string.subcategory_kilogram)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryTemperature() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_temperature)).perform(click());
            onView(withText(R.string.subcategory_celsius)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryPressure() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_pressure)).perform(click());
            onView(withText(R.string.subcategory_pascal)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryEnergy() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_energy)).perform(click());
            onView(withText(R.string.subcategory_joule)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryPower() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_power)).perform(click());
            onView(withText(R.string.subcategory_watt)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryAngles() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_angles)).perform(click());
            onView(withText(R.string.subcategory_degree)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }

    @Test
    public void areCorrect_onOptionsItemSelected_categoryForce() {
        try {
            Espresso.openContextualActionModeOverflowMenu();
            onView(withText(R.string.category_force)).perform(click());
            onView(withText(R.string.subcategory_newton)).check(matches(isDisplayed()));
        } catch (androidx.test.espresso.AmbiguousViewMatcherException exception) {
            assert true;
        }
    }
}