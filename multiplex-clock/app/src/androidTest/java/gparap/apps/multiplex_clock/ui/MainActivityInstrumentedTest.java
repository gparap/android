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

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import gparap.apps.multiplex_clock.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityInstrumentedTest {

    @Before
    public void setUp() {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void Toolbar_IsDisplayed() {
        onView(ViewMatchers.withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void ClockFragment_IsDisplayed() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(withText(R.string.menu_item_clock)).perform(click());
        onView(withId(R.id.clockFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void ChronometerFragment_IsDisplayed() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(withText(R.string.menu_item_chronometer)).perform(click());
        onView(withId(R.id.chronometerFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void CountdownTimerFragment_IsDisplayed() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(withText(R.string.menu_item_countdown_timer)).perform(click());
        onView(withId(R.id.countdownTimerFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void AlarmClockFragment_IsDisplayed() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(withText(R.string.menu_item_alarm_clock)).perform(click());
        onView(withId(R.id.alarmClockFragment)).check(matches(isDisplayed()));
    }
}