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

import gparap.apps.converter_unit.MainActivity;
import gparap.apps.converter_unit.R;

@RunWith(AndroidJUnit4.class)
public class AnglesConverterInstrumentedTest {

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void isCorrect_convertDegreeToRadian() {
        selectAnglesUnitCategory();
        selectSpinnerValues(0, 1);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.017453292519943295")));
    }

    @Test
    public void isCorrect_convertDegreeToGradian() {
        selectAnglesUnitCategory();
        selectSpinnerValues(0, 2);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.1111111111111112")));
    }

    @Test
    public void isCorrect_convertRadianToDegree() {
        selectAnglesUnitCategory();
        selectSpinnerValues(1, 0);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("0.017453292519943295"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0")));
    }

    @Test
    public void isCorrect_convertRadianToGradian() {
        selectAnglesUnitCategory();
        selectSpinnerValues(1, 2);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("0.017453292519943295"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.1111111111111112")));
    }

    @Test
    public void isCorrect_convertGradianToDegree() {
        selectAnglesUnitCategory();
        selectSpinnerValues(2, 0);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1.1111111111111112"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("1.0")));
    }

    @Test
    public void isCorrect_convertGradianToRadian() {
        selectAnglesUnitCategory();
        selectSpinnerValues(2, 1);
        onView(withId(R.id.editText_conversionInputValue)).perform(typeText("1.1111111111111112"));
        onView(withId(R.id.button_convert)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.textView_conversionResult)).check(matches(withText("0.0174532925199433")));
    }

    private void selectAnglesUnitCategory() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.category_angles)).perform(click());
    }

    private void selectSpinnerValues(int spinnerFromPosition, int spinnerToPosition) {
        onView(withId(R.id.spinner_convertFromUnit)).perform(click());
        onData(anything()).atPosition(spinnerFromPosition).perform(click());
        onView(withId(R.id.spinner_convertToUnit)).perform(click());
        onData(anything()).atPosition(spinnerToPosition).perform(click());
    }
}