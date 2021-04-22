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

import android.widget.Chronometer;

import androidx.fragment.app.testing.FragmentScenario;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

public class ChronometerFragmentInstrumentedTest {
    FragmentScenario<ChronometerFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(ChronometerFragment.class);
    }

    @Test
    public void isWidgetNotVisible_ProgressBar() {
        onView(withId(R.id.progressBarChronometer)).check(matches(not(isDisplayed())));
    }

    @Test
    public void isWidgetVisible_Chronometer() {
        onView(withId(R.id.chronometer)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_ButtonStart() {
        onView(withId(R.id.buttonStartChronometer)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_ButtonStop() {
        onView(withId(R.id.buttonStopChronometer)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_ButtonReset() {
        onView(withId(R.id.buttonResetChronometer)).check(matches(isDisplayed()));
    }

    @Test
    public void startButton_ChronometerTimerIsRunning() throws InterruptedException {
        AtomicReference<String> startTime = new AtomicReference<>("-1");
        fragmentScenario.onFragment(fragment -> {
            Chronometer chronometer = fragment.getActivity().findViewById(R.id.chronometer);
            startTime.set(chronometer.getText().toString());
        });//get the timer value BEFORE started counting

        onView(withId(R.id.buttonStartChronometer)).perform(click());
        Thread.sleep(1000);

        AtomicReference<String> runningTime = new AtomicReference<>("");
        fragmentScenario.onFragment(fragment -> {
            Chronometer chronometer = fragment.getActivity().findViewById(R.id.chronometer);
            runningTime.set(chronometer.getText().toString());
        });//get the timer value AFTER started counting

        assert !(startTime.toString().equals(runningTime.toString()));
    }

    @Test
    public void stopButton_ChronometerTimerIsStopping() throws InterruptedException {
        //start timer
        onView(withId(R.id.buttonStartChronometer)).perform(click());
        Thread.sleep(1000);

        AtomicReference<String> justBeforeStopTime = new AtomicReference<>("-1");
        fragmentScenario.onFragment(fragment -> {
            Chronometer chronometer = fragment.getActivity().findViewById(R.id.chronometer);
            justBeforeStopTime.set(chronometer.getText().toString());
        });//get the timer value JUST before stopped counting

        onView(withId(R.id.buttonStopChronometer)).perform(click());

        AtomicReference<String> justAfterStopTime = new AtomicReference<>("");
        fragmentScenario.onFragment(fragment -> {
            Chronometer chronometer = fragment.getActivity().findViewById(R.id.chronometer);
            justAfterStopTime.set(chronometer.getText().toString());
        });//get the timer value after stopped counting

        // !!! we assume that the delay will be less than 1 second
        // !!! from the moment espresso presses the stop button
        // !!! and after it retrieves the timer value
        assert justBeforeStopTime.toString().equals(justAfterStopTime.toString());
    }

    @Test
    public void startButton_ChronometerTimerIsResetting() throws InterruptedException {
        //reset chronometer from previous values
        onView(withId(R.id.buttonResetChronometer)).perform(click());

        //start and reset timer
        onView(withId(R.id.buttonStartChronometer)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.buttonResetChronometer)).perform(click());

        AtomicReference<String> resetTime = new AtomicReference<>("");
        fragmentScenario.onFragment(fragment -> {
            Chronometer chronometer = fragment.getActivity().findViewById(R.id.chronometer);
            resetTime.set(chronometer.getText().toString());
        });//get the timer value after reset

        assert resetTime.toString().equals("00:00") || resetTime.toString().equals("0:00");
    }
}