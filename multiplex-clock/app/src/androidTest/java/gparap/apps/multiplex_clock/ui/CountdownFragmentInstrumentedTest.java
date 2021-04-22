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

import android.widget.EditText;

import androidx.fragment.app.testing.FragmentScenario;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class CountdownFragmentInstrumentedTest {
    FragmentScenario<CountdownFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(CountdownFragment.class);
    }

    @Test
    public void isWidgetVisible_TextViewHHMMSS() {
        onView(withId(R.id.textViewHHMMSS)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_editTextHours() {
        onView(withId(R.id.editTextHours)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_editTextMinutes() {
        onView(withId(R.id.editTextMinutes)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_editTextSeconds() {
        onView(withId(R.id.editTextSeconds)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_buttonStartCountdown() {
        onView(withId(R.id.buttonStartCountdown)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_buttonStopCountdown() {
        onView(withId(R.id.buttonStopCountdown)).check(matches(isDisplayed()));
    }

    @Test
    public void isWidgetVisible_buttonResetCountdown() {
        onView(withId(R.id.buttonResetCountdown)).check(matches(isDisplayed()));
    }

    @Test
    public void startButton_CountdownTimerIsRunning() throws InterruptedException {
        //init timer
        onView(withId(R.id.editTextSeconds)).perform(typeText("10"));
        onView(withId(R.id.editTextSeconds)).perform(closeSoftKeyboard());
        String startTime = "10";

        //start timer
        onView(withId(R.id.buttonStartCountdown)).perform(click());
        Thread.sleep(1667);

        AtomicReference<String> runningTime = new AtomicReference<>("");
        fragmentScenario.onFragment(fragment -> {
            EditText editTextSeconds = fragment.getActivity().findViewById(R.id.editTextSeconds);
            runningTime.set(editTextSeconds.getText().toString());
        });//get the timer value after started counting down

        assert !(startTime.equals(runningTime.toString()));
    }

    @Test
    public void stopButton_CountdownTimerIsStopping() throws InterruptedException {
        //reset timer from previous values
        onView(withId(R.id.buttonResetCountdown)).perform(click());

        //init timer
        onView(withId(R.id.editTextSeconds)).perform(typeText("10"));
        onView(withId(R.id.editTextSeconds)).perform(closeSoftKeyboard());

        //start timer
        onView(withId(R.id.buttonStartCountdown)).perform(click());
        Thread.sleep(1000);

        AtomicReference<String> justBeforeStopTime = new AtomicReference<>("-1");
        fragmentScenario.onFragment(fragment -> {
            EditText editTextSeconds = fragment.getActivity().findViewById(R.id.editTextSeconds);
            justBeforeStopTime.set(editTextSeconds.getText().toString());
        });//get the timer value JUST before stopped counting down

        //stop timer
        onView(withId(R.id.buttonStopCountdown)).perform(click());

        AtomicReference<String> justAfterStopTime = new AtomicReference<>("");
        fragmentScenario.onFragment(fragment -> {
            EditText editTextSeconds = fragment.getActivity().findViewById(R.id.editTextSeconds);
            justAfterStopTime.set(editTextSeconds.getText().toString());
        });//get the timer value after stopped counting down

        // !!! we assume that the delay will be less than 1 second
        // !!! from the moment espresso presses the stop button
        // !!! and after it retrieves the timer value
        assert justBeforeStopTime.toString().equals(justAfterStopTime.toString());
    }

    @Test
    public void startButton_CountdownTimerIsResetting() throws InterruptedException {
        String zeroTime = "00";

        //start and reset timer
        onView(withId(R.id.editTextSeconds)).perform(typeText("10"));
        onView(withId(R.id.editTextSeconds)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonStartCountdown)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.buttonResetCountdown)).perform(click());

        AtomicReference<String> resetTime = new AtomicReference<>("");
        fragmentScenario.onFragment(fragment -> {
            EditText editTextSeconds = fragment.getActivity().findViewById(R.id.editTextSeconds);
            resetTime.set(editTextSeconds.getText().toString());
        });//get the timer value after reset

        assert zeroTime.equals(resetTime.toString());
    }

    @Test
    public void hours_CannotBeGreaterThan_23() {
        onView(withId(R.id.editTextHours)).perform(typeText("24"));

        AtomicReference<EditText> hours = new AtomicReference<>();
        fragmentScenario.onFragment(fragment ->
                hours.set(fragment.getActivity().findViewById(R.id.editTextHours)));

        assert !(hours.get().getText().toString().equals("24"));
    }

    @Test
    public void minutes_CannotBeGreaterThan_59() {
        onView(withId(R.id.editTextMinutes)).perform(typeText("60"));

        AtomicReference<EditText> minutes = new AtomicReference<>();
        fragmentScenario.onFragment(fragment ->
                minutes.set(fragment.getActivity().findViewById(R.id.editTextMinutes)));

        assert !(minutes.get().getText().toString().equals("60"));
    }

    @Test
    public void seconds_CannotBeGreaterThan_59() {
        onView(withId(R.id.editTextSeconds)).perform(typeText("60"));

        AtomicReference<EditText> seconds = new AtomicReference<>();
        fragmentScenario.onFragment(fragment ->
                seconds.set(fragment.getActivity().findViewById(R.id.editTextSeconds)));

        assert !(seconds.get().getText().toString().equals("60"));
    }
}