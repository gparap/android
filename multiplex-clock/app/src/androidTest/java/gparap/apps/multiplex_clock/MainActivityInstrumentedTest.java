package gparap.apps.multiplex_clock;

import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import gparap.apps.multiplex_clock.ui.MainActivity;

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
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void AlarmFragment_IsDisplayed() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(withText(R.string.item_clock)).perform(click());
        onView(withId(R.id.clockFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void ChronometerFragment_IsDisplayed() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(withText(R.string.item_chronometer)).perform(click());
        onView(withId(R.id.chronometerFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void CountdownTimerFragment_IsDisplayed() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(withText(R.string.item_countdown_timer)).perform(click());
        onView(withId(R.id.countdownTimerFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void AlarmClockFragment_IsDisplayed() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(withText(R.string.item_alarm_clock)).perform(click());
        onView(withId(R.id.alarmClockFragment)).check(matches(isDisplayed()));
    }
}