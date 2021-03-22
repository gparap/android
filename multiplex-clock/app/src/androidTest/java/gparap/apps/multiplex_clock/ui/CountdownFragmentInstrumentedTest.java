/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.multiplex_clock.ui;

import androidx.fragment.app.testing.FragmentScenario;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

public class CountdownFragmentInstrumentedTest {

    @Before
    public void setUp() throws Exception {
        FragmentScenario.launchInContainer(CountdownFragment.class);
    }

    @Test
    public void isNotWidgetVisible_progressBar(){
        onView(withId(R.id.progressBarCountdown)).check(matches(not(isDisplayed())));
    }
    @Test
    public void isWidgetVisible_textViewHHMMSS(){
        onView(withId(R.id.textViewHHMMSS)).check(matches(isDisplayed()));
    }
    @Test
    public void isWidgetVisible_editTextHours(){
        onView(withId(R.id.editTextHours)).check(matches(isDisplayed()));
    }
    @Test
    public void isWidgetVisible_editTextMinutes(){
        onView(withId(R.id.editTextMinutes)).check(matches(isDisplayed()));
    }
    @Test
    public void isWidgetVisible_editTextSeconds(){
        onView(withId(R.id.editTextSeconds)).check(matches(isDisplayed()));
    }
    @Test
    public void isWidgetVisible_buttonStartCountdown(){
        onView(withId(R.id.buttonStartCountdown)).check(matches(isDisplayed()));
    }
    @Test
    public void isWidgetVisible_buttonStopCountdown(){
        onView(withId(R.id.buttonStopCountdown)).check(matches(isDisplayed()));
    }
    @Test
    public void isWidgetVisible_buttonResetCountdown(){
        onView(withId(R.id.buttonResetCountdown)).check(matches(isDisplayed()));
    }
}